import org.mitre.secretsharing.Part;
import org.mitre.secretsharing.Secrets;
import org.mitre.secretsharing.codec.PartFormats;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by Vitalik on 18.12.2017.
 */

class Example {

    public static void main(String[] args) {
        final Random random = new Random();
        String str = "42000000";
        byte[] secret = str.getBytes(Charset.forName("UTF-8"));
        Part[] parts = Secrets.split(secret,5,3,random);

        for (int i = 0; i < 5; i++) {
            String formatted = PartFormats.currentStringFormat().format(parts[i]);
            System.out.println(formatted);
        }
        byte[] secretn = Secrets.join(parts);
        String ready = new String(secretn);
        System.out.println(ready);

    }
}