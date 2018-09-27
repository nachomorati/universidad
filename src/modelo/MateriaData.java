/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.Materia;
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
public class MateriaData {
    private Connection connection;

    public MateriaData(Conexion conexion) {
        try {
            connection = conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia (nombre) VALUES ( ? );";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombre());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                materia.setId(rs.getInt("id"));
            } else {
                System.out.println("No se pudo obtener el id de la materia al guardarla.");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Materia buscarMateria(int id) {
        Materia materia = null;
        try {
            String sql = "SELECT * FROM materia WHERE id = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                materia = new Materia(rs.getString("nombre"));
                materia.setId(rs.getInt("id"));
            } 
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }
    
    public void actualizarMateria(Materia materia, String nuevoNombre) {
        try {
            String sql = "UPDATE materia SET nombre = ? WHERE id = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nuevoNombre);
            statement.setInt(2, materia.getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            materia.setNombre(this.buscarMateria(materia.getId()).getNombre());
            
            if(rs.next()) {
                System.out.println(rs.getString(1));
                materia.setNombre(rs.getString("nombre"));
            } else {
                System.out.println("No se pudo obtener el id de la materia actualizada");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarMateria(int jtId) {
        try {
            String sql = "DELETE FROM materia WHERE id = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, jtId);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                System.out.println("Materia '" + rs.getString("nombre") + "' eliminada.");
            } else {
                System.out.println("No se pudo obtener el id de la materia eliminada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
