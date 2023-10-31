package edu.umg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;


import static edu.umg.GestorBaseDatos.obtenerFechaActual;

public class Interfaz extends JFrame {



    JPanel interfaz;
    private JTextField txtnombre;
    private JTextField txtapellido;
    private JTextField txtemail;
    private JRadioButton radiomate;
    private JRadioButton radiohist;
    private JRadioButton radiocien;
    private JRadioButton radiolit;
    private JRadioButton radioprogra;
    private JButton btnguardar;
    private JButton btnmodificar;
    private JButton btneliminar;
    private JTable tbllista;
    private DefaultTableModel modeloTabla;




    public Interfaz() {
        // Inicializar el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Materias");
        modeloTabla.addColumn("Fecha de Inscripción");

        // Configurar la tabla con el modelo
        tbllista.setModel(modeloTabla);

        // Configurar eventos para los botones
        btnguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos de los campos
                String nombreEstudiante = txtnombre.getText();
                String apellidoEstudiante = txtapellido.getText();
                String emailEstudiante = txtemail.getText();
                String nombreCurso = obtenerMateriasSeleccionadas();
                String fechaInscripcion = obtenerFechaActual();

                // Agregar nueva fila al modelo de la tabla
                modeloTabla.addRow(new Object[]{nombreEstudiante, apellidoEstudiante, emailEstudiante, nombreCurso, fechaInscripcion});

                // Insertar datos en la base de datos
                Connection conexion = new Cconexion().establecerConexion();
                GestorBaseDatos.insertarEstudiante(conexion, nombreEstudiante, apellidoEstudiante, emailEstudiante);

                // Cerrar la conexión
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Limpiar campos después de guardar
                limpiarCampos();
            }
        });



        btnmodificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada
                int filaSeleccionada = tbllista.getSelectedRow();

                // Verificar si se ha seleccionado una fila
                if (filaSeleccionada != -1) {
                    // Obtener datos de los campos
                    String nombre = txtnombre.getText();
                    String apellido = txtapellido.getText();
                    String email = txtemail.getText();
                    String materias = obtenerMateriasSeleccionadas();

                    // Modificar la fila seleccionada en el modelo de la tabla
                    modeloTabla.setValueAt(nombre, filaSeleccionada, 0);
                    modeloTabla.setValueAt(apellido, filaSeleccionada, 1);
                    modeloTabla.setValueAt(email, filaSeleccionada, 2);
                    modeloTabla.setValueAt(materias, filaSeleccionada, 3);

                    // Modificar datos en la base de datos
                    Connection conexion = new Cconexion().establecerConexion();
                    GestorBaseDatos.actualizarEstudiante(conexion, nombre, apellido, email);


                    // Cerrar la conexión
                    try {
                        if (conexion != null) {
                            conexion.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                    // Limpiar campos después de modificar
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(Interfaz.this, "Seleccione una fila para modificar");
                }
            }
        });

        btneliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada
                int filaSeleccionada = tbllista.getSelectedRow();

                // Verificar si se ha seleccionado una fila
                if (filaSeleccionada != -1) {
                    // Eliminar la fila seleccionada del modelo de la tabla
                    modeloTabla.removeRow(filaSeleccionada);

                    // Eliminar datos en la base de datos
                    Connection conexion = new Cconexion().establecerConexion();
                    GestorBaseDatos.eliminarEstudiante(conexion, txtnombre.getText());


                    // Limpiar campos después de eliminar
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(Interfaz.this, "Seleccione una fila para eliminar");
                }
            }
        });
    }



    // Método para obtener las materias seleccionadas
    private String obtenerMateriasSeleccionadas() {

        StringBuilder materias = new StringBuilder();

        if (radiomate.isSelected()) {
            materias.append("Matemáticas ");
        }
        if (radiohist.isSelected()) {
            materias.append("Historia ");
        }
        if (radiocien.isSelected()) {
            materias.append("Ciencias ");
        }
        if (radiolit.isSelected()) {
            materias.append("Literatura ");
        }
        if (radioprogra.isSelected()) {
            materias.append("Programación ");
        }

        return materias.toString().trim(); // Elimina espacios al inicio y al final
    }


    // Método para limpiar los campos después de realizar una operación
    private void limpiarCampos() {
        txtnombre.setText("");
        txtapellido.setText("");
        txtemail.setText("");
        radiomate.setSelected(false);
        radiohist.setSelected(false);
        radiocien.setSelected(false);
        radiolit.setSelected(false);
        radioprogra.setSelected(false);
    }
    }


