package com.cgt.backendComponent.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.exception.InternalServerError;
import com.cgt.backendComponent.model.DnsApiModel;
import com.cgt.backendComponent.model.IPAddressModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CallDnsResolverAPI {

	public static IPAddressModel callDnsApi(Optional<String> hostname, ConfigUtils config, UUID loggerID) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(config.getDnsApiEndpoint());
		builder.queryParam("name", hostname.get());

		String url = builder.build().toString();

		CustomLogger.formatLogMessage("DEBUG", loggerID, "CallDnsResolverAPI", "getIP",
				"Final URL before calling DNSResolverAPI ->  " + url);

		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class);

			DnsApiModel h = parseBody(response);

			if (h.getStatus().equals("3")) {
				CustomLogger.formatLogMessage("INFO", loggerID, "Unknown hostname " + hostname.get());
				return new IPAddressModel(Collections.emptyList());
			}

			List<String> addresses = new ArrayList<String>();

			if (h.getAnswer() != null) {
				for (Iterator<HashMap<String, String>> ans = h.getAnswer().iterator(); ans.hasNext();) {
					addresses.add(ans.next().get("data"));
				}
			}

			return new IPAddressModel(addresses);
		} catch (HttpStatusCodeException e) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "CallDnsResolverAPI", "getIP", "HttpStatusCodeException", e);
			throw new InternalServerError(e.getMessage(), "5");
		} catch (RestClientException e) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "CallDnsResolverAPI", "getIP", "RestClientException", e);
			throw new InternalServerError(e.getMessage(), "5");
		}
	}

	private static DnsApiModel parseBody(ResponseEntity<String> response) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(response.getBody(), DnsApiModel.class);
	}
}
