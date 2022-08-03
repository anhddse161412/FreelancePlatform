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
@Table(name = "jobs")
public class Job implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	private String title;
	
	@Column(columnDefinition = "nvarchar(20) not null")
	private String scope;
	
	@Column(columnDefinition = "nvarchar(30) not null")
	private String duration;
	
	@Column(nullable = false)
	private double budget;
	
	@Column(columnDefinition = "nvarchar(1500) default 'Write a description for your job post...'")
	private String description;

	@Column(nullable = false)
	private int status;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
    @JoinColumn(name = "field_id")
	Field field;
	
	@ManyToMany
	@JoinTable(
	        name = "Tag", 
	        joinColumns = { @JoinColumn(name = "job_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "tech_id") }
	    )
	Set<Tech> techs = new HashSet<>();
}
