/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package steamlab;

import javax.swing.SwingUtilities;

/**
 *
 * @author David
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    /*
    public boolean playerExists(String username) throws IOException {
    player.seek(0);

    while (player.getFilePointer() < player.length()) {
        int code = player.readInt();
        String user = player.readUTF();
        String pass = player.readUTF();
        String name = player.readUTF();
        long nac = player.readLong();
        int score = player.readInt();

        // Leer imagen (debes conocer el tamaño fijo, si no lo usas, ignóralo)
        byte[] img = new byte["];
        player.read(img);

        String tipo = player.readUTF();

        if (user.equals(username)) {
            return true;
        }
    }
    */
    public static void main(String[] args) {
        
        steam st=new steam();
        byte[] img = .readAllBytes(
            Paths.get("C:\\Users\\ljmc2\\Documents\\steamLab\\steamLab\\src\\res\\defaultImg.jpg")
        );
        st.addPlayer("admin", "password", "Administrador Default", 1,img, "admin");
        SwingUtilities.invokeLater(() -> new loginFrame().setVisible(true));
    }
    
}
