/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ljmc2
 */
public class profileFrame extends JFrame {
    private steam st;
    private steam.Player currentUser;

    public profileFrame(steam st, steam.Player currentUser){
        this.st = st;
        this.currentUser = currentUser;

        setTitle("Perfil del Usuario");
        setSize(700, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        contentPanel.setBounds(40, 15, 620, 680);
        mainPanel.add(contentPanel);

        // Título
        JLabel lblTitulo = new JLabel("Perfil del Usuario", SwingConstants.CENTER);
        lblTitulo.setForeground(textLight);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setBounds(0, 45, 620, 60);
        contentPanel.add(lblTitulo);

        // Imagen
        JLabel lblImagen = new JLabel();
        lblImagen.setBounds(230, 125, 150, 150);
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.GRAY);
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        ImageIcon icono = new ImageIcon(currentUser.getRutaImagen() != null ? currentUser.getRutaImagen() : "src/res/defaultImg.jpg");
        Image img = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
        contentPanel.add(lblImagen);

        JButton btnCambiarImagen = new JButton("Cambiar");
        btnCambiarImagen.setBackground(steamBlue);
        btnCambiarImagen.setForeground(textLight);
        btnCambiarImagen.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCambiarImagen.setBounds(400, 175, 120, 40);
        contentPanel.add(btnCambiarImagen);

        // Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(textLight);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblUsuario.setBounds(80, 305, 200, 30);
        contentPanel.add(lblUsuario);

        JLabel txtUsuario = new JLabel(currentUser.getUsername());
        txtUsuario.setForeground(textLight);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtUsuario.setBounds(280, 305, 200, 30);
        contentPanel.add(txtUsuario);

        // Contraseña
        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setForeground(textLight);
        lblContra.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblContra.setBounds(80, 365, 200, 30);
        contentPanel.add(lblContra);

        JLabel txtContra = new JLabel(currentUser.getPassword());
        txtContra.setForeground(textLight);
        txtContra.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtContra.setBounds(280, 365, 200, 30);
        contentPanel.add(txtContra);

        JButton btnCambiarContra = new JButton("Cambiar");
        btnCambiarContra.setBackground(steamBlue);
        btnCambiarContra.setForeground(textLight);
        btnCambiarContra.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCambiarContra.setBounds(480, 360, 110, 40);
        contentPanel.add(btnCambiarContra);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(textLight);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNombre.setBounds(80, 405, 200, 30);
        contentPanel.add(lblNombre);

        JLabel txtNombre = new JLabel(currentUser.getNombre());
        txtNombre.setForeground(textLight);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtNombre.setBounds(280, 405, 200, 30);
        contentPanel.add(txtNombre);

        JButton btnCambiarNombreReal = new JButton("Cambiar");
        btnCambiarNombreReal.setBackground(steamBlue);
        btnCambiarNombreReal.setForeground(textLight);
        btnCambiarNombreReal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCambiarNombreReal.setBounds(480, 400, 110, 40);
        contentPanel.add(btnCambiarNombreReal);

        // Nacimiento
        JLabel lblNac = new JLabel("Nacimiento:");
        lblNac.setForeground(textLight);
        lblNac.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNac.setBounds(80, 455, 200, 30);
        contentPanel.add(lblNac);

        JLabel txtNac = new JLabel(String.valueOf(currentUser.getNacimiento()));
        txtNac.setForeground(textLight);
        txtNac.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtNac.setBounds(280, 455, 300, 30);
        contentPanel.add(txtNac);

        // Descargas
        JLabel lblDescargas = new JLabel("Descargas:");
        lblDescargas.setForeground(textLight);
        lblDescargas.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblDescargas.setBounds(80, 505, 200, 30);
        contentPanel.add(lblDescargas);

        JLabel txtDescargas = new JLabel(String.valueOf(currentUser.getContadorDownloads()));
        txtDescargas.setForeground(steamBlue);
        txtDescargas.setFont(new Font("Segoe UI", Font.BOLD, 26));
        txtDescargas.setBounds(280, 505, 200, 30);
        contentPanel.add(txtDescargas);

        // Botón volver
        JButton btnVolver = new JButton("← Volver");
        btnVolver.setBackground(bgDark);
        btnVolver.setForeground(textLight);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnVolver.setBounds(10, 10, 150, 50);
        contentPanel.add(btnVolver);

        // Acción del botón volver
        btnVolver.addActionListener(e -> {
            this.dispose(); // Cierra profileFrame
            new userFrame(st, currentUser).setVisible(true); // Abre userFrame con el mismo usuario
        });

    }
}