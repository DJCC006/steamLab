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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class steam {

    private RandomAccessFile codes, games, player;
    private String pathDownloads = "steam/downloads";

    public steam() {

        //Creacion de carpetas
        File steamFile = new File("steam");
        File downloadsFile = new File(steamFile, "downloads");

        if (!steamFile.exists()) {
            steamFile.mkdirs();
        }

        if (!downloadsFile.exists()) {
            downloadsFile.mkdirs();
        }
        //Creacion de archivos principales
        try {
            codes = new RandomAccessFile("steam/codes.stm", "rw");
            games = new RandomAccessFile("steam/games.stm", "rw");
            player = new RandomAccessFile("steam/player.stm", "rw");

            //Inicializacion de archivos principales
            initCodes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    games
    players
    downloads
     */
    private void initCodes() throws IOException {
        if (codes.length() == 0) {
            codes.writeInt(1);
            codes.writeInt(1);
            codes.writeInt(1);
        }
    }

    public int getCode(int type) throws IOException {

        if (type >= 1 && type <= 3) {
            if (type == 1) {
                codes.seek(0);
                int actualCode = codes.readInt();
                codes.seek(0);
                codes.writeInt(actualCode + 1);
                return actualCode;
            }
            if (type == 2) {
                codes.seek(4);
                int actualCode = codes.readInt();
                codes.seek(4);
                codes.writeInt(actualCode + 1);
                return actualCode;
            }
            if (type == 3) {
                codes.seek(8);
                int actualCode = codes.readInt();
                codes.seek(8);
                codes.writeInt(actualCode + 1);
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
    public void addGame(String titulo, char OS, int edadMin, double precio, String rutaImagen) throws IOException {
        int code = getCode(2);

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
    public int addPlayer(String username, String password, String realName, long nacimiento, String rutaImagen, String tipo) throws IOException {
        int code = getCode(1);//obtencion de codigo en base a la funcion

        //Posicionamiento al final de la longitud
        player.seek(player.length());

        //Llenado de datos
        player.writeInt(code);
        player.writeUTF(username);
        player.writeUTF(password);
        player.writeUTF(realName);
        player.writeLong(nacimiento);
        player.writeInt(0);
        player.writeUTF(rutaImagen != null ? rutaImagen : "");
        player.writeUTF(tipo);
        
        return code;
    }

    public boolean downloadGame(int codeGame, int codePlayer, char OS) throws IOException {

        boolean gExists = false;
        boolean pExists = false;
        boolean osExists = false;
        boolean ed = false;
        long posGame = 0;
        long posPlayer = 0;
        int minAge = 0;
        String rutaImagen = "";

        String pName = "";
        String gName = "";

        double precio = 0;

        //revision de existencia juego
        games.seek(0);
        while (games.getFilePointer() < games.length()) {
            long pos = games.getFilePointer();
            int code = games.readInt();
            String titulo = games.readUTF();
            char os = games.readChar();
            int edadMin = games.readInt();
            double precioJ = games.readDouble();
            int downloads = games.readInt();
            String ruta = games.readUTF();

            if (code == codeGame) {
                gExists = true;
                posGame = pos;
                gName = titulo;
                minAge = edadMin;
                precio = precioJ;
                rutaImagen = ruta;
                break;
            }
        }

        //revision de existencia jugador
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int code = player.readInt();
            String u = player.readUTF();
            String p = player.readUTF();
            String nombre = player.readUTF();
            long nacimiento = player.readLong();
            int cont = player.readInt();
            String img = player.readUTF();
            String tipo = player.readUTF();

            if (code == codePlayer && !u.equals("BORRADO")) {
                pExists = true;
                posPlayer = pos;
                pName = nombre;
                break;
            }
        }

        //Verificacion OS
        if (gExists) {
            games.seek(posGame);
            games.readInt();
            games.readUTF();
            if (OS == games.readChar()) {
                osExists = true;
            }
        }

        //verificacion edad
        if (pExists) {
            player.seek(posPlayer);
            player.readInt();
            player.readUTF();
            player.readUTF();
            player.readUTF();
            long nPlayer = player.readLong();
            int edadActual = calcularEdad(nPlayer);
            if (edadActual >= minAge) { // >= para permitir edad exacta
                ed = true;
            }
        }

        if (gExists && pExists && osExists && ed) {

            //Creacion codigo
            int codeD = getCode(3);

            //creacion archivo de nueva descarga
            String newPath = pathDownloads + "/download" + codeD + ".stm";
            File newDownload = new File(newPath);
            RandomAccessFile downFile = new RandomAccessFile(newDownload, "rw");

            /*
           UTF Fecha
           UTF Imagen
           UTF Codigo
           UTF Nombres
           UTF precio 
             */
            Calendar fechaDescarga = Calendar.getInstance();
            SimpleDateFormat formtear = new SimpleDateFormat("dd/MM/yyyy");
            String fechaD = formtear.format(fechaDescarga.getTime());

            downFile.writeUTF(fechaD);
            downFile.writeUTF(rutaImagen);
            downFile.writeUTF("Download #" + codeD);
            downFile.writeUTF(pName + " ha bajado [" + gName + "] a un precio $" + precio);
            downFile.close();

            //Actualizar contador de juego
            games.seek(posGame);
            games.readInt();
            games.readUTF();
            games.readChar();
            games.readInt();
            games.readDouble();
            int previous = games.readInt();
            games.seek(games.getFilePointer() - 4); // volver a posición del contador
            games.writeInt(previous + 1);
            games.readUTF(); // saltar rutaImagen

            //actualizar contador de jugador
            player.seek(posPlayer);
            player.readInt();
            player.readUTF();
            player.readUTF();
            player.readUTF();
            player.readLong();
            int previousP = player.readInt();
            player.seek(player.getFilePointer() - 4); // volver a posición del contador
            player.writeInt(previousP + 1);
            player.readUTF();
            player.readUTF();

            return true;
        }

        return false;

    }


    private int calcularEdad(long fecha) {
        Date nacimiento = new Date(fecha);

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(nacimiento);

        Calendar hoy = Calendar.getInstance();

        int edad = hoy.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (hoy.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            edad--;
        } else if (hoy.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)) {
            if (hoy.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
                edad--;
            }
        }

        return edad;
    }

    public boolean updatePriceFor(int code, double newPrice) throws IOException {
        games.seek(0);
        while (games.getFilePointer() < games.length()) {
            long pos = games.getFilePointer();
            int codeG = games.readInt();
            games.readUTF();
            games.readChar();
            games.readInt();
            games.writeDouble(newPrice);
            games.readInt();
            games.readUTF();
        }
        return true;
    }

    public void reportForClient(int codigo, String fileName) throws IOException {

        File newReport = new File("steam", fileName + ".txt");
        long posClient = 0;
        String username = "";
        String nombre = "";
        long nacimiento = 0;
        int descargas = 0;
        String rutaImag = "";
        String type = "";

        //buscar existencia de cliente
        if (searchClient(codigo)) {
            player.seek(0);
            while (player.getFilePointer() < player.length()) {
                long pos = player.getFilePointer();
                int code = player.readInt();
                String u = player.readUTF();
                String p = player.readUTF();
                String n = player.readUTF();
                long birth = player.readLong();
                int cont = player.readInt();
                String img = player.readUTF();
                String tipo = player.readUTF();

                if (code == codigo) {
                    posClient = pos;
                    username = u;
                    nombre = n;
                    nacimiento = birth;
                    descargas = cont;
                    rutaImag = img;
                    type = tipo;
                    break;
                }
            }

            Date fechanacimiento = new Date(nacimiento);
            SimpleDateFormat formtear = new SimpleDateFormat("dd/MM/yyyy");
            String fechaD = formtear.format(fechanacimiento);

            BufferedWriter escribir = new BufferedWriter(new FileWriter(newReport));
            escribir.write("Codigo: " + codigo + "\n");
            escribir.write("Username: " + username + "\n");
            escribir.write("Nombre: " + nombre + "\n");
            escribir.write("Fecha Nacimiento: " + fechaD + "\n");
            escribir.write("Descargas Totales: " + descargas + "\n");
            escribir.write("Tipo de Cuenta: " + type + "\n");
            escribir.close();

            if (newReport.createNewFile()) {
                JOptionPane.showMessageDialog(null, "REPORTE CREADO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE");
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE");
        }

    }

    private boolean searchClient(int codigo) throws IOException {
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int code = player.readInt();
            String u = player.readUTF();
            player.readUTF();
            player.readUTF();
            player.readLong();
            player.readInt();
            player.readUTF();
            player.readUTF();

            if (code == codigo && !u.equals("BORRADO")) {
                return true;
            }
        }

        return false;
    }

    public String printGames() throws IOException {
        games.seek(0);
        String gameList = "";
        while (games.getFilePointer() < games.length()) {
            gameList += games.readInt() + " | ";
            gameList += games.readUTF() + " | ";
            gameList += games.readChar() + " | ";
            gameList += games.readInt() + " | ";
            gameList += games.readDouble() + " | ";
            gameList += games.readInt() + " | ";
            gameList += games.readUTF() + " | ";
            gameList += "\n";
        }

        return gameList;
    }

    public String printPlayers() throws IOException {
        String playerList = "";
        player.seek(0);

        while (player.getFilePointer() < player.length()) {

            int code = player.readInt();
            String username = player.readUTF();
            String password = player.readUTF();
            String nombre = player.readUTF();
            long nacimiento = player.readLong();
            int contador = player.readInt();
            String rutaImg = player.readUTF();
            String tipo = player.readUTF();

            playerList += code + " | " + username + " | " + nombre + " | descargas: " + contador + " | tipo: " + tipo + "\n";
        }

        return playerList;
    }

    public class Player {

        private int code;
        private String username;
        private String password;
        private String nombre;
        private long nacimiento;
        private int contadorDownloads;
        private String ImagePath;
        private String tipoUsuario;

        public Player(int code, String username, String password, String nombre, long nacimiento, int contadorDownloads, String ImagePath, String tipoUsuario) {
            this.code = code;
            this.username = username;
            this.password = password;
            this.nombre = nombre;
            this.nacimiento = nacimiento;
            this.contadorDownloads = contadorDownloads;
            this.ImagePath = ImagePath;
            this.tipoUsuario = tipoUsuario;
        }

        public int getContadorDownloads() {
            return contadorDownloads;
        }

        public String getPassword() {
            return password;
        }

        public long getNacimiento() {
            return nacimiento;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public String getTipoUsuario() {
            return tipoUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCode() {
            return code;
        }

        public String getUsername() {
            return username;
        }

    }

    public Player login(String username, String password) throws IOException {
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long startPos = player.getFilePointer();
            try {
                int code = player.readInt();
                String u = player.readUTF();
                String p = player.readUTF();
                String nombre = player.readUTF();
                long nacimiento = player.readLong();
                int contadorDownloads = player.readInt();
                String rutaImg = player.readUTF();
                String tipoUsuario = player.readUTF();

                if (u.equals(username) && p.equals(password)) {
                    return new Player(code, u, p, nombre, nacimiento, contadorDownloads, rutaImg, tipoUsuario);
                }
            } catch (IOException ex) {
                System.err.println("Registro corrupto en login, posición: " + startPos);
                break;
            }
        }
        return null;
    }

    public boolean updatePlayer(int code, String newName, String newPass, String newImage) throws IOException {
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int c = player.readInt();
            String u = player.readUTF();
            String p = player.readUTF();
            String n = player.readUTF();
            long birth = player.readLong();
            int downloads = player.readInt();
            String img = player.readUTF();
            String tipo = player.readUTF();

            if (c == code && !u.equals("BORRADO")) {
                //Reescribimos el registro completo de manera segura
                player.seek(pos);
                player.writeInt(c);
                player.writeUTF(u);
                player.writeUTF(newPass != null && !newPass.isEmpty() ? newPass : p);
                player.writeUTF(newName != null && !newName.isEmpty() ? newName : n);
                player.writeLong(birth);
                player.writeInt(downloads);
                player.writeUTF(newImage != null && !newImage.isEmpty() ? newImage : img);
                player.writeUTF(tipo);
                return true;
            }
        }
        return false;
    }

    public boolean deletePlayer(int code) throws IOException {
        player.seek(0);

        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int c = player.readInt();
            String username = player.readUTF();
            String password = player.readUTF();
            String nombre = player.readUTF();
            long nacimiento = player.readLong();
            int contador = player.readInt();
            String rutaImg = player.readUTF();
            String tipo = player.readUTF();

            if (c == code) {
                player.seek(pos + 4); //moverse al inicio del UTF username
                player.writeUTF("BORRADO");
                return true;
            }
        }

        return false;
    }

    public Player getPlayer(int code) throws IOException {
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int c = player.readInt();
            String u = player.readUTF();
            String p = player.readUTF();
            String n = player.readUTF();
            long birth = player.readLong();
            int downloads = player.readInt();
            String img = player.readUTF();
            String tipo = player.readUTF();

            if (c == code && !u.equals("BORRADO")) {
                return new Player(c, u, p, n, birth, downloads, img, tipo);
            }
        }
        return null;
    }

    public class Game {

        private int code;
        private String titulo;
        private char os;
        private int edadMin;
        private double precio;
        private int downloads;
        private String imagePath;

        public Game(int code, String titulo, char os, int edadMin, double precio, int downloads, String imagePath) {
            this.code = code;
            this.titulo = titulo;
            this.os = os;
            this.edadMin = edadMin;
            this.precio = precio;
            this.downloads = downloads;
            this.imagePath = imagePath;
        }

        public int getCode() {
            return code;
        }

        public String getTitulo() {
            return titulo;
        }

        public char getOs() {
            return os;
        }

        public int getEdadMin() {
            return edadMin;
        }

        public double getPrecio() {
            return precio;
        }

        public int getDownloads() {
            return downloads;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
    
    public Game getGame(int code) throws IOException {
        games.seek(0);
        while (games.getFilePointer() < games.length()) {

            long pos = games.getFilePointer();
            int c = games.readInt();
            String titulo = games.readUTF();
            char os = games.readChar();
            int edadMin = games.readInt();
            double precio = games.readDouble();
            int downloads = games.readInt();
            String imagePath = games.readUTF();

            if (c == code) {
                return new Game(c, titulo, os, edadMin, precio, downloads, imagePath);
            }
        }
        return null;
    }
    
    public boolean updateGame(int code, String newTitle, char newOS, int newAge, double newPrice, String imagePath) throws IOException {

        games.seek(0);

        while (games.getFilePointer() < games.length()) {

            long pos = games.getFilePointer();

            int c = games.readInt();
            String titulo = games.readUTF();
            char os = games.readChar();
            int edad = games.readInt();
            double precio = games.readDouble();
            int downloads = games.readInt();
            games.writeUTF(imagePath != null && !imagePath.isEmpty() ? imagePath : "");

            if (c == code) {

                games.seek(pos);

                games.writeInt(c);
                games.writeUTF(newTitle != null ? newTitle : titulo);
                games.writeChar(newOS != '\0' ? newOS : os);
                games.writeInt(newAge >= 0 ? newAge : edad);
                games.writeDouble(newPrice >= 0 ? newPrice : precio);
                games.writeInt(downloads);
                games.writeUTF(imagePath);

                return true;
            }
        }

        return false;
    }
    
    public boolean deleteGame(int code) throws IOException {

        games.seek(0);

        while (games.getFilePointer() < games.length()) {

            long pos = games.getFilePointer();

            int c = games.readInt();
            String titulo = games.readUTF();
            char os = games.readChar();
            int edadMin = games.readInt();
            double precio = games.readDouble();
            int downloads = games.readInt();
            String imagePath = games.readUTF();

            if (c == code) {

                games.seek(pos + 4);
                games.writeUTF("BORRADO");

                return true;
            }
        }

        return false;
    }

    public ArrayList<Game> getGames() throws IOException {
        ArrayList<Game> lista = new ArrayList<>();

        games.seek(0);

        while (games.getFilePointer() < games.length()) {
            int code = games.readInt();
            String titulo = games.readUTF();
            char os = games.readChar();
            int edadMin = games.readInt();
            double precio = games.readDouble();
            int downloads = games.readInt();
            String imagePath = games.readUTF();

            lista.add(new Game(code, titulo, os, edadMin, precio, downloads, imagePath));
        }

        return lista;
    }
    
    public boolean existeAdmin() throws IOException {
        player.seek(0);
        while (player.getFilePointer() < player.length()) {
            long pos = player.getFilePointer();
            int code = player.readInt();
            String username = player.readUTF();
            player.readUTF();
            player.readUTF();
            player.readLong();
            player.readInt();
            player.readUTF();
            String tipo = player.readUTF();

            if (!username.equals("BORRADO") && username.equals("admin")) {
                return true;
            }
        }
        return false;
    }
}
