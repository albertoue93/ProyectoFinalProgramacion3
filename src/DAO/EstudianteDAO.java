/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.IEstudiante;
import VO.EstudianteVO;
import Conexion.ConexionSQL;
import Vista.Principal.frmInicioSesion;
import Vista.Principal.frmVentanaPrincipal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

public class EstudianteDAO extends ConexionSQL implements IEstudiante {

    @Override
    public void crearEstudiante(EstudianteVO est) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "INSERT INTO estudiante(cedulaEstudiante,nombreEstudiante,apellidosEstudiante,emailEstudiante,telefonoEstudiante,direccionEstudiante,sexoEstudiante,tipoUsuario,Usuario,Contrasennia) Values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setString(1, est.getCedulaEstudiante());
            st.setString(2, est.getNombreEstudiante());
            st.setString(3, est.getApellidosEstudiante());
            st.setString(4, est.getEmailEstudiante());
            st.setString(5, est.getTelefonoEstudiante());
            st.setString(6, est.getDireccionEstudiante());
            st.setString(7, est.getSexoEstudiante());
            st.setString(8, est.getTipoUsuario());
            st.setString(9, est.getUsuario());
            st.setString(10, est.getContrasennia());

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
    public void modificarEstudiante(EstudianteVO est) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "UPDATE estudiante SET cedulaEstudiante=?,nombreEstudiante=?,apellidosEstudiante=?,emailEstudiante=?,telefonoEstudiante=?,direccionEstudiante=?,sexoEstudiante=?,tipoUsuario=?,Usuario=?,Contrasennia=?" + "WHERE idEstudiante=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setString(1, est.getCedulaEstudiante());
            st.setString(2, est.getNombreEstudiante());
            st.setString(3, est.getApellidosEstudiante());
            st.setString(4, est.getEmailEstudiante());
            st.setString(5, est.getTelefonoEstudiante());
            st.setString(6, est.getDireccionEstudiante());
            st.setString(7, est.getSexoEstudiante());
            st.setString(8, est.getTipoUsuario());
            st.setString(9, est.getUsuario());
            st.setString(10, est.getContrasennia());
            st.setInt(11, est.getIdEstudiante());

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
    public void eliminarEstudiante(EstudianteVO est) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "DELETE FROM estudiante WHERE idEstudiante=?";

        PreparedStatement st = null;

        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, est.getIdEstudiante());

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

        String sql1 = "SELECT * FROM estudiante WHERE Usuario='" + usuario + "'&& Contrasennia='" + pass + "'";
        try {

            st = conect.getConnection().prepareStatement(sql1);
            rs = st.executeQuery();

            while (rs.next()) {

                car = rs.getString("tipoUsuario");

            }
            if (car.equals("Estudiante")) {

                frmVentanaPrincipal ventana = new frmVentanaPrincipal();
                ventana.setVisible(true);
                ventana.menuAdministrador.setVisible(false);
                ventana.menuProfesor.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Estudiante NO se encuentra Registrado en el sistema\nIngrese de nuevo!","AVISO",JOptionPane.WARNING_MESSAGE);
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
    public DefaultTableModel mostrarEstudiantes(String buscar) {
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

        sql = "SELECT * FROM estudiante WHERE nombreEstudiante LIKE '%" + buscar + "%'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idEstudiante");
                registros[1] = rs.getString("cedulaEstudiante");
                registros[2] = rs.getString("nombreEstudiante");
                registros[3] = rs.getString("apellidosEstudiante");
                registros[4] = rs.getString("emailEstudiante");
                registros[5] = rs.getString("telefonoEstudiante");
                registros[6] = rs.getString("direccionEstudiante");
                registros[7] = rs.getString("sexoEstudiante");
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

}
