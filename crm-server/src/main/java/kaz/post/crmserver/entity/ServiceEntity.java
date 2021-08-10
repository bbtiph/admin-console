package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Rustem Yessekeyev on 17.02.2016.
 */
@Entity
@Table(name = "T_SERVICE")
public class ServiceEntity implements Serializable {

	@Id
	@NotNull
	@Column
	private String name;

	@Column(name = "name_ru")
	private String nameRu;

	@Column(name = "name_kz")
	private String nameKz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public String getNameKz() {
		return nameKz;
	}

	public void setNameKz(String nameKz) {
		this.nameKz = nameKz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ServiceEntity service = (ServiceEntity) o;

		if (!name.equals(service.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Service{" +
			"name='" + name + '\'' +
			", nameRu='" + nameRu + '\'' +
			", nameKz='" + nameKz + '\'' +
			'}';
	}
}
