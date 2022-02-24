package ProjetAAV.aavpj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract class SacADos {

    protected ArrayList<Item> itemsDisponibles;
    protected ArrayList<Item> itemsSelectionnes;
    protected int poidsMax;

    public SacADos() {
        itemsDisponibles = new ArrayList<Item>(0);
        itemsSelectionnes = new ArrayList<Item>(0);
        poidsMax = 0;
    }

    public SacADos(String chemin, int poidsMaximal) {
        this();
        this.poidsMax = poidsMaximal;
        decoderItemsDisponibles(chemin);
    }

    public abstract void resoudre();

    private void decoderItemsDisponibles(String chemin) {
        BufferedReader reader;
        
        try {
            reader = new BufferedReader(new FileReader(chemin));
            String line = reader.readLine();
            while (line != null) {
                declarerItem(Item.decoderitem(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void declarerItem(Item item) {
        itemsDisponibles.add(item);
    }

    public void selectionnerItem(Item item) {
        itemsSelectionnes.add(item);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        if (itemsSelectionnes.size() > 0){
            sb.append("Solution trouvée : \n");
            int poidsTotal = 0;
            int valeurTotale = 0;
            for (Item item : itemsSelectionnes) {
                sb.append(item.toString());
                sb.append("\n");
                poidsTotal += item.getPoids();
                valeurTotale += item.getValeur();
            }
            sb.append("Poids total : ");
            sb.append(poidsTotal);
            sb.append("/");
            sb.append(poidsMax);
            sb.append("\n");
            sb.append("Valeur totale : ");
            sb.append(valeurTotale);
            sb.append("\n");
        } else {
            sb.append("Aucune solution encore trouvée ou solution vide.");
        }
        return sb.toString();
    }
}
