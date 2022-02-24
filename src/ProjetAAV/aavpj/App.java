package ProjetAAV.aavpj;    

public class App
{
    public static void main(String[] args) 
    {

        if(args.length < 3) {
            System.out.println("Il manque des arguments");
            return;
        }
        String chemin = args[0];
        int poidsMax = Integer.parseInt(args[1]);
        String nomMethode = args[2];

        if (nomMethode.compareTo("gloutonne") == 0)
            resoudreGlouton(chemin, poidsMax);
        else if (nomMethode.compareTo("pse") == 0)
            resoudrePSE(chemin, poidsMax);
    }

    public static void resoudreGlouton(String chemin, int poidsMaximal) {
        long debutTime = System.currentTimeMillis();
        SacADos bag = new SacADosGlouton(chemin, poidsMaximal);
        bag.resoudre();
        System.out.println(bag);

        long finTime = System.currentTimeMillis()-debutTime;
        String s="";
        s += "temps d'éxecution : " + finTime + " ms";
        System.out.println(s);
    }

    public static void resoudrePSE(String chemin, int poidsmaximal) {
        long debutTime = System.currentTimeMillis();

        SacADos bag = new SacADosPSE(chemin, poidsmaximal);
        bag.resoudre();
        System.out.println(bag);

        long finTime= System.currentTimeMillis()-debutTime;
        String s="";
        s += "temps d'éxecution : " + finTime + " ms";
        System.out.println(s);
    }
}
