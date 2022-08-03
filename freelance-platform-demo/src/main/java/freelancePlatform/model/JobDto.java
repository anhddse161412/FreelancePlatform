package freelancePlatform.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto implements Serializable {

	private Long jobId;
	private String title;
	private String scope;
	private String duration;
	private double budget;
	private String description="Write something about your job post...";
	private LocalDateTime createdAt;
	private int status;
	private Long userId;
	private Long fieldId;
	private List<String> techs;
}
