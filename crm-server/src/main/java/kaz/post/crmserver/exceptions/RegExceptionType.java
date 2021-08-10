package kaz.post.crmserver.exceptions;

import java.util.HashMap;
import java.util.Map;

public enum RegExceptionType {

	LOGIN_RESERVED(
		"loginReserved",
		"Логин бос емес.",
		"Логин занят.",
		"The login is reserved."
	),
	USER_LOGIN_EXIST(
		"userExists",
		"Мұндай логині/ЖСН бар пайдаланушы тіркелген.",
		"Пользователь с таким логином/ИИН уже зарегистрирован.",
		"User with this login/IIN is already registered."),
	ORG_LOGIN_EXIST(
		"orgExists",
		"Мұндай логині бар кәсіпорын тіркелген.",
		"Предприятие с таким логином уже зарегистрировано.",
		"A company with this login has already been registered."),
	BIN_EXIST(
		"binExists",
		"Бұл БСН жүйеде тіркелген, жүйеге қол жетімділікті қалпына келтіріңіз. Егер сіз бұл БСН тіркемеген болсаңыз, онда заңды тұлғаларға қызмет көрсету бөліміне хабарлауыңызды сұраймыз.",
		"БИН уже зарегистрирован, просим восстановить доступ. Если это не Вы регистрировались, то просим обратиться в отдел обслуживания юридических лиц.",
		"BIN is already registered, please restore access. If it is not you who registered, then please contact the Legal Entity Service Department."),
	BIN_NOT_EXIST(
		"binNotExists",
		"БСН тіркелмеген",
		"БИН не зарегистрирован.",
		"BIN is not registered."),
	ORG_BY_LOGIN_NOT_EXIST(
		"orgByLoginNotExists",
		"Көрсетілген логині бар ұйым тіркелмеген.",
		"Организация по указанному логину не зарегистрирована.",
		"The organization with the specified login is not registered."),
	IIN_EXIST(
		"iinExists",
		"ЖСН тіркелді.",
		"ИИН уже зарегистрирован.",
		"IIN is registered"),
	USER_MOBILE_NUMBER_EXIST(
		"mobileNumberExists",
		"Ұялы телефон нөмірі бірдей қолданушы тіркеліп үлгерген.",
		"Пользователь с таким номером мобильного телефона уже зарегистрирован.",
		"A user with the same mobile phone number is already registered."),
	ORG_MOBILE_NUMBER_EXIST(
		"mobileNumberExists",
		"An enterprise with the same mobile phone number is already registered.",
		"Предприятие с таким номером мобильного телефона уже зарегистрировано.",
		"Дәл осындай ұялы телефон нөмірі бар кәсіпорын тіркелген."),
	SMS_SEND_FAIL(
		"smsSendFail",
		"Ешқандай SMS жіберілмеді.",
		"СМС не было послано.",
		"No SMS was sent."),
	INVALID_TOKEN(
		"invalidToken",
		"Токен жарамсыз.",
		"Некорректный токен.",
		"Invalid token."),
	MORE_THAN_ONE(
		"moreThanOneEntry",
		"Бірнеше жазбалар табылды - бірегейлік қажет.",
		"Найдено больше одной записи - требуется уникальность.",
		"More than one record found - uniqueness required."),
	ENTITY_NOT_FOUND(
		"entityNotFound",
		"Жазбалар табылған жоқ.",
		"Записи не найдено.",
		"No entries found."),
	KGD_SERVICE_FAULT(
		"kgdServiceFault",
		"Енгізілген деректер бос немесе жарамсыз.",
		"Введены пустые или неверные данные.",
		"The data entered is blank or invalid."),
	KGD_SERVICE_JSON_FAULT(
		"kgdServiceJsonFault",
		"Сыртқы қызметтен алынған JSON деректерін сериясыздандыру қателігі.",
		"Ошибка десериализации полученных JSON данных от внешнего сервиса.",
		"Error deserializing received JSON data from an external service."),
	IIN_BIN_EMPTY(
		"iinBinEmtpy",
		"ЖСН/БСН бос мәні.",
		"Пустое значение ИИН/БИН.",
		"IIN/BIN empty value."),
	NO_NAME(
		"noNameOrTitle",
		"Сыртқы қызметте аты-жөні (толық аты-жөні) немесе атауы жоқ.",
		"Во внешнем сервисе нет имени (ФИО) или названия.",
		"There is no name (full name) or title in the external service."),
	DATA_NOT_FOUND(
		"dataNotFound",
		"Сыртқы қызметте деректер жоқ.",
		"Во внешнем сервисе данных нет.",
		"There is no data in the external service."
	),
	MORE_THAN_ONE_BY_MOBILE_NUMBER("moreThanOneByMobileNumber",
		"Құрметті клиент, көрсетілген нөмірде бірнеше шот тіркелді. Авторизациялау мүмкін емес, деректерге сәйкес " +
			"түзетулер енгізіңіз. Деректерді түзету үшін «Қазпошта» АҚ филиалына деректерді түзетуге өтінім бере " +
			"отырып хабарласыңыз.",
		"Уважаемый клиент, по указанному номеру зарегистрировано более одного аккаунта. Авторизация невозможна, " +
			"просим провести корректировки по данным. Для корректировки данных просим обратиться в отделение " +
			"АО «Казпочта» с предоставлением заявления о корректировке данных.",
		"Dear customer, more than one account has been registered at the specified number. Authorization is not " +
			"possible, please make corrections according to the data. To correct the data, please contact the " +
			"Kazpost JSC branch with an application for data correction.");

	private Map<MsgParam, String> msgMap = new HashMap<>();

	RegExceptionType(String... msgs) {
		this.msgMap.put(MsgParam.ERROR, msgs[0]);
		this.msgMap.put(MsgParam.KK, msgs[1]);
		this.msgMap.put(MsgParam.RU, msgs[2]);
		this.msgMap.put(MsgParam.EN, msgs[3]);
	}

	public Map<MsgParam, String> getMsgMap() {
		return msgMap;
	}
}
