/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.IMatricula;
import VO.MatriculaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MatriculaDAO extends ConexionSQL implements IMatricula {

    @Override
    public void crearMatricula(MatriculaVO matricula) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "INSERT INTO matricula(idEstudiante,idCurso,idProfesor) VALUES(?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setInt(1, matricula.getIdEstudiante());
            st.setInt(2, matricula.getIdCurso());
            st.setInt(3, matricula.getIdProfesor());

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
    public DefaultTableModel mostrarCurso() {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Curso", "Nombre Curso", "ID Profesor", "Nombre", "Apellidos"};
        String[] registros = new String[5];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT curso.idCurso,curso.nombreCurso,profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor FROM (curso INNER JOIN profesor ON curso.idProfesor = profesor.idProfesor)";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idCurso");
                registros[1] = rs.getString("nombreCurso");
                registros[2] = rs.getString("idProfesor");
                registros[3] = rs.getString("nombreProfesor");
                registros[4] = rs.getString("apellidosProfesor");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarEstudiantes() {
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

        sql = "SELECT estudiante.idEstudiante,estudiante.nombreEstudiante,estudiante.apellidosEstudiante FROM estudiante";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idEstudiante");
                registros[1] = rs.getString("nombreEstudiante");
                registros[2] = rs.getString("apellidosEstudiante");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarMatricula() {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Matricula", "Nombre Estudiante", "Apellidos Estudiante", "Nombre Curso", "Nombre Profesor", "Apellidos Profesor"};
        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "Select matricula.idMatricula,estudiante.nombreEstudiante,estudiante.apellidosEstudiante,curso.nombreCurso,profesor.nombreProfesor,profesor.apellidosProfesor FROM (((matricula INNER JOIN estudiante ON matricula.idEstudiante = estudiante.idEstudiante) INNER JOIN curso ON matricula.idCurso = curso.idCurso) INNER JOIN profesor ON matricula.idProfesor = profesor.idProfesor)";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idMatricula");
                registros[1] = rs.getString("nombreEstudiante");
                registros[2] = rs.getString("apellidosEstudiante");
                registros[3] = rs.getString("nombreCurso");
                registros[4] = rs.getString("nombreProfesor");
                registros[5] = rs.getString("apellidosProfesor");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }
}
