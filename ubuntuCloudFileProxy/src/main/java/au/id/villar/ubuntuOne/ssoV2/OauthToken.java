package au.id.villar.ubuntuOne.ssoV2;

import au.id.villar.ubuntuOne.SSOCredentials;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* Represents an OAuth token resource
*/
public class OauthToken extends SSOCredentials {

	private String openId;
	private String href;

	@JsonProperty("consumer_key")
	public String getConsumerKey() {
		return super.getConsumerKey();
	}

	@JsonProperty("consumer_secret")
	public String getConsumerSecret() {
		return super.getConsumerSecret();
	}

	@JsonProperty("token_key")
	public String getToken() {
		return super.getToken();
	}

	@JsonProperty("token_secret")
	public String getTokenSecret() {
		return super.getTokenSecret();
	}

	@JsonProperty("token_name")
	public String getTokenName() {
		return super.getTokenName();
	}

	@JsonProperty("date_created")
	public String getTimeCreated() {
		return super.getTimeCreated();
	}

	@JsonProperty("date_updated")
	public String getTimeUpdated() {
		return super.getTimeUpdated();
	}

	@JsonProperty("openid")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return super.toString() + "{" +
				"openId='" + openId + '\'' +
				", href='" + href + '\'' +
				'}';
	}

}
