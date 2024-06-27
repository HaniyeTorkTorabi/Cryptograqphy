import java.util.Scanner;

public class Main {
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

}
