package au.id.villar.ubuntuOne.account;

//import au.id.villar.ubuntuOne.ssoV1.SSOProxy;
import au.id.villar.ubuntuOne.ProxyData;
import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.TestUtils;
import au.id.villar.ubuntuOne.UbuntuException;
import au.id.villar.ubuntuOne.ssoV2.SSOAPIClient;

import java.io.IOException;
import java.util.Properties;

public class AccountProxyTest {

	public static void main(String[] args) throws UbuntuException {

		SSOCredentials credentials = getCredentialsFromV2();

		AccountAPIClient accountAPIClient = new AccountAPIClient(credentials);

		AccountInfo accountInfo = accountAPIClient.getAccountInfo();
		System.out.println("Account >>>> " + accountInfo);

		QuotaInfo quotaInfo = accountAPIClient.getQuotaInfo();
		System.out.println("Quota >>>> " + quotaInfo);


	}


	private static SSOCredentials getCredentialsFromV1() throws UbuntuException {

		Properties props = TestUtils.getProperties();

		String proxyHost = props.getProperty(TestUtils.PROXY_HOST_PROP);
		String strProxyPort = props.getProperty(TestUtils.PROXY_PORT_PROP);
		int proxyPort = strProxyPort != null? Integer.valueOf(strProxyPort): -1;
		String proxyUser = props.getProperty(TestUtils.PROXY_USER_PROP);
		String proxyPassword = props.getProperty(TestUtils.PROXY_PASS_PROP);

		String ubuntuUser = props.getProperty(TestUtils.TOKEN_USER_PROP);
		String ubuntuPassword = props.getProperty(TestUtils.TOKEN_PASS_PROP);

		au.id.villar.ubuntuOne.ssoV1.SSOAPIClient proxy;

		if(proxyHost != null) {
			ProxyData proxyData = new ProxyData(proxyHost, proxyPort, proxyUser, proxyPassword);
			proxy = new au.id.villar.ubuntuOne.ssoV1.SSOAPIClient(proxyData);
		} else {
			proxy = new au.id.villar.ubuntuOne.ssoV1.SSOAPIClient();
		}

		proxy.login(ubuntuUser, ubuntuPassword, "localhost", null);

		proxy.authorizeToken();

		return proxy.getCredentials();
	}

	private static SSOCredentials getCredentialsFromV2() throws UbuntuException {

		Properties props = TestUtils.getProperties();

		String proxyHost = props.getProperty(TestUtils.PROXY_HOST_PROP);
		String strProxyPort = props.getProperty(TestUtils.PROXY_PORT_PROP);
		int proxyPort = strProxyPort != null? Integer.valueOf(strProxyPort): -1;
		String proxyUser = props.getProperty(TestUtils.PROXY_USER_PROP);
		String proxyPassword = props.getProperty(TestUtils.PROXY_PASS_PROP);

		String ubuntuTokenName = props.getProperty(TestUtils.TOKEN_NAME_PROP);
		String ubuntuUser = props.getProperty(TestUtils.TOKEN_USER_PROP);
		String ubuntuPassword = props.getProperty(TestUtils.TOKEN_PASS_PROP);

		SSOAPIClient proxy;
		if(proxyHost != null) {
			ProxyData proxyData = new ProxyData(proxyHost, proxyPort, proxyUser, proxyPassword);
			proxy = new SSOAPIClient(proxyData);
		} else {
			proxy = new SSOAPIClient();
		}

		return proxy.createToken(ubuntuTokenName, ubuntuUser, ubuntuPassword);

	}
}
