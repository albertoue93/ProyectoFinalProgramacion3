/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import VO.MatriculaVO;
import javax.swing.table.DefaultTableModel;

public interface IMatricula {

    public void crearMatricula(MatriculaVO matricula);

    public DefaultTableModel mostrarCurso();

    public DefaultTableModel mostrarEstudiantes();

    public DefaultTableModel mostrarMatricula();
}
