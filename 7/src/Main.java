import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {

    /*
    123 -> x
    456 -> y
    x AND y -> d
    x OR y -> e
    x LSHIFT 2 -> f
    y RSHIFT 2 -> g
    NOT x -> h
    NOT y -> i
    */
    private static HashMap<String, Wire> wires = new HashMap<>();

    public static void main(String[] args) {

        Scanner scan = null;
        try {
            scan = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<>();

        Pattern linePattern = Pattern.compile(("([\\w\\s]+) -> (\\w+)"));
        Pattern opPattern = Pattern.compile(("(\\w+)\\s?(\\w+)?\\s?(\\w+)?"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Matcher lineMatcher = linePattern.matcher(line);
            lineMatcher.find();
            String op = lineMatcher.group(1);
            String wireId = lineMatcher.group(2);

            Matcher opMatcher = opPattern.matcher(op);
            opMatcher.find();
            Wire wire = new Wire();
            if (opMatcher.group(2) == null) {
                wire.op = Op.VALUE;
                System.out.println(line);
//                wire.value = Integer.valueOf(opMatcher.group(1));
                if (opMatcher.group(1).matches("\\d+")) {
                    wire.op = Op.VALUE;
                    wire.value = Integer.valueOf(opMatcher.group(1));
                } else {
                    wire.op = Op.FORWARD;
                    wire.lid = opMatcher.group(1);
                }
            } else if (opMatcher.group(3) == null) {
                wire.op = Op.NOT;
                wire.rid = opMatcher.group(2);
            } else {
                switch (opMatcher.group(2)) {
                    case "AND":
                        wire.op = Op.AND;
                        break;
                    case "OR":
                        wire.op = Op.OR;
                        break;
                    case "LSHIFT":
                        wire.op = Op.LSHIFT;
                        break;
                    case "RSHIFT":
                        wire.op = Op.RSHIFT;
                        break;
                }

                wire.lid = opMatcher.group(1);
                wire.rid = opMatcher.group(3);
            }
            System.out.println(wire + " -> " + wireId);

            wires.put(wireId, wire);
        }
        //System.out.println(wires.entrySet());

        System.out.println("a " + solve("a"));
//        System.out.println("d " + solve("d"));
//        System.out.println("e " + solve("e"));
//        System.out.println("f " + solve("f"));
//        System.out.println("g " + solve("g"));
//        System.out.println("h " + solve("h"));
//        System.out.println("i " + solve("i"));
//        System.out.println("x " + solve("x"));
//        System.out.println("y " + solve("y"));
    }

    private static int solve(String key) {
        Wire w = wires.get(key);
        if (w.op == Op.VALUE) {
            return w.value & 0xFFFF;
        } else if (w.op == Op.FORWARD) {
            return solve(w.lid);
        } else if (w.op == Op.NOT) {
            int rv = w.rid.matches("\\d+") ? Integer.valueOf(w.rid) : solve(w.rid);
            return ~rv & 0xFFFF;
        } else {
            int lv = w.lid.matches("\\d+") ? Integer.valueOf(w.lid) : solve(w.lid);
            int rv = w.rid.matches("\\d+") ? Integer.valueOf(w.rid) : solve(w.rid);
            if (w.op == Op.AND) {
                w.value = lv & rv;
            } else if (w.op == Op.OR) {
                w.value = lv | rv;
            } else if (w.op == Op.LSHIFT) {
                w.value = (lv << rv) & 0xFFFF;
            } else if (w.op == Op.RSHIFT) {
                w.value = (lv >> rv) & 0xFFFF;
            } else {
                throw (new RuntimeException("Invalid OP" + w.op));
            }
            w.op = Op.VALUE;
            wires.put(key, w);
            return w.value;
        }
    }
}


















