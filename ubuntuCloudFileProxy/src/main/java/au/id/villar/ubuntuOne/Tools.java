package au.id.villar.ubuntuOne;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Tools {

	public static String generateBaseString(String method, String baseUrl, List<Parameter> parameters) {

		StringBuilder baseString = new StringBuilder();

		baseString.append(method.toUpperCase()).append('&').append(percentEncode(baseUrl)).append('&');

		List<Parameter> encodedPars = new ArrayList<>(parameters.size());
		for(Parameter parameter: parameters) {
			encodedPars.add(parameter.percentEncode());
		}

		Collections.sort(encodedPars, COMPARE_BY_NAME);

		boolean added = false;
		for(Parameter parameter: encodedPars) {
			baseString.append(percentEncode(parameter.getName())).append("%3D").append(percentEncode(parameter.getValue()))
					.append("%26");
			added = true;
		}
		if(added) {
			baseString.delete(baseString.length() - 3, baseString.length());
		}
		return baseString.toString();
	}

	public static String generateHmacSHA1Signature(String method, String baseUrl, List<Parameter> parameters,
												   String consumerSecret, String tokenSecret) {

		String baseString = generateBaseString(method, baseUrl, parameters);

		StringBuilder signingKey = new StringBuilder();
		signingKey.append(percentEncode(consumerSecret)).append('&').append(percentEncode(tokenSecret));

		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(signingKey.toString().getBytes(),"HmacSHA1");
			mac.init(secret);
			byte[] digest = mac.doFinal(baseString.getBytes());
			return DatatypeConverter.printBase64Binary(digest);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}

	public static String percentEncode(String str) {
		StringBuilder result = new StringBuilder(str.length() + 10);
		for(byte b: str.getBytes()) {
			char ch = (char)(b & 0xff);
			if(ch < 127 && (ch == '-' || ch == '.' || ch == '_' || ch == '~' || Character.isDigit(ch)
					|| Character.isAlphabetic(ch))) {
				result.append(ch);
				continue;
			}
			result.append(String.format("%%%02X", (int)ch));
		}
		return result.toString();
	}

	public static String generateAuthorizationHeader(SSOCredentials credentials, String httpMethod, String url) {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new Parameter("oauth_version", "1.0"));
		parameters.add(new Parameter("oauth_nonce", "" + Math.random())); // TODO <----
		parameters.add(new Parameter("oauth_consumer_key", credentials.getConsumerKey()));
		parameters.add(new Parameter("oauth_token", credentials.getToken()));
		parameters.add(new Parameter("oauth_signature_method", /*"HMAC-SHA1"*/"PLAINTEXT"));
		parameters.add(new Parameter("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000)));

		String signature = Tools.generateHmacSHA1Signature(httpMethod, url, parameters,
				credentials.getConsumerSecret(), credentials.getTokenSecret());

		Collections.sort(parameters, COMPARE_BY_NAME);

		StringBuilder auth = new StringBuilder();
		auth.append("OAuth realm=\"\"");

		for(Parameter parameter: parameters) {
			auth.append(',').append(parameter.getName()).append("=\"")
					.append(Tools.percentEncode(parameter.getValue())).append('"');
		}
		auth.append(',').append("oauth_signature=\"").append(credentials.getConsumerSecret() + "%26" + credentials.getTokenSecret()/*Tools.percentEncode(signature)*/).append('"');
		return auth.toString();
	}

	private static final Comparator<Parameter> COMPARE_BY_NAME = new Comparator<Parameter>() {
		@Override
		public int compare(Parameter o1, Parameter o2) {
			int r = o1.getName().compareTo(o2.getName());
			return r != 0? r: o1.getValue().compareTo(o2.getValue());
		}
	};

	public static String getFileHashSHA1(Path path) {
		return getHashSHA1(path, false);
	}

	public static String getMagicHashField(Path path) {
		return getHashSHA1(path, true);
	}

	private static String getHashSHA1(Path path, boolean addUbuntuOne) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			if(addUbuntuOne) {
				sha1.update("Ubuntu One".getBytes());
			}
			try(InputStream input = Files.newInputStream(path)) {
				byte[] buffer = new byte[2048];
				int read;
				while((read = input.read(buffer)) != -1) {
					sha1.update(buffer, 0, read);
				}
			}
			byte[] hash = sha1.digest();
			StringBuilder strHash = new StringBuilder(hash.length * 2);
			for(byte b: hash) {
				strHash.append(String.format("%02x", b));
			}
			return strHash.toString();
		} catch (NoSuchAlgorithmException | IOException ignore) {
		}
		return null;
	}
}
