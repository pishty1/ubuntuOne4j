package au.id.villar.ubuntuOne;

public class SSOCredentials {

	private String consumerKey;
	private String consumerSecret;
	private String token;
	private String tokenSecret;
	private String tokenName;
	private String timeUpdated;
	private String timeCreated;

	public SSOCredentials() {
	}

	public String getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String name) {
		this.tokenName = name;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{" +
				"token='" + token + '\'' +
				", tokenSecret='" + tokenSecret + '\'' +
				", tokenName='" + tokenName + '\'' +
				", consumerKey='" + consumerKey + '\'' +
				", consumerSecret='" + consumerSecret + '\'' +
				", timeCreated='" + timeCreated + '\'' +
				", timeUpdated='" + timeUpdated + '\'' +
				'}';
	}

}
