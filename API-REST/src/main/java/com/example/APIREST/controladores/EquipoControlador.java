/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.APIREST.controladores;

import com.example.APIREST.modelo.Equipo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefit
 */
@RestController
@RequestMapping("/equipos")
public class EquipoControlador {
    
    ArrayList<Equipo> equipos = new ArrayList<>(List.of(
        new Equipo("Argentina", "Conmebol"),
        new Equipo("Colombia", "Conmebol"),
        new Equipo("Peru", "Conmebol"),
        new Equipo("España", "UEFA"),
        new Equipo("Inglaterra", "UEFA")));
    
    @GetMapping
    public List<Equipo> listarTodos(){
         return equipos;
    } 
    
    @GetMapping("/{nombre}")
    public Equipo recuperarEquipoPorNombre(@PathVariable String nombre){
         
        for(Equipo equipo: equipos){
            if(equipo.getNombrePais().equalsIgnoreCase(nombre)) return equipo;
        }
        return null;
    }
    
    @PostMapping
    public void agregarEquipos(@RequestBody Equipo equipo){
        equipos.add(equipo);
    }
    
    @DeleteMapping("/{nombre}")
    public void eliminarEquipo(@PathVariable String nombre){
        
        Optional<Equipo> optEquipo = equipos.stream().filter(equipos -> equipos.getNombrePais().equalsIgnoreCase(nombre)).findFirst();
        
        if(optEquipo.isPresent()){
            equipos.remove(optEquipo.get());
        }
    }
    
    @PutMapping
    public void modificarEquipo(@RequestBody Equipo equipoMod){
        
        for(Equipo equipo: equipos){
            if(equipo.getNombrePais().equalsIgnoreCase(equipoMod.getNombrePais())){
                equipo.setRankingFifa(equipoMod.getRankingFifa());
                equipo.setConf(equipoMod.getConf());
            }
        }
    }
}
