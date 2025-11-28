/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author ljmc2
 */
public class catalogFrame extends JFrame {

    public catalogFrame() {

        setTitle("Steam - Catálogo");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Colores Steam
        Color bgDark = new Color(23, 26, 33);
        Color panelDark = new Color(36, 41, 51);
        Color steamBlue = new Color(0, 153, 255);
        Color textLight = Color.WHITE;

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgDark);
        add(mainPanel);

        // ----------------------------------------------
        // PANEL SUPERIOR (Volver + Título Catálogo)
        // ----------------------------------------------
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgDark);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnVolver = new JButton("← Volver");
        btnVolver.setBackground(panelDark);
        btnVolver.setForeground(textLight);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel("CATÁLOGO DE JUEGOS", SwingConstants.CENTER);
        lblTitulo.setForeground(textLight);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));

        topPanel.add(btnVolver, BorderLayout.WEST);
        topPanel.add(lblTitulo, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ----------------------------------------------
        // PANEL DEL CATÁLOGO (scroll con tarjetas)
        // ----------------------------------------------
        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(bgDark);
        gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));

        // TEMPORAL: agregar tarjetas de muestra (sin lógica)
        for (int i = 0; i < 8; i++) {
            gridPanel.add(crearTarjetaDemo());
        }

        JScrollPane scroll = new JScrollPane(gridPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(bgDark);

        mainPanel.add(scroll, BorderLayout.CENTER);

        // LISTENER VACÍO VOLVER
        btnVolver.addActionListener(e -> {
            // TODO volver al menu
        });
    }

    // ------------------------------------------------------
    // TARJETA DEMO — diseño tipo Steam (sin lógica)
    // ------------------------------------------------------
    private JPanel crearTarjetaDemo() {

        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(220, 330));
        tarjeta.setBackground(new Color(36, 41, 51));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 80, 95), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 153, 255), 2),
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 80, 95), 1),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        // Imagen demo
        JLabel img = new JLabel();
        img.setPreferredSize(new Dimension(180, 180));
        img.setOpaque(true);
        img.setBackground(new Color(60, 70, 85)); // Placeholder estilo Steam
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setForeground(Color.WHITE);
        img.setText("IMAGEN");
        img.setFont(new Font("Segoe UI", Font.BOLD, 14));
        img.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nombre
        JLabel nombre = new JLabel("<html><center>Nombre del Juego</center></html>");
        nombre.setForeground(Color.WHITE);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombre.setBorder(new EmptyBorder(5, 0, 5, 0));

        // Etiqueta tipo juego
        JLabel tipo = new JLabel("GAME");
        tipo.setOpaque(true);
        tipo.setBackground(new Color(0, 153, 255));
        tipo.setForeground(Color.WHITE);
        tipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipo.setBorder(new EmptyBorder(4, 8, 4, 8));

        // Precio demo
        JLabel precio = new JLabel("299 Lps");
        precio.setForeground(new Color(0, 153, 255));
        precio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        precio.setAlignmentX(Component.CENTER_ALIGNMENT);
        precio.setBorder(new EmptyBorder(8, 0, 0, 0));

        tarjeta.add(img);
        tarjeta.add(nombre);
        tarjeta.add(tipo);
        tarjeta.add(precio);

        return tarjeta;
    }

    // Test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new catalogFrame().setVisible(true));
    }
}
