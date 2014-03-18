package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo extends NodeWithContentPath {

	private String resourcePath;
	private String type;
	private String path;
	private int generation;
	private DateTime whenCreated;
	private String nodePath;
	private String contentPath;

	@JsonProperty("resource_path")
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("generation")
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	@JsonProperty("when_created")
	public DateTime getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(DateTime whenCreated) {
		this.whenCreated = whenCreated;
	}

	@JsonProperty("node_path")
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	@JsonProperty("content_path")
	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
}
