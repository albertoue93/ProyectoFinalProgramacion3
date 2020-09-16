/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionSQL;
import Modelo.ICurso;
import VO.CursoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class CursoDAO extends ConexionSQL implements ICurso {

    @Override
    public void crearCurso(CursoVO curs) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "INSERT INTO curso(idProfesor,nombreCurso,horaInicio,horaFinal,diaCurso) VALUES(?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setInt(1, curs.getIdProfesor());
            st.setString(2, curs.getNombreCurso());
            st.setString(3, curs.getHoraInicio());
            st.setString(4, curs.getHoraFinal());
            st.setString(5, curs.getDiaCurso());

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
    public void modificarCurso(CursoVO curs) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";
        sql = "UPDATE curso SET idProfesor=?,nombreCurso=?,horaInicio=?,horaFinal=?,diaCurso=?" + "WHERE idCurso=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);

            st.setInt(1, curs.getIdProfesor());
            st.setString(2, curs.getNombreCurso());
            st.setString(3, curs.getHoraInicio());
            st.setString(4, curs.getHoraFinal());
            st.setString(5, curs.getDiaCurso());
            st.setInt(6, curs.getIdCurso());

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
    public void eliminarCurso(CursoVO curs) {
        ConexionSQL conect = new ConexionSQL();

        String sql = "";

        sql = "DELETE FROM curso WHERE idCurso=?";
        PreparedStatement st = null;
        try {
            st = conect.getConnection().prepareStatement(sql);
            st.setInt(1, curs.getIdCurso());

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
    public DefaultTableModel mostrarCurso(String buscar) {
        DefaultTableModel modelo;
        ConexionSQL conect = new ConexionSQL();
        String sql = "";

        String[] titulos = {"ID Curso", "Nombre Curso", "ID Profesor", "Nombre", "Apellidos", "Dia del Curso", "Hora de Inicio", "Hora de Salida"};
        String[] registros = new String[8];

        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sql = "SELECT curso.idCurso,curso.nombreCurso,profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor,curso.diaCurso,curso.horaInicio,curso.horaFinal FROM (curso INNER JOIN profesor ON curso.idProfesor = profesor.idProfesor) where curso.nombreCurso LIKE '%" + buscar + "%'";

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
                registros[5] = rs.getString("diaCurso");
                registros[6] = rs.getString("horaInicio");
                registros[7] = rs.getString("horaFinal");

                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarProfesor(String Buscar) {
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

        sql = "SELECT profesor.idProfesor,profesor.nombreProfesor,profesor.apellidosProfesor FROM profesor";

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

}
