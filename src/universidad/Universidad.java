/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import modelo.CursadaData;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nachomorati
 */
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion;
        
        try {
            conexion = new Conexion("jdbc:mysql://localhost/universidad", "root", "");
//            AlumnoData alumnoData = new AlumnoData(conexion);
//            Alumno alumno1 = new Alumno("Ochoa Joaquin", LocalDate.of(2013, 3, 15), true);
//            alumnoData.guardarAlumno(alumno1);
//            System.out.println("El id del nuevo alumno es: " + alumno1.getId());
            
//            MateriaData materiaData = new MateriaData(conexion);
//            Materia materia1 = new Materia("Calculo I");
//            materiaData.guardarMateria(materia1);
//            System.out.println("El id de la nueva materia es: " + materia1.getId());
            
//PARA PROBAR CursadaData CREAR buscarAlumno() en AlumnoData y buscarMateria() en MateriaData
            CursadaData cursadaData = new CursadaData(conexion);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Universidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
