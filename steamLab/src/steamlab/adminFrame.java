/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

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

        // Crear botones
        JButton registroBtn = createButton("Registrar Jugador", steamBlue, textLight);
        registroBtn.setBounds(10, 0, 330, 90);
        buttonsPanel.add(registroBtn);

        JButton modBtn = createButton("Modificar Jugador", steamBlue, textLight);
        modBtn.setBounds(350, 0, 330, 90);
        buttonsPanel.add(modBtn);

        JButton eliminarBtn = createButton("Eliminar Jugador", steamBlue, textLight);
        eliminarBtn.setBounds(10, 110, 330, 90);
        buttonsPanel.add(eliminarBtn);

        JButton registroJuegoBtn = createButton("Registrar Juego", steamBlue, textLight);
        registroJuegoBtn.setBounds(350, 110, 330, 90);
        buttonsPanel.add(registroJuegoBtn);

        JButton modJuegoBtn = createButton("Modificar Juego", steamBlue, textLight);
        modJuegoBtn.setBounds(10, 220, 330, 90);
        buttonsPanel.add(modJuegoBtn);

        JButton eliminarJuegoBtn = createButton("Eliminar Juego", steamBlue, textLight);
        eliminarJuegoBtn.setBounds(350, 220, 330, 90);
        buttonsPanel.add(eliminarJuegoBtn);

        JButton cambiarPrecioBtn = createButton("Cambiar Precio", steamBlue, textLight);
        cambiarPrecioBtn.setBounds(10, 330, 330, 90);
        buttonsPanel.add(cambiarPrecioBtn);

        JButton catalogoBtn = createButton("Ver Catálogo", steamBlue, textLight);
        catalogoBtn.setBounds(350, 330, 330, 90);
        buttonsPanel.add(catalogoBtn);

        JButton reportesBtn = createButton("Ver Reportes", steamBlue, textLight);
        reportesBtn.setBounds(190, 440, 330, 90);
        buttonsPanel.add(reportesBtn);

        JButton returnBtn = createButton("← Cerrar Sesión", bgDark, textLight);
        returnBtn.setBounds(10, 10, 150, 50);
        contentPanel.add(returnBtn);

        // ------------------- Listeners -------------------

        // Registrar Jugador
        registroBtn.addActionListener(e -> {
            JTextField userField = new JTextField();
            JTextField passField = new JTextField();
            JTextField nombreField = new JTextField();
            JDateChooser nacimientoChooser = new JDateChooser();
            nacimientoChooser.setDateFormatString("dd/MM/yyyy");

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Username:"));
            panel.add(userField);
            panel.add(new JLabel("Password:"));
            panel.add(passField);
            panel.add(new JLabel("Nombre:"));
            panel.add(nombreField);
            panel.add(new JLabel("Fecha de nacimiento:"));
            panel.add(nacimientoChooser);

            // Selección de imagen
            JFileChooser fileChooser = new JFileChooser();
            JButton imgBtn = new JButton("Seleccionar Imagen (opcional)");
            JLabel imgLabel = new JLabel("Ninguna");
            imgBtn.addActionListener(event -> {
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    imgLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            });
            panel.add(new JLabel("Imagen:"));
            panel.add(imgBtn);
            panel.add(imgLabel);

            int result = JOptionPane.showConfirmDialog(this, panel, "Registrar Jugador", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    Date selectedDate = nacimientoChooser.getDate();
                    if (selectedDate == null) {
                        JOptionPane.showMessageDialog(this, "Selecciona una fecha", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    long nacimiento = selectedDate.getTime();
                    String imagePath = imgLabel.getText().equals("Ninguna") ? "" : imgLabel.getText();

                    int assignedCode = st.addPlayer(
                            userField.getText(),
                            passField.getText(),
                            nombreField.getText(),
                            nacimiento,
                            imagePath,
                            "normal"
                    );
                    JOptionPane.showMessageDialog(this, "Jugador registrado.\nCódigo asignado: " + assignedCode);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Modificar Jugador
        modBtn.addActionListener(e -> {
            String codeStr = JOptionPane.showInputDialog(this, "Código del jugador a modificar:");
            if (codeStr == null) return;
            if (!codeStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El código debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int code = Integer.parseInt(codeStr);

            try {
                steam.Player p = st.getPlayer(code);
                if (p == null) {
                    JOptionPane.showMessageDialog(this, "No existe un jugador con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField nombreField = new JTextField(p.getNombre());
                JTextField passField = new JTextField(p.getPassword());

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Nombre:"));
                panel.add(nombreField);
                panel.add(new JLabel("Contraseña:"));
                panel.add(passField);

                // Imagen
                JFileChooser fileChooser = new JFileChooser();
                JButton imgBtn = new JButton("Seleccionar Imagen (opcional)");
                JLabel imgLabel = new JLabel(p.getImagePath().isEmpty() ? "Ninguna" : p.getImagePath());
                imgBtn.addActionListener(ev -> {
                    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        imgLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });
                panel.add(new JLabel("Imagen:"));
                panel.add(imgBtn);
                panel.add(imgLabel);

                int res = JOptionPane.showConfirmDialog(this, panel, "Modificar Jugador", JOptionPane.OK_CANCEL_OPTION);
                if (res != JOptionPane.OK_OPTION) return;

                String newName = nombreField.getText().trim();
                String newPass = passField.getText().trim();
                String newImage = imgLabel.getText().equals("Ninguna") ? "" : imgLabel.getText();

                if (st.updatePlayer(code, newName, newPass, newImage)) {
                    JOptionPane.showMessageDialog(this, "Jugador modificado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error interno al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Eliminar Jugador
        eliminarBtn.addActionListener(e -> {
            try {
                String codeStr = JOptionPane.showInputDialog(this, "Código del jugador a eliminar:");
                if (codeStr == null) return;
                if (!codeStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "El código debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int code = Integer.parseInt(codeStr);
                if (st.deletePlayer(code)) {
                    JOptionPane.showMessageDialog(this, "Jugador eliminado (marcado como BORRADO)");
                } else {
                    JOptionPane.showMessageDialog(this, "Jugador no encontrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Registrar Juego
        registroJuegoBtn.addActionListener(e -> {
            JTextField tituloField = new JTextField();
            JTextField OSField = new JTextField();
            JTextField edadMinField = new JTextField();
            JTextField precioField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Título:"));
            panel.add(tituloField);
            panel.add(new JLabel("OS (W/M/L):"));
            panel.add(OSField);
            panel.add(new JLabel("Edad mínima:"));
            panel.add(edadMinField);
            panel.add(new JLabel("Precio:"));
            panel.add(precioField);

            // Imagen
            JFileChooser fileChooser = new JFileChooser();
            JButton imgBtn = new JButton("Seleccionar Imagen (opcional)");
            JLabel imgLabel = new JLabel("src\\res\\defaultImg.jpg");
            imgBtn.addActionListener(ev -> {
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    imgLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            });
            panel.add(new JLabel("Imagen:"));
            panel.add(imgBtn);
            panel.add(imgLabel);

            int result = JOptionPane.showConfirmDialog(this, panel, "Registrar Juego", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            if (tituloField.getText().trim().isEmpty() ||
                OSField.getText().trim().isEmpty() ||
                edadMinField.getText().trim().isEmpty() ||
                precioField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No puede dejar campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String titulo = tituloField.getText().trim();
                char os = OSField.getText().trim().charAt(0);
                int edadMin = Integer.parseInt(edadMinField.getText().trim());
                double precio = Double.parseDouble(precioField.getText().trim());
                String imgPath = imgLabel.getText();

                st.addGame(titulo, os, edadMin, precio, imgPath);
                JOptionPane.showMessageDialog(this, "Juego registrado");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error registrando juego.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Modificar Juego
        modJuegoBtn.addActionListener(e -> {
            try {
                String codeStr = JOptionPane.showInputDialog(this, "Código del juego a modificar:");
                if (codeStr == null || !codeStr.matches("\\d+")) return;
                int code = Integer.parseInt(codeStr);

                steam.Game g = st.getGame(code);
                if (g == null) {
                    JOptionPane.showMessageDialog(this, "No existe un juego con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField tituloField = new JTextField(g.getTitulo());
                JTextField OSField = new JTextField(String.valueOf(g.getOs()));
                JTextField edadField = new JTextField(String.valueOf(g.getEdadMin()));
                JTextField precioField = new JTextField(String.valueOf(g.getPrecio()));

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Título:"));
                panel.add(tituloField);
                panel.add(new JLabel("OS (W/M/L):"));
                panel.add(OSField);
                panel.add(new JLabel("Edad mínima:"));
                panel.add(edadField);
                panel.add(new JLabel("Precio:"));
                panel.add(precioField);

                // Imagen
                JFileChooser fileChooser = new JFileChooser();
                JButton imgBtn = new JButton("Seleccionar Imagen (opcional)");
                JLabel imgLabel = new JLabel(g.getImagePath().isEmpty() ? "Ninguna" : g.getImagePath());
                imgBtn.addActionListener(ev -> {
                    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        imgLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });
                panel.add(new JLabel("Imagen:"));
                panel.add(imgBtn);
                panel.add(imgLabel);

                int res = JOptionPane.showConfirmDialog(this, panel, "Modificar Juego", JOptionPane.OK_CANCEL_OPTION);
                if (res != JOptionPane.OK_OPTION) return;

                String nuevoTitulo = tituloField.getText().trim();
                char nuevoOS = OSField.getText().trim().charAt(0);
                int edadMin = Integer.parseInt(edadField.getText().trim());
                double precio = Double.parseDouble(precioField.getText().trim());
                String imgPath = imgLabel.getText().equals("Ninguna") ? "" : imgLabel.getText();

                if (st.updateGame(code, nuevoTitulo, nuevoOS, edadMin, precio, imgPath)) {
                    JOptionPane.showMessageDialog(this, "Juego modificado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error modificando el juego.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Eliminar Juego
        eliminarJuegoBtn.addActionListener(e -> {
            try {
                String codeStr = JOptionPane.showInputDialog(this, "Código del juego a eliminar:");
                if (codeStr == null || !codeStr.matches("\\d+")) return;
                int code = Integer.parseInt(codeStr);
                if (st.deleteGame(code)) {
                    JOptionPane.showMessageDialog(this, "Juego eliminado (marcado como borrado).");
                } else {
                    JOptionPane.showMessageDialog(this, "Juego no encontrado.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Cambiar precio
        cambiarPrecioBtn.addActionListener(e -> {
            try {
                String codeStr = JOptionPane.showInputDialog(this, "Código del juego:");
                if (codeStr == null) return;
                int code = Integer.parseInt(codeStr);

                String priceStr = JOptionPane.showInputDialog(this, "Nuevo precio:");
                if (priceStr == null) return;
                double newPrice = Double.parseDouble(priceStr);

                if (st.updatePriceFor(code, newPrice)) {
                    JOptionPane.showMessageDialog(this, "Precio actualizado");
                } else {
                    JOptionPane.showMessageDialog(this, "Juego no encontrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Ver catálogo
        catalogoBtn.addActionListener(e -> {
            new catalogFrame(st, currentUser).setVisible(true);
            this.dispose();
        });

        // Ver reportes
        reportesBtn.addActionListener(e -> {
            try {
                String codeStr = JOptionPane.showInputDialog(this, "Código del cliente:");
                if (codeStr == null) return;
                int code = Integer.parseInt(codeStr);

                String fileName = JOptionPane.showInputDialog(this, "Nombre del archivo:");
                if (fileName == null || fileName.trim().isEmpty()) return;

                st.reportForClient(code, fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Cerrar sesión
        returnBtn.addActionListener(e -> {
            new loginFrame(st).setVisible(true);
            this.dispose();
        });
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn.setFocusPainted(false);
        return btn;
    }
}