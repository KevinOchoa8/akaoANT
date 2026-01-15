package Infrastructure;

import DataAccess.DAOs.akaoAlimentoDAO;
import DataAccess.DAOs.akaoHormigaDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class akaoDataHelper {
    
    // Colores ANSI
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void akaoOperacionETL() {
        try {
            DataAccess.Helpers.akaoSQLiteDataHelper.akaoGetConnection();
        } catch (Exception e) {
           
        }

        akaoCargarHormigas();
        System.out.println(); 

        akaoCargarAlimentos();
    }

    private void akaoCargarHormigas() {
        System.out.println(" [+] Hormigas"); 
        
        akaoHormigaDAO hormigaDAO = new akaoHormigaDAO();
        String filePath = "akaoANT\\storage\\DataFiles\\AntNest.txt"; 

        List<String> validas = Arrays.asList("HLarva", "HSoldado", "HZángano", "HRastreadora", "HReina", "HObrera");
        String miCaso = "HObrera"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split("[,\\s-]+");
                for (String dato : datos) {
                    if (dato.isEmpty()) continue;

                    boolean esValida = validas.contains(dato);
                    boolean esMiCaso = dato.equals(miCaso);
                    
                    String estadoTexto;
                    if (esMiCaso) {
                        estadoTexto = "Almacenado"; 
                        hormigaDAO.akaoCreateHormiga(dato); 
                    } else if (esValida) {
                        estadoTexto = "Detectado";  
                    } else {
                        estadoTexto = "Descartado"; 
                    }

                    akaoAnimarHormiga(dato, esValida, estadoTexto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo AntNest: " + e.getMessage());
        }
    }

    private void akaoCargarAlimentos() {
        System.out.println(" [+] Alimentos"); 
        
        akaoAlimentoDAO alimentoDAO = new akaoAlimentoDAO();
        String filePath = "akaoANT\\storage\\DataFiles\\AntFood.txt";

        List<String> validos = Arrays.asList("Nectarívoros", "Carnívoro", "Omnívoro", "Insectívoro", "Herbívoro");
        String miCaso = "Herbívoro"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split("[,\\s-]+");
                for (String dato : datos) {
                    if (dato.isEmpty()) continue;

                    boolean esValido = validos.contains(dato);
                    boolean esMiCaso = dato.equals(miCaso);

                    String estadoTexto;
                    if (esMiCaso) {
                        estadoTexto = "Almacenado";
                        alimentoDAO.akaoCreateAlimento(dato);
                    } else if (esValido) {
                        estadoTexto = "Detectado";
                    } else {
                        estadoTexto = "Descartado"; 
                    }

                    akaoAnimarAlimento(dato, esValido, estadoTexto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo AntFood: " + e.getMessage());
        }
    }

    private void akaoAnimarHormiga(String dato, boolean esAzul, String mensaje) {
        char[] frames = {'\\', '|', '/', '-'};
        String color = esAzul ? ANSI_BLUE : ANSI_RED; 

        for (int i = 0; i < 10; i++) { 
            try {
                System.out.print("\r  " + frames[i % 4] + "  " + dato);
                Thread.sleep(20); 
            } catch (InterruptedException e) {}
        }
        
        System.out.print("\r  -  " + color + "[" + mensaje + "] " + dato + ANSI_RESET + "\n");
    }

    private void akaoAnimarAlimento(String dato, boolean esAzul, String mensaje) {
        String[] frames = {".o.", "o.o", ".o."};
        String color = esAzul ? ANSI_BLUE : ANSI_RED;

        for (int i = 0; i < 10; i++) { 
            try {
                System.out.print("\r  " + frames[i % 3] + " " + dato);
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }

        System.out.print("\r  .o. " + color + "[" + mensaje + "] " + dato + ANSI_RESET + "\n");
    }
}
