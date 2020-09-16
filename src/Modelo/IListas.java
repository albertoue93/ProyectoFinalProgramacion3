/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.table.DefaultTableModel;

public interface IListas {

    public DefaultTableModel mostrarCursos(String buscar);

    public DefaultTableModel mostrarHorario(String buscar);

    public DefaultTableModel mostrarEstudiantes(String buscar);

    public DefaultTableModel mostrarProfesores(String buscar);
}
