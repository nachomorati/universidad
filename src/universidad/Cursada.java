/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.util.Date;

/**
 *
 * @author nachomorati
 */
public class Cursada {
    private int id;
    private double ultimaCalificacion;
    private Materia materia;
    private Alumno alumno;
    private Date fechaInscripcion;

    public Cursada(Materia materia, Alumno alumno) {
        this.materia = materia;
        this.alumno = alumno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUltimaCalificacion() {
        return ultimaCalificacion;
    }

    public void setUltimaCalificacion(double ultimaCalificacion) {
        this.ultimaCalificacion = ultimaCalificacion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Materia getMateria() {
        return materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }
    
    
}
