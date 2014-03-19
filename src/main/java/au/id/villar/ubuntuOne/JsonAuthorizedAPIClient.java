package au.id.villar.ubuntuOne;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonAuthorizedAPIClient extends AuthorizedAPIClient {

	protected static final String BASE_URL = "https://one.ubuntu.com/api";

	private static final String CONTENT_LENGTH = "Content-Length";

	protected JsonAuthorizedAPIClient(SSOCredentials credentials) {
		super(credentials);
	}

	protected JsonAuthorizedAPIClient(ProxyData proxyData, SSOCredentials credentials) {
		super(proxyData, credentials);
	}

	protected <T> T getJsonObject(Class<T> clazz, String url) throws UbuntuException {
		HttpGet get = new HttpGet(url);
		return opJsonObject(clazz, get);
	}

	protected void deleteObject(String url) throws UbuntuException {
		HttpDelete delete = new HttpDelete(url);
		opJsonObject(null, delete);
	}

	protected <T> T putObject(Class<T> clazz, String url, String content) throws UbuntuException {
		ByteArrayInputStream streamContent = new ByteArrayInputStream(content.getBytes());
		return putObject(clazz, url, streamContent, -1);
	}

	protected <T> T putObject(Class<T> clazz, String url, InputStream content, long contentLength)
			throws UbuntuException {
		HttpPut put = new HttpPut(url);
		if(content != null) {
			addEntity(put, content);
			if(contentLength > 0) {
				put.addHeader(new BasicHeader(CONTENT_LENGTH, String.valueOf(contentLength)));
			}
		}
		return opJsonObject(clazz, put);
	}

	private <T> T opJsonObject(Class<T> clazz, HttpRequestBase op)
			throws UbuntuException {
		try {
			Result result = execute(op, false);
			if(result.httpStatus == STATUS_CODE_OK || result.httpStatus == STATUS_CREATED) {
				return clazz != null? new ObjectMapper().readValue(result.content, clazz): null;
			}
			throwException(result);
			return null;
		} catch (IOException e) {
			throw new UbuntuException(e);
		}
	}

	private void addEntity(HttpEntityEnclosingRequestBase request, InputStream content) {
		BasicHttpEntity entity = new BasicHttpEntity();
		entity.setContent(content);
		request.setEntity(entity);
	}

	private void throwException(Result result) throws UbuntuAPIException {
		String error;
		try {
			error = (String)new ObjectMapper().readValue(result.content, Map.class).get("error");
		} catch (Exception e) {
			error = new String(result.content);
		}
		throw new UbuntuAPIException(result.httpStatus, error);
	}
}
