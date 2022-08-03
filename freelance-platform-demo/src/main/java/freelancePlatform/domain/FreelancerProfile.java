package freelancePlatform.domain;

import java.io.Serializable;
import java.util.HashSet;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "freelancer_profiles")
public class FreelancerProfile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long freelancerId;
	
	@ManyToMany
	@JoinTable(
	        name = "Skill", 
	        joinColumns = { @JoinColumn(name = "freelancer_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "tech_id") }
	    )
	Set<Tech> techs = new HashSet<>();
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
