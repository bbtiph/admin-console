package kaz.post.crmserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@MappedSuperclass
public abstract class AbstractUserEntity extends AbstractAuditingEntity {


	@Size(min = 1, max = 50)
	@NotNull(message = "Логин нөл болуы мүмкін емес./Логин не может быть нулевым./Login can't be null.")
	@NotEmpty(message = "Логин 5-тен 50-ге дейін белгіден тұруы керек./Логин должен содержать от 5 до 50 символов./Login must contain from 5 to 50 symbols.")
	@NotBlank(message = "Логин бос болмауы керек./Логин не может быть пустым./Login can't be blank.")
	@Pattern(regexp = "^[a-z0-9\\.\\-\\_]*$")
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@JsonIgnore
	@Size(min = 8, max = 100, message = "Құпия сөз кемінде 8 таңбадан тұруы керек./Пароль должен быть не менее 8 символов./Password must be at least 8 characters.")
	@NotNull(message = "Құпия сөз нөл болуы мүмкін емес./Пароль не может быть нулевым./Password can't be null")
	@NotEmpty(message = "Құпия сөз бос болмауы керек./Пароль не может быть пустым./Password can't be empty.")
	@NotBlank(message = "Құпия сөзде бос орын болмауы керек./Пароль не должен состоять из знаков пробела./The password must not contain spaces.")
	@Column(length = 100)
	private String password;

	@Size(min = 10, max = 50, message = "Ұялы телефон нөмірі кемінде 10 цифрдан тұруы керек./Длина номера мобильного телефона должна быть не менее 10 цифр./The mobile phone number must be at least 10 digits long.")
	@Column(name = "mobile_number", length = 50)
	private String mobileNumber;

	@Column
	private String iosToken;

	@Column
	private String androidToken;
}


