package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllElementRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.services.productService.iProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {
    @Autowired
    private iProductService productService;
    AddProductRequest request;
    AddProductResponse response;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/ucj/Documents/SEMICOLON/IdeaProjects/LumExpress/src/test/images/peak.jpeg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));

        request = buildAddProductRequest(file);
        response = productService.addProduct(request);
    }

    @AfterEach
    void tearDown() {
        productService.deleteALl();
    }

    @Test
    void addProductTest() {
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isGreaterThan(0L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

//    @Test
//    void updateProductDetailsTest() {
//        ObjectMapper mapper = new ObjectMapper();
//        UpdateProductResponse updateRes=null;
//        try {
//            JsonNode value = mapper.readTree("50.0");
//            JsonPatch patch = new JsonPatch(List.of(new ReplaceOperation(new JsonPointer("/price"), value)));
//            updateRes = productService.updateProductDetails(1L, patch);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        log.info("updated product:: {}", updateRes);
//        assertThat(response).isNotNull();
//        assertThat(updateRes.getStatusCode()).isEqualTo(200);
//        assertThat(productService.getProductById(1L).getPrice().compareTo(BigDecimal.valueOf(50.00))).isEqualTo(0);
////        assertThat(productService.getProductById(1L).getName()).isEqualTo("eggs");
//    }

    @Test
    void getProductByIdTest() {
        var foundProduct = productService.getProductById(response.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(response.getProductId());
    }

    @Test
    void getAllProductsTest() {
        var getItemRequest = buildGetAllElementRequest();
        var productPage = productService.getAllProducts(getItemRequest);
        log.info("page contents::{}", productPage);
        assertEquals(1L, productService.count());
    }

    @Test
    void deleteProductTest() {
        var foundProduct = productService.getProductById(response.getProductId());
        productService.deleteProduct(foundProduct.getId());
        assertThat(productService.count()).isEqualTo(0L);
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

    private GetAllElementRequest buildGetAllElementRequest() {
        return GetAllElementRequest
                .builder()
                .numberOfProductPerPage(8)
                .pageNumber(1)
                .build();
    }
}
