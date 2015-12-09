import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    static int a[][] = new int[1000][1000];

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                a[i][j] = 0;
            }
        }

        Scanner scan = null;
        try {
            scan = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<>();

        Pattern pattern = Pattern.compile(("(.+) (\\d+),(\\d+) through (\\d+),(\\d+)"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String op = matcher.group(1);
            int x1 = Integer.valueOf(matcher.group(2));
            int y1 = Integer.valueOf(matcher.group(3));
            int x2 = Integer.valueOf(matcher.group(4));
            int y2 = Integer.valueOf(matcher.group(5));

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (op.contains("on")) {
                        a[x][y]++;
                    } else if (op.contains("off") && a[x][y] > 0) {
                        a[x][y]--;
                    } else if (op.contains("toggle")){
                        a[x][y] += 2;
                    }
                }
            }
        }

        long c = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                c += a[i][j];
            }
        }
        System.out.println(c);
    }
}
