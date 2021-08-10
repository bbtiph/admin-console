package kaz.post.crmserver.exceptions;

public class OpenWayTransferException extends Exception{
	String code;
	public OpenWayTransferException(String message) {
		super(message);
	}
	public OpenWayTransferException(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
