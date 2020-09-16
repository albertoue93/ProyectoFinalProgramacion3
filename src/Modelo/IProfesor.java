/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import VO.ProfesorVO;

import javax.swing.table.DefaultTableModel;

public interface IProfesor {

    public void crearProfesor(ProfesorVO prof);

    public void modificarProfesor(ProfesorVO prof);

    public void eliminarProfesor(ProfesorVO prof);

    public boolean acceder(String usuario, String pass);

    public DefaultTableModel mostrarProfesor(String buscar);

    public DefaultTableModel mostrarProfesores();

    public DefaultTableModel mostrarEstudiantesAsistencia(String buscar, String curso);
}
