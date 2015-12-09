import java.util.HashMap;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    static HashMap<String, Integer> map = new HashMap();

    public static void main(String[] args) {
        map.put("0,0", 2);
        String s = Input.data;
        int ns = 0, ew = 0;
        for (int i = 0; i < s.length(); i+=2) {
            switch (s.charAt(i)) {
                case '<':
                    ew--;
                    break;
                case '>':
                    ew++;
                    break;
                case '^':
                    ns++;
                    break;
                case 'v':
                    ns--;
                    break;
            }
            Integer v = map.get(ew + "," + ns);
            if (v != null) {
                map.put(ew + "," + ns, v + 1);
            } else {
                map.put(ew + "," + ns, 1);
            }
        }
        ns = 0;
        ew = 0;
        for (int i = 1; i < s.length(); i+=2) {
            switch (s.charAt(i)) {
                case '<':
                    ew--;
                    break;
                case '>':
                    ew++;
                    break;
                case '^':
                    ns++;
                    break;
                case 'v':
                    ns--;
                    break;
            }
            Integer v = map.get(ew + "," + ns);
            if (v != null) {
                map.put(ew + "," + ns, v + 1);
            } else {
                map.put(ew + "," + ns, 1);
            }
        }
        System.out.println(map.size());
    }
}
