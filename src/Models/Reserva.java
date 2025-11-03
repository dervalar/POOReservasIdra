/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author lisan
 */
public class Reserva {
    
    private int id;
    private int habitacionId;
    private int personaId;
    private java.sql.Date checkIn;
    private java.sql.Date checkOut;
    private double monto;
    private String estado;
    private String numeroHabitacion;
    private String dniPersona;

    public Reserva() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getHabitacionId() { return habitacionId; }
    public void setHabitacionId(int habitacionId) { this.habitacionId = habitacionId; }

    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }

    public Date getCheckIn() { return checkIn; }
    public void setCheckIn(java.util.Date d)  { this.checkIn  = new java.sql.Date(d.getTime()); }



    public Date getCheckOut() { return checkOut; }
    public void setCheckOut(java.util.Date d) { this.checkOut = new java.sql.Date(d.getTime()); }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(String numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }

    public String getDniPersona() { return dniPersona; }
    public void setDniPersona(String dniPersona) { this.dniPersona = dniPersona; }
}
