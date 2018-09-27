/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import universidad.Conexion;

/**
 *
 * @author nachomorati
 */
public class AlumnoData {
    private Connection connection;
    public AlumnoData(Conexion conexion) {
        try {
            connection = conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarAlumno(Alumno alumno) {
        try {
            String sql = "INSERT INTO alumno (nombre, fecNac, activo) VALUES ( ? , ? , ? );";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, alumno.getNombre());
            statement.setDate(2, Date.valueOf(alumno.getFecNac()));
            statement.setBoolean(3, alumno.isActivo());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId(rs.getInt("id"));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el alumno.");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;
        try {
            String sql = "SELECT * FROM alumno WHERE id = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                alumno = new Alumno(resultSet.getString("nombre"));
                alumno.setId(resultSet.getInt("id"));
                alumno.setFecNac(resultSet.getDate("fecNac").toLocalDate());
                alumno.setActivo(resultSet.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }
}
