public final class Utils {
    private Utils() {}

    public static int gcd(int a, int b) {
        // Trova mcd
        a = Math.abs(a);
        b = Math.abs(b);

        if (a > b){
            return gcd(b, a);
        }

        // O(sqrt(b)) perché non ricordo l'altro modo
        int gcd = 1;
        for (int i = 2; i <= Math.sqrt(b); i++) {
            if  (b % i == 0 && a % i == 0) {
                gcd = i;
            }
        }

        return gcd;
    }
}
