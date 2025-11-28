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
public class loginFrame extends JFrame {
    private steam st;
    
    public loginFrame(steam st) {
        this.st = st;
        setTitle("LOGIN");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color bgDark = new Color(23, 26, 33);
        Color panelDark = new Color(36, 41, 51);
        Color steamBlue = new Color(0, 153, 255);
        Color textLight = Color.WHITE;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgDark);
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(panelDark);
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        formPanel.setLayout(new GridLayout(6, 1, 15, 15));

        JLabel title = new JLabel("Iniciar Sesión");
        title.setForeground(textLight);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField txtUser = new JTextField();
        txtUser.setBackground(Color.WHITE);
        txtUser.setForeground(Color.BLACK);
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtUser.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(steamBlue, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBackground(Color.WHITE);
        txtPass.setForeground(Color.BLACK);
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtPass.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(steamBlue, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(steamBlue);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        formPanel.add(title);
        formPanel.add(txtUser);
        formPanel.add(txtPass);
        formPanel.add(btnLogin);

        mainPanel.add(formPanel);
        
        
        btnLogin.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            try {
                Player loggedUser = st.login(username, password);
                if(loggedUser != null){
                    JOptionPane.showMessageDialog(this, "Login exitoso.");

                    if(loggedUser.getTipoUsuario().equals("admin")){
                        new adminFrame(st, loggedUser).setVisible(true);
                    } else {
                        new userFrame(st, loggedUser).setVisible(true);
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
    }

}
