/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

public class MatriculaVO {

    private int idMatricula;
    private int idEstudiante;
    private int idCurso;
    private int idProfesor;

    public MatriculaVO() {

    }

    /**
     * @return the idMatricula
     */
    public int getIdMatricula() {
        return idMatricula;
    }

    /**
     * @param idMatricula the idMatricula to set
     */
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    /**
     * @return the idEstudiante
     */
    public int getIdEstudiante() {
        return idEstudiante;
    }

    /**
     * @param idEstudiante the idEstudiante to set
     */
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    /**
     * @return the idCurso
     */
    public int getIdCurso() {
        return idCurso;
    }

    /**
     * @param idCurso the idCurso to set
     */
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    /**
     * @return the idProfesor
     */
    public int getIdProfesor() {
        return idProfesor;
    }

    /**
     * @param idProfesor the idProfesor to set
     */
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

}
