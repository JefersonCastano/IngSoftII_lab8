package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.controller;

import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.access.CustomerRepositoryImplArrays;
import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.infra.JsonError;
import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model.Customer;
import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.services.CustomerService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @brief Metodos HTTP para servicios REST
 * @author Jeferson Castano Ossa <jcastanoossa@unicauca.edu.co>
 * @author David Santiago Giron Munoz <davidgiron@unicauca.edu.co>
*/

@RestController
@RequestMapping("/client") //URL para el recurso cliente
public class ClientController {
    
    private CustomerService service = new CustomerService(new CustomerRepositoryImplArrays());
    
    /**
    * @brief Obtiene la informacion de todos los clientes registrados
    * @return Clientes registrados
    */
    @GetMapping
    public List<Customer> processGetCustomer(){
         return service.getCustomers();
    }
    
    /**
    * @brief Obtiene la informacion de un cliente registrado
    * @param id Cedula del cliente
    * @return Informacion del cliente o un JsonError si ocurre un error
    */
    @GetMapping("/{id}")
    public Object processGetCustomerID(@PathVariable String id){      
        return service.findCustomer(id);
    }
    
    /**
    * @brief Crea un nuevo cliente
    * @param customer Cliente a crear
    * @return NULL si el cliente se crea con exito o lista de errores en caso contrario
    */
    @PostMapping
    public List<JsonError> processPostCustomer(@RequestBody Customer customer){
        return service.createCustomer(customer);
    }
    
    /**
    * @brief Elimina cliente
    * @param id Cedula del cliente a eliminar
    * @return NULL si el cliente se elimina con exito o lista de errores en caso contrario
    */
    @DeleteMapping("/{id}")
    public List<JsonError> processDeleteCustomer(@PathVariable String id){       
        return service.deleteCustomer(id);
    }
    
    /**
    * @brief Actualiza cliente
    * @param customer Cliente con las modificaciones
    * @return NULL si el cliente se edita con exito o lista de errores en caso contrario
    */
    @PutMapping
    public List<JsonError> processPutCustomer(@RequestBody Customer customer){
        return service.editCustomer(customer); 
    }
}
