/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.APIREST.modelo;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author jefit
 */
@Data
public class Equipo {
    @NonNull
    private String nombrePais;
    @NonNull
    private String conf;
    private String rankingFifa;
}
