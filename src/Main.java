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
}
