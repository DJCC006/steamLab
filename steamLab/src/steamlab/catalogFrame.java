/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author ljmc2
 */
public class catalogFrame extends JFrame {

    private steam st;
    private steam.Player currentUser;

    public catalogFrame(steam st, steam.Player currentUser) {
        this.st = st;
        this.currentUser = currentUser;

        setTitle("Catálogo de Juegos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ============================
        //   CARGAR JUEGOS (try/catch)
        // ============================
        ArrayList<steam.Game> juegos = new ArrayList<>();

        try {
            juegos = st.getGames();  // tu función que lanza IOException
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar juegos.");
        }

        // ============================
        //   AGREGAR CARDS DE JUEGOS
        // ============================
        for (steam.Game g : juegos) {
            cardsPanel.add(createGameCard(g));
        }

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            new adminFrame(st, currentUser).setVisible(true);
            this.dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnVolver);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createGameCard(steam.Game g) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel imgLabel;
        try {
            ImageIcon icon = new ImageIcon(g.getImagePath());
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaled));
        } catch (Exception e) {
            imgLabel = new JLabel("Sin imagen");
        }

        card.add(imgLabel, BorderLayout.CENTER);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(0, 1));

        info.add(new JLabel("Código: " + g.getCode()));
        info.add(new JLabel("Nombre: " + g.getTitulo()));
        info.add(new JLabel("Edad Mínima: " + g.getEdadMin()));
        info.add(new JLabel("Precio: L. " + g.getPrecio()));
        info.add(new JLabel("Plataforma: " + g.getOs()));

        card.add(info, BorderLayout.SOUTH);

        return card;
    }
}