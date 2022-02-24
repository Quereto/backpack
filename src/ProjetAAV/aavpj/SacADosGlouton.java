package ProjetAAV.aavpj;

import java.util.ArrayList;
import java.util.Collections;

class SacADosGlouton extends SacADos {


    public SacADosGlouton() {
        super();
    }

    public SacADosGlouton(String chemin, int poidsMax) {
        super(chemin, poidsMax);
    }

    @Override
    public void resoudre() {
        itemsSelectionnes = new ArrayList<Item>();
        Collections.sort(itemsDisponibles);
        int sommePoids = 0;
        int i = itemsDisponibles.size() - 1;
        while(sommePoids < poidsMax && i >= 0) {
            Item item = itemsDisponibles.get(i--);
            if(sommePoids + item.getPoids() <= poidsMax) {
                selectionnerItem(item);
                sommePoids += item.getPoids();
            }
        }
    }

}

