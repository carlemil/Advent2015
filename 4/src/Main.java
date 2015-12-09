import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {
    public static void main(String[] args) {
        String secret = "ckczppom";

        try {
            int i = 0;

            while (true) {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update((secret + i).getBytes());
                byte[] b = md5.digest();
                String ht = new BigInteger(1, b).toString(16);
                if (ht.length() + 6 == 32) {
                    System.out.println("HT: " + ht);
                    System.out.println("i: " + i);
                    break;
                }
                i++;
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
