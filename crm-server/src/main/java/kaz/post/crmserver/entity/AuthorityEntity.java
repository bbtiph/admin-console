package kaz.post.crmserver.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "T_AUTHORITY")
public class AuthorityEntity implements Serializable {

	@NotNull
	@Size(min = 0, max = 50)
	@Id
	@Column(length = 50)
	private String name;

	public AuthorityEntity(){}

	public AuthorityEntity(String name) { this.name = name;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AuthorityEntity authority = (AuthorityEntity) o;

		if (name != null ? !name.equals(authority.name) : authority.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"name='" + name + '\'' +
				"}";
	}
}
