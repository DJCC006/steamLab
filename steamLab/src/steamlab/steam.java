/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package steamlab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author David
 */
public class steam {
    
    private RandomAccessFile codes,games, player;
    private String pathDownloads= "steam/downloads";
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
     
     
   public void addGame(String titulo, char OS, int edadMin, double precio, String rutaImagen) throws IOException{
       int code= getCode(2);
       
       
       games.seek(games.length());//posicionamiento al final de la lista
       
       
       //agregado de informacion
       games.writeInt(code);
       games.writeUTF(titulo);
       games.writeChar(OS);
       games.writeInt(edadMin);
       games.writeDouble(precio);
       games.writeInt(0);
       games.writeUTF(rutaImagen);
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
   
   public void addPlayer(String username, String password, String realName, long nacimiento,  String rutaImagen, String tipo) throws IOException {
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
       player.writeUTF(rutaImagen);
       player.writeUTF(tipo);
       
       //Se registra al jugador
   }
   
   
   public boolean downloadGame(int codeGame, int codePlayer, char OS) throws IOException{
       
       boolean gExists=false;
       boolean pExists=false;
       boolean osExists=false;
       boolean ed=false;
       long posGame=0;
       long posPlayer=0;
       int minAge=0;
       String rutaImagen="";
       
       
       String pName= "";
       String gName = "";
       
       double precio=0;
       
       
       
       //revion de existencia juego
       games.seek(0);
       while(games.getFilePointer()<games.length()){
           int code= games.readInt();
           if(code==codeGame){
               gExists=true;
               posGame=games.getFilePointer();
               gName=games.readUTF();
               games.readChar();
               minAge=games.readInt();//obtencion de edad minima para ese juego
               
                precio=games.readDouble();
                games.readInt();
                rutaImagen=games.readUTF();
               
               
               
               break;
           }
           
           //movimiento Puntero
           games.readUTF();
           games.readChar();
           games.readInt();
           games.readDouble();
           games.readInt();
           games.readUTF();
           
           //lee todo y salta al nuevo espacio
       }
       
       //revision de existencia jugador
       player.seek(0);
       while(player.getFilePointer()<player.length()){
           int code= player.readInt();
           
           if(code == codePlayer){
               pExists=true;
               posPlayer=player.getFilePointer();//referencia de jugador 
               
               player.readUTF();
               player.readUTF();
               
               pName=player.readUTF();
               break;
           }
           
           player.readUTF();
           player.readUTF();
           player.readUTF();
           player.readLong();
           player.readInt();
           player.readUTF();
           player.readUTF();
           //lee y continua
       }
       
       
       //Verificacion OS
       games.seek(posGame);//ubicarnos en la referencia del game
       games.readUTF();
       if(OS == games.readChar()){
           osExists=true;
       }
       
       
       
       //verificacion edad
       player.seek(posPlayer);//posicion en base a codigo
       
       //movimiento puntero
       player.readUTF();
       player.readUTF();
       player.readUTF();
       long nPlayer= player.readLong();
       
       int edadActual=calcularEdad(nPlayer);
       
       if(edadActual>minAge){
           ed=true;
       }
       
       
       
       
       if(gExists==true && pExists==true && osExists == true && ed ==true){
           
           //Crecion codigo
           int codeD= getCode(3);
           
           //creacion archivo de nueva descarga
           String newPath= pathDownloads+"/download"+codeD+".stm";
           File newDownload = new File(newPath);
           RandomAccessFile downFile = new RandomAccessFile(newDownload, "rw");
           
           
           /*
           UTF Fecha
           UTF Imagen
           UTF Codigo
           UTF Nombres
           UTF precio 
           */
           
           //obtencion de fecha
           Calendar fechaDescarga = Calendar.getInstance();
           String format = "dd/MM/yyyy";
           SimpleDateFormat formtear = new SimpleDateFormat(format);
           String fechaD= formtear.format(fechaDescarga.getTime());
           
           
           downFile.writeUTF(fechaD);
           downFile.writeUTF(rutaImagen);
           downFile.writeUTF("Download #"+codeD);
           downFile.writeUTF(pName+" ha bajado ["+gName+"] a un precio $"+precio);
           
           
           
           
           //Actualizar contador de juego
            games.seek(posGame);
            games.readUTF();
            games.readChar();
            games.readInt();
            games.readDouble();
            int previous = games.readInt();
            
            games.seek(posGame);
            games.readUTF();
            games.readChar();
            games.readInt();
            games.readDouble();
            games.writeInt(previous+1);
            
            
            
            //actualizar contador de jugador
            player.seek(posPlayer);
            player.readUTF();
            player.readUTF();
            player.readUTF();
            player.readLong();
            int previousP = player.readInt();
            
            player.seek(posPlayer);
            player.readUTF();
            player.readUTF();
            player.readUTF();
            player.readLong();
            player.writeInt(previousP+1);
            
           return true;
       }
       
       return false;
       
   }
   
   
   private int calcularEdad(long fecha){
       Date nacimiento = new Date(fecha);
       
       Calendar birthCalendar = Calendar.getInstance();
       birthCalendar.setTime(nacimiento);
       
       Calendar hoy= Calendar.getInstance();
       
       int edad= hoy.get((Calendar.YEAR)- birthCalendar.get(Calendar.YEAR));
       
       if(hoy.get(Calendar.MONTH)< birthCalendar.get(Calendar.MONTH)){
           edad--;
       }
       
       else if(hoy.get(Calendar.MONTH)== birthCalendar.get(Calendar.MONTH)){
           if(hoy.get(Calendar.DAY_OF_MONTH)< birthCalendar.get(Calendar.DAY_OF_MONTH)){
               edad--;
           }
       }
       
       return edad;
       
   }
   
   
   
   
   public boolean updatePriceFor(int code, double newPrice) throws IOException{
       games.seek(0);
       while(games.getFilePointer()<games.length()){
           int codeG= games.readInt();
           if(codeG==code){
               games.readUTF();
               games.readChar();
               games.readInt();
               
               games.writeDouble(newPrice);
               return true;
           }
           
           //movimiento Puntero
           games.readUTF();
           games.readChar();
           games.readInt();
           games.readDouble();
           games.readInt();
           games.readUTF();
           
           //lee todo y salta al nuevo espacio
       }
       
       return false;
   }
   
   
   
   public void reportForClient(int codigo, String fileName) throws IOException {
       
       File newReport = new File("steam", fileName+".txt");
       long posClient=0;
       String username="";
       String nombre = "";
       long nacimiento=0;
       int descargas =0;
       String rutaImag ="";
       String type="";
       
       
       
       
       //buscar existencia de cliente
       
       if(searchClient(codigo)){
           player.seek(0);
           while(player.getFilePointer()< player.length()){
                int code=player.readInt();
                if(code!= codigo){
                     posClient=player.getFilePointer();
                }
                player.readUTF();
                player.readUTF();
                player.readUTF();
                player.readLong();
                player.readInt();
                player.readUTF();
                player.readUTF();
           }
           
           //extraccion de info
           player.seek(posClient);
           username=player.readUTF();
           player.readUTF();//obviamos password
           nombre = player.readUTF();
           nacimiento = player.readLong();
           descargas = player.readInt();
           rutaImag = player.readUTF();
           type = player.readUTF();
           
           
           
           Date fechanacimiento = new Date(nacimiento);
           String format = "dd/MM/yyyy";
           SimpleDateFormat formtear = new SimpleDateFormat(format);
           String fechaD= formtear.format(fechanacimiento);
           
           
           
           BufferedWriter escribir = new BufferedWriter(new FileWriter(newReport));
           escribir.write("Codigo: "+codigo+"\n");
           escribir.write("Username: "+username+"\n");
           escribir.write("Nombre: "+nombre+"\n");
           escribir.write("Fecha Nacimiento: "+fechaD);
           escribir.write("Descargas Totales: "+descargas);
           escribir.write("Tipo de Cuenta: "+type);
           escribir.close();
           
           if(newReport.createNewFile()){
               JOptionPane.showMessageDialog(null, "REPORTE CREADO");
           }else{
               JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE");
           }
       }else{
           JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE");
       }
       
       //escritura de los datos en el archivo
       
       
       
       
       
   }
   
   
   
   private boolean searchClient(int codigo) throws IOException {
       player.seek(0);
       while(player.getFilePointer()< player.length()){
           int code=player.readInt();
           if(code== codigo){
               return true; 
           }
           player.readUTF();
           player.readUTF();
           player.readUTF();
           player.readLong();
           player.readInt();
           player.readUTF();
           player.readUTF();
       }
       
       
       return false;
   }
   
   
    
    
    
    
}
