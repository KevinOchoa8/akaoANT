package BusinessLogic.Interfaces;

import BusinessLogic.Entities.akaoHormigaBL;
import BusinessLogic.Entities.akaoGenoma;

public interface IakaoEntomologo {
    
    void akaoAlimentarHormigas();
    
    void akaoAlimentarAnt(akaoHormigaBL hormiga, String alimento, akaoGenoma genoma);
}
