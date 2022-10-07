package africa.semicolon.lumExpress.data.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductResponse {
    private String message;
    private Long productId;
    private String productName;
    private int statusCode;
    private BigDecimal price;
}
