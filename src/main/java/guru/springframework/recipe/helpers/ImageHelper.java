package guru.springframework.recipe.helpers;

public interface ImageHelper {
    Byte[] getImageBytes(String imageUrl);
    Byte[] toByteArray(byte[] array);
    byte[] toByteArray(Byte[] array);
    String toBase64(byte[] array);
    String toBase64(Byte[] array);
}
