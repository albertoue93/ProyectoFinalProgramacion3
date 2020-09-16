package VO;

public class ProfesorVO {

    private int idProfesor;
    private String cedulaProfesor;
    private String nombreProfesor;
    private String apellidosProfesor;
    private String emailProfesor;
    private String telefonoProfesor;
    private String direccionProfesor;
    private String sexoProfesor;
    private String tipoUsuario;
    private String usuario;
    private String contrasennia;

    public ProfesorVO() {

    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    /**
     * @return the cedulaProfesor
     */
    public String getCedulaProfesor() {
        return cedulaProfesor;
    }

    /**
     * @param cedulaProfesor the cedulaProfesor to set
     */
    public void setCedulaProfesor(String cedulaProfesor) {
        this.cedulaProfesor = cedulaProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidosProfesor() {
        return apellidosProfesor;
    }

    public void setApellidosProfesor(String apellidosProfesor) {
        this.apellidosProfesor = apellidosProfesor;
    }

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public String getDireccionProfesor() {
        return direccionProfesor;
    }

    public void setDireccionProfesor(String direccionProfesor) {
        this.direccionProfesor = direccionProfesor;
    }

    public String getSexoProfesor() {
        return sexoProfesor;
    }

    public void setSexoProfesor(String sexoProfesor) {
        this.sexoProfesor = sexoProfesor;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }
}
