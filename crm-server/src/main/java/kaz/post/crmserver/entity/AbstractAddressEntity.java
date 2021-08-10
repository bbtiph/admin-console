package kaz.post.crmserver.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractAddressEntity extends AbstractEntity implements Serializable {

	@Column(name = "rka")
	private String rka;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "old_postcode")
	private String oldPostcode;

	@Column(name = "ats")
	private String ats;

	@Column(name = "region")
	private String region;

	@Column(name = "geonim")
	private String geonim;

	@Column(name = "building")
	private String building;

	@Column(name = "flat")
	private String flat;

	public String getRka() {
		return rka;
	}

	public void setRka(String rka) {
		this.rka = rka;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAts() {
		return ats;
	}

	public void setAts(String ats) {
		this.ats = ats;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getGeonim() {
		return geonim;
	}

	public void setGeonim(String geonim) {
		this.geonim = geonim;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getOldPostcode() {
		return oldPostcode;
	}

	public void setOldPostcode(String oldPostcode) {
		this.oldPostcode = oldPostcode;
	}
}
