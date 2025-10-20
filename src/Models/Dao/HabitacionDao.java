/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Models.Dao;

import Models.Habitacion;
import config.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lisan
 */
public class HabitacionDao implements Dao<Habitacion>{
    
    private static HabitacionDao instance;
    
    private Connection cn;
    
    public HabitacionDao() throws SQLException {
        conectar();
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
    public Habitacion findById(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Habitacion t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM habitacion WHERE id = ? AND estado = 'DISPONIBLE'";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    private void conectar() throws SQLException {
        cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "root88"
        );
    }
    
}
