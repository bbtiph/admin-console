package kaz.post.crmserver.service;

import com.google.common.collect.Lists;
import kaz.post.crmserver.dto.KgdDTO;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.AuthorityEntity;
import kaz.post.crmserver.entity.RegistrationCacheEntity;
import kaz.post.crmserver.entity.ReservedLoginEntity;
import kaz.post.crmserver.entity.UserCacheEntity;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.exceptions.MsgParam;
import kaz.post.crmserver.exceptions.RegExceptionType;
import kaz.post.crmserver.exceptions.RegistrationException;
import kaz.post.crmserver.repositories.mail.*;
import kaz.post.crmserver.util.MailAppUtils;
import kaz.post.crmserver.util.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservedLoginRepository reservedLoginRepository;
    @Autowired
    private UserCacheRepository userCacheRepository;
    @Autowired
    private Environment env;
    @Autowired
    private KgdService kgdService;
    @Inject
    private RegistrationCacheRepository registrationCacheRepository;
    @Inject
    private AuthorityRepository authorityRepository;

    public void createUser(UserDTO userDTO, String gaCookie, String device, String type) throws RegistrationException {
        Optional<ReservedLoginEntity> reservedLoginOpt = reservedLoginRepository.findOneByLogin(userDTO.getLogin());
        Optional<UserEntity> userByLogin = userRepository.findOneByLogin(userDTO.getLogin());
        String mobileNumber = userDTO.getMobileNumber();
        Optional<UserEntity> userByMobileNumber = userRepository.findFirstByMobileNumber(mobileNumber);
        Optional<UserEntity> userByShortMobileNumber = null;
        Optional<UserEntity> userByIin = null;

        if (reservedLoginOpt.isPresent()) {
            throw new RegistrationException(RegExceptionType.LOGIN_RESERVED);
        }
        if (userByLogin.isPresent()) {
            throw new RegistrationException(RegExceptionType.USER_LOGIN_EXIST);
        }
        if (mobileNumber.length() == 11) {
            userByShortMobileNumber = userRepository.findFirstByMobileNumber(mobileNumber.substring(1));
        }
        if (userByMobileNumber.isPresent() || Objects.nonNull(userByShortMobileNumber)
                && userByShortMobileNumber.isPresent()) {
            throw new RegistrationException(RegExceptionType.USER_MOBILE_NUMBER_EXIST);
        }
        if (Objects.nonNull(userDTO.getIin()) && !userDTO.getIin().isEmpty()) {
            userByIin = userRepository.findOneByIin(userDTO.getIin());
            if (userByIin.isPresent()) {
                throw new RegistrationException(RegExceptionType.USER_LOGIN_EXIST);
            }
        }

        UserCacheEntity userCache;
        if (StringUtils.isBlank(userDTO.getToken())) {
            userCache = new UserCacheEntity();
        } else {
            Optional<UserCacheEntity> optionalUserCache = userCacheRepository.findOneByInviteToken(userDTO.getToken());
            if (optionalUserCache.isPresent()) {
                userCache = optionalUserCache.get();
            } else {
                throw new RegistrationException(RegExceptionType.INVALID_TOKEN);
            }
        }
        userCache.setLogin(userDTO.getLogin());
        userCache.setMobileNumber(userDTO.getMobileNumber());
        if (userDTO.getRegistrationCacheUid() != null) {
            Optional<RegistrationCacheEntity> registrationCache = registrationCacheRepository.findOneByUid(userDTO.getRegistrationCacheUid());
            if (registrationCache.isPresent()) {
                if (registrationCache.get().getIin() != null)
                    userDTO.setIin(registrationCache.get().getIin());
                if (registrationCache.get().getLastName() != null)
                    userDTO.setLastName(registrationCache.get().getLastName());
                if (registrationCache.get().getFirstName() != null)
                    userDTO.setFirstName(registrationCache.get().getFirstName());
                if (registrationCache.get().getBirthDate() != null)
                    userDTO.setBirthDate(registrationCache.get().getBirthDate());
            }
        }
        else if (Objects.nonNull(userDTO.getIin()) && !userDTO.getIin().isEmpty()) {
            KgdDTO kgdDTO = kgdService.requestKgdData(userDTO.getIin());
            String[] fioNames;
            if (Objects.nonNull(kgdDTO) && kgdDTO.getExist()) {
                if (Objects.nonNull(kgdDTO.getFio()) && !kgdDTO.getFio().isEmpty()) {
                    fioNames = kgdDTO.getFio().split(" ");
                    userDTO.setLastName(fioNames[0]);
                    userDTO.setFirstName(fioNames[1]);
                    if (fioNames.length > 2 && Objects.nonNull(fioNames[2]) && !fioNames[2].isEmpty()) {
                        userDTO.setMiddleName(fioNames[2]);
                    } else {
                        userDTO.setMiddleName("");
                    }
                } else {
                    throw new RegistrationException(RegExceptionType.NO_NAME);
                }
            } else {
                throw new RegistrationException(RegExceptionType.DATA_NOT_FOUND);
            }
        }
        String activationKey = RandomUtil.generateActivationKey();
        if (!env.getActiveProfiles().equals("prod") || !env.getActiveProfiles().equals("prod")) {
            log.info("activation key: {}", activationKey);
        }
        userCache.setActivationKey(activationKey);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setType(type);
//        userCache.setJsonData(MailAppUtils.createJsonObject(userDTO));
        System.out.println("**getOrganizations**");
        System.out.println(userDTO.getOrganizations());
        System.out.println("dffd>> " + userDTO.getUserRole());
        userCacheRepository.saveAndFlush(userCache);
        userService.createUserInformation(userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(),
                                          userDTO.getMiddleName(), userDTO.getBirthDate(), userDTO.getIin(), userDTO.getMobileNumber(),
                                          userDTO.getLangKey(), null, userCache, userDTO.getUserRole());
        List<String> activeProfiles = Lists.newArrayList(Arrays.asList(env.getActiveProfiles()));
    }

    public ResponseEntity<?> tryUserRegistry(UserDTO userDTO, String gaCookie, String device, String userType) {
        if (MailAppUtils.isNullOrEmpty(userDTO.getLogin())) {
            return new ResponseEntity<>("{\"error\":\"loginRequired\", " +
                    "\"messageKk\":\"Логин қажет.\", " +
                    "\"messageRu\":\"Требуется логин.\", " +
                    "\"messageEn\":\"Login required.\", " +
                    "\"status\":\"error\"}", HttpStatus.NOT_ACCEPTABLE);
        } else if (userDTO.getLogin().length() < 5) {
            System.out.println("there2");
            return new ResponseEntity<>("{\"error\":\"loginTooShort\"," +
                    "\"messageKk\":\"Логин 5 таңбадан тұруы керек.\"," +
                    "\"messageRu\":\"Логин должен быть не менее 5 символов.\"," +
                    "\"messageEn\":\"Login must be at least 5 characters.\"," +
                    "\"status\":\"error\"}", HttpStatus.NOT_ACCEPTABLE);
        }
        userDTO.setLogin(userDTO.getLogin().toLowerCase());
        if (userDTO.getLogin().matches("\\d{12}")) {
            userDTO.setIin(userDTO.getLogin());
        } else {
            userDTO.setIin("");
        }
        ResponseEntity<String> FORBIDDEN = MailAppUtils.checkForbiddenLoginAndEmptyMobileNumber(userDTO);
        if (FORBIDDEN != null) return FORBIDDEN;
        try {
            createUser(userDTO, gaCookie, device, userType);
        } catch (RegistrationException exc) {
            if (exc.getExcType().equals(RegExceptionType.LOGIN_RESERVED)
                    || exc.getExcType().equals(RegExceptionType.USER_LOGIN_EXIST)
                    || exc.getExcType().equals(RegExceptionType.USER_MOBILE_NUMBER_EXIST)
                    || exc.getExcType().equals(RegExceptionType.IIN_EXIST)) {
                return new ResponseEntity<>("{\"error\":\"" + exc.getExcType().getMsgMap().get(MsgParam.ERROR) + "\", " +
                        "\"messageKk\":\"" + exc.getExcType().getMsgMap().get(MsgParam.KK) + "\", " +
                        "\"messageRu\":\"" + exc.getExcType().getMsgMap().get(MsgParam.RU) + "\", " +
                        "\"messageEn\":\"" + exc.getExcType().getMsgMap().get(MsgParam.EN) + "\", " +
                        "\"status\":\"error\"}", HttpStatus.CONFLICT);
            } else if (exc.getExcType().equals(RegExceptionType.SMS_SEND_FAIL)) {
                return new ResponseEntity<>("{\"error\":\"" + exc.getExcType().getMsgMap().get(MsgParam.ERROR) + "\", " +
                        "\"messageKk\":\"" + exc.getExcType().getMsgMap().get(MsgParam.KK) + "\", " +
                        "\"messageRu\":\"" + exc.getExcType().getMsgMap().get(MsgParam.RU) + "\", " +
                        "\"messageEn\":\"" + exc.getExcType().getMsgMap().get(MsgParam.EN) + "\", " +
                        "\"status\":\"error\"}", HttpStatus.GATEWAY_TIMEOUT);
            } else if (exc.getExcType().equals(RegExceptionType.INVALID_TOKEN)
                    || exc.getExcType().equals(RegExceptionType.NO_NAME)
                    || exc.getExcType().equals(RegExceptionType.DATA_NOT_FOUND)) {
                return new ResponseEntity<>("{\"error\":\"" + exc.getExcType().getMsgMap().get(MsgParam.ERROR) + "\", " +
                        "\"messageKk\":\"" + exc.getExcType().getMsgMap().get(MsgParam.KK) + "\", " +
                        "\"messageRu\":\"" + exc.getExcType().getMsgMap().get(MsgParam.RU) + "\", " +
                        "\"messageEn\":\"" + exc.getExcType().getMsgMap().get(MsgParam.EN) + "\", " +
                        "\"status\":\"error\"}", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("{"
                + "\"messageKk\":\"Пайдаланушының деректері кэштелген. SMS коды арқылы белсендіру қажет.\", "
                + "\"messageRu\":\"Данные пользователя закешированы. Требуется активация по СМС-коду.\", "
                + "\"messageEn\":\"User data is cached. Activation by SMS code is required.\", "
                + "\"login\":\"" + userDTO.getLogin() + "\", "
                + "\"status\":\"ok\"}", HttpStatus.CREATED);
    }

    public ResponseEntity<?> refactoring(UserDTO userDTO) {
        UserEntity user = userService.getUserByLogin(userDTO.getLogin());
        System.out.println("555>>"  + user.getMobileNumber());
        user.setBirthDate(userDTO.getBirthDate());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        AuthorityEntity authority = authorityRepository.findFirstByName(userDTO.getUserRole());
        Set<AuthorityEntity> authorities = new HashSet<>();
        authorities.add(authority);
        System.out.println("auth" + authority);
        user.setAuthorities(authorities);
        System.out.println("user>>"  + user.getEmail());
        userRepository.saveAndFlush(user);
        return new ResponseEntity<>("{"
                + "\"messageKk\":\"OK.\", "
                + "\"messageRu\":\"OK.\", "
                + "\"messageEn\":\"OK.\", "
                + "\"login\":\"" + userDTO.getLogin() + "\", "
                + "\"status\":\"ok\"}", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUsersByLogin(ArrayList<String> listLogins) {
        for (int i = 0; i < listLogins.size(); i++) {
            System.out.println("log: " + listLogins.get(i));
            if (listLogins.get(i) != null && userService.getUserByLogin(listLogins.get(i)) != null)  userRepository.delete(userService.getUserByLogin(listLogins.get(i)));
        }
        return new ResponseEntity<>("{"
                + "\"messageKk\":\"OK.\", "
                + "\"messageRu\":\"OK.\", "
                + "\"messageEn\":\"OK.\", "
                + "\"status\":\"ok\"}", HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(String password, UserDTO userDTO) {
        System.out.println("password>" + password);
        System.out.println("userDTO" + userDTO);
        Optional<UserEntity> userOpt2 = userRepository.findOneByLogin(userDTO.getLogin());
        Optional<UserEntity> userOpt = userRepository.findOneByIin(userDTO.getIin());
        if (!userOpt2.isPresent()) {
            userOpt2 = userOpt;
        }
        System.out.println("dslkfjlsd?> " + userOpt2.get());
        userOpt2.get().setPassword(passwordEncoder.encode(password));
        userRepository.saveAndFlush(userOpt2.get());

        return new ResponseEntity<>("{"
                + "\"messageKk\":\"OK.\", "
                + "\"messageRu\":\"OK.\", "
                + "\"messageEn\":\"OK.\", "
                + "\"status\":\"ok\"}", HttpStatus.OK);
    }

    public String getUserFioByiIn(String iin) throws RegistrationException {
        KgdDTO kgdDTO = kgdService.requestKgdData(iin);
        String[] fioNames;
        String response = "";
        if (Objects.nonNull(kgdDTO) && kgdDTO.getExist()) {
            if (Objects.nonNull(kgdDTO.getFio()) && !kgdDTO.getFio().isEmpty()) {
                fioNames = kgdDTO.getFio().split(" ");
                response = response + fioNames[0] + " " + fioNames[1] + " ";
                if (fioNames.length > 2 && Objects.nonNull(fioNames[2]) && !fioNames[2].isEmpty()) {
                    response = response + fioNames[2];
                }
            } else {
                throw new RegistrationException(RegExceptionType.NO_NAME);
            }
        } else {
            throw new RegistrationException(RegExceptionType.DATA_NOT_FOUND);
        }
        return response;
    }
}
