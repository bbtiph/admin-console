package kaz.post.crmserver.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Component
@Scope("prototype")
public class RestTemplateConfig {

	@Value("${kisc.keyStorePath}")
	private String keyStorePath;
	@Value("${kisc.keyStorePass}")
	private String keyStorePass;

	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(120000);
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(120000);
		return restTemplate;
	}

	public RestTemplate restTemplate4Kisc() {
		InputStream resourceAsStream = RestTemplateConfig.class.getResourceAsStream(keyStorePath);
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(resourceAsStream, keyStorePass.toCharArray());
		} catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SSLContext sslContext = null;
		try {
			sslContext = SSLContextBuilder
				.create()
				.loadKeyMaterial(ks, keyStorePass.toCharArray())
				.loadTrustMaterial(null, new TrustSelfSignedStrategy())
				.build();
		} catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | KeyManagementException e) {
			e.printStackTrace();
		}

		HttpClient client = HttpClients.custom()
			.setSSLContext(sslContext)
			.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
			new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(client);
		return new RestTemplate(requestFactory);
	}

}
