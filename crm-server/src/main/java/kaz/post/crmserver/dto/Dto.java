package kaz.post.crmserver.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public abstract class Dto {

	@Pattern(regexp = "^[A-Za-z0-9\\.\\-\\_]*$")
	@Size(min = 5, max = 50, message = "Логин ұзындығы 5-тен 50 таңбаға дейін болуы керек./Длина логина должна быть между 5 и 50 символами./Login length must be between 5 and 50 characters.")
	@NotNull(message = "Логин нөл болуы мүмкін емес./Логин не может быть нулевым./Login can't be null.")
	@NotEmpty(message = "Логин бос болмауы керек./Логин не может быть пустым./Login can't be empty.")
	@NotBlank(message = "Логин сөзде бос орын болмауы керек./Логин не должен состоять из знаков пробела./Login must not contain spaces.")
	private String login;

	@Size(min = 10, max = 50, message = "Ұялы телефонның нөмірі кемінде 11 цифрдан тұруы керек./Длина номера мобильного телефона должна быть не менее 11 цифр./Mobile phone number must be at least 11 digits long.")
	@NotNull(message = "Ұялы нөмір нөл болмауы керек./Номер мобильного телефона не может быть нулевым./Mobile number can't be null.")
	@NotEmpty(message = "Ұялы нөмір бос болмауы керек./Номер мобильного телефона не может быть пустым./Mobile number can't be empty")
	@NotBlank(message = "Ұялы нөмір бос орын болмауы керек./Номер мобильного телефона не должен состоять из знаков пробела./Mobile number must not contain spaces.")
	private String mobileNumber;

	private String password;

	@Size(min = 12, max = 12, message = "ЖСН ұзындығы 12 символдан тұруы керек./Длина ИИН должна составлять 12 символов./Length of IIN must be 12 symbols.")
	private String iin;

	public Dto() {
	}

	public Dto(String login, String iin, String mobileNumber, String password) {
		this.login = login;
		this.iin = iin;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
}
