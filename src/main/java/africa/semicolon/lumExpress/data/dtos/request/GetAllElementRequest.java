package africa.semicolon.lumExpress.data.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllElementRequest {
    private int numberOfProductPerPage;
    private int pageNumber;
}
