package ProjetAAV.aavpj;

import java.util.ArrayList;

public class SacADosPSE extends SacADosGlouton {

    private int meilleurValeurTrouvee;
    private BTreeCS<Integer> meilleurFeuille;

    public SacADosPSE(String chemin, int poidsMaximal) {
        super(chemin, poidsMaximal);
    }

    @Override
    public void resoudre() {
        // Borne inf
        meilleurValeurTrouvee = 0;
        // Borne sup
        int MaxTheorique = 0;
        for (Item item : itemsDisponibles)
            MaxTheorique += item.getValeur();
        // On utilise la méthode gloutonne pour déterminer
        // la borne inf
        super.resoudre();
        for (Item item : itemsSelectionnes)
            meilleurValeurTrouvee += item.getValeur();

        itemsSelectionnes = new ArrayList<Item>(0);

        BTreeCS<Integer> decisionTree = new BTreeCS<>(-1);
        meilleurFeuille = decisionTree;

        resoudreRec(decisionTree, 0, 0, MaxTheorique);

        BTreeCS<Integer> branche = meilleurFeuille;
        int i = branche.getRootValue();

        while(i > -1) {
            BTreeCS<Integer> parent = branche.getParent();

            if(branche == parent.getLeftTree())
                selectionnerItem(itemsDisponibles.get(i));

                branche = parent;
            i = branche.getRootValue();
        }
    }

    public void resoudreRec(BTreeCS<Integer> brancheDecision, int sommePoids, int sommeValeurs, int maximumTheorique)
    {
        int i = brancheDecision.getRootValue();

        if(sommeValeurs >= meilleurValeurTrouvee) {
            meilleurValeurTrouvee = sommeValeurs;
            meilleurFeuille = brancheDecision;
        }

        if(meilleurValeurTrouvee <= maximumTheorique && i < itemsDisponibles.size() - 1) {
            int prochaineValeur = itemsDisponibles.get(i+1).getValeur();

            int poidsSiOnGarde = sommePoids+itemsDisponibles.get(i+1).getPoids();
            if(poidsSiOnGarde <= poidsMax) {
                brancheDecision.setLeftTree(new BTreeCS<>(i+1, brancheDecision));
                resoudreRec(brancheDecision.getLeftTree(), poidsSiOnGarde, sommeValeurs+prochaineValeur, maximumTheorique);
            }

            int maximumTheoriqueSiOnLaisse = maximumTheorique - prochaineValeur;
            brancheDecision.setRightTree(new BTreeCS<>(i+1, brancheDecision));
            resoudreRec(brancheDecision.getRightTree(), sommePoids, sommeValeurs, maximumTheoriqueSiOnLaisse);
        }
    }
}
