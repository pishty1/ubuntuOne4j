package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FilesInfo {

	private String resourcePath;
	private long userId;
	private String visibleName;
	private long maxBytes;
	private long usedBytes;
	private String root;
	private List<String> paths;

	@JsonProperty("resource_path")
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@JsonProperty("user_id")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JsonProperty("visible_name")
	public String getVisibleName() {
		return visibleName;
	}

	public void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}

	@JsonProperty("max_bytes")
	public long getMaxBytes() {
		return maxBytes;
	}

	public void setMaxBytes(long maxBytes) {
		this.maxBytes = maxBytes;
	}

	@JsonProperty("used_bytes")
	public long getUsedBytes() {
		return usedBytes;
	}

	public void setUsedBytes(long usedBytes) {
		this.usedBytes = usedBytes;
	}

	@JsonProperty("root_node_path")
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	@JsonProperty("user_node_paths")
	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	@Override
	public String toString() {
		return "{\n    \"@class\"=\"FilesInfo\"" +
				",\n    \"resourcePath\"=\"" + resourcePath +
				"\",\n    \"userId\"=\"" + userId +
				"\",\n    \"visibleName\"=\"" + visibleName +
				"\",\n    \"maxBytes\"=\"" + maxBytes +
				"\",\n    \"usedBytes\"=\"" + usedBytes +
				"\",\n    \"root\"=\"" + root +
				"\",\n    \"paths\"=\"" + paths +
				"\"\n}";
	}
}
