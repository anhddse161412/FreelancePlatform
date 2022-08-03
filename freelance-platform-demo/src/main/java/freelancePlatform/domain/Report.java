package freelancePlatform.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "reports")
public class Report implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	private String title;
	
	@Column(columnDefinition = "nvarchar(1500)")
	private String description;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String fileName;
	
	@Column(columnDefinition = "nvarchar(200)")
	private String fileUrl;
	
	@Column(nullable = false)
	private int status;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "freelancer_id")
	private User freelancer;
	
	@OneToOne
    @JoinColumn(name = "job_id")
    private Job job;
	
}
