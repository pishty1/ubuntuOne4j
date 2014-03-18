package au.id.villar.ubuntuOne.filesV1;

import au.id.villar.ubuntuOne.SSOCredentials;
import au.id.villar.ubuntuOne.TestUtils;
import au.id.villar.ubuntuOne.UbuntuException;
import au.id.villar.ubuntuOne.ssoV2.SSOAPIClient;

import java.util.List;
import java.util.Properties;

public class FilesProxyTest {

	public static void main(String[] args) throws UbuntuException {

		Properties props = TestUtils.getProperties();

		String proxyHost = props.getProperty(TestUtils.PROXY_HOST_PROP);
		String strProxyPort = props.getProperty(TestUtils.PROXY_PORT_PROP);
		int proxyPort = strProxyPort != null? Integer.valueOf(strProxyPort): -1;
		String proxyUser = props.getProperty(TestUtils.PROXY_USER_PROP);
		String proxyPassword = props.getProperty(TestUtils.PROXY_PASS_PROP);

		String ubuntuTokenName = props.getProperty(TestUtils.TOKEN_NAME_PROP);
		String ubuntuUser = props.getProperty(TestUtils.TOKEN_USER_PROP);
		String ubuntuPassword = props.getProperty(TestUtils.TOKEN_PASS_PROP);

//		SSOProxy ssoProxy = new SSOProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
//		ssoProxy.login(ubuntuUser", ubuntuPassword, "localhost", null);
//		ssoProxy.authorizeToken();
//		SSOCredentials credentials = ssoProxy.getCredentials();



//		SSOAPIClient ssoProxy = new SSOAPIClient(proxyHost, proxyPort, proxyUser, proxyPassword);
//		SSOCredentials credentials = ssoProxy.createToken(ubuntuTokenName, ubuntuUser, ubuntuPassword);
//		FilesAPIClient filesAPIClient = new FilesAPIClient(proxyHost, proxyPort, proxyUser, proxyPassword, credentials);



		SSOAPIClient ssoProxy = new SSOAPIClient();
		SSOCredentials credentials = ssoProxy.createToken(ubuntuTokenName, ubuntuUser, ubuntuPassword);
		FilesAPIClient filesAPIClient = new FilesAPIClient(credentials);



//		FilesInfo info = filesAPIClient.getInfo();
//		System.out.println(info);

		NodeInfo x = filesAPIClient.getNodeInfo("~/Stuff/03_code_and_projects", "testing_dir/testing_file.txt", false);
		System.out.println(x);

//		NodeInfo x = filesAPIClient.createFile("~/Stuff/03_code_and_projects", "testing_dir/testing_file.txt", NodeKind.FILE);
//		System.out.println(x);

//		filesAPIClient.deleteFile("~/Stuff/03_code_and_projects", "testing_dir");

//		NodeInfo x = filesAPIClient.setPublic("~/Stuff/03_code_and_projects", "testing_dir/testing_file.txt", true);
//		System.out.println(x);

//		ByteArrayInputStream inputStream = new ByteArrayInputStream("Texto de prueba\nHola mundo\n".getBytes());
//		filesAPIClient.uploadFile("/content/~/Stuff/03_code_and_projects/testing_dir/testing_file.txt", inputStream);

//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		filesAPIClient.getFileContent("/content/~/Stuff/03_code_and_projects/testing_dir/testing_file.txt", outputStream);
//		System.out.println(new String(outputStream.toByteArray()));

//		List<VolumeInfo> x = filesAPIClient.getVolumes();
//		System.out.println(x);

//		Object x = filesAPIClient.createVolume("~/MyAwesomeVolume");
//		System.out.println(x);

//		filesAPIClient.deleteVolume("~/MyAwesomeVolume");

//		NodeInfo x = filesAPIClient.move("~/Stuff/03_code_and_projects", "testing_dir/testing_file.txt", "testing_dir/testing_file_2.txt");
//		System.out.println(x);

	}



}
