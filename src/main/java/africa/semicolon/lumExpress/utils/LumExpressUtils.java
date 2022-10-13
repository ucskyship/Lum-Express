package africa.semicolon.lumExpress.utils;

import java.security.SecureRandom;

public class LumExpressUtils {
    public static String generateToken(){
        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(10000, 89999);
        return String.valueOf(number);
    }

    public static String getMockCloudinaryImageUrl(){
        return "https://ww.cloudinary.com/abcd/";
    }
}
