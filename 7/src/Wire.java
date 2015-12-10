/**
 * Created by carl on 12/9/15.
 */
public class Wire {
    String lid;
    String rid;
    Op op = Op.NOOP;
    Integer value = null;

    public String toString() {
        return lid + " " + op + " " + rid + " - " + value;
    }
}
