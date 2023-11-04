package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.access;

import co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ICustomerRepository. Utiliza arreglos en memoria
 * @author Libardo Pantoja, Julio Hurtado
 * @author Jeferson Castano Ossa <jcastanoossa@unicauca.edu.co>
 * @author David Santiago Giron Munoz <davidgiron@unicauca.edu.co>
 */
public final class CustomerRepositoryImplArrays implements ICustomerRepository {

    /**
     * List de clientes
     */
    private static List<Customer> customers;

    /**
     * @brief Constructor para el repositorio
     */
    public CustomerRepositoryImplArrays() {
        if (customers == null){
            customers = new ArrayList();
        }        
        if (customers.isEmpty()){
            inicializar();
        }
    }

    /**
     * @brief Agrega clientes por defecto a la lista de clientes
     */
    public void inicializar() {
        customers.add(new Customer("98000001", "Andrea", "Sanchez", "Calle 14 No 11-12 Popayan", "3145878752", "andrea@hotmail.com", "Femenino"));
        customers.add(new Customer("98000002", "Libardo", "Pantoja", "Santa Barbar Popayan", "3141257845", "libardo@gmail.com", "Masculino"));
        customers.add(new Customer("98000003", "Carlos", "Pantoja", "Santa Barbar Popayan", "3141257846", "carlos@gmail.com", "Masculino"));
        customers.add(new Customer("98000004", "Fernanda", "Arevalo", "Calle 16 No 12-12 Popayan", "3154562133", "fercha@hotmail.com", "Femenino"));
        customers.add(new Customer("98000005", "Manuel", "Perez", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000006", "Alejandro", "Mosquera", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000007", "Cesar", "Gutierres Sanchez", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000008", "Julio", "Bravo Bravo", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000009", "Alberto", "Mendez Bravo", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000010", "Alexander", "Ponce Yepes", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }
    
    @Override
    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public String createCustomer(Customer customer) {
        customers.add(customer);
        return customer.getId();
    }

    @Override
    public int deleteCustomer(String id) { 
        //Clientes a eliminar
        List<Customer> customersDelete = new ArrayList();
        
        for(Customer customer : customers){
            if(customer.getId().equals(id)){
                customersDelete.add(customer);
            }
        }
        //Eliminar clientes
        for(Customer customer : customersDelete){
            customers.remove(customer);
        }
        return customersDelete.size();
    }

    @Override
    public int editCustomer(Customer newCustomer) {
        //Contador de clientes editados
        int count = 0;
        
        for(Customer customer : customers){
            if(customer.getId().equals(newCustomer.getId())){
                //Actualizar propiedades
                customer.setFirstName(newCustomer.getFirstName());
                customer.setLastName(newCustomer.getLastName());
                customer.setAddress(newCustomer.getAddress());
                customer.setMobile(newCustomer.getMobile());
                customer.setEmail(newCustomer.getEmail());
                customer.setGender(newCustomer.getGender());
                count++;
            }
        }
        return count;
    }
}
