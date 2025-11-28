/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package steamlab;

import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * @author David
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        steam st=new steam();
        try{//administrador default
            st.addPlayer("admin", 
                    "password", 
                    "Administrador Default", 
                    1,
                    "src\\res\\defaultImg.jpg", 
                    "admin");
        
        }catch(IOException e){
            e.getMessage();
        }
        SwingUtilities.invokeLater(() -> new loginFrame().setVisible(true));
    }
    
}
