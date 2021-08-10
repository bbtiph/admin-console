package kaz.post.crmserver.util;

public final class HmailIndexUtil {

	public static String getOldIndex(String newIndex) {

		if (newIndex != null && newIndex.length() > 3) {
			String oldIndex = null;
			String firstLetter = newIndex.substring(0, 1).toUpperCase();
			String secondLetter = newIndex.substring(1, 2);
			String thirdLetter = newIndex.substring(2, 3);
			switch (firstLetter) {
				case "A":
					oldIndex = "050000";
					break;
				case "B":
					oldIndex = "040000";
					break;
				case "C":
					if (secondLetter.equals("1") && Integer.valueOf(thirdLetter) > 5 && Integer.valueOf(thirdLetter) < 9) {
						oldIndex = "010000";
					} else if (secondLetter.equals("3") && Integer.valueOf(thirdLetter) > 6 && Integer.valueOf(thirdLetter) <= 9) {
						oldIndex = "010000";
					} else if (secondLetter.equals("5") && Integer.valueOf(thirdLetter) > 1 && Integer.valueOf(thirdLetter) < 5) {
						oldIndex = "010000";
					} else {
						oldIndex = "020000";
					}
					break;
				case "D":
					oldIndex = "030000";
					break;
				case "E":
					oldIndex = "060000";
					break;
				case "F":
					oldIndex = "070000";
					break;
				case "H":
					oldIndex = "080000";
					break;
				case "L":
					oldIndex = "090000";
					break;
				case "M":
					oldIndex = "100000";
					break;
				case "N":
					oldIndex = "120000";
					break;
				case "P":
					oldIndex = "110000";
					break;
				case "R":
					oldIndex = "130000";
					break;
				case "S":
					oldIndex = "140000";
					break;
				case "T":
					oldIndex = "150000";
					break;
				case "X":
					oldIndex = "160000";
					break;
				case "Z":
					oldIndex = "010000";
					break;
				default:
					break;
			}
			return oldIndex;
		}
		return null;
	}

}
