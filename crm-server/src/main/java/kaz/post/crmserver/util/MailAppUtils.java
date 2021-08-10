package kaz.post.crmserver.util;

import com.unboundid.util.json.JSONObject;
import com.unboundid.util.json.JSONValue;
import kaz.post.crmserver.dto.Dto;
import kaz.post.crmserver.entity.reservation.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MailAppUtils {
	public static <T> boolean isNonNullOrEmpty(T obj) {
		if (Objects.nonNull(obj)) {
			if (obj instanceof String && !((String) obj).isEmpty()) {
				return true;
			}
			return true;
		}
		return false;
	}

	public static <T> boolean isNullOrEmpty(T obj) {
		if (Objects.isNull(obj)) {
			return true;
		} else return obj instanceof String && ((String) obj).isEmpty();
	}

	public static boolean isLoginForbidden(String login) {
		List<String> forbiddenLogins = MailAppConstants.forbiddenLogins;
		login = login.toLowerCase();
		for (String forbiddenLogin: forbiddenLogins) {
			if (login.contains(forbiddenLogin)) {
				return true;
			}
		}
		return false;
	}

	public static ResponseEntity<String> checkForbiddenLoginAndEmptyMobileNumber(Dto dto) {
		if (MailAppUtils.isLoginForbidden(dto.getLogin())) {
			return new ResponseEntity<>("{\"error\":\"forbiddenLogin\", " +
				"\"messageKk\":\"Жүйеге кіруге тыйым салынған сөздердің бірі кіреді.\", " +
				"\"messageRu\":\"Логин содержит одно из запрещенных слов для регистрации в качестве логина.\", " +
				"\"messageEn\":\"Login contain one of the forbidden word for registration as login.\", " +
				"\"status\":\"error\"}", HttpStatus.FORBIDDEN);
		}
		if (MailAppUtils.isNullOrEmpty(dto.getMobileNumber())) {
			return new ResponseEntity<>("{\"error\":\"mobileNumberRequired\", " +
				"\"messageKk\":\"Ұялы телефон нөмірі қажет.\", " +
				"\"messageRu\":\"Требуется номер мобильного телефона.\", " +
				"\"messageEn\":\"Mobile phone number required.\", " +
				"\"status\":\"error\"}", HttpStatus.NOT_ACCEPTABLE);
		}
		return null;
	}

	public static String createJsonObject(Dto dto) {
		JSONObject jsonObject = new JSONObject((Map<String, JSONValue>) dto);
		return jsonObject.toString();
	}

	public static UserEntity extractUserData(Map<String, Object> result, Optional<UserEntity> optionalUser) {
		UserEntity user = optionalUser.get();
		extractUserBasicData(result, user);
		result.put("isJur", false);
		return user;
	}

	public static void extractUserBasicData(Map<String, Object> result, UserEntity user) {
		result.put("login", user.getLogin());
		result.put("iin", user.getIin());
		result.put("lastName", user.getLastName());
		result.put("firstName", user.getFirstName());
		result.put("fatherName", user.getMiddleName());
		result.put("email", user.getLogin() + "@post.kz");
		result.put("phone", user.getMobileNumber());
	}
}
