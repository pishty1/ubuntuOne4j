package au.id.villar.ubuntuOne.ssoV2;

import au.id.villar.ubuntuOne.APIClient;

import au.id.villar.ubuntuOne.ProxyData;
import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.UbuntuException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// http://canonical-identity-provider.readthedocs.org/en/latest/index.html
/**
 * Provides a way to use Ubuntu SSO service to create tokens.
 * (See <a href="https://one.ubuntu.com/developer/account_admin/auth/otherplatforms">Ubuntu One's cloud API</a> and
 * <a href="https://canonical-identity-provider.readthedocs.org/en/latest/index.html">Canonical Identity Provider’s
 * documentation</a>)
 */
public class SSOAPIClient extends APIClient {

	private static final String SSO_API = "https://login.ubuntu.com/api/v2/";
	private static final String AUTH_TOKEN = "tokens/oauth";
	private static final String PASSWORD_RESET_TOKEN = "tokens/password";

	private static final String CONTENT_TYPE_NAME = "Content-type";
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final Header JSON_CONTENT = new BasicHeader(CONTENT_TYPE_NAME, CONTENT_TYPE_JSON);

	/**
	 * Constructs a default SSOAPIClient.
	 */
	public SSOAPIClient() {}

	/**
	 * Constructs a SSOAPIClient specifying a http proxy.
	 * @param proxyData object containing information about the proxy.
	 * @See ProxyData
	 */
	public SSOAPIClient(ProxyData proxyData) {
		super(proxyData);
	}

	/**
	 * Creates or returns an already existent Oauth token. This is a convenient method equivalent to
	 * {@code createToken(tokenName, email, password, null);}
	 * @param tokenName  a name for the token.
	 * @param email user’s email address.
	 * @param password user’s password.
	 * @return A token to be used in other {@link APIClient} objects for authentication purposes.
	 * @throws SSOException If the token retrieveal was not successful.
	 * @see  SSOAPIClient#createToken(String, String, String, String)
	 */
	public SSOCredentials createToken(String tokenName, String email, String password) throws SSOException {
		return createToken(tokenName, email, password, null);
	}

	/**
	 * <p>Creates or returns an already existent oauth token.</p>
	 * <p>{@code tokenName} is used as human-readable identifier so the
	 * user can easily identify which tokens belong to which application or device and revoke the ones they don't need
	 * any more; it is recommended to use the format “application_name-device_name” or "Ubuntu One @ $hostname
	 * [$application]" (see Ubuntu One's docs).</p>
	 *
	 * @param tokenName  a name for the token.
	 * @param email user’s email address.
	 * @param password user’s password.
	 * @param otp one time password (just if required, it can be null)
	 * @return A token to be used in other {@link APIClient} objects for authentication purposes.
	 * @throws SSOException If the token retrieveal was not successful.
	 */
	public SSOCredentials createToken(String tokenName, String email, String password, String otp) throws SSOException {

		try {

			// create JSON string (request)
			Map<String, String> request = new HashMap<>();
			request.put("email", email);
			request.put("password", password);
			request.put("token_name", tokenName);
			if(otp != null) {
				request.put("otp", otp);
			}

			// create POST request
			HttpPost post = createPost(SSO_API + AUTH_TOKEN, request);

			ObjectMapper objectMapper = new ObjectMapper();

			Result result = execute(post, false);
			int status = result.httpStatus;
			if(status == STATUS_CODE_OK || status == STATUS_CREATED) {
				return objectMapper.readValue(result.content, OauthToken.class);
			} else {
				throw new SSOAPIException(status, objectMapper.readValue(result.content, Error.class));
			}

		} catch (IOException e) {
			throw new SSOException(e);
		}

	}


	/**
	 *
	 * @param email
	 * @throws SSOException
	 */
	public void createPasswordResetToken(String email) throws SSOException {
		try {
			Map<String, String> request = new HashMap<>();
			request.put("email", email);

			HttpPost post = createPost(SSO_API + PASSWORD_RESET_TOKEN, request);
			try {
				Result result = execute(post, false);
				int status = result.httpStatus;
				if(status != STATUS_CODE_OK && status != STATUS_CREATED) {
					ObjectMapper objectMapper = new ObjectMapper();
					throw new SSOAPIException(result.httpStatus, objectMapper.readValue(result.content, Error.class));
				}
			} catch (UbuntuException e) {
				throw new SSOException(e);
			}

		} catch (IOException e) {
			throw new SSOException(e);
		}
	}

	protected HttpPost createPost(String url, Map<String, String> content) throws IOException {
		HttpPost post = new HttpPost(url);
		post.setHeader(JSON_CONTENT);
		String jsonContent = new ObjectMapper().writeValueAsString(content);
		post.setEntity(new StringEntity(jsonContent));
		return post;
	}

}
