package au.id.villar.ubuntuOne;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ToolsTest {

	@Test
	public void generateHmacSHA1SignatureTest() {

		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new Parameter("status", "Hello Ladies + Gentlemen, a signed OAuth request!"));
		parameters.add(new Parameter("include_entities", "true"));
		parameters.add(new Parameter("oauth_consumer_key", "xvz1evFS4wEEPTGEFPHBog"));
		parameters.add(new Parameter("oauth_nonce", "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"));
		parameters.add(new Parameter("oauth_signature_method", "HMAC-SHA1"));
		parameters.add(new Parameter("oauth_timestamp", "1318622958"));
		parameters.add(new Parameter("oauth_token", "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"));
		parameters.add(new Parameter("oauth_version", "1.0"));

		String signature = Tools.generateHmacSHA1Signature("POST", "https://api.twitter.com/1/statuses/update.json",
				parameters, "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw",
				"LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE");


		assertEquals("tnnArxj06cWHq44gCs1OSKk/jLY=", signature);
	}

	@Test
	public void generateBaseStringTest() {

		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new Parameter("b5", "=%3D"));
		parameters.add(new Parameter("a3", "a"));
		parameters.add(new Parameter("c@", ""));
		parameters.add(new Parameter("a2", "r b"));

		parameters.add(new Parameter("oauth_consumer_key", "9djdj82h48djs9d2"));
		parameters.add(new Parameter("oauth_token", "kkk9d7dh3k39sjv7"));
		parameters.add(new Parameter("oauth_signature_method", "HMAC-SHA1"));
		parameters.add(new Parameter("oauth_timestamp", "137131201"));
		parameters.add(new Parameter("oauth_nonce", "7d8f3e4a"));
		parameters.add(new Parameter("c2", ""));
		parameters.add(new Parameter("a3", "2 q"));


		String signature = Tools.generateBaseString("POST", "http://example.com/request",
				parameters);


		assertEquals("POST&http%3A%2F%2Fexample.com%2Frequest&a2%3Dr%2520b%26a3%3D2%2520q" +
				"%26a3%3Da%26b5%3D%253D%25253D%26c%2540%3D%26c2%3D%26oauth_consumer_" +
				"key%3D9djdj82h48djs9d2%26oauth_nonce%3D7d8f3e4a%26oauth_signature_m" +
				"ethod%3DHMAC-SHA1%26oauth_timestamp%3D137131201%26oauth_token%3Dkkk" +
				"9d7dh3k39sjv7", signature);
	}

	@Test
	public void percentEncodeTest() {
		String test = "%$@uno=dos?tres";
		String encoded = Tools.percentEncode(test);
		assertEquals("%25%24%40uno%3Ddos%3Ftres", encoded);
	}

}
