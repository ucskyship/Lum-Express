package africa.semicolon.lumExpress.services.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryCloudServiceImpl implements iCloudService{
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(byte[] imageBytes, Map<?, ?> map) throws IOException {
        var uploadResponse = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

        return uploadResponse.get("url").toString();
    }
}
