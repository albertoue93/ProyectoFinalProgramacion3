/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import VO.EstudianteVO;
import javax.swing.table.DefaultTableModel;

public interface IEstudiante {

    public void crearEstudiante(EstudianteVO est);

    public void modificarEstudiante(EstudianteVO est);

    public void eliminarEstudiante(EstudianteVO est);

    public boolean acceder(String usuario, String pass);

    public DefaultTableModel mostrarEstudiantes(String buscar);
}
