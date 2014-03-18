package au.id.villar.ubuntuOne;

public class Parameter {

	private String name;
	private String value;

	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Parameter percentEncode() {
		String name = Tools.percentEncode(this.name);
		String value = Tools.percentEncode(this.value);
		return new Parameter(name, value);
	}
}
