package co.unicauca.travelagency.server.apirest.TravelAgencyAPIRest.model;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Cliente de la agencia de viajes
 * @author Libardo, Julio
 */

@Data   
@AllArgsConstructor
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
}
