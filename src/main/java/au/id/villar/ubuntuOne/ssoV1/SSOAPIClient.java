package au.id.villar.ubuntuOne.ssoV1;


import au.id.villar.ubuntuOne.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  Provides a way to use Ubuntu SSO service. (see https://one.ubuntu.com/developer/account_admin/auth/otherplatforms)
 */
public class SSOAPIClient extends APIClient {

	public static final String LOGIN_URL = "https://login.ubuntu.com/api/1.0/authentications";

	private static final String GET_TOKEN_URL = "https://one.ubuntu.com/oauth/sso-finished-so-get-tokens/";

	private OauthToken credentials;

	public SSOAPIClient() {}

	public SSOAPIClient(ProxyData proxyData) {
		super(proxyData);
	}

	public void login(String username, String password, String server, String application)
			throws UbuntuException {

		String auth = "Basic " + DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());

		try {
			server = URLEncoder.encode(server, "ASCII");
			application = application != null? URLEncoder.encode(application, "ASCII"): null;
		} catch (UnsupportedEncodingException e) {
			throw new UbuntuException(e);
		}

		String url = LOGIN_URL + "?ws.op=authenticate&token_name=Ubuntu+One+@+" + server
				+ (application != null? "+" + application: "");

		HttpGet get = new HttpGet(url);
		get.addHeader(new BasicHeader("Authorization", auth));
		get.addHeader(new BasicHeader("Accept", "application/json"));


		try(CloseableHttpClient httpClient = buildHttpClient()) {
			try (CloseableHttpResponse response = httpClient.execute(get)) {

				int statusCode = response.getStatusLine().getStatusCode();

				if(statusCode == STATUS_UNAUTHORIZED) {
					throw new BadCredentialsException();
				}
				if(statusCode != STATUS_CODE_OK) {
					throw new UbuntuException("Ubuntu SSO service returned HTTP " + statusCode);
				}

				String responseText = EntityUtils.toString(response.getEntity());

				credentials = new ObjectMapper().readValue(responseText, OauthToken.class);
			}
		} catch(IOException e) {
			throw new UbuntuException(e);
		}

	}

	public SSOCredentials getCredentials() {
		return credentials;
	}

	public void authorizeToken() throws UbuntuException {

		String authorization = Tools.generateAuthorizationHeader(getCredentials(), "GET", GET_TOKEN_URL);

		HttpGet get = new HttpGet(GET_TOKEN_URL);
		get.addHeader(new BasicHeader("Authorization", authorization));

		try(CloseableHttpClient httpClient = buildHttpClient()) {
			try (CloseableHttpResponse response = httpClient.execute(get)) {

				int statusCode = response.getStatusLine().getStatusCode();

				String responseText = EntityUtils.toString(response.getEntity());

//TODO				return new ObjectMapper().readValue(responseText, SSOLoginAnswer.class);

				System.out.println(">>>> " + statusCode + "\n>>>> " + responseText);
			}
		} catch(IOException e) {
			throw new UbuntuException(e);
		}

	}
}
