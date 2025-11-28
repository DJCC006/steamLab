/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 *
 * @author ljmc2
 */
public class adminFrame extends JFrame {

    public adminFrame() {

        setTitle("Administrador");
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

        JButton btn1 = new JButton("Registrar Jugador");
        btn1.setBackground(steamBlue);
        btn1.setForeground(textLight);
        btn1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn1.setBounds(10, 0, 330, 90);
        buttonsPanel.add(btn1);

        JButton btn2 = new JButton("Modificar Jugador");
        btn2.setBackground(steamBlue);
        btn2.setForeground(textLight);
        btn2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn2.setBounds(350, 0, 330, 90);
        buttonsPanel.add(btn2);

        JButton btn3 = new JButton("Eliminar Jugador");
        btn3.setBackground(steamBlue);
        btn3.setForeground(textLight);
        btn3.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn3.setBounds(10, 110, 330, 90);
        buttonsPanel.add(btn3);

        JButton btn4 = new JButton("Registrar Juego");
        btn4.setBackground(steamBlue);
        btn4.setForeground(textLight);
        btn4.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn4.setBounds(350, 110, 330, 90);
        buttonsPanel.add(btn4);

        JButton btn5 = new JButton("Modificar Juego");
        btn5.setBackground(steamBlue);
        btn5.setForeground(textLight);
        btn5.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn5.setBounds(10, 220, 330, 90);
        buttonsPanel.add(btn5);

        JButton btn6 = new JButton("Eliminar Juego");
        btn6.setBackground(steamBlue);
        btn6.setForeground(textLight);
        btn6.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn6.setBounds(350, 220, 330, 90);
        buttonsPanel.add(btn6);

        JButton btn7 = new JButton("Cambiar Precio");
        btn7.setBackground(steamBlue);
        btn7.setForeground(textLight);
        btn7.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn7.setBounds(10, 330, 330, 90);
        buttonsPanel.add(btn7);

        JButton btn8 = new JButton("Ver Catálogo");
        btn8.setBackground(steamBlue);
        btn8.setForeground(textLight);
        btn8.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn8.setBounds(350, 330, 330, 90);
        buttonsPanel.add(btn8);

        JButton btn9 = new JButton("Ver Reportes");
        btn9.setBackground(steamBlue);
        btn9.setForeground(textLight);
        btn9.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn9.setBounds(190, 440, 330, 90);
        buttonsPanel.add(btn9);

        JButton btn10 = new JButton("← Volver");
        btn10.setBackground(bgDark);
        btn10.setForeground(textLight);
        btn10.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn10.setBounds(10, 10, 150, 50); 
        contentPanel.add(btn10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new adminFrame().setVisible(true));
    }
}
