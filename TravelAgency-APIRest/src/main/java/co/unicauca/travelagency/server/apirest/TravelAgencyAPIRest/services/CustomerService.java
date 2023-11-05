package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.services;

import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model.Customer;
import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.access.ICustomerRepository;
import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.infra.JsonError;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @brief Servicio de clientes. Da acceso a la lógica de negocio
 * @author Libardo, Julio
 * @author Jeferson Castano Ossa <jcastanoossa@unicauca.edu.co>
 * @author David Santiago Giron Munoz <davidgiron@unicauca.edu.co>
*/
@Service
public class CustomerService {

    /**
     * Repositorio de clientes
     */
    @Autowired
    ICustomerRepository repo;

    /**
     * @brief Constructor parametrizado. Hace inyeccion de dependencias
     * @param repo Repositorio de tipo ICustomerRepository
     */
    public CustomerService(ICustomerRepository repo) {
        this.repo = repo;
    }
    
    /**
     * @brief Obtiene todos los clientes
     * @return Clientes registrados o NULL si no hay
     */
    public synchronized List<Customer> getCustomers() {
        List<Customer> customers = repo.getCustomers();
        if(customers.isEmpty()) return null; //Caso en que no hay clientes
        return customers;
    }

    /**
     * @brief Buscar un cliente
     * @param id Cedula del cliente
     * @return Objeto tipo Customer o JsonError en caso de error
     */
    public synchronized Object findCustomer(String id) {
        Customer customer = repo.findCustomer(id);
        
        if (customer == null) { //El cliente no existe
            JsonError error = new JsonError();
            error.setCode("404");
            error.setError("NOT_FOUND");
            error.setMessage("Cliente no encontrado. Cédula no existe");
            return error;
        } else {
            return customer;
        }
    }

    /**
     * @brief Crea un nuevo cliente. Aplica validaciones de negocio
     * @param customer Cliente a crear
     * @return NULL en caso de exito o errores producidos en caso contrario
     */
    public synchronized List<JsonError> createCustomer(Customer customer) {
        List<JsonError> errors = new ArrayList<>();
  
        // Validaciones y reglas de negocio
        if (customer.getId().isEmpty() || customer.getFirstName().isEmpty()
                || customer.getLastName().isEmpty() || customer.getEmail().isEmpty()
                || customer.getGender().isEmpty()) {
           errors.add(new JsonError("400", "BAD_REQUEST","id, nombres, apellidos, email y género son obligatorios. "));
        }
        
        if (!customer.getEmail().contains("@")){
            errors.add(new JsonError("400", "BAD_REQUEST","Email debe tener una @. "));
        }
        
        if(!(customer.getGender().equalsIgnoreCase("M") || customer.getGender().equalsIgnoreCase("F"))){
            errors.add(new JsonError("400", "BAD_REQUEST","Sexo debo ser M o F. "));
        }      
        
        if(!isNumeric(customer.getMobile())){
            errors.add(new JsonError("400", "BAD_REQUEST","Teléfono móvil debe contener sólo dígitos "));            
        }
        
        if(isCustomerRegister(this.findCustomer(customer.getId()))){
            errors.add(new JsonError("400", "BAD_REQUEST","La cédula ya existe. "));
        }
        
        //Si no se producen errores, crea el cliente
        if (errors.isEmpty()) {
            repo.createCustomer(customer);
            return null;
        }  
       
       return errors;  
    }
    
    /**
     * @brief Elimina un cliente. Aplica validaciones de negocio
     * @param id Cedula del cliente
     * @return NULL si el cliente se elimina o errores producidos en caso contrario
     */
    public synchronized List<JsonError> deleteCustomer(String id) {
        List<JsonError> errors = new ArrayList<>();
        
        // Validaciones
        if(!isNumeric(id)){
            errors.add(new JsonError("400", "BAD_REQUEST","Id debe contener sólo dígitos "));
        } 
        
        if(!isCustomerRegister(this.findCustomer(id))){
            errors.add(new JsonError("400", "BAD_REQUEST","Cliente no encontrado. Cédula no existe."));
        }
        
        //POST: El cliente exite y se puede eliminar
        if (errors.isEmpty()) {
            int result = repo.deleteCustomer(id);
            
            if(result > 0) return null;
            else {
                //No se elimino el cliente por un error en el repositorio
                errors.add(new JsonError("400", "BAD_REQUEST", "Ocurrio un error."));
            } 
        } 
        
        return errors;
    }
    
    /**
     * @brief Edita un cliente. Aplica validaciones de negocio
     * @param customer Cliente modificado
     * @return NULL si el cliente se actualiza con exito o errores producidos en caso contrario
     */
    public synchronized List<JsonError> editCustomer(Customer customer) {
        
        List<JsonError> errors = new ArrayList<>();
  
        // Validaciones y reglas de negocio
        if (customer.getId().isEmpty() || customer.getFirstName().isEmpty()
                || customer.getLastName().isEmpty() || customer.getEmail().isEmpty()
                || customer.getGender().isEmpty()) {
           errors.add(new JsonError("400", "BAD_REQUEST","id, nombres, apellidos, email y género son obligatorios. "));
        }
        
        if (!customer.getEmail().contains("@")){
            errors.add(new JsonError("400", "BAD_REQUEST","Email debe tener una @. "));
        }
        
        if(!(customer.getGender().equalsIgnoreCase("M") || customer.getGender().equalsIgnoreCase("F"))){
            errors.add(new JsonError("400", "BAD_REQUEST","Sexo debo ser M o F. "));
        }      
        
        if(!isNumeric(customer.getMobile())){
            errors.add(new JsonError("400", "BAD_REQUEST","Teléfono móvil debe contener sólo dígitos "));            
        }
        
        if(!isCustomerRegister(this.findCustomer(customer.getId()))){
            errors.add(new JsonError("400", "BAD_REQUEST","Cliente no encontrado. Cédula no existe."));
        }
        
        //POST: El cliente es valido y se puede actualizar
        if (errors.isEmpty()) {
            int result = repo.editCustomer(customer);
            if(result > 0) return null;
            else {
                //No se edito el cliente por un error en el repositorio
                errors.add(new JsonError("400", "BAD_REQUEST", "Ocurrio un error."));
            }
        }  
       
       return errors;  
    }
    
    /**
     * @brief Valida si una cadena es de tipo numerico
     * @param str Cadena 
     * @return TRUE si es de tipo numerico o FALSE en caso contrario
     */
    private boolean isNumeric(String str) {

        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }
    /**
     * @brief Valida si un objeto es de tipo Customer
     * @param obj Objeto 
     * @return TRUE si es de tipo Customer o FALSE en caso contrario
     */
    private boolean isCustomerRegister(Object obj) {

        try{
            Customer customerSearched = (Customer) obj;
            return true;
        }catch(ClassCastException e){
            return false;
        }
    }
}
