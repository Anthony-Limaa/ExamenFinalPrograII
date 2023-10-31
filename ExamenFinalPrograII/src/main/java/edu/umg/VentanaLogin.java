package edu.umg;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContraseña;




    public VentanaLogin() {



        // Configuración de la ventana
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear componentes
        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblContraseña = new JLabel("Contraseña:");
        txtUsuario = new JTextField(20);
        txtContraseña = new JPasswordField(20);
        JButton btnIngresar = new JButton("Ingresar");

        // Configurar el diseño
        setLayout(new GroupLayout(getContentPane()));
        GroupLayout.SequentialGroup hGroup = ((GroupLayout) getContentPane().getLayout()).createSequentialGroup();
        hGroup.addContainerGap();
        hGroup.addGroup(((GroupLayout) getContentPane().getLayout()).createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblUsuario)
                        .addComponent(lblContraseña))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(((GroupLayout) getContentPane().getLayout()).createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtContraseña)
                        .addComponent(btnIngresar, GroupLayout.Alignment.TRAILING))
                .addContainerGap();
        ((GroupLayout) getContentPane().getLayout()).setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = ((GroupLayout) getContentPane().getLayout()).createSequentialGroup();
        vGroup.addContainerGap();
        vGroup.addGroup(((GroupLayout) getContentPane().getLayout()).createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUsuario)
                        .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(((GroupLayout) getContentPane().getLayout()).createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblContraseña)
                        .addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngresar)
                .addContainerGap();
        ((GroupLayout) getContentPane().getLayout()).setVerticalGroup(vGroup);

        // Configurar eventos
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica las credenciales aquí (puedes agregar tu lógica de verificación)
                boolean credencialesCorrectas = verificarCredenciales(txtUsuario.getText(), new String(txtContraseña.getPassword()));

                if (credencialesCorrectas) {
                    // Llamar al método abrirInterfazPrincipal()
                    abrirInterfazPrincipal();

                    // Cerrar la ventana de login actual
                    dispose();
                } else {
                    // Muestra un mensaje de error si las credenciales son incorrectas
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean verificarCredenciales(String usuario, String contraseña) {
        // Verifica las credenciales aquí
        return usuario.equals("anthony") && contraseña.equals("12345");
    }

    private void abrirInterfazPrincipal() {
        // Crear y mostrar la nueva ventana principal
        Interfaz interfaz = new Interfaz();

        // Crear la ventana principal
        JFrame frame = new JFrame("Interfaz");
        frame.setContentPane(interfaz.interfaz);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
}
