/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ljmc2
 */
public class reportsFrame extends JFrame {
    private steam st;
    private steam.Player currentUser;

    public reportsFrame(steam st, steam.Player currentUser){
        this.st = st;
        this.currentUser = currentUser;
        
        setTitle("REPORTES");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Color bgDark = new Color(22, 27, 34);
        Color blueSteam = new Color(0, 119, 200);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgDark);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgDark);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnVolver = new JButton("← Volver");
        btnVolver.setBackground(new Color(33, 37, 43));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Listener vacío
        btnVolver.addActionListener(e -> {
            // TODO: volver al frame anterior
        });

        JLabel titulo = new JLabel("GESTIÓN DE REPORTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        topPanel.add(btnVolver, BorderLayout.WEST);
        topPanel.add(titulo, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        //panel central con dos secciones
        JPanel contenido = new JPanel();
        contenido.setBackground(bgDark);
        contenido.setLayout(new GridLayout(1, 2, 25, 0));
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //panel generar reporte
        JPanel panelGenerar = new JPanel();
        panelGenerar.setLayout(new BoxLayout(panelGenerar, BoxLayout.Y_AXIS));
        panelGenerar.setBackground(new Color(33, 37, 43));
        panelGenerar.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel lblGen = new JLabel("GENERAR REPORTE");
        lblGen.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGen.setForeground(Color.WHITE);
        lblGen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Ingrese nombre del usuario:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(Color.WHITE);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // NUEVO CAMPO: nombre del reporte
        JLabel lblNombreReporte = new JLabel("Nombre del reporte:");
        lblNombreReporte.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombreReporte.setForeground(Color.WHITE);

        JTextField txtNombreReporte = new JTextField();
        txtNombreReporte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        txtNombreReporte.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton btnGenerar = new JButton("Generar reporte");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGenerar.setBackground(new Color(0, 119, 200));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnGenerar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listener vacío
        btnGenerar.addActionListener(e -> {
            // TODO: generar reporte automáticamente
        });

        panelGenerar.add(lblGen);
        panelGenerar.add(Box.createVerticalStrut(20));
        panelGenerar.add(lblUser);
        panelGenerar.add(Box.createVerticalStrut(8));
        panelGenerar.add(txtUsuario);
        panelGenerar.add(Box.createVerticalStrut(15));
        panelGenerar.add(lblNombreReporte);
        panelGenerar.add(Box.createVerticalStrut(8));
        panelGenerar.add(txtNombreReporte);
        panelGenerar.add(Box.createVerticalStrut(20));
        panelGenerar.add(btnGenerar);

        JPanel panelVer = new JPanel();
        panelVer.setLayout(new BorderLayout());
        panelVer.setBackground(new Color(33, 37, 43));
        panelVer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel lblVer = new JLabel("REPORTES DISPONIBLES");
        lblVer.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblVer.setForeground(Color.WHITE);
        lblVer.setHorizontalAlignment(SwingConstants.CENTER);
        panelVer.add(lblVer, BorderLayout.NORTH);

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Reporte_Usuario01.txt");
        modeloLista.addElement("Reporte_Usuario02.txt");
        modeloLista.addElement("Reporte_Usuario03.txt");

        JList<String> listaReportes = new JList<>(modeloLista);
        listaReportes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaReportes.setBackground(new Color(24, 28, 35));
        listaReportes.setForeground(Color.WHITE);

        JScrollPane scrollReportes = new JScrollPane(listaReportes);
        scrollReportes.setBorder(null);
        panelVer.add(scrollReportes, BorderLayout.CENTER);

        listaReportes.addListSelectionListener(e -> {
            // TODO: ver reporte seleccionado
        });

        contenido.add(panelGenerar);
        contenido.add(panelVer);
        mainPanel.add(contenido, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    
}