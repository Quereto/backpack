package ProjetAAV.aavpj;

public class BTreeCS<T> {

    T value;
    BTreeCS<T> filsGauche = null;
    BTreeCS<T> filsDroit = null;
    BTreeCS<T> parent = null;

    BTreeCS() { }

    BTreeCS(T value) {
        this.value = value;
    }

    BTreeCS(T value, BTreeCS<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public T getRootValue(){
        return this.value;
    }

    public BTreeCS<T> getLeftTree(){
        return this.filsGauche;
    }

    public BTreeCS<T> getRightTree(){
        return this.filsDroit;
    }

    public BTreeCS<T> getParent(){
        return this.parent;
    }

    public void setLeftTree(BTreeCS<T> leftTree){
        this.filsGauche = leftTree;
    }

    public void setRightTree(BTreeCS<T> rightTree){
        this.filsDroit = rightTree;
    }

    public void setParent(BTreeCS<T> parentP){
        this.parent = parentP;
    }
}
