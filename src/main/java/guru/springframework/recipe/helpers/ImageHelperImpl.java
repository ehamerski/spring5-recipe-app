package guru.springframework.recipe.helpers;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

@Component
public class ImageHelperImpl implements ImageHelper {
    public Byte[] getImageBytes(String imageUrl) {
        Byte[] bytes = new Byte[0];

        URL url = null;
        URLConnection urlConnection = null;

        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            url = new URL(imageUrl);
            urlConnection = url.openConnection();
            urlConnection.addRequestProperty("user-agent", "chrome");

            is = urlConnection.getInputStream();

            byte[] buffer = new byte[4096];
            int n;

            while ((n = is.read(buffer)) > 0) {
                baos.write(buffer, 0, n);
            }

            bytes = toByteArray(baos.toByteArray());
        } catch(Exception e) {
            System.out.println("image could not be load");
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch(Exception e) {
                    System.out.println("image input stream could not be closed");
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }

    public Byte[] toByteArray(byte[] array) {
        Byte[] byteArray = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            byteArray[i] = array[i];
        }
        return byteArray;
    }

    public byte[] toByteArray(Byte[] array) {
        byte[] byteArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            byteArray[i] = array[i];
        }
        return byteArray;
    }

    public String toBase64(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    public String toBase64(Byte[] array) {
        return toBase64(toByteArray(array));
    }
}
