package kaz.post.crmserver.exceptions;

public class RegistrationException extends Exception {

	private RegExceptionType excType;
	private String msg;

	public RegistrationException(RegExceptionType excType) {
		super(excType.getMsgMap().get(MsgParam.RU));
		this.excType = excType;
	}

	public RegistrationException(RegExceptionType excType, String msg) {
		super(excType.getMsgMap().get(MsgParam.RU));
		this.excType = excType;
		this.msg = msg;
	}

	public RegExceptionType getExcType() {
		return excType;
	}

	public String getMsg() {
		return msg;
	}
}
