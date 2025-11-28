/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author David
 */
public class steam {
    
    private RandomAccessFile codes,games, player;
    public steam(){
        
        //Creacion de carpetas
        File steamFile = new File("steam");
        File downloadsFile = new File(steamFile, "downloads");
        
        if(!steamFile.exists()){
            steamFile.mkdirs();
        }
        
        if(!downloadsFile.exists()){
            downloadsFile.mkdirs();
        }
        //Creacion de archivos principales
        try{
            codes = new RandomAccessFile("steam/codes.stm", "rw");
            games = new RandomAccessFile("steam/codes.stm", "rw");
            player = new RandomAccessFile("steam/codes.stm", "rw");  
            
            //Inicializacion de archivos principales

            initCodes();
            
            
        }catch(IOException e){}
        
       
        
                
    }
    
    
    /*
    games
    players
    downloads
    */
    
     private void initCodes() throws IOException {
        if(codes.length()==0){
            codes.writeInt(1);
            codes.writeInt(1);
            codes.writeInt(1);
        }   
    }
    
    
    
    
}
