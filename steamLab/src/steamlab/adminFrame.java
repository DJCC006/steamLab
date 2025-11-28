/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author ljmc2
 */

public class adminFrame extends JFrame {

    private steam st;
    private steam.Player currentUser;

    public adminFrame(steam st, steam.Player currentUser) {
        this.st = st;
        this.currentUser = currentUser;

        setTitle("Administrador - " + currentUser.getNombre());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color bgDark = new Color(23, 26, 33);
        Color panelDark = new Color(36, 41, 51);
        Color steamBlue = new Color(0, 153, 255);
        Color textLight = Color.WHITE;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgDark);
        mainPanel.setLayout(null);
        add(mainPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(panelDark);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(null);
        contentPanel.setBounds(50, 20, 700, 720);
        mainPanel.add(contentPanel);

        JLabel lblAdministrador = new JLabel("Administrador", SwingConstants.CENTER);
        lblAdministrador.setForeground(textLight);
        lblAdministrador.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblAdministrador.setBounds(200, 0, 300, 60);
        contentPanel.add(lblAdministrador);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        buttonsPanel.setBackground(panelDark);
        buttonsPanel.setBounds(0, 80, 700, 620);
        contentPanel.add(buttonsPanel);

        // Botones
        JButton btn1 = createButton("Registrar Jugador", steamBlue, textLight);
        btn1.setBounds(10, 0, 330, 90);
        buttonsPanel.add(btn1);

        JButton btn2 = createButton("Modificar Jugador", steamBlue, textLight);
        btn2.setBounds(350, 0, 330, 90);
        buttonsPanel.add(btn2);

        JButton btn3 = createButton("Eliminar Jugador", steamBlue, textLight);
        btn3.setBounds(10, 110, 330, 90);
        buttonsPanel.add(btn3);

        JButton btn4 = createButton("Registrar Juego", steamBlue, textLight);
        btn4.setBounds(350, 110, 330, 90);
        buttonsPanel.add(btn4);

        JButton btn5 = createButton("Modificar Juego", steamBlue, textLight);
        btn5.setBounds(10, 220, 330, 90);
        buttonsPanel.add(btn5);

        JButton btn6 = createButton("Eliminar Juego", steamBlue, textLight);
        btn6.setBounds(350, 220, 330, 90);
        buttonsPanel.add(btn6);

        JButton btn7 = createButton("Cambiar Precio", steamBlue, textLight);
        btn7.setBounds(10, 330, 330, 90);
        buttonsPanel.add(btn7);

        JButton btn8 = createButton("Ver Catálogo", steamBlue, textLight);
        btn8.setBounds(350, 330, 330, 90);
        buttonsPanel.add(btn8);

        JButton btn9 = createButton("Ver Reportes", steamBlue, textLight);
        btn9.setBounds(190, 440, 330, 90);
        buttonsPanel.add(btn9);

        JButton btn10 = createButton("← Cerrar Sesión", bgDark, textLight);
        btn10.setBounds(10, 10, 150, 50);
        contentPanel.add(btn10);

        // ActionListeners
        btn1.addActionListener(e -> {
            // Registrar jugador
            String user = JOptionPane.showInputDialog(this, "Username:");
            String pass = JOptionPane.showInputDialog(this, "Password:");
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            long nacimiento = System.currentTimeMillis(); // Ejemplo simple
            String rutaImg = "";
            String tipo = "normal";

            try {
                st.addPlayer(user, pass, nombre, nacimiento, rutaImg, tipo);
                JOptionPane.showMessageDialog(this, "Jugador registrado");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn2.addActionListener(e -> {
            try {
                int code = Integer.parseInt(JOptionPane.showInputDialog(this, "Código del jugador a modificar:"));
                String newName = JOptionPane.showInputDialog(this, "Nuevo nombre:");
                String newPass = JOptionPane.showInputDialog(this, "Nueva contraseña:");

                if(st.updatePlayer(code, newName, newPass)){
                    JOptionPane.showMessageDialog(this, "Jugador modificado");
                } else {
                    JOptionPane.showMessageDialog(this, "Jugador no encontrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn3.addActionListener(e -> {
            try {
                int code = Integer.parseInt(JOptionPane.showInputDialog(this, "Código del jugador a eliminar:"));
                if(st.deletePlayer(code)){
                    JOptionPane.showMessageDialog(this, "Jugador eliminado (marcado como BORRADO)");
                } else {
                    JOptionPane.showMessageDialog(this, "Jugador no encontrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn4.addActionListener(e -> {
            try {
                String titulo = JOptionPane.showInputDialog(this, "Título del juego:");
                char OS = JOptionPane.showInputDialog(this, "OS (W/M/L):").charAt(0);
                int edadMin = Integer.parseInt(JOptionPane.showInputDialog(this, "Edad mínima:"));
                double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio:"));
                String rutaImg = "";

                st.addGame(titulo, OS, edadMin, precio, rutaImg);
                JOptionPane.showMessageDialog(this, "Juego registrado");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn7.addActionListener(e -> {
            try {
                int code = Integer.parseInt(JOptionPane.showInputDialog(this, "Código del juego:"));
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Nuevo precio:"));
                if(st.updatePriceFor(code, newPrice)){
                    JOptionPane.showMessageDialog(this, "Precio actualizado");
                } else {
                    JOptionPane.showMessageDialog(this, "Juego no encontrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn8.addActionListener(e -> {
            try {
                String catalogo = st.printGames();
                JTextArea area = new JTextArea(catalogo);
                area.setEditable(false);
                JScrollPane scroll = new JScrollPane(area);
                JOptionPane.showMessageDialog(this, scroll, "Catálogo de Juegos", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn9.addActionListener(e -> {
            try {
                int code = Integer.parseInt(JOptionPane.showInputDialog(this, "Código del cliente:"));
                String fileName = JOptionPane.showInputDialog(this, "Nombre del archivo:");
                st.reportForClient(code, fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn10.addActionListener(e -> {
            this.dispose(); // cerrar sesión
            // Aquí podrías volver a loginFrame si quieres
        });
    }

    private JButton createButton(String text, Color bg, Color fg){
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn.setFocusPainted(false);
        return btn;
    }

}