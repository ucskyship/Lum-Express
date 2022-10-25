package africa.semicolon.lumExpress.services.cartService;

import africa.semicolon.lumExpress.data.dtos.request.CartRequest;
import africa.semicolon.lumExpress.data.dtos.response.CartResponse;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.data.models.Item;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.data.repositories.CartRepository;
import africa.semicolon.lumExpress.exceptions.CartNotFoundException;
import africa.semicolon.lumExpress.exceptions.ProductNotFoundException;
import africa.semicolon.lumExpress.services.productService.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements iCartService{
    private final CartRepository cartRepository;
    private final ProductServiceImpl productService;

    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException {
//        find the cart
//        Add cart to customer
//        find the product
//        build item from product
//        add builtItem to cart
//        save cart
        var cart = cartRepository.findById(cartRequest.getCartId()).orElseThrow(
                () -> new CartNotFoundException(String.format("cart with id %d, not found", cartRequest.getCartId())));
        var foundProduct = productService.getProductById(cartRequest.getProductId());
        var item = buildCartItem(foundProduct);
        cart.getItems().add(item);
        var cartToSave = updateCartSubTotal(cart, item);
        Cart savedCart = cartRepository.save(cartToSave);
        return CartResponse.builder()
                .message("item added to cart")
                .cart(savedCart)
                .build();
    }

    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    private Item buildCartItem(Product foundProduct) {
        return Item.builder()
                .product(foundProduct)
                .quantity(1)
                .build();
    }

    private Cart updateCartSubTotal(Cart cart, Item item){
        cart.setSubTotal(cart.getSubTotal().add(item.getProduct().getPrice()));
        return cart;
    }
}
