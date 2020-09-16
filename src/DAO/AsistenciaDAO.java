/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.IAsistencia;
import VO.AsistenciaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class AsistenciaDAO extends ConexionSQL implements IAsistencia {

    @Override
    public void crearAsistencia(AsistenciaVO asistencia) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "INSERT INTO asistencia(idEstudiante,idProfesor,idCurso,fechaFalta,justificacion) VALUES(?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, asistencia.getIdEstudiante());
            st.setInt(2, asistencia.getIdProfesor());
            st.setInt(3, asistencia.getIdCurso());
            st.setString(4, asistencia.getFechaFalta());
            st.setString(5, asistencia.getJustificacion());
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
    public void modificarAsistencia(AsistenciaVO asistencia) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "UPDATE asistencia SET idEstudiante=?,idProfesor=?,idCurso=?,fechaFalta=?,justificacion=?" + "WHERE idAsistencia=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, asistencia.getIdEstudiante());
            st.setInt(2, asistencia.getIdProfesor());
            st.setInt(3, asistencia.getIdCurso());
            st.setString(4, asistencia.getFechaFalta());
            st.setString(5, asistencia.getJustificacion());
            st.setInt(6, asistencia.getIdAsistencia());

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
    public DefaultTableModel mostrarCursos(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Curso", "Curso", "ID Profesor", "Nombre Profesor", "Apellidos Profesor"};
        String[] registros = new String[5];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT DISTINCT curso.idCurso,curso.nombreCurso,profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor FROM((matricula INNER JOIN profesor ON matricula.idProfesor = profesor.idProfesor)INNER JOIN curso ON matricula.idCurso = curso.idCurso) WHERE profesor.cedulaProfesor ='" + buscar + "'";

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
    public DefaultTableModel mostrarAsistencia(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Asistencia", "ID Curso", "Nombre", "ID Estudiante", "Nombre", "Apellidos", "ID Profesor", "Nombre", "Apellidos", "Fecha de Falta", "Justificacion"};
        String[] registros = new String[11];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT asistencia.idAsistencia,curso.idCurso,curso.nombreCurso,estudiante.idEstudiante,estudiante.nombreEstudiante,estudiante.apellidosEstudiante,profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor,asistencia.fechaFalta,asistencia.justificacion FROM (((asistencia INNER JOIN curso ON asistencia.idCurso = curso.idCurso) INNER JOIN estudiante ON asistencia.idEstudiante = estudiante.idEstudiante) INNER JOIN profesor ON asistencia.idProfesor = profesor.idProfesor) WHERE profesor.cedulaProfesor ='" + buscar + "';";
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idAsistencia");
                registros[1] = rs.getString("idCurso");
                registros[2] = rs.getString("nombreCurso");
                registros[3] = rs.getString("idEstudiante");
                registros[4] = rs.getString("nombreEstudiante");
                registros[5] = rs.getString("apellidosEstudiante");
                registros[6] = rs.getString("idProfesor");
                registros[7] = rs.getString("nombreProfesor");
                registros[8] = rs.getString("apellidosProfesor");
                registros[9] = rs.getString("fechaFalta");
                registros[10] = rs.getString("justificacion");

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

        sql = "SELECT estudiante.nombreEstudiante,estudiante.apellidosEstudiante,asistencia.fechaFalta,asistencia.justificacion FROM ((asistencia INNER JOIN estudiante ON asistencia.idEstudiante = estudiante.idEstudiante) INNER JOIN curso ON asistencia.idCurso = curso.idCurso) WHERE curso.idCurso ='" + buscar + "' AND estudiante.cedulaEstudiante='" + curso + "'";
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
