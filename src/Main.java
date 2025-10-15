import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Frazione f1 = new Frazione(2, 7);
        Frazione f2 = new Frazione(13, -10);

        System.out.println("Prima frazione: " + f1);
        System.out.println("Seconda frazione: " + f2);
        System.out.println("Valore reale prima frazione: " + f1.calcola());
        System.out.println("Valore reale seconda frazione: " + f2.calcola());

        Frazione f3 = f1.mul(f2);
        System.out.println("Terza frazione (f1 * f2): " + f3);
        System.out.println("Quarta frazione (f3 semplificata): " + f3.semplifica());
        System.out.println("Quinta frazione (f1 + f2): " + f1.add(f2));
        System.out.println("Sesta frazione (f1 - f2): " + f1.sub(f2));
        System.out.println("Settima frazione (inv(f1)): " + f1.inv());
        System.out.println("Settima frazione (f1/f2): " + f1.div(f2));

        System.out.println("\nCollaudo della eccezione: inserire il denominatore nullo...\n");

        System.out.print("Inserisci il numeratore: ");
        int num = input.nextInt();
        System.out.print("Inserisci il denominatore: ");
        int den = input.nextInt();

        try{
            Frazione f9 =  new Frazione(num, den);
        }
        catch(ZeroDenominatorException e){
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
