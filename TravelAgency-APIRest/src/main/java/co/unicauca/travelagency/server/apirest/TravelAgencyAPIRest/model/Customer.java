package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

/**
 * Cliente de la agencia de viajes
 * @author Libardo, Julio
 */

@Data
public class Customer {

    /**
     * Cedula
     */
    @NonNull
    private String id;
    /**
     * Nombres
     */
    @NonNull
    private String firstName;
    /**
     * Apellidos
     */
    @NonNull
    private String lastName;
    /**
     * Dirección de residencia
     */
    @NonNull
    private String address;
    /**
     * Teléfono Móvil
     */
    @NonNull
    private String mobile;
    /**
     * Email
     */
    @NonNull
    private String email;
    /**
     * Sexo
     */
    @NonNull
    private String gender;
    
     /**
     * @brief Constructor parametrizado
     * @param id cedula
     * @param firstName nombres
     * @param lastName apellidos
     * @param address dirección
     * @param mobile celular
     * @param email email
     * @param gender sexo
     */
    public Customer(String id, String firstName, String lastName, String address, String mobile, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
    }   
}
