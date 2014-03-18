package au.id.villar.ubuntuOne;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.*;

public class APIClient {

	protected static final int STATUS_CODE_OK = 200;
	protected static final int STATUS_CREATED = 201;
	protected static final int STATUS_UNAUTHORIZED = 401;


	private String proxyName;
	private int proxyPort;
	private String proxyUsername;
	private String proxyPassword;

	protected APIClient(String proxyName, int proxyPort, String proxyUsername, String proxyPassword) {
		this.proxyName = proxyName;
		this.proxyPort = proxyPort;
		this.proxyUsername = proxyUsername;
		this.proxyPassword = proxyPassword;
	}

	protected CloseableHttpClient buildHttpClient() {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		if(proxyName != null) {
			HttpHost proxy = new HttpHost(proxyName, proxyPort);
			clientBuilder = clientBuilder.setProxy(proxy);
			if(proxyUsername != null) {
				CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider.setCredentials(
						new AuthScope(proxy),
						new UsernamePasswordCredentials(proxyUsername, proxyPassword));
				clientBuilder = clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			}
		}
		return clientBuilder.build();
	}

	protected static abstract class HttpHeader {
		public final String name;

		public HttpHeader(String name) {
			this.name = name;
		}
	}

	protected static class SingleHttpHeader extends HttpHeader {
		public final String value;

		public SingleHttpHeader(String name, String value) {
			super(name);
			this.value = value;
		}
	}

	protected static class MultipleHttpHeader extends HttpHeader {
		public final List<String> values;

		public MultipleHttpHeader(String name, List<String> values) {
			super(name);
			this.values = Collections.unmodifiableList(values);
		}
	}

	protected static class Result {
		public final int httpStatus;
		public final List<HttpHeader> headers;
		public final byte[] content;

		public Result(int httpStatus, List<HttpHeader> headers, byte[] content) {
			this.httpStatus = httpStatus;
			this.headers = headers;
			this.content = content;
		}

	}

	protected Result execute(HttpRequestBase request, boolean includeHeaders) throws IOException {
		return execute(request, includeHeaders, null);
	}

	protected Result execute(HttpRequestBase request, boolean includeHeaders, OutputStream contentTarget)
			throws IOException {
		try(CloseableHttpClient httpClient = buildHttpClient()) {
			try(CloseableHttpResponse response = httpClient.execute(request)) {
				int status = response.getStatusLine().getStatusCode();
				List<HttpHeader> headers = includeHeaders? getHeaders(response): null;
				byte[] output;
				if(contentTarget != null) {
					response.getEntity().writeTo(contentTarget);
					output = null;
				} else {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					response.getEntity().writeTo(outputStream);
					output = outputStream.toByteArray();
				}
				return new Result(status, headers, output);
			}
		}
	}

	private List<HttpHeader> getHeaders(HttpResponse response) {
		List<HttpHeader> httpHeaders = new ArrayList<>();
		Map<String, List<Header>> headers = new HashMap<>();
		for(Header header: response.getAllHeaders()) {
			List<Header> headerList = headers.get(header.getName());
			if(headerList == null) {
				headerList = new ArrayList<>();
				headers.put(header.getName(), headerList);
			}
			headerList.add(header);
		}
		for(String name: headers.keySet()) {
			List<Header> headerList = headers.get(name);
			HttpHeader httpHeader;
			if(headerList.size() == 1) {
				httpHeader = new SingleHttpHeader(name, headerList.get(0).getValue());
			} else {
				List<String> values = new ArrayList<>();
				for(Header header: headerList) {
					values.add(header.getValue());
				}
				httpHeader = new MultipleHttpHeader(name, values);
			}
			httpHeaders.add(httpHeader);
		}
		return httpHeaders;
	}

}
