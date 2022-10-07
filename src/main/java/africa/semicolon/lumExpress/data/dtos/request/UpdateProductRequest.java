package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class UpdateProductRequest {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String description;
}
