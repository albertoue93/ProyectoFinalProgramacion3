package VO;

public class CursoVO {

    private int idCurso;
    private int idProfesor;
    private String nombreCurso;
    private String horaInicio;
    private String horaFinal;
    private String diaCurso;

    public CursoVO() {

    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDiaCurso() {
        return diaCurso;
    }

    public void setDiaCurso(String diaCurso) {
        this.diaCurso = diaCurso;
    }

}
