package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeWithContentPath {

	private String contentPath;

	@JsonProperty("content_path")
	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

}
