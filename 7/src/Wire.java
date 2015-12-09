/**
 * Created by carl on 12/9/15.
 */
public class Wire {
    String leftWireId;
    String rightWireId;
    Op op = Op.NOOP;
    int value = 0;

    public String toString() {
        return leftWireId + " " + op + " " + rightWireId + " - " + value;
    }
}
