package africa.semicolon.lumExpress.services.cloud;

import africa.semicolon.lumExpress.data.dtos.response.ImageUrl;

import java.io.IOException;
import java.util.Map;

public interface iCloudService {
    String uploadImage(byte[] imageBytes, Map<?,?> map) throws IOException;
}
