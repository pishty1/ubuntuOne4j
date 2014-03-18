package au.id.villar.ubuntuOne.ssoV1;

import au.id.villar.ubuntuOne.UbuntuException;

public class BadCredentialsException extends UbuntuException {

	public BadCredentialsException() {
		super("Bad credentials");
	}
}
