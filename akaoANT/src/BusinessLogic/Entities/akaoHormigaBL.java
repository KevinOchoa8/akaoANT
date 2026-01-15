package BusinessLogic.Entities;

public class akaoHormigaBL {
    private int id;
    private String tipo;   
    private String sexo;
    private String estado;
    
    private akaoSuperHabilidad habilidad; 

    public akaoHormigaBL(int id, String tipo, String sexo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.sexo = sexo;
        this.estado = estado;
        this.habilidad = null; 
    }

    public void setSuperHabilidad(akaoSuperHabilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void mostrarHabilidad() {
        if (this.habilidad != null) {
            this.habilidad.usarHabilidad(this.tipo);
        }
    }

    public int getId() { return id; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getSexo() { return sexo; }

    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return tipo + " [" + sexo + "] - Estado: " + estado;
    }
}
