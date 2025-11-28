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
     
     
     
    
     public int getCode(int type) throws IOException {
         
        if(type>=1 && type<=3){
            if(type==1){
              codes.seek(0);
              int actualCode=codes.readInt();
              codes.seek(0);
              codes.writeInt(actualCode+1);
              return actualCode;
          }
          if(type==2){
              codes.seek(1);
              int actualCode=codes.readInt();
              codes.seek(1);
              codes.writeInt(actualCode+1);
              return actualCode;
          }
          if(type==3){
              codes.seek(2);
              int actualCode=codes.readInt();
              codes.seek(2);
              codes.writeInt(actualCode+1);
              return actualCode;
          }   
        }
        
        return 0; //tener en consideracion esto bien!! Esto equivale a opcion 
    }
     
     
     /*
     int code

    String título

    char sistemaOperativo (Window, Mac o Linux)

    int edadMínima

    double precio

    int contadorDownloads

    Imagen (almacenada como bytes o ruta relativa)
     
     
     */
     
     
   public void addGame(){
       
       
       
       
   }
   
   
   /*
   int code

    String username

    String password

    String nombre

    long nacimiento 

    int contadorDownloads

    Imagen

    String tipoUsuario (“admin” o “normal”)
   */
   
   public void addPlayer(String username, String password, String realName, long nacimiento,  byte[] Imagen, String tipo) throws IOException {
       int code=getCode(1);//obtencion de codigo en base a la funcion
   
       //Posicionamiento al final de la longitud
       player.seek(player.length());
       
       //Llenado de datos
       player.writeInt(code);
       player.writeUTF(username);
       player.writeUTF(password);
       player.writeUTF(realName);
       player.writeLong(nacimiento);
       player.writeInt(0);
       player.write(Imagen);
       player.writeUTF(tipo);
       
       //Se registra al jugador
   }
   
   
   
    
    
    
    
}
