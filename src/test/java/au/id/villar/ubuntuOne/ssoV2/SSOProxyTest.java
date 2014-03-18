package au.id.villar.ubuntuOne.ssoV2;

import au.id.villar.ubuntuOne.TestUtils;

import java.util.Properties;

/**
 * Created by villarr on 18/02/14.
 */
public class SSOProxyTest {

	public static void main(String[] args) throws SSOException {

		Properties props = TestUtils.getProperties();

		String proxyHost = props.getProperty(TestUtils.PROXY_HOST_PROP);
		String strProxyPort = props.getProperty(TestUtils.PROXY_PORT_PROP);
		int proxyPort = strProxyPort != null? Integer.valueOf(strProxyPort): -1;
		String proxyUser = props.getProperty(TestUtils.PROXY_USER_PROP);
		String proxyPassword = props.getProperty(TestUtils.PROXY_PASS_PROP);

		String ubuntuTokenName = props.getProperty(TestUtils.TOKEN_NAME_PROP);
		String ubuntuUser = props.getProperty(TestUtils.TOKEN_USER_PROP);
		String ubuntuPassword = props.getProperty(TestUtils.TOKEN_PASS_PROP);


		SSOAPIClient proxy = new SSOAPIClient(proxyHost, proxyPort, proxyUser, proxyPassword);
		OauthToken token = (OauthToken)proxy.createToken(ubuntuTokenName, ubuntuUser, ubuntuPassword);
		System.out.println(token);

//		proxy.createPasswordResetToken(ubuntuUser);
	}

}
