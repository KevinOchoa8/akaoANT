package App.ConsoleApp;

public class akaoKGD {
    private String cedula;
    private String nombre;
    
    public akaoKGD(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }
    
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public void show() {
        System.out.println("=========================================");
        System.out.println("[+] Alumno:");
        System.out.println("Cédula: " + getCedula() + " | Nombre: " + getNombre());
        System.out.println("=========================================");
    }

    @Override
    public String toString() {
        return getClass().getName()
             + "\n cedula    : " + getCedula()
             + "\n nombre    : " + getNombre();
    }
}
