/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import universidad.Conexion;

/**
 *
 * @author nachomorati
 */
public class CursadaData {
    private Connection connection;
    
    public CursadaData(Conexion conexion) {
        try {
            connection = conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inscribir (Alumno alumno, Materia materia) {
        String sql = "INSERT INTO alumno_materia (id_alumno, id_materia) VALUES ( ? , ?);";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, alumno.getId());
            statement.setInt(2, materia.getId());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if (rs.next()) {
                //hacer buscarAlumno para traer los datos de la BD
                System.out.println("El alumno " + alumno.getNombre() + " se inscribio en la materia " + materia.getNombre() + " "
                        + "con el id: " + rs.getInt("id"));
            } else {
                System.out.println("No se pudo obtener el id de la inscripcion del alumno.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
