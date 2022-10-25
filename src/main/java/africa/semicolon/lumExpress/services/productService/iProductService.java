package africa.semicolon.lumExpress.services.productService;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.exceptions.ProductNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface iProductService {
    AddProductResponse addProduct(AddProductRequest productRequest) throws IOException;
    UpdateProductResponse updateProductDetails(Long productId , JsonPatch patch) throws JsonPatchException, ProductNotFoundException;
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest);
    String deleteProduct(Long id);
    Long count();
    void deleteALl();
}
