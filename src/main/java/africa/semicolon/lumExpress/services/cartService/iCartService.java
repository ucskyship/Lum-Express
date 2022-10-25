package africa.semicolon.lumExpress.services.cartService;

import africa.semicolon.lumExpress.data.dtos.request.CartRequest;
import africa.semicolon.lumExpress.data.dtos.response.CartResponse;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.exceptions.CartNotFoundException;
import africa.semicolon.lumExpress.exceptions.ProductNotFoundException;

import java.util.List;

public interface iCartService {
    CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException;
    List<Cart> getCartList();
    Cart save(Cart cart);
}
