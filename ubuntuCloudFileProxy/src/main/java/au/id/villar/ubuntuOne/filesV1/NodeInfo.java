package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeInfo extends NodeWithContentPath {

	private NodeKind kind;
	private String resourcePath;
	private String path;
	private String parentPath;
	private String volumePath;
	private String key;
	private DateTime whenCreated;
	private DateTime whenChanged;
	private int generation;
	private int generationCreated;
	private boolean isLive;

	private boolean isRoot;

	private boolean isPublic;
	private String hash;
	private String publicUrl;
	private long size;

	boolean hasChildren;
	private List<NodeInfo> children;


	@JsonProperty("is_public")
	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@JsonProperty("hash")
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@JsonProperty("public_url")
	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	@JsonProperty("size")
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@JsonProperty("has_children")
	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@JsonProperty("children")
	public List<NodeInfo> getChildren() {
		return children;
	}

	public void setChildren(List<NodeInfo> children) {
		this.children = children;
	}

	@JsonProperty("kind")
	@JsonDeserialize(using = NodeKindDeserializer.class)
	public NodeKind getKind() {
		return kind;
	}

	public void setKind(NodeKind kind) {
		this.kind = kind;
	}

	@JsonProperty("resource_path")
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("parent_path")
	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@JsonProperty("volume_path")
	public String getVolumePath() {
		return volumePath;
	}

	public void setVolumePath(String volumePath) {
		this.volumePath = volumePath;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("when_created")
	@JsonDeserialize(using = DateTimeDeserializer.class)
	public DateTime getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(DateTime whenCreated) {
		this.whenCreated = whenCreated;
	}

	@JsonProperty("when_changed")
	@JsonDeserialize(using = DateTimeDeserializer.class)
	public DateTime getWhenChanged() {
		return whenChanged;
	}

	public void setWhenChanged(DateTime whenChanged) {
		this.whenChanged = whenChanged;
	}

	@JsonProperty("generation")
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	@JsonProperty("generation_created")
	public int getGenerationCreated() {
		return generationCreated;
	}

	public void setGenerationCreated(int generationCreated) {
		this.generationCreated = generationCreated;
	}

	@JsonProperty("is_live")
	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	@JsonProperty("is_root")
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	@Override
	public String toString() {
		return "{\n\t\"@type\": \"NodeInfo\"" +
				", \n\t\"kind\"=" + kind +
				", \n\t\"resourcePath\"='" + resourcePath + '\'' +
				", \n\t\"path\"='" + path + '\'' +
				", \n\t\"parentPath\"='" + parentPath + '\'' +
				", \n\t\"volumePath\"='" + volumePath + '\'' +
				", \n\t\"contentPath\"='" + getContentPath() + '\'' +
				", \n\t\"key\"='" + key + '\'' +
				", \n\t\"whenCreated\"=" + whenCreated +
				", \n\t\"whenChanged\"=" + whenChanged +
				", \n\t\"generation\"=" + generation +
				", \n\t\"generationCreated\"=" + generationCreated +
				", \n\t\"isLive\"=" + isLive +
				", \n\t\"isPublic\"=" + isPublic +
				", \n\t\"hash\"='" + hash + '\'' +
				", \n\t\"publicUrl\"='" + publicUrl + '\'' +
				", \n\t\"size\"=" + size +
				", \n\t\"hasChildren\"=" + hasChildren +
				", \n\t\"isRoot\"=" + isRoot +
				", \n\t\"children\"=" + children +
				"\n}";
	}

}
