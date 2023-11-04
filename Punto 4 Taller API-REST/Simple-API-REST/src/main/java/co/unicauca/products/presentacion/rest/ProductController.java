package co.unicauca.products.presentation.rest;

import co.unicauca.products.domain.service.ProductService;
import co.unicauca.products.domain.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * API REST de los servicios web. Es muy simple por ahora, en otra versión se
 * hará una API más robusta. Son nuestros servicios web. La anotación @Path
 * indica la URL en la cual responderá los servicios. Esta anotación se puede *
 * poner a nivel de clase y método. En este ejemplo todos los servicios
 * comparten el mismo Path, la acción se hacer mediante la anotació GET
 * (consultar), POST (agregar), PUT (editar), DELETE (eliminar).
 *
 * @author Libardo, Julio
 */
@Stateless
@Path("products")
public class ProductController {

    /**
     * Se inyecta la única implementación que hay de ProductService
     */
    @Inject
    private ProductService service;

    public ProductController() {
    }

    /*
 Su uso desde consola mediante client url:
 curl -X GET http://localhost:8080/Simple-API-REST/product-service/products/
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> findAll() {
        return service.findAll();
    }

    /*
 Su uso desde consola mediante client url:
 curl -X GET http://localhost:8080/Simple-API-REST/product-service/products/1
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Product findById(@PathParam("id") int id) {
        return service.findById(id);
    }

    /*
 Su uso desde consola mediante client url:
 curl -X POST \
 http://localhost:8080/Simple-API-REST/product-service/products/ \
 -H 'Content-Type: application/json' \
 -d '{
 "id":1,
 "name":"Nevera Lg",
 "price":6700000
 }'
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String create(Product prod) {
        if (service.create(prod)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Producto creado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo crear el producto\",\"errores\":\"Id ya existe\"}";
        }
    }

    /*
 Su uso desde consola mediante client url:
 curl -X PUT \
 http://localhost:8080/Simple-API-REST/product-service/products/\
 -H 'Content-Type: application/json' \
 -d '{
 "name":"Nevera Lg REF. JDK3-34-343",
 "price":2450000
 }'
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String update(Product prod) {
        if (service.update(prod)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Producto modificado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo modificar el producto\",\"errores\":\"Id no existe\"}";
        }
    }

    /*
 Su uso desde consola mediante client url:
 curl -X DELETE http://localhost:8080/Simple-API-REST/productservice/products/ 
     */
    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Integer id) {
        if (service.delete(id)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Producto borrado\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo borrar el producto\",\"errores\":\"Id no existe\"}";
        }
    }
}
