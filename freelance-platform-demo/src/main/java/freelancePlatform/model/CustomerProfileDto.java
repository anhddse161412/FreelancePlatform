package freelancePlatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerProfileDto{
	private Long customerId;
	private String company;
	private Long userId;
}
