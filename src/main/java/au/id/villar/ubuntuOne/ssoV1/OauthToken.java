package au.id.villar.ubuntuOne.ssoV1;

import au.id.villar.ubuntuOne.SSOCredentials;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an OAuth token resource
 */
public class OauthToken extends SSOCredentials {

	@Override
	@JsonProperty("updated")
	public String getTimeUpdated() {
		return super.getTimeUpdated();
	}

	@Override
	@JsonProperty("created")
	public String getTimeCreated() {
		return super.getTimeCreated();
	}

	@Override
	@JsonProperty("consumer_secret")
	public String getConsumerSecret() {
		return super.getConsumerSecret();
	}

	@Override
	@JsonProperty("consumer_key")
	public String getConsumerKey() {
		return super.getConsumerKey();
	}

	@Override
	@JsonProperty("token_secret")
	public String getTokenSecret() {
		return super.getTokenSecret();
	}

	@Override
	@JsonProperty("name")
	public String getTokenName() {
		return super.getTokenName();
	}
}
