package au.id.villar.ubuntuOne;

public class ProxyData {

	private String name;
	private int port;
	private String username;
	private String password;

	public ProxyData(String name, int port) {
		this(name, port, null, null);
	}

	public ProxyData(String name, int port, String username, String password) {
		if(name == null) {
			throw new IllegalArgumentException("\"name\" can't be null");
		}
		this.name = name;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
