package au.id.villar.ubuntuOne.account;

import au.id.villar.ubuntuOne.JsonAuthorizedAPIClient;
import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.UbuntuException;

public class AccountAPIClient extends JsonAuthorizedAPIClient {

	private final String ACCOUNT_URL;
	private final String QUOTA_URL;

	public AccountAPIClient(SSOCredentials credentials) {
		this(null, 0, null, null, credentials);
	}

	public AccountAPIClient(String proxyName, int proxyPort, String proxyUsername, String proxyPassword,
							SSOCredentials credentials) {
		super(proxyName, proxyPort, proxyUsername, proxyPassword, credentials);
		ACCOUNT_URL = BASE_URL + "/account/";
		QUOTA_URL = BASE_URL + "/quota/";
	}

	public AccountInfo getAccountInfo() throws UbuntuException {
		return getJsonObject(AccountInfo.class, ACCOUNT_URL);
	}

	public QuotaInfo getQuotaInfo() throws UbuntuException {
		return getJsonObject(QuotaInfo.class, QUOTA_URL);
	}

}
