import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the secret (S): ");
        int secret = input.nextInt();

        System.out.print("Enter the threshold (t): ");
        int t = input.nextInt();

        System.out.print("Enter the total number of shares (n): ");
        int n = input.nextInt();

        System.out.print("Enter the prime number (p): ");
        int prime = input.nextInt();

        List<Share> shares = generateShares(secret, n, t, prime);
        System.out.println("Shares: " + shares);

        List<Share> selectedShares = shares.subList(0, t);
        BigInteger reconstructedSecret = reconstructSecret(selectedShares, prime);
        System.out.println("Reconstructed Secret: " + reconstructedSecret);

        input.close();
    }

    static class Share {
        int x;
        BigInteger y;

        Share(int x, BigInteger y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static BigInteger evalPolynomial(BigInteger[] coefficients, int x, int prime) {
        BigInteger result = BigInteger.ZERO;
        BigInteger xi = BigInteger.ONE;
        for (BigInteger coefficient : coefficients) {

            xi = xi.multiply(BigInteger.valueOf(x));
        }
        return result;
    }

    public static List<Share> generateShares(int secret, int totalShares, int threshold, int prime) {
        BigInteger[] coefficients = new BigInteger[threshold];
        coefficients[0] = BigInteger.valueOf(secret);
        for (int i = 1; i < threshold; i++) {
            coefficients[i] = new BigInteger(prime - 1, random);
        }
        List<Share> shares = new ArrayList<>();
        for (int i = 1; i <= totalShares; i++) {
            shares.add(new Share(i, evalPolynomial(coefficients, i, prime)));
        }
        return shares;
    }

    public static BigInteger modularInverse(BigInteger a, int prime) {
        return a.modInverse(BigInteger.valueOf(prime));
    }

    public static BigInteger reconstructSecret(List<Share> shares, int prime) {
        BigInteger secret = BigInteger.ZERO;
        for (int j = 0; j < shares.size(); j++) {
            BigInteger xj = BigInteger.valueOf(shares.get(j).x);
            BigInteger yj = shares.get(j).y;
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            for (int m = 0; m < shares.size(); m++) {
                if (m != j) {
                    BigInteger xm = BigInteger.valueOf(shares.get(m).x);
                    numerator = numerator.multiply(xm.negate()).mod(BigInteger.valueOf(prime));
                    denominator = denominator.multiply(xj.subtract(xm)).mod(BigInteger.valueOf(prime));
                }
            }
            BigInteger lagrangePolynomial = numerator.multiply(modularInverse(denominator, prime)).mod(BigInteger.valueOf(prime));
            secret = secret.add(yj.multiply(lagrangePolynomial)).mod(BigInteger.valueOf(prime));
        }
        return secret;
    }
}
