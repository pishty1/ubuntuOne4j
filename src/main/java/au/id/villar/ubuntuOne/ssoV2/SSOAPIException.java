package au.id.villar.ubuntuOne.ssoV2;

public class SSOAPIException extends SSOException {

	private int httpStatus;
	private Error errorDescription;

	public SSOAPIException(int httpStatus, Error errorDescription) {
		super("API Returned error: HTTP " + httpStatus + ", code: " + errorDescription.getCode() +
				", description: " + errorDescription.getMessage());
		this.httpStatus = httpStatus;
		this.errorDescription = errorDescription;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public Error getErrorDescription() {
		return errorDescription;
	}

}
