/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import VO.AsistenciaVO;
import javax.swing.table.DefaultTableModel;

public interface IAsistencia {

    public void crearAsistencia(AsistenciaVO asistencia);

    public void modificarAsistencia(AsistenciaVO asistencia);

    public DefaultTableModel mostrarCursos(String buscar);

    public DefaultTableModel mostrarEstudiantes(String buscar);

    public DefaultTableModel mostrarAsistencia(String buscar);

    public DefaultTableModel mostrarEstudiantesAsistencia(String buscar, String curso);
}
