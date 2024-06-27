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

    public static BigInteger modularca(BigInteger a, int prime) {
        return a.modInverse(BigInteger.valueOf(prime));
    }
}
