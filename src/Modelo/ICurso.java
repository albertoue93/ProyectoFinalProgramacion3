/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import VO.CursoVO;
import javax.swing.table.DefaultTableModel;

public interface ICurso {

    public void crearCurso(CursoVO curs);

    public void modificarCurso(CursoVO curs);

    public void eliminarCurso(CursoVO curs);

    public DefaultTableModel mostrarCurso(String buscar);

    public DefaultTableModel mostrarProfesor(String Buscar);
}
