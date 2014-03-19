package au.id.villar.ubuntuOne.filesV1;

import au.id.villar.ubuntuOne.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.joda.time.DateTime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilesAPIClient extends JsonAuthorizedAPIClient {

	private static final String CONTENT_ROOT = "https://files.one.ubuntu.com";

	private static final String FILES_BASE_URL = BASE_URL +  "/file_storage/v1";

	public FilesAPIClient(SSOCredentials credentials) {
		super(credentials);
	}

	public FilesAPIClient(ProxyData proxyData, SSOCredentials credentials) {
		super(proxyData, credentials);
	}

	public FilesInfo getInfo() throws UbuntuException {
		return getJsonObject(FilesInfo.class, FILES_BASE_URL);
	}

	public NodeInfo getNodeInfo(String volume, String path, boolean includeChildren) throws UbuntuException {
		return getJsonObject(NodeInfo.class, FILES_BASE_URL + "/" + volume + "/" + path +
				(includeChildren ? "?include_children=true" : ""));
	}

	public NodeInfo createFile(String volume, String path, NodeKind kind) throws UbuntuException {
		return putObject(NodeInfo.class, FILES_BASE_URL + "/" + volume + "/" + path,
				"{\"kind\":\"" + kind.getStr() + "\"}");
	}

	public NodeInfo move(String volume, String path, String newPath) throws UbuntuException {
		return putObject(NodeInfo.class, FILES_BASE_URL + "/" + volume + "/" + path, "{\"path\":\"" + newPath + "\"}");
	}

	public NodeInfo setPublic(String volume, String path, boolean isPublic) throws UbuntuException {
		return putObject(NodeInfo.class, FILES_BASE_URL + "/" + volume + "/" + path, "{\"public\":" + isPublic + "}");
	}

	public void deleteFile(String volume, String path) throws UbuntuException {
		deleteObject(FILES_BASE_URL + "/" + volume + "/" + path);
	}

	public NodeInfo uploadFile(NodeInfo nodeInfo, InputStream content) throws UbuntuException {
		return putObject(NodeInfo.class, CONTENT_ROOT + nodeInfo.getContentPath(), content, -1);
	}

	public NodeInfo uploadFile(NodeWithContentPath nodeInfo, Path path) throws UbuntuException {
		if(!Files.isRegularFile(path)) {
			throw new UbuntuException(path + " is not a regular file");
		}
		try {
			long size = Files.size(path);
			return putObject(NodeInfo.class, CONTENT_ROOT + nodeInfo.getContentPath(),
					Files.newInputStream(path), size);
		} catch (IOException e) {
			throw new UbuntuException(e);
		}
	}

	public NodeInfo tryMagicUpload(String volume, String path, Path localPath) throws UbuntuException {
		return putObject(NodeInfo.class, FILES_BASE_URL + "/" + volume + "/" + path,
				"{\"kind\":\"file\", " +
						"\"hash\": \"sha1:" + Tools.getFileHashSHA1(localPath) + "\", " +
						"\"magic_hash\": \"magic_hash:" + Tools.getMagicHashField(localPath) + "\"" +
						"}");
	}

	public void getFileContent(NodeInfo nodeInfo, OutputStream content) throws UbuntuException {
		HttpGet get = new HttpGet(CONTENT_ROOT + nodeInfo.getContentPath());
		try {
			Result result = execute(get, true, content);
		} catch (IOException e) {
			throw new UbuntuException(e);
		}
	}

	public List<NodeInfo> getPublicFiles() throws UbuntuException {
		List<?> list = getJsonObject(List.class, FILES_BASE_URL + "/public_files");
		List<NodeInfo> nodeList = new ArrayList<>();
		for(Object obj: list) {
			Map<?, ?> map = (Map<?, ?>)obj;
			NodeInfo node = new NodeInfo();
			node.setKey(map.get("key").toString());
			node.setPath(map.get("path").toString());
			node.setContentPath(map.get("content_path").toString());
			node.setResourcePath(map.get("resource_path").toString());
			node.setVolumePath(map.get("volume_path").toString());
			node.setGeneration(Integer.valueOf(map.get("generation").toString()));
			node.setGenerationCreated(Integer.valueOf(map.get("generation_created").toString()));
			node.setWhenCreated(new DateTime(map.get("when_created").toString()));
			node.setWhenChanged(new DateTime(map.get("when_changed").toString()));
			node.setKind(NodeKind.getType(map.get("type").toString()));
			node.setParentPath(map.get("parent_path").toString());
			node.setHash(map.get("hash").toString());
			node.setPublic((boolean)(Boolean)map.get("is_public"));
			node.setPublicUrl(map.get("public_url").toString());
			node.setSize((long)(Long)map.get("size"));
			node.setHasChildren((boolean)(Boolean)map.get("has_children"));
			node.setChildren(null);
			node.setLive((boolean)(Boolean)map.get("is_live"));
			node.setRoot((boolean)(Boolean)map.get("is_root"));
			nodeList.add(node);
		}
		return nodeList;
	}

	public List<VolumeInfo> getVolumes() throws UbuntuException {
		List<?> list = getJsonObject(List.class, FILES_BASE_URL + "/volumes");
		List<VolumeInfo> volumeList = new ArrayList<>();
		for(Object obj: list) {
			Map<?, ?> map = (Map<?, ?>)obj;
			VolumeInfo volume = new VolumeInfo();
			volume.setPath(map.get("path").toString());
			volume.setContentPath(map.get("content_path").toString());
			volume.setResourcePath(map.get("resource_path").toString());
			volume.setGeneration(Integer.valueOf(map.get("generation").toString()));
			volume.setWhenCreated(new DateTime(map.get("when_created").toString()));
			volume.setType(map.get("type").toString());
			volume.setNodePath(map.get("node_path").toString());
			volumeList.add(volume);
		}
		return volumeList;
	}

	public VolumeInfo getVolume(String name) throws UbuntuException {
		return getJsonObject(VolumeInfo.class, FILES_BASE_URL + "/volumes/" + name);
	}

	public VolumeInfo createVolume(String name) throws UbuntuException {
		return putObject(VolumeInfo.class, FILES_BASE_URL + "/volumes/" + name, null, -1);
	}

	public void deleteVolume(String name) throws UbuntuException {
		deleteObject(FILES_BASE_URL + "/volumes/" + name);
	}

}
