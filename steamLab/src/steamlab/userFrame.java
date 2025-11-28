/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import steamlab.steam.Player;

/**
 *
 * @author ljmc2
 */
public class userFrame extends JFrame {

    private steam st;
    private Player currentUser;

    public userFrame(steam st, Player currentUser) {
        this.st = st;
        this.currentUser = currentUser;

        setTitle("Admin - " + currentUser.getNombre());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MENÚ USUARIO");
        setSize(620, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color bgDark = new Color(23, 26, 33);
        Color panelDark = new Color(36, 41, 51);
        Color steamBlue = new Color(0, 153, 255);
        Color textLight = Color.WHITE;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgDark);
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgDark);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnVolver = new JButton("← Cerrar Sesión");
        btnVolver.setBackground(panelDark);
        btnVolver.setForeground(textLight);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        topPanel.add(btnVolver, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        lblTitulo.setForeground(textLight);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        topPanel.add(lblTitulo, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(panelDark);
        centerPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
        centerPanel.setLayout(new GridLayout(4, 1, 25, 25));

        JButton btnCatalogo = createSteamButton("Ver catálogo de juegos", steamBlue, textLight);
        JButton btnDescargar = createSteamButton("Descargar juegos", steamBlue, textLight);
        JButton btnDescargados = createSteamButton("Ver juegos descargados", steamBlue, textLight);
        JButton btnConfigPerfil = createSteamButton("Configurar perfil", steamBlue, textLight);

        centerPanel.add(btnCatalogo);
        centerPanel.add(btnDescargar);
        centerPanel.add(btnDescargados);
        centerPanel.add(btnConfigPerfil);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        btnVolver.addActionListener(e -> {
            this.dispose();
            new loginFrame().setVisible(true);
        });

        btnCatalogo.addActionListener(e -> {
            // TODO: ver catálogo
        });

        btnDescargar.addActionListener(e -> {
            // TODO: descargar juegos
        });

        btnDescargados.addActionListener(e -> {
            // TODO: ver juegos descargados
        });

        btnConfigPerfil.addActionListener(e -> {
            // TODO: configurar perfil
        });

    }

    private JButton createSteamButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Supongamos que 'steam' es tu clase principal que maneja players
                steam st = new steam();

                // Ejemplo de login
                Player usuario = st.login("miUsuario", "miContraseña");

                if (usuario != null) {
                    new userFrame(st, usuario).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
