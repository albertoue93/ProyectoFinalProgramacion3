/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.IProfesor;
import VO.ProfesorVO;
import Vista.Principal.frmInicioSesion;
import Vista.Principal.frmVentanaPrincipal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProfesorDAO extends ConexionSQL implements IProfesor {

    @Override
    public void crearProfesor(ProfesorVO prof) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "INSERT INTO profesor(cedulaProfesor,nombreProfesor,apellidosProfesor,emailProfesor,telefonoProfesor,direccionProfesor,sexoProfesor,tipoUsuario,Usuario,Contrasennia) Values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setString(1, prof.getCedulaProfesor());
            st.setString(2, prof.getNombreProfesor());
            st.setString(3, prof.getApellidosProfesor());
            st.setString(4, prof.getEmailProfesor());
            st.setString(5, prof.getTelefonoProfesor());
            st.setString(6, prof.getDireccionProfesor());
            st.setString(7, prof.getSexoProfesor());
            st.setString(8, prof.getTipoUsuario());
            st.setString(9, prof.getUsuario());
            st.setString(10, prof.getContrasennia());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("A" + e.getMessage());
        } catch (Exception ex) {
            System.out.println("A" + ex.getMessage());
        } finally {
            try {
                st.close();
                conect.desconectar();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void modificarProfesor(ProfesorVO prof) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "UPDATE profesor SET cedulaProfesor=?, nombreProfesor=?,apellidosProfesor=?,emailProfesor=?,telefonoProfesor=?,direccionProfesor=?,sexoProfesor=?,tipoUsuario=?,Usuario=?,Contrasennia=?" + "WHERE idProfesor=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setString(1, prof.getCedulaProfesor());
            st.setString(2, prof.getNombreProfesor());
            st.setString(3, prof.getApellidosProfesor());
            st.setString(4, prof.getEmailProfesor());
            st.setString(5, prof.getTelefonoProfesor());
            st.setString(6, prof.getDireccionProfesor());
            st.setString(7, prof.getSexoProfesor());
            st.setString(8, prof.getTipoUsuario());
            st.setString(9, prof.getUsuario());
            st.setString(10, prof.getContrasennia());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
            }
            conect.desconectar();
        }
    }

    @Override
    public void eliminarProfesor(ProfesorVO prof) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "DELETE FROM profesor WHERE idProfesor=?";

        PreparedStatement st = null;

        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, prof.getIdProfesor());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                st.close();
                conect.desconectar();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public boolean acceder(String usuario, String pass) {
        ConexionSQL conect = new ConexionSQL();
        ResultSet rs = null;
        PreparedStatement st = null;

        String car = "";

        String sql1 = "SELECT * FROM profesor WHERE Usuario='" + usuario + "'&& Contrasennia='" + pass + "'";
        try {

            st = conect.getConnection().prepareStatement(sql1);
            rs = st.executeQuery();

            while (rs.next()) {

                car = rs.getString("tipoUsuario");

            }
            if (car.equals("Profesor")) {

                frmVentanaPrincipal ventana = new frmVentanaPrincipal();
                ventana.setVisible(true);
                ventana.menuAdministrador.setVisible(false);
                ventana.menuEstudiante.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Profesor NO se encuentra Registrado en el sistema\nIngrese de nuevo!","AVISO",JOptionPane.WARNING_MESSAGE);
                frmInicioSesion inicio = new frmInicioSesion();
                inicio.setVisible(true);
                inicio.setLocationRelativeTo(null);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                st.close();
                rs.close();
                conect.desconectar();
            } catch (SQLException e) {
            }
        }
        return false;

    }

    @Override
    public DefaultTableModel mostrarProfesor(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID", "Cedula", "Nombre", "Apellidos", "Email", "Telefono", "Direccion", "Sexo", "Tipo de Usuario", "Usuario", "Contraseña"};
        String[] registros = new String[11];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT * FROM profesor WHERE nombreProfesor LIKE '%" + buscar + "%'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idProfesor");
                registros[1] = rs.getString("cedulaProfesor");
                registros[2] = rs.getString("nombreProfesor");
                registros[3] = rs.getString("apellidosProfesor");
                registros[4] = rs.getString("emailProfesor");
                registros[5] = rs.getString("telefonoProfesor");
                registros[6] = rs.getString("direccionProfesor");
                registros[7] = rs.getString("sexoProfesor");
                registros[8] = rs.getString("tipoUsuario");
                registros[9] = rs.getString("Usuario");
                registros[10] = rs.getString("Contrasennia");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public DefaultTableModel mostrarProfesores() {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID", "Nombre", "Apellidos"};
        String[] registros = new String[3];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT idProfesor,nombreProfesor,apellidosProfesor FROM profesor";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idProfesor");
                registros[1] = rs.getString("nombreProfesor");
                registros[2] = rs.getString("apellidosProfesor");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public DefaultTableModel mostrarEstudiantesAsistencia(String buscar, String curso) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"Nombre", "Apellidos", "Fecha de Falta", "Justificacion"};
        String[] registros = new String[4];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT estudiante.nombreEstudiante,estudiante.apellidosEstudiante,asistencia.fechaFalta,asistencia.justificacion FROM (((asistencia INNER JOIN estudiante ON asistencia.idEstudiante = estudiante.idEstudiante)INNER JOIN profesor ON asistencia.idProfesor = profesor.idProfesor) INNER JOIN curso ON asistencia.idCurso = curso.idCurso) WHERE curso.idCurso ='" + buscar + "' AND profesor.cedulaProfesor='" + curso + "'";
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("nombreEstudiante");
                registros[1] = rs.getString("apellidosEstudiante");
                registros[2] = rs.getString("fechaFalta");
                registros[3] = rs.getString("justificacion");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }
}
