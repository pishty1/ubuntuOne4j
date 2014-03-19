package au.id.villar.ubuntuOne.account;

import au.id.villar.ubuntuOne.JsonAuthorizedAPIClient;
import au.id.villar.ubuntuOne.ProxyData;
import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.UbuntuException;

public class AccountAPIClient extends JsonAuthorizedAPIClient {

	private final String ACCOUNT_URL = BASE_URL + "/account/";
	private final String QUOTA_URL = BASE_URL + "/quota/";

	public AccountAPIClient(SSOCredentials credentials) {
		super(credentials);
	}

	public AccountAPIClient(ProxyData proxyData, SSOCredentials credentials) {
		super(proxyData, credentials);
	}

	public AccountInfo getAccountInfo() throws UbuntuException {
		return getJsonObject(AccountInfo.class, ACCOUNT_URL);
	}

	public QuotaInfo getQuotaInfo() throws UbuntuException {
		return getJsonObject(QuotaInfo.class, QUOTA_URL);
	}

}
