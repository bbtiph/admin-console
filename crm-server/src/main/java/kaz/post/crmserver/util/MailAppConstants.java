package kaz.post.crmserver.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MailAppConstants {
	public static final String KOD_ACTIVATION = "kod.activation";
	public static final String DOWNLOAD_APP_WITH_TOKEN = "down.app.with.token";
	public static final String DOWNLOAD_APP_WITHOUT_TOKEN =  "down.app.without.token";
	public static final String TRANSLITERATION_PROPERTIES =  "translitaration";
	public static final String MOBILE_TOKEN =  "\n #28G3GYKwDEh";
	public static final String KOD_CONFIRMED =  "kod.confirmed";
	public static final String NOT_SAY =  "not.say";
	public static final String SMART_BOT =  "smart.bot";
	public static final String INVITE_EMPLOYEE =  "invite.employee";
	public static final String IIN_BIN_REGEX = "\\d{12}";
	public static final List<String> forbiddenLogins
		= Collections.unmodifiableList(Arrays.asList(
			"admin", "null", "post", "kazpost", "postkaz", "postkz", "kzpost", "god", "allah", "jesus", "budda",
			"buddha", "satan", "heck", "evil", "devil"));
}
