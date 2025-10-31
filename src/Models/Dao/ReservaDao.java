/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Dao;

import Models.Dao.DaoException;
import Models.Reserva;
import config.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
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
public class ReservaDao implements Dao<Reserva>{

    private static ReservaDao instance;
    
    private Connection cn;
    
    public ReservaDao() throws SQLException {
        conectar();
    }
    
    public static ReservaDao getInstance() throws DaoException {
        if ( instance == null ) {
            try {
                instance = new ReservaDao();
            } catch (SQLException ex) {
                throw new DaoException(ex.getMessage());
            }
        }
        
        return instance;
    }

    
    @Override
    public void save(Reserva r) throws DaoException {
        String sql = """
        INSERT INTO reserva (habitacion_id, persona_id, check_in, check_out, monto, estado)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, r.getHabitacionId());
            ps.setInt(2, r.getPersonaId());
            ps.setDate(3, (Date) r.getCheckIn());
            ps.setDate(4, (Date) r.getCheckOut());
            ps.setDouble(5, r.getMonto());
            ps.setString(6, r.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error al guardar la reserva: " + e.getMessage());
        }
    }

    @Override
    public List findAll() throws DaoException {
        List<Reserva> reservas = new ArrayList<>();

        String sql = """
            SELECT 
                r.id,
                r.habitacion_id,
                r.persona_id,
                h.numero AS numero_habitacion,
                p.dni AS dni_persona,
                r.check_in,
                r.check_out,
                r.monto,
                r.estado
            FROM reserva r
            INNER JOIN habitacion h ON r.habitacion_id = h.id
            INNER JOIN persona p ON r.persona_id = p.id
            ORDER BY r.id DESC;
        """;

        try (Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setHabitacionId(rs.getInt("habitacion_id"));
                r.setPersonaId(rs.getInt("persona_id"));
                r.setNumeroHabitacion(rs.getString("numero_habitacion"));
                r.setDniPersona(rs.getString("dni_persona"));
                r.setCheckIn(rs.getDate("check_in"));
                r.setCheckOut(rs.getDate("check_out"));
                r.setMonto(rs.getDouble("monto"));
                r.setEstado(rs.getString("estado"));

                reservas.add(r);
            }

        } catch (SQLException e) {
                throw new DaoException("Error al obtener reservas: " + e.getMessage());
        }

        return reservas;
    }


    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error al eliminar la reserva: " + e.getMessage());
        }
    }
    
    private void conectar() throws SQLException {
        cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "root88"
        );
    }

    @Override
    public void update(Reserva t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean isDisponible(int habitacionId, Date checkIn, Date checkOut) throws DaoException {
        String sql = """
            SELECT COUNT(*) 
            FROM reserva 
            WHERE habitacion_id = ? 
              AND (
                    (check_in <= ? AND check_out >= ?)  -- se superpone con fecha inicio
                 OR (check_in <= ? AND check_out >= ?)  -- se superpone con fecha fin
                 OR (check_in >= ? AND check_out <= ?)  -- está completamente dentro del rango
              )
        """;

        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, habitacionId);
            ps.setDate(2, checkIn);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);
            ps.setDate(5, checkOut);
            ps.setDate(6, checkIn);
            ps.setDate(7, checkOut);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // true = disponible
            }
            return true;

        } catch (SQLException e) {
            throw new DaoException("Error al comprobar disponibilidad: " + e.getMessage());
        }
    }

    public int obtenerIdHabitacionPorReserva(int reservaId) throws DaoException {
        String sql = "SELECT habitacion_id FROM reserva WHERE id = ?";
        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, reservaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("habitacion_id");
            }
            throw new DaoException("No se encontró la reserva con ID " + reservaId);

        } catch (SQLException e) {
            throw new DaoException("Error al obtener habitación de la reserva: " + e.getMessage());
        }
    }

    public void actualizarEstado(int id, String estado) throws DaoException {
        String sql = "UPDATE reserva SET estado = ? WHERE id = ?";
        try (Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root88");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error al actualizar estado de reserva: " + e.getMessage());
        }
    }

}
