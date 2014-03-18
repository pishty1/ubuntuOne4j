package au.id.villar.ubuntuOne;

public class UbuntuAPIException extends UbuntuException {

	private int httpStatus;
	private String description;

	public UbuntuAPIException(int httpStatus) {
		super("API Returned error: HTTP " + httpStatus);
		this.httpStatus = httpStatus;
	}

	public UbuntuAPIException(int httpStatus, String description) {
		super("API Returned error: HTTP " + httpStatus + " and description: " + description);
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getDescription() {
		return description;
	}
}
