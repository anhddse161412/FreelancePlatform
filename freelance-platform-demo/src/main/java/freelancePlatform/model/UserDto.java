package freelancePlatform.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto{
	private Long userId;
	private String email;
	private String password;
	private String name;
	private String avatar;
	private MultipartFile avatarFile;
	private String phone;
	private String description="Write something about yourself...";
	private LocalDateTime createdAt;
	private int role;
	private int status;
}
