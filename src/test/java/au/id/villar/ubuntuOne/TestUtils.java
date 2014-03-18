package au.id.villar.ubuntuOne;

import java.io.IOException;
import java.util.Properties;

public class TestUtils {

	public static final String PROXY_HOST_PROP = "au.id.villar.ubuntuOne.proxy.host";
	public static final String PROXY_PORT_PROP = "au.id.villar.ubuntuOne.proxy.port";
	public static final String PROXY_USER_PROP = "au.id.villar.ubuntuOne.proxy.user";
	public static final String PROXY_PASS_PROP = "au.id.villar.ubuntuOne.proxy.password";
	public static final String TOKEN_NAME_PROP = "au.id.villar.ubuntuOne.token.name";
	public static final String TOKEN_USER_PROP = "au.id.villar.ubuntuOne.token.user";
	public static final String TOKEN_PASS_PROP = "au.id.villar.ubuntuOne.token.password";

	public static Properties getProperties() {
		Properties props;
		try {
			props = new Properties();
			props.load(ClassLoader.getSystemResourceAsStream("test.properties"));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		return props;
	}

}
