package com.cgt.backendComponent.helper;

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
import com.cgt.backendComponent.model.CellModel;

public class CallOpenCellAPI {

	public static CellModel getCell(Optional<String> mcc, Optional<String> mnc, Optional<String> lac,
			Optional<String> cellid, ConfigUtils config, UUID loggerID) {

		String key = config.getKey();
		String format = config.getFormat();
		String url = config.getOpenCellAPIUrl();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		builder.queryParam("key", key);
		builder.queryParam("mcc", mcc.get());
		builder.queryParam("mnc", mnc.get());
		builder.queryParam("lac", lac.get());
		builder.queryParam("cellid", cellid.get());
		builder.queryParam("format", format);

		String unencodedUrl = builder.build().toString();

		CustomLogger.formatLogMessage("DEBUG", loggerID, "CallOpenCellAPI", "getCell",
				"Final URL before call OpenCellAPI ->  " + unencodedUrl);
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CellModel> responseEntity = restTemplate.getForEntity(unencodedUrl, CellModel.class);
			CellModel station = responseEntity.getBody();

			CustomLogger.formatLogMessage("DEBUG", loggerID, "CallOpenCellAPI", "getCell",
					" API response ->  " + station.toString());

			return station;

		} catch (HttpStatusCodeException e) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "CallOpenCellAPI", "getCell", "HttpStatusCodeException",
					e);
			throw new InternalServerError(e.getMessage(), ErrorCode.internalServerErrorCode);
		} catch (RestClientException e) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "CallOpenCellAPI", "getCell", "RestClientException", e);
			throw new InternalServerError(e.getMessage(), ErrorCode.internalServerErrorCode);
		}

	}

}
