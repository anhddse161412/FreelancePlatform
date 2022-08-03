package freelancePlatform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import freelancePlatform.service.impl.FirebaseImageService;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
	private String bucketName;

    private String imageUrl;
}
