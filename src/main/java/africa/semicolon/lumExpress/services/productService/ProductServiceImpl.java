package africa.semicolon.lumExpress.services.productService;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Category;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.data.repositories.ProductRepository;
import africa.semicolon.lumExpress.exceptions.ProductNotFoundException;
import africa.semicolon.lumExpress.services.cloud.iCloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements iProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    private final iCloudService cloudService;

    @Override
    public AddProductResponse addProduct(AddProductRequest productRequest) throws IOException {
        var product = modelMapper.map(productRequest, Product.class);
        product.getCategories().add(Category.valueOf(productRequest.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.uploadImage(productRequest.getImageUrl().getBytes(), ObjectUtils.emptyMap());
        log.info("cloudinary image url::{}", imageUrl);
        product.setImageUrl(imageUrl);
        var savedProduct = productRepository.save(product);

        return buildAddProductResponse(savedProduct);
    }

    private AddProductResponse buildAddProductResponse(Product savedProduct) {
        return AddProductResponse.builder()
                .message("product add success")
                .productId(savedProduct.getId())
                .productName(savedProduct.getName())
                .statusCode(201)
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch) throws JsonPatchException, ProductNotFoundException {
//        find product
        var foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(String.format("product with id %d not found", productId)));
        var updatedProduct = applyPatchToProduct(patch, foundProduct);
//        save found product
        var savedProduct = productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }

    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct) throws JsonPatchException {
        //        convert found product to json node
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        //        apply patch to productNode
        JsonNode patchedProductNode;
        try {
            patchedProductNode = patch.apply(productNode);
        //        convert patchedNode to product object
            var updatedProduct = objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode), Product.class);
            return updatedProduct;
        }catch (IOException | JsonPatchException exception){
            exception.printStackTrace();
        }
        return null;
      }

    private UpdateProductResponse buildUpdateResponse(Product savedProduct) {
        return UpdateProductResponse
                .builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("update successful")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("product %d not found", id)));
    }

    @Override
    public Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageSpecs = PageRequest.of(
                getAllItemsRequest.getPageNumber() - 1, getAllItemsRequest.getNumberOfProductPerPage());
        return productRepository.findAll(pageSpecs);
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "product deleted successfully";
    }

    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public void deleteALl() {
        productRepository.deleteAll();
    }
}
