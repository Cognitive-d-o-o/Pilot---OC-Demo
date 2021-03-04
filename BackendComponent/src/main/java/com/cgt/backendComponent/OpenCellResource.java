package com.cgt.backendComponent;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.exception.InternalServerError;
import com.cgt.backendComponent.helper.CustomLogger;
import com.cgt.backendComponent.model.CellModel;
import com.cgt.backendComponent.service.OpenCellService;

@RestController
@RequestMapping(produces = "application/json; charset=UTF-8", value = "/cell")
public class OpenCellResource {

	@Autowired
	OpenCellService stationService;

	@Autowired
	ConfigUtils config;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CellModel> getCell(@RequestParam Optional<String> mcc, @RequestParam Optional<String> mnc,
			@RequestParam Optional<String> lac, @RequestParam Optional<String> cellid) {
		UUID loggerID = UUID.randomUUID();
		if(!mcc.isPresent() || !mnc.isPresent() || !lac.isPresent() || !cellid.isPresent()) {
			throw new InternalServerError("All of these (mcc, mnc, lac, cellid) parameters must be provided!", "1");
		}

		CustomLogger.formatLogMessage("INFO", loggerID, "OpenCellResource", "getCell", "Get cell API invoked");

		ResponseEntity<CellModel> returnedStation = stationService.getCell(mcc, mnc, lac, cellid, config, loggerID);

		return returnedStation;
	}

}
