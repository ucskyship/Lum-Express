package africa.semicolon.lumExpress.services.cartService;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.CartRequest;
import africa.semicolon.lumExpress.data.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.models.Cart;
import africa.semicolon.lumExpress.services.customerService.CustomerServiceImpl;
import africa.semicolon.lumExpress.services.productService.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CartServiceImplTest {
    @Autowired
    private iCartService cartService;
    @Autowired
    private ProductServiceImpl productService;
    AddProductRequest request;
    @Autowired
    private CustomerServiceImpl customerService;

    private Cart savedCart;

    @BeforeEach
    void setUp() throws IOException {
        Cart cart = new Cart();
        savedCart = cartService.save(cart);

        CustomerRegistrationRequest registerRequest = CustomerRegistrationRequest
                .builder()
                .email("fanalas844@inkmoto.com")
                .password("123456")
                .country("Nigeria")
                .build();
        customerService.register(registerRequest);

        Path path = Paths.get("/home/ucj/Documents/SEMICOLON/IdeaProjects/LumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));

        request = buildAddProductRequest(file);
        productService.addProduct(request);
    }

    @Test
    @DisplayName("test that product can be added to cart")
    void addProductToCartTest() {
        CartRequest cartRequest = CartRequest
                .builder()
                .cartId(savedCart.getId())
                .productId(productService.getAllProducts(new GetAllItemsRequest(10, 1))
                        .getContent().get(productService.getAllProducts(new GetAllItemsRequest(10, 1))
                                .getNumberOfElements()-1).getId())
                .build();
        var cartResponse = cartService.addProductToCart(cartRequest);
        log.info("{}", cartResponse);
        assertThat(cartResponse).isNotNull();
        var cartSubTotal = cartResponse.getCart().getSubTotal();
        assertThat(cartSubTotal).isEqualTo(BigDecimal.valueOf(39.89));
    }

    private AddProductRequest buildAddProductRequest(MultipartFile file) {
        return AddProductRequest
                .builder()
                .productName("Milk")
                .productCategory("BEVERAGES")
                .price(BigDecimal.valueOf(39.89))
                .quantity(10)
                .imageUrl(file)
                .build();
    }
}
