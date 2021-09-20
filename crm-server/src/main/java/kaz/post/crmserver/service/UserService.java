package kaz.post.crmserver.service;

import com.oracle.javafx.jmx.json.JSONException;
import kaz.post.crmserver.dto.UserDTO;
import kaz.post.crmserver.entity.*;
import kaz.post.crmserver.entity.UserEntity;
import kaz.post.crmserver.repositories.mail.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private ReportTransactionRepository transactionRepository;

    public void setAuthority(AuthorityEntity authority, UserEntity newUser) {
        Set<AuthorityEntity> authorities = new HashSet<>();
        authorities.add(authority);
        newUser.setAuthorities(authorities);
    }

    public UserEntity createUserInformation(String login, String password, String firstName, String lastName,
                                            String middleName, Date birthDate, String iin, String mobileNumber,
                                            String langKey, String organizationBin, UserCacheEntity cachedUser, String userRole) throws JSONException {
        UserEntity newUser = new UserEntity();

        Optional<OrganizationEntity> optionalOrganization;

        newUser.setLogin(login);
        newUser.setPassword(password);
        if (Objects.nonNull(firstName))
            newUser.setFirstName(firstName);
        if (Objects.nonNull(lastName))
            newUser.setLastName(lastName);
        if (Objects.nonNull(middleName))
            newUser.setMiddleName(middleName);
        if (Objects.nonNull(birthDate))
            newUser.setBirthDate(birthDate);
        if (Objects.nonNull(iin))
            newUser.setIin(iin);
        if (Objects.nonNull(mobileNumber))
            newUser.setMobileNumber(mobileNumber);
        if (Objects.nonNull(langKey))
            newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(true);
        newUser.setConfirmed(true);
        // new user gets registration key
        newUser.setActivationKey(null);
        AuthorityEntity authority2 = authorityRepository.findFirstByName(userRole);
        Set<AuthorityEntity> authorities = new HashSet<>();
        authorities.add(authority2);
        System.out.println("Auth");
        System.out.println("auth" + authority2);
        newUser.setAuthorities(authorities);
        System.out.println("bhhh>>" + authorities);

        newUser = userRepository.saveAndFlush(newUser);

        if (StringUtils.isNotBlank(organizationBin)) {
            optionalOrganization = organizationRepository.findOneByBin(organizationBin);

//            if (optionalOrganization.isPresent()) {
//                processEmployee(authority, cachedUser, optionalOrganization.get(), newUser);
//            }
        } else {
//            authority = authorityRepository.findOne("ROLE_USER");
            setAuthority(null, newUser);
        }
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    public void deleteUserAddress(String accountLogin, int index) {
        List<UserAddressEntity> addresses = userAddressRepository.findByUserLogin(accountLogin);
        userAddressRepository.delete(addresses.get(index));
    }

    public UserAddressEntity getUserAddressByLogin(String login) {
        log.debug("userService.getUserAddressByLogin()");
        List<UserAddressEntity> address = userAddressRepository.findByUserLogin(login);
        return address.get(0);
    }

    public List<UserAddressEntity> getUserAddressesByLogin(String login) {
        log.debug("userService.getUserAddressByLogin()");
        List<UserAddressEntity> addresses = userAddressRepository.findByUserLogin(login);
        return isNotEmpty(addresses) ? addresses : null;
    }

	public void changePassword(String login, String password) {
		userRepository.findOneByLogin(login).ifPresent(u -> {
			String encryptedPassword = passwordEncoder.encode(password);
			u.setPassword(encryptedPassword);
			userRepository.save(u);
			log.debug("Changed password for User: {}", u);
		});
	}

    public UserEntity getUserByLogin(String login) {
        log.debug("userService.getUserByLogin()");
        Optional<UserEntity> userOpt2 = userRepository.findOneByLogin(login);
        Optional<UserEntity> userOpt = userRepository.findOneByIin(login);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else if (userOpt2.isPresent()) {
            return userOpt2.get();
        } else return null;
    }

    public ResponseEntity<List<UserDTO>> getAllUser(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        int pageNumber = 0;
        int pageSize = 5;
        String sortBy = "id";
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        if (allRequestParams.containsKey("sortDirection")) {
            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;
        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        System.out.println(pageableRequest + ">>> ttt");
        Page<UserEntity> users = userRepository.findAll(pageableRequest);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity user : users) {
            UserDTO userDTO = new UserDTO(user.getLogin(),
                    null, user.getFirstName(), user.getLastName(), user
                    .getMiddleName(), user.getBirthDate(), user
                    .getIin(), user.getMobileNumber(), user
                    .getLangKey(), user.getConfirmed(), user.getAuthorities().stream()
                    .map(AuthorityEntity::getName)
                    .collect(Collectors.toList()), user.getPosition(), user.getDisablePush(), user.getContract(), user.getWalletConfirmedOffer(), user.getEnabledMobileSecurity(), user.getEmployeeNumber(), null, user.getCreatedDate(), user.getId());
//            System.out.println("userDTO " + userDTO);
            userDTOList.add(userDTO);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    public Long idGenerate() {
        Date dNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
        String unique = format.format(dNow);
        String uniqueNum="";
        for (int i=0; i<unique.length(); i++) {
            uniqueNum+=unique.charAt(i);
        }
        return Long.parseLong(uniqueNum);
    }

    public ResponseEntity<List<UserDTO>> searchingWithFilter(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        int pageNumber = 0;
        int pageSize = 5;
        String input = "";
        String field = "";
        String sortBy = "id";
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        if (allRequestParams.containsKey("input")) {
            input = (allRequestParams.get("input"));
        }
        if (allRequestParams.containsKey("field")) {
            field = (allRequestParams.get("field"));
        }
        if (allRequestParams.containsKey("sortDirection")) {
            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;
        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        System.out.println(pageableRequest + ">>> ttt <<<" + input + " " + " <> " + field);
        Page<UserEntity> users = null;
        switch (field) {
            case "IIN":
                users = userRepository.findAllByIinContains(input, pageableRequest);
                break;

            case "FIRST_NAME":
                users = userRepository.findAllByFirstNameContains(input, pageableRequest);
                break;

            case "MIDDLE_NAME":
                users = userRepository.findAllByMiddleNameContains(input, pageableRequest);
                break;

            case "LAST_NAME":
                users = userRepository.findAllByLastNameContains(input, pageableRequest);
                break;

            case "MOBILE_NUMBER":
                users = userRepository.findAllByMobileNumberContains(input, pageableRequest);
                break;

            default:
                users = userRepository.findAll(pageableRequest);
                break;
        }

        if (input.length() == 0 || input == null || input.isEmpty()) {
            users = userRepository.findAll(pageableRequest);
        }

        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity user : users) {
            UserDTO userDTO = new UserDTO(user.getLogin(),
                    null, user.getFirstName(), user.getLastName(), user
                    .getMiddleName(), user.getBirthDate(), user
                    .getIin(), user.getMobileNumber(), user
                    .getLangKey(), user.getConfirmed(), user.getAuthorities().stream()
                    .map(AuthorityEntity::getName)
                    .collect(Collectors.toList()), user.getPosition(), user.getDisablePush(), user.getContract(),
                    user.getWalletConfirmedOffer(), user.getEnabledMobileSecurity(),
                    user.getEmployeeNumber(), null, user.getCreatedDate(), user.getId());
            userDTOList.add(userDTO);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> getReportByUser(Map<String, String> allRequestParams) throws ParseException {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        ReportTransactionEntity transactionEntity = new ReportTransactionEntity();

        int pageNumber = 0;
        int pageSize = 5;
        String startDate = null;
        String endDate = null;
        Boolean isFullInf = null;
        String sortBy = "id";
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        if (allRequestParams.containsKey("start")) {
            startDate = (allRequestParams.get("start"));
        }
        if (allRequestParams.containsKey("end")) {
            endDate = (allRequestParams.get("end"));
        }
        if (allRequestParams.containsKey("isFullInf")) {
            isFullInf = Boolean.parseBoolean(allRequestParams.get("isFullInf"));
        }

        transactionEntity.setId(idGenerate());
        transactionEntity.setFromDate(startDate);
        transactionEntity.setNameOfTransaction("Отчет по пользователям");
        transactionEntity.setToDate(endDate);
        transactionEntity.setLinkOfExcel("post.kz/admin/console/" + transactionEntity.getId());
        transactionEntity.setTypeReport(1);
        transactionRepository.saveAndFlush(transactionEntity);


        System.out.println(startDate + " <rr> " + endDate);
        if (allRequestParams.containsKey("sortDirection")) {
            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;
        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        List<UserDTO> userDTOList = new ArrayList<>();

        if (isFullInf != null) {
            List<UserEntity> users = null;
            users = userRepository.findUsersBetweenTwoDateWithOutPageable(startDate, endDate);
            for (UserEntity user : users) {
                UserDTO userDTO = new UserDTO(user.getLogin(),
                        null, user.getFirstName(), user.getLastName(), user
                        .getMiddleName(), user.getBirthDate(), user
                        .getIin(), user.getMobileNumber(), user
                        .getLangKey(), user.getConfirmed(), user.getAuthorities().stream()
                        .map(AuthorityEntity::getName)
                        .collect(Collectors.toList()), user.getPosition(), user.getDisablePush(), user.getContract(),
                        user.getWalletConfirmedOffer(), user.getEnabledMobileSecurity(),
                        user.getEmployeeNumber(), null, user.getCreatedDate(),user.getId());
                userDTOList.add(userDTO);
            }
        }else {
            Page<UserEntity> users = null;
            users = userRepository.findUsersBetweenTwoDate(startDate, endDate, pageableRequest);
            for (UserEntity user : users) {
                UserDTO userDTO = new UserDTO(user.getLogin(),
                        null, user.getFirstName(), user.getLastName(), user
                        .getMiddleName(), user.getBirthDate(), user
                        .getIin(), user.getMobileNumber(), user
                        .getLangKey(), user.getConfirmed(), user.getAuthorities().stream()
                        .map(AuthorityEntity::getName)
                        .collect(Collectors.toList()), user.getPosition(), user.getDisablePush(), user.getContract(),
                        user.getWalletConfirmedOffer(), user.getEnabledMobileSecurity(),
                        user.getEmployeeNumber(), null, user.getCreatedDate(),user.getId());
                userDTOList.add(userDTO);
            }
        }


        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    public Long countUser() {
        return userRepository.countUser();
    }

    public Long countReportByUser(Map<String, String> allRequestParams) {
        String startDate = null;
        String endDate = null;
        if (allRequestParams.containsKey("start")) {
            startDate = (allRequestParams.get("start"));
        }
        if (allRequestParams.containsKey("end")) {
            endDate = (allRequestParams.get("end"));
        }
        return userRepository.findCountUsersBetweenTwoDate(startDate, endDate);
    }

    public Optional<UserEntity> getByLoginAndMobileNumber(String login, String mobileNumber) {
        Optional<UserEntity> user = userRepository.findOneByLoginAndMobileNumber(login, mobileNumber);
        log.warn("user by login: {} and mobile number: {} is: {}", login, mobileNumber, user.isPresent() ? user.get() : null);
        if (!user.isPresent() && mobileNumber.length() == 11) {
            String shortMobNum = mobileNumber.substring(1);
            user = userRepository.findOneByLoginAndMobileNumber(login, shortMobNum);
            log.warn("user by login: {} and short mobile number: {} is: {}", login, shortMobNum, user.isPresent() ? user.get() : null);
        }
        return user;
    }

    public Optional<UserEntity> getByLoginOrIinAndMobileNumber(String login, String iin, String mobileNumber) {
        Optional<UserEntity> user = userRepository.findOneByLoginOrIinAndMobileNumber(login, iin, mobileNumber);
        log.warn("user by login or iin: {} and mobile number: {} is: {}", login, mobileNumber, user.isPresent() ? user.get() : null);
        if (!user.isPresent() && mobileNumber.length() == 11) {
            String shortMobNum = mobileNumber.substring(1);
            user = userRepository.findOneByLoginOrIinAndMobileNumber(login, iin, shortMobNum);
            log.warn("user by login or iin: {} and short mobile number: {} is: {}", login, shortMobNum, user.isPresent() ? user.get() : null);
        }
        return user;
    }

}
