package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AddProductRequest {
    private String productName;
    private BigDecimal price;
    private String productCategory;
    @NotNull(message = "image cannot be null")
    private MultipartFile imageUrl;
    private int quantity;
}
