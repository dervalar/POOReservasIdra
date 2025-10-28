/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Dao;

import Models.Dao.DaoException;
import Models.Persona;
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
public class PersonasDao implements Dao<Persona>{
    private static PersonasDao instance;
    
    private Connection cn;
    
    public PersonasDao() throws SQLException {
        conectar();
    }
    
    public static PersonasDao getInstance() throws DaoException {
        if ( instance == null ) {
            try {
                instance = new PersonasDao();
            } catch (SQLException ex) {
                throw new DaoException(ex.getMessage());
            }
        }
        
        return instance;
    }

    @Override
    public List<Persona> findAll() throws DaoException {
        String sql = "SELECT id, dni, nombre, email, telefono FROM persona;";
        List<Persona> salida = new ArrayList();
        
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                salida.add(p);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        
        return salida;
    }

    @Override
    public void save(Persona p) throws DaoException {
        String sql = "INSERT INTO persona (dni, nombre, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88"); PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setString(1, p.getDni());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getEmail());
                ps.setString(4, p.getTelefono());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("Error al insertar persona: " + e.getMessage());
        }
    }

    @Override
    public void update(Persona p) throws DaoException {
        String sql = "UPDATE persona SET dni=?, nombre=?, email=?, telefono=? WHERE id=?";

        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, p.getDni());
        ps.setString(2, p.getNombre());
        ps.setString(3, p.getEmail());
        ps.setString(4, p.getTelefono());
        ps.setInt(6, p.getId());
        ps.executeUpdate();

    } catch (SQLException e) {
        throw new DaoException("Error al actualizar habitación: " + e.getMessage());
    }
    }


    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM persona WHERE id = ?";
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
