package VO;

public class NotaVO {

    private int idnota;
    private int idCurso;
    private int idEstudiante;
    private int idProfesor;
    private String calificacion;

    public NotaVO() {

    }

    public int getidNota() {
        return idnota;
    }

    public void setidNota(int nota) {
        this.idnota = nota;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
