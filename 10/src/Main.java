/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    private static String input = "1321131112";

    public static void main(String[] args) {
        StringBuilder read = new StringBuilder(input);
        StringBuilder write = new StringBuilder();

        for (int i = 1; i <= 50; i++) {
            System.out.println(i + " - " + read);

            char prev = read.charAt(0);
            int j = 1;
            int n = 1;
            do {
                char curr = read.charAt(j);
                if (prev == curr) {
                    n++;
                } else {
                    write.append(n);
                    write.append(prev);
                    n = 1;
                    prev = curr;
                }

                j++;
            } while (j < read.length());
            write.append(n);
            write.append(prev);
            read = new StringBuilder(write);
            write = new StringBuilder();
        }
        System.out.println(read.length());

    }
}
