package VO;

public class AsistenciaVO {

    private int idAsistencia;
    private int idProfesor;
    private int idEstudiante;
    private int idCurso;
    private String fechaFalta;
    private String justificacion;

    public AsistenciaVO() {

    }

    /**
     * @return the idAsistencia
     */
    public int getIdAsistencia() {
        return idAsistencia;
    }

    /**
     * @param idAsistencia the idAsistencia to set
     */
    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
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
     * @return the fechaFalta
     */
    public String getFechaFalta() {
        return fechaFalta;
    }

    /**
     * @param fechaFalta the fechaFalta to set
     */
    public void setFechaFalta(String fechaFalta) {
        this.fechaFalta = fechaFalta;
    }

    /**
     * @return the justificacion
     */
    public String getJustificacion() {
        return justificacion;
    }

    /**
     * @param justificacion the justificacion to set
     */
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}
