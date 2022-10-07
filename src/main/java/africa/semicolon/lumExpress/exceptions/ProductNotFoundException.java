package africa.semicolon.lumExpress.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException (String message){
        super(message);
    }
}
