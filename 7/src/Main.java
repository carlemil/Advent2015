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
            scan = new Scanner(new File("short_input.txt"));
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
                wire.value = Integer.valueOf(opMatcher.group(1));
            } else if (opMatcher.group(3) == null) {
                wire.op = Op.NOT;
                wire.rightWireId = opMatcher.group(2);
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

                wire.leftWireId = opMatcher.group(1);
                wire.rightWireId = opMatcher.group(3);
            }
            System.out.println(wire + " -> " + wireId);

            wires.put(wireId, wire);
        }
        System.out.println(wires.entrySet());
    }
}
