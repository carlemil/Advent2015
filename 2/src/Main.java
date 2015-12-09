import java.util.HashMap;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    public static void main(String[] args) {
        long sum = 0;
        for (String s : Input.data.split(",")) {
            String[] sa = s.split("x");
            int a = Integer.valueOf(sa[0]);
            int b = Integer.valueOf(sa[1]);
            int c = Integer.valueOf(sa[2]);
            int ab = a * b * 2;
            int bc = b * c * 2;
            int ca = c * a * 2;
            int m = Math.min(Math.min(ab, bc), ca);

            sum += ab + bc + ca + m / 2;
        }
        System.out.println(sum);

        sum = 0;
        for (String s : Input.data.split(",")) {
            String[] sa = s.split("x");
            int a = Integer.valueOf(sa[0]);
            int b = Integer.valueOf(sa[1]);
            int c = Integer.valueOf(sa[2]);
            int ab = (a + b) * 2;
            int bc = (b + c) * 2;
            int ca = (c + a) * 2;
            int m = Math.min(Math.min(ab, bc), ca);

            sum += a*b*c + m;
        }
        System.out.println(sum);
    }
}
