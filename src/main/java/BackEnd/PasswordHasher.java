package BackEnd;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordHasher {
 public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(input.getBytes());

            // Convert the bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   
}