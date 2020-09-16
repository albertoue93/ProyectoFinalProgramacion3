/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.IListas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ListasDAO extends ConexionSQL implements IListas {

    @Override
    public DefaultTableModel mostrarCursos(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Curso", "Nombre del Curso"};
        String[] registros = new String[2];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT curso.idCurso,curso.nombreCurso FROM ((matricula INNER JOIN estudiante ON matricula.idEstudiante = estudiante.idEstudiante) INNER JOIN curso ON matricula.idCurso = curso.idCurso) WHERE estudiante.cedulaEstudiante ='" + buscar + "'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idCurso");
                registros[1] = rs.getString("nombreCurso");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarHorario(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Profesor", "Nombre", "Apellidos", "Dia del Curso", "Hora de Inicio", "Hora de Salida"};
        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT DISTINCT profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor,curso.diaCurso,curso.horaInicio,curso.horaFinal FROM ((( matricula INNER JOIN estudiante ON matricula.idEstudiante = estudiante.idEstudiante) INNER JOIN profesor ON matricula.idProfesor = profesor.idProfesor) INNER JOIN curso ON matricula.idCurso = curso.idCurso) WHERE curso.idCurso ='" + buscar + "'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idProfesor");
                registros[1] = rs.getString("nombreProfesor");
                registros[2] = rs.getString("apellidosProfesor");
                registros[3] = rs.getString("diaCurso");
                registros[4] = rs.getString("horaInicio");
                registros[5] = rs.getString("horaFinal");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarEstudiantes(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Estudiante", "Nombre", "Apellidos"};
        String[] registros = new String[3];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT estudiante.idEstudiante,estudiante.nombreEstudiante,estudiante.apellidosEstudiante FROM ((matricula INNER JOIN estudiante ON matricula.idEstudiante = estudiante.idEstudiante) INNER JOIN curso ON matricula.idCurso = curso.idCurso) WHERE curso.idCurso ='" + buscar + "'";

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
    public DefaultTableModel mostrarProfesores(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"Nombre", "Apellidos", "Curso"};
        String[] registros = new String[3];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT DISTINCT profesor.nombreProfesor,profesor.apellidosProfesor,curso.nombreCurso FROM ((matricula INNER JOIN profesor ON matricula.idProfesor = profesor.idProfesor) INNER JOIN curso ON matricula.idCurso = curso.idCurso) WHERE curso.idCurso ='" + buscar + "'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("nombreProfesor");
                registros[1] = rs.getString("apellidosProfesor");
                registros[2] = rs.getString("nombreCurso");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

}
