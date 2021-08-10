package kaz.post.crmserver.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Alibek Kulzhabayev on 14.06.2016.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 2771308706770904146L;

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
