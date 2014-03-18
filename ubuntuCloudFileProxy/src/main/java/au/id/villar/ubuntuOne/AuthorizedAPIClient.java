package au.id.villar.ubuntuOne;

import org.apache.http.client.methods.*;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.OutputStream;

public class AuthorizedAPIClient extends APIClient {

	private SSOCredentials credentials;

	protected AuthorizedAPIClient(String proxyName, int proxyPort, String proxyUsername, String proxyPassword,
								  SSOCredentials credentials) {
		super(proxyName, proxyPort, proxyUsername, proxyPassword);
		this.credentials = credentials;
	}

	@Override
	protected Result execute(HttpRequestBase request, boolean includeHeaders) throws IOException {
		addAuthorizationHeader(request);
		return super.execute(request, includeHeaders);
	}

	@Override
	protected Result execute(HttpRequestBase request, boolean includeHeaders, OutputStream contentTarget)
			throws IOException {
		addAuthorizationHeader(request);
		return super.execute(request, includeHeaders, contentTarget);
	}

	private void addAuthorizationHeader(HttpRequestBase request) {
		String authorization = Tools.generateAuthorizationHeader(credentials, request.getMethod(),
				request.getURI().toString());
		request.addHeader(new BasicHeader("Authorization", authorization));
	}

}
