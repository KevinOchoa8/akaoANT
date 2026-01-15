import App.ConsoleApp.akaoKGD;
import BusinessLogic.Entities.akaoGenoma;
import BusinessLogic.Entities.akaoHormigaBL;
import BusinessLogic.akaoEntomologoLogic;
import DataAccess.DAOs.akaoHormigaDAO;
import Infrastructure.akaoDataHelper;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    
    private static final String USER_REQ = "patmic";
    private static final String PASS_REQ = "123";
    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        if (akaoLogin()) {
            
            System.out.println("\n=== BIENVENIDO AL SISTEMA ANTDRON2K25 ===");
            akaoKGD alumno = new akaoKGD("1724077829 (Caso E)", "Anthonny Almeida (akao)");
            akaoKGD alumno1 = new akaoKGD("1726941279 (Caso E)", "Kevin Ochoa (akao)");
            alumno.show();
            alumno1.show();
            System.out.println(); 


            try {
                akaoDataHelper etl = new akaoDataHelper();
                etl.akaoOperacionETL();
            } catch (Exception e) {
                System.out.println("Error en ETL: " + e.getMessage());
            }



            System.out.println("\n\n>>>  INICIANDO EXPERIMENTO DEL ENTOMÓLOGO RUSO  <<<");
            
            akaoHormigaDAO dao = new akaoHormigaDAO();
            List<akaoHormigaBL> misHormigas = dao.akaoReadAll();
            akaoEntomologoLogic entomologo = new akaoEntomologoLogic();
            Random random = new Random();

            String[] menu = {"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Nectarívoros"};

            if (misHormigas.isEmpty()) {
                System.out.println("No hay hormigas en la base de datos. Revisa el ETL.");
            }

            for (akaoHormigaBL hormiga : misHormigas) {
                System.out.println("\n------------------------------------------------");
                System.out.println("Probando con: " + hormiga.toString());

                String comidaRandom = menu[random.nextInt(menu.length)];
                
                akaoGenoma genomaRandom = null;
                if (random.nextBoolean()) {
                    akaoGenoma[] genomas = akaoGenoma.values();
                    genomaRandom = genomas[random.nextInt(genomas.length)];
                }

                entomologo.akaoAlimentarAnt(hormiga, comidaRandom, genomaRandom);
                
                try { Thread.sleep(300); } catch (Exception e) {}
            }

        } else {
            System.out.println("\n[!] Acceso denegado. Cerrando sistema.");
            System.exit(0);
        }
    }

    private static boolean akaoLogin() {
        Scanner sc = new Scanner(System.in);
        int intentos = 0;
        while (intentos < MAX_ATTEMPTS) {
            System.out.print("Usuario: ");
            String user = sc.nextLine(); 
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (user.equals(USER_REQ) && pass.equals(PASS_REQ)) return true;
            else {
                intentos++;
                System.out.println("Credenciales incorrectas (" + intentos + "/3)");
            }
        }
        return false;
    }
}
