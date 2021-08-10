package kaz.post.crmserver.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
		Random rand = new Random();
		int min = 1001;
		int max = 9998;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return String.valueOf(randomNum);
    }

	/**
	 * Generates an telegram UUID.
	 *
	 * @return the generated telegram UUID
	 */
	public static String generateTelegramUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
		/*Random rand = new Random();
		int min = 10000001;
		int max = 99999998;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return String.valueOf(randomNum);*/
	}
}
