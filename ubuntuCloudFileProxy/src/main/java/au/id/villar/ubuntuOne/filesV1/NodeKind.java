package au.id.villar.ubuntuOne.filesV1;

public enum NodeKind {
	FILE("file"), DIRECTORY("directory");

	private String str;

	NodeKind(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public static NodeKind getType(String str) {
		try {
			return NodeKind.valueOf(str.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			return null;
		}
	}
}
