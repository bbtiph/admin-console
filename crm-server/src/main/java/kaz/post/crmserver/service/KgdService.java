package kaz.post.crmserver.service;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import kaz.post.crmserver.config.RestTemplateConfig;
import kaz.post.crmserver.dto.KgdDTO;
import kaz.post.crmserver.exceptions.KgdServiceException;
import kaz.post.crmserver.exceptions.RegExceptionType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@Service
public class KgdService {

	Logger log = LoggerFactory.getLogger(KgdService.class);

	@Value("${kgdIinBin.url}")
	private String kgdIinBinUrl;
	@Value("${kgdIinBin.auth}")
	private String kgdIinBinAuth;

	private HttpEntity request;

	public KgdDTO requestKgdData(String iinBin) throws KgdServiceException {

		KgdDTO kgdDTO = new KgdDTO();
		HttpHeaders headers = new HttpHeaders();
		if (StringUtils.isNotBlank(iinBin)) {
			headers.add("Authentication", kgdIinBinAuth);
			this.request = new HttpEntity(headers);
			String requestUrl = kgdIinBinUrl.concat(iinBin);
			RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
			restTemplate.getMessageConverters()
				.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			ResponseEntity<String> response = restTemplate.exchange(
				requestUrl,
				HttpMethod.GET,
				request,
				String.class
			);
			JsonObject parser = null;
			if (response.hasBody()) {
				log.info("KgdService response body: {}", response.getBody());
				Reader reader = new StringReader(response.getBody());
				try {
					parser = (JsonObject) Jsoner.deserialize(reader);
				} catch (JsonException e) {
					e.printStackTrace();
					throw new KgdServiceException(RegExceptionType.KGD_SERVICE_JSON_FAULT);
				}
				kgdDTO.setExist(true);
				kgdDTO.setResidFl((String) parser.get("RESIDFL"));
				kgdDTO.setRnn((String) parser.get("RNN"));
				kgdDTO.setCorrectDt((String) parser.get("CORRECTDT"));
				kgdDTO.setInactive((String) parser.get("INACTIVE"));
				kgdDTO.setTaxDepId((String) parser.get("TAXDEP_ID"));
				kgdDTO.setJurFl((String) parser.get("JURFL"));
				kgdDTO.setId((String) parser.get("ID"));
				kgdDTO.setConstFl((String) parser.get("CONSTFL"));
				kgdDTO.setIndividFl((String) parser.get("INDIVIDFL"));
				kgdDTO.setIinBin((String) parser.get("IIN"));
				String fio = (String) parser.get("FIO");
				kgdDTO.setFio(fio);
				kgdDTO.setName((String) parser.get("NAME"));
			} else {
				kgdDTO.setExist(false);
			}
		} else {
			throw new KgdServiceException(RegExceptionType.IIN_BIN_EMPTY);
		}
		return kgdDTO;
	}
}
