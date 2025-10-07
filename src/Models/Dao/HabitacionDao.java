/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Models.Dao;

import Models.Habitacion;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lisan
 */
public class HabitacionDao implements Dao<Habitacion>{
    
    private static HabitacionDao instance;
    
    private Connection cn;
    
    private HabitacionDao() throws SQLException {
        cn = DriverManager.getConnection("jdbc:jdbc:mysql://localhost:3306/hotel?useSSL=false&serverTimezone=UTC", "root", "root88");
    }
    
    public static HabitacionDao getInstance() throws DaoException {
        if ( instance == null ) {
            try {
                instance = new HabitacionDao();
            } catch (SQLException ex) {
                throw new DaoException(ex.getMessage());
            }
        }
        
        return instance;
    }
    
    @Override
    public List<Habitacion> findAll() throws DaoException {
        String sql = "SELECT id, numero, tipo, capacidad, precio_base, estado FROM habitacion;";
        List<Habitacion> salida = new ArrayList();
        
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Habitacion h = new Habitacion(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getString(6)
                );
                salida.add(h);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        
        return salida;
    }

    @Override
    public void save(Habitacion t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Habitacion findById(Long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Habitacion t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
