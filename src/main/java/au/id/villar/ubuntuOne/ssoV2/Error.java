package au.id.villar.ubuntuOne.ssoV2;

/**
 * Represents a standard error response.
 */
public class Error {

	private String code;
	private String message;
	private Object extra;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}
}
