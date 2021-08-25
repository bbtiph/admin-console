package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * An user Address.
 */
@Entity
@Table(name = "T_USER_ADDRESS")
@SequenceGenerator(name = "id_gen", sequenceName = "user_address_seq", initialValue = 60000000, allocationSize = 1)
public class UserAddressEntity extends AbstractAddressEntity {


//	@Column(name = "user_id")
//	private Long userId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserEntity user;

	//@Column(name = "user_login", length = 50)
	//private String userLogin;

	@JsonIgnore
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	//public Long getUserId() {
	//	return userId;
	//}

//	public void setUserId(Long userId) {
	//	this.userId = userId;
//	}

//	public String getUserLogin() {
//		return userLogin;
//	}
//
//	public void setUserLogin(String userLogin) {
//		this.userLogin = userLogin;
//	}
}
