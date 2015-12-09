import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    static HashMap<String, Integer> distance = new HashMap<>();

    static Set<String> all=new HashSet<>();

    static String[] names = new String[]{"Tristram", "Faerun", "Snowdin", "Tambi", "AlphaCentauri", "Arbre", "Norrath", "Straylight"};

    static int minRes = Integer.MAX_VALUE;

    public static void main(String[] args) {

        try {
            readInput();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> notInRoute = new ArrayList<>();
        notInRoute.addAll(Arrays.asList(names));
        findShortestPath(new ArrayList<>(), notInRoute);
        System.out.println("" + minRes);
    }

    private static void findShortestPath(ArrayList<String> inRoute, ArrayList<String> notInRoute) {
        if (notInRoute.isEmpty()) {
            measureLength(inRoute);
            return;
        }

        for (int i = 0; i < notInRoute.size(); i++) {
            String s = notInRoute.get(0);
            notInRoute.remove(s);
            inRoute.add(s);
            findShortestPath(inRoute, notInRoute);
            notInRoute.add(s);
            inRoute.remove(s);
        }
    }

    private static void measureLength(ArrayList<String> inRoute) {
        int res = 0;
        all.add(inRoute.toString());
        for (int i = 1; i < inRoute.size(); i++) {
            res += distance.get(inRoute.get(i - 1) + "" + inRoute.get(i));
        }

        if (res < minRes) {
            minRes = res;
        }
    }

    public static void readInput() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));
        ArrayList<String> lines = new ArrayList<>();

        Pattern pattern = Pattern.compile(("(.+) to (.+) = (.+)"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            distance.put(matcher.group(1) + "" + matcher.group(2), Integer.valueOf(matcher.group(3)));
            distance.put(matcher.group(2) + "" + matcher.group(1), Integer.valueOf(matcher.group(3)));
        }
    }
}
