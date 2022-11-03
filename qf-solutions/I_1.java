import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int xmax = 2147483647;
        int xmin = -2147483647;
        int ymax = 2147483647;
        int ymin = -2147483647;
        for (int i = 0; i < n; i++) {
            int xi = scan.nextInt();
            int yi = scan.nextInt();
            int hi = scan.nextInt();
            xmax = Math.min(xmax, xi - hi);
            xmin = Math.max(xmin, xi + hi);
            ymax = Math.min(ymax, yi - hi);
            ymin = Math.max(ymin, yi + hi);
        }
        System.out.print((xmax + xmin) / 2 + " " + (ymax + ymin) / 2 + " " + (Math.max(xmin - xmax, ymin - ymax) + 1)/2);
    }
}
