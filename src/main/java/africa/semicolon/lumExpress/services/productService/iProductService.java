package africa.semicolon.lumExpress.services.productService;

import africa.semicolon.lumExpress.data.dtos.request.AddProductRequest;
import africa.semicolon.lumExpress.data.dtos.request.GetAllElementRequest;
import africa.semicolon.lumExpress.data.dtos.response.AddProductResponse;
import africa.semicolon.lumExpress.data.dtos.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface iProductService {
    AddProductResponse addProduct(AddProductRequest productRequest) throws IOException;
    UpdateProductResponse updateProductDetails(Long productId , JsonPatch patch) throws JsonPatchException;
    Product getProductById(Long id);
    Page<Product> getAllProducts(GetAllElementRequest getAllElementRequest);
    String deleteProduct(Long id);
    Long count();
    void deleteALl();
}
