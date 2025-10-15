import org.w3c.dom.ls.LSOutput;

import java.util.HashSet;
import java.util.Set;

public class Frazione implements Operando {
    public int _numeratore;
    public int _denominatore;

    public Frazione(int numeratore, int denominatore) {
        if  (denominatore == 0) {
            throw new ZeroDenominatorException("Denominatore non valido");
        }

        _numeratore = numeratore;
        _denominatore = denominatore;
    }

    private static int denominatoreComune(Frazione first, Frazione other){
        first = first.semplifica();
        other = other.semplifica();
        return first._denominatore == other._denominatore ? first._denominatore : first._denominatore * other._denominatore;
    }

    public static Frazione inv(Frazione first){
        Frazione r;

        try {
            r = new Frazione(first._denominatore, first._numeratore) ;
        }
        catch (ZeroDenominatorException e)
        {
            throw new ZeroDenominatorException("Inversione non possibile");
        }

        return r;
    }

    public Frazione add(Frazione other){
        Frazione first = this.aggiustaSegno();
        other = other.aggiustaSegno();

        int numeratorePrimo = first._numeratore * (other._denominatore);
        int numeratoreSecondo = other._numeratore * (first._denominatore);

        return new Frazione(numeratorePrimo + numeratoreSecondo,  Frazione.denominatoreComune(first, other)).semplifica();
    }

    public Frazione mul(Frazione other){
        return new Frazione(this._numeratore * other._numeratore, this._denominatore * other._denominatore).aggiustaSegno();
    }

    public Frazione sub(Frazione other){
        Frazione first = this.aggiustaSegno();
        other = other.aggiustaSegno();

        int numeratorePrimo = first._numeratore * (other._denominatore);
        int numeratoreSecondo = other._numeratore * (first._denominatore);

        return new Frazione(numeratorePrimo - numeratoreSecondo,  Frazione.denominatoreComune(first, other)).semplifica();
    }

    public Frazione semplifica(){
        int mcd = Utils.gcd(this._numeratore, this._denominatore);
        Frazione r = mcd != 1 ? new Frazione(this._numeratore / mcd, this._denominatore / mcd) : this;
        return r.aggiustaSegno();
    }

    public Frazione aggiustaSegno(){
        if (_denominatore > 0)
            return this;

        if (_numeratore > 0)
            return new Frazione(this._numeratore * -1, this._denominatore * -1);
        return new Frazione(Math.abs(this._numeratore), Math.abs(this._denominatore));
    }

    public Frazione div(Frazione other){
        return mul(Frazione.inv(other));
    }

    public Frazione inv(){
        Frazione r;

        try {
            r = new Frazione(this._denominatore, this._numeratore) ;
        }
        catch (ZeroDenominatorException e)
        {
            throw new ZeroDenominatorException("Inversione non possibile");
        }

        return r;
    }

    public boolean isGreater(Frazione other){
        return this.calcola() > other.calcola();
    }

    public boolean isLess(Frazione other){
        return this.calcola() < other.calcola();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Frazione))
            return false;

        Frazione other =  (Frazione)o;
        other.semplifica();

        return this._numeratore == other._numeratore && this._denominatore == other._denominatore;
    }

    @Override
    public void stampa() {
        System.out.println(this);
    }

    @Override
    public float calcola() {
        return (float)_numeratore / _denominatore;
    }

    @Override
    public String toString() {
        return String.valueOf(_numeratore) + '/' + String.valueOf(_denominatore);
    }
}
