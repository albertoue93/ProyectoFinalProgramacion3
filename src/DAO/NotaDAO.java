/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.INota;
import VO.NotaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class NotaDAO extends ConexionSQL implements INota {

    @Override
    public void crearNota(NotaVO nota) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "INSERT INTO nota(idCurso,idEstudiante,idProfesor,Calificacion) VALUES(?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, nota.getIdCurso());
            st.setInt(2, nota.getIdEstudiante());
            st.setInt(3, nota.getIdProfesor());
            st.setString(4, nota.getCalificacion());

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
    public void modificarNota(NotaVO nota) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "UPDATE nota SET idCurso=?,idEstudiante=?,idProfesor=?,Calificacion=?" + "WHERE idNota=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, nota.getIdCurso());
            st.setInt(2, nota.getIdEstudiante());
            st.setInt(3, nota.getIdProfesor());
            st.setString(4, nota.getCalificacion());
            st.setInt(5, nota.getidNota());

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
    public DefaultTableModel mostrarCursos(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Curso", "Curso", "ID Profesor", "Nombre", "Apellidos"};
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
    public DefaultTableModel mostraNota(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Nota", "ID Curso", "Nombre Curso", "ID Estudiante", "Nombre Estudiante", "ID Profesor", "Nombre Profesor", "Calificación"};
        String[] registros = new String[8];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT nota.idNota,curso.idCurso,curso.nombreCurso,estudiante.idEstudiante,estudiante.nombreEstudiante,profesor.idProfesor,profesor.nombreProfesor,nota.Calificacion FROM (((nota INNER JOIN curso ON nota.idCurso = curso.idCurso) INNER JOIN estudiante ON nota.idEstudiante = estudiante.idEstudiante) INNER JOIN profesor ON nota.idProfesor = profesor.idProfesor) WHERE profesor.cedulaProfesor ='" + buscar + "'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idNota");
                registros[1] = rs.getString("idCurso");
                registros[2] = rs.getString("nombreCurso");
                registros[3] = rs.getString("idEstudiante");
                registros[4] = rs.getString("nombreEstudiante");
                registros[5] = rs.getString("idProfesor");
                registros[6] = rs.getString("nombreProfesor");
                registros[7] = rs.getString("Calificacion");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostraCalificaciones(String buscar, String curso) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Estudiante", "Nombre Estudiante", "Apellidos Estudiante", "Calificación"};
        String[] registros = new String[4];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT estudiante.idEstudiante,estudiante.nombreEstudiante,estudiante.apellidosEstudiante,nota.Calificacion FROM (nota INNER JOIN estudiante ON nota.idEstudiante = estudiante.idEstudiante ) WHERE nota.idCurso ='" + buscar + "' AND estudiante.cedulaEstudiante='" + curso + "'";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idEstudiante");
                registros[1] = rs.getString("nombreEstudiante");
                registros[2] = rs.getString("apellidosEstudiante");
                registros[3] = rs.getString("Calificacion");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

}
