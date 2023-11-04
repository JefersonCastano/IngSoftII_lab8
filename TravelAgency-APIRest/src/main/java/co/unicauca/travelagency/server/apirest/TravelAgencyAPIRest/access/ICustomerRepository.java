package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.access;

import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model.Customer;
import java.util.List;



/**
 * Interface del respositorio de clientes
 * @author Libardo Pantoja
 * @author Jeferson Castano Ossa <jcastanoossa@unicauca.edu.co>
 * @author David Santiago Giron Munoz <davidgiron@unicauca.edu.co>
 */
public interface ICustomerRepository {
    /**
     * @brief Obtiene todos los clientes registrados
     * @return  Clientes registrados
     */
    public List<Customer> getCustomers();
    
    /**
     * @brief Busca un cliente por su cedula
     * @param id Cedula del cliente
     * @return  Cliente encontrado
     */
    public Customer findCustomer(String id);
    
    /**
     * @brief Crea un nuevo cliente
     * @param customer Cliente a agregar
     * @return  Cedula del cliente agregado
     */
    public String createCustomer(Customer customer);
    
    /**
     * @brief Elimina un cliente
     * @param id Cedula del cliente a eliminar
     * @return  Numero de clientes eliminados
     */
    public int deleteCustomer(String id);
    
    /**
     * @brief Actualiza un cliente
     * @param customer Cliente modificado
     * @return Numero de clientes editados
     */
    public int editCustomer(Customer customer);
}
