/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package co.unicauca.products.cliente.rest;

/**
 *
 * @author jefit
 */
public class ClienteRest {

    public static void main(String[] args) {
        // CREANDO UN PRODUCTO
        NewJerseyClient jersey = new NewJerseyClient();
        //POST
        String rta = jersey.create_JSON(new Product(5, "Nevera Lg", 4000000d));
        System.out.println("Rta " + rta);
        // BUSCANDO UN PRODUCTO
        //GET
        Product product = jersey.findById(Product.class, "1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        // PROBAR LOS DEMAS SERVICIOS
        //REMOVE
        rta = jersey.remove("1");
        System.out.println("Rta " + rta);
        //PUT
        rta = jersey.update_JSON(new Product(5, "Patineta", 40500d));
        System.out.println("Rta " + rta);
        
        //GET ALL
        
        Product[] products = jersey.findAll(Product[].class);
        for(int i = 0; i < products.length; i++){
            System.out.println(products[i].getId() + " "+ products[i].getName() + " "+ products[i].getPrice());
        }
    }
}
