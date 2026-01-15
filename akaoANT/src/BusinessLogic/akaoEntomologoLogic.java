package BusinessLogic;

import BusinessLogic.Entities.akaoGenoma;
import BusinessLogic.Entities.akaoHormigaBL;
import BusinessLogic.Interfaces.IakaoEntomologo;

public class akaoEntomologoLogic implements IakaoEntomologo {     //"Refactorización" Nueva clase

    public void prepararAlimento(String alimento, akaoGenoma genoma) {
        if (genoma == null) {
            System.out.println("[ Preparado ]--(" + alimento + ")--");
        } else {
            System.out.println("[ Preparado ]--(" + alimento + " + " + genoma + ")--");
        }
    }

    @Override
    public void akaoAlimentarHormigas() {
        System.out.println("Iniciando alimentación masiva...");
    }

@Override
    public void akaoAlimentarAnt(akaoHormigaBL hormiga, String alimento, akaoGenoma genoma) {
        
        prepararAlimento(alimento, genoma);

        if (!esAlimentoCorrecto(hormiga.getTipo(), alimento)) {
            hormiga.setEstado("MUERTA");
            System.out.println(";c " + hormiga.getTipo() + " comió " + alimento + " y MURIÓ.");
            return; 
        }

        System.out.println(";)" + hormiga.getTipo() + " comió " + alimento + " y VIVE.");

        if (hormiga.getTipo().equals("HLarva")) {
            evolucionarLarva(hormiga, genoma);
        }

        if (hormiga.getTipo().equals("HObrera") && genoma == akaoGenoma.XY) {
            
            BusinessLogic.Entities.akaoSuperCortadora superPoder = new BusinessLogic.Entities.akaoSuperCortadora();
            hormiga.setSuperHabilidad(superPoder);
            
            System.out.println("¡MUTACIÓN VALIDA! HObrera + XY = SuperCortadora.");
            hormiga.mostrarHabilidad();
            
        } else if (genoma == akaoGenoma.XY) {
            System.out.println("Genoma XY rechazado: Solo las HObreras pueden desarrollar este poder.");
        }
    }

    private boolean esAlimentoCorrecto(String tipoHormiga, String alimento) {
        switch (tipoHormiga) {
            case "HLarva":       return alimento.equals("Nectarívoros") || alimento.equals("Omnívoro"); 
            case "HZángano":     return alimento.equals("Omnívoro");
            case "HRastreadora": return alimento.equals("Insectívoro") || alimento.equals("Herbívoro"); 
            case "HReina":       return alimento.equals("Nectarívoros");
            case "HObrera":      return alimento.equals("Herbívoro");
            default:             return false;
        }
    }

    private void evolucionarLarva(akaoHormigaBL hormiga, akaoGenoma genoma) {

        if (genoma == null) {

            hormiga.setTipo("HObrera"); 
            hormiga.setSexo("Hembra");
            System.out.println("WAOOOO, La Larva evolucionó a HObrera.");
        } else {

            if (genoma == akaoGenoma.X) {
                hormiga.setTipo("HObrera");
                hormiga.setSexo("Asexual"); 
            } else if (genoma == akaoGenoma.XX) {
                hormiga.setTipo("HReina"); 
                hormiga.setSexo("Hembra");
            } else if (genoma == akaoGenoma.XY) {
                hormiga.setTipo("HZángano"); 
                hormiga.setSexo("Macho");
            }
            System.out.println("Evolución Genética: Ahora es " + hormiga.getTipo() + " (" + hormiga.getSexo() + ")");
        }
    }
}