package BusinessLogic.Entities;

public class akaoSuperCortadora implements akaoSuperHabilidad {
    
    // Colores para que se vea épico en consola
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void usarHabilidad(String nombreHormiga) {
        System.out.println(ANSI_PURPLE + "\t--- !ACTIVANDO SUPER HABILIDAD (CASO E)¡  ---");
        System.out.println("\tLa hormiga " + nombreHormiga + " ha desplegado sus mandíbulas de titanio.");
        System.out.println("\t[Efecto]: Corta hojas y enemigos a velocidad sónica." + ANSI_RESET);
    }
}
