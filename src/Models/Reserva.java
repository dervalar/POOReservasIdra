/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author lisan
 */
public class Reserva {
    
        private int id;
        
        private Habitacion habitacion;
        
        private Persona persona;
        
        private Date checkIn = new Date();
        
        private Date checkOut = new Date();
        
        private double monto;
        
        private boolean estado;
    
}
