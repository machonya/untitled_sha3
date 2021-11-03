package rps;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGeneration {

    public static String randomKeyGeneration() {
        SecureRandom random = new SecureRandom();
        byte[] aesKey = new byte[16];
        random.nextBytes(aesKey);
        return Hex.toHexString(aesKey);


    }
    public static String calculateHmac(String value, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signKey);
        byte[] resultHmac = mac.doFinal(value.getBytes());
        return Hex.toHexString(resultHmac);
    }

}
