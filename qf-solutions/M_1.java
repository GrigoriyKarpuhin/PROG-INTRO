import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class M {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int num = 0; num < t; num++) {
            int n = scan.nextInt();
            int[] a = new int[n];
            for (int x = 0; x < n; x++) {
                a[x] = scan.nextInt();
            }
            int count = 0;
            for (int y = 0; y < n - 1; y++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int x = n - 1; x > y; x--) {
                    int key = 2 * a[x] - a[y];
                    if (map.containsKey(key)) {
                        count += map.get(key);
                    }
                    if (map.containsKey(a[x])) {
                        map.put(a[x], map.get(a[x]) + 1);
                    } else {
                        map.put(a[x], 1);
                    }
                }
            }
            System.out.println(count);
        }
    }
}