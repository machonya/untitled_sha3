package sha3;

import org.bouncycastle.util.encoders.Hex;
import pl.thewalkingcode.sha3.Sha3;
import pl.thewalkingcode.sha3.Type;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class FileToSha3 {
    public static ArrayList<String> addKeysToList() {
        try {
            File folder = new File("/Users/mihas/Downloads/task2");
            Type type = Type.SHA3_256;
            Sha3 sha3 = new Sha3(type);
            File[] listOfFiles = folder.listFiles();
            StringBuilder builder;

            ArrayList<String> keys = new ArrayList<>();
            StringBuilder s = new StringBuilder();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        InputStream inputStream = new FileInputStream(file);
                        byte[] encode = sha3.encode(inputStream);
                        builder = new StringBuilder(Hex.toHexString(encode));
                        keys.add(builder.toString());
                        inputStream.close();
                    }
                }
                return keys;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<String> keys;
        keys = addKeysToList();
        assert keys != null;
        Collections.sort(keys);
        String keysString = String.join("", keys);
        System.out.println(keysString);
        String myEmail = "mikhail.klimau@gmail.com";
        String result = keysString + myEmail;
        Type type = Type.SHA3_256;
        Sha3 sha3 = new Sha3(type);
        byte[] encode = sha3.encode(result.getBytes());
        StringBuilder resultKey;
        resultKey = new StringBuilder(Hex.toHexString(encode));
        System.out.println(resultKey);


    }
}
