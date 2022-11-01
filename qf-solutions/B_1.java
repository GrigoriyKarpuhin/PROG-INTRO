import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int keys = -710 * 25000;
        while (n > 0) {
            n--;
            keys += 710;
            System.out.println(keys);
        }
    }
}