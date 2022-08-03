package freelancePlatform.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	private String email;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String name;
	
	@Column(length = 200)
	private String avatar;
	
	@Column(columnDefinition = "nvarchar(20) not null")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(1500) default 'Write a description about yourself...'")
	private String description;
	
	@Column(nullable = false)
	private int role;
	
	@Column(nullable = false)
	private int status;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@OneToOne(mappedBy = "user")
    private CustomerProfile customerProfile;
	
	@OneToOne(mappedBy = "user")
    private FreelancerProfile freelancerProfile;
}
