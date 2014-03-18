package au.id.villar.ubuntuOne.ssoV2;

import au.id.villar.ubuntuOne.UbuntuException;

public class SSOException extends UbuntuException {

	public SSOException() {
	}

	public SSOException(String message) {
		super(message);
	}

	public SSOException(Throwable cause) {
		super(cause);
	}

}
