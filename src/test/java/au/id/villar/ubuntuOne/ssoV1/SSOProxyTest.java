package au.id.villar.ubuntuOne.ssoV1;

import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.TestUtils;
import au.id.villar.ubuntuOne.UbuntuException;

import java.util.Properties;

public class SSOProxyTest {

	public static void main(String[] args) throws UbuntuException {

		Properties props = TestUtils.getProperties();

		String proxyHost = props.getProperty(TestUtils.PROXY_HOST_PROP);
		String strProxyPort = props.getProperty(TestUtils.PROXY_PORT_PROP);
		int proxyPort = strProxyPort != null? Integer.valueOf(strProxyPort): -1;
		String proxyUser = props.getProperty(TestUtils.PROXY_USER_PROP);
		String proxyPassword = props.getProperty(TestUtils.PROXY_PASS_PROP);

		String ubuntuUser = props.getProperty(TestUtils.TOKEN_USER_PROP);
		String ubuntuPassword = props.getProperty(TestUtils.TOKEN_PASS_PROP);

		SSOAPIClient proxy = new SSOAPIClient(proxyHost, proxyPort, proxyUser, proxyPassword);
		//SSOProxy proxy = new SSOProxy();

		proxy.login(ubuntuUser, ubuntuPassword, "localhost", null);
		SSOCredentials answer = proxy.getCredentials();
		System.out.println(">>>> " + answer);

	}

}
