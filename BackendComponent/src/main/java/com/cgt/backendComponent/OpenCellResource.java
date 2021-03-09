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
import com.cgt.backendComponent.helper.ErrorCode;
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
		CustomLogger.formatLogMessage("INFO", loggerID, "OpenCellResource", "getCell", "Get cell API invoked");

		if (!mcc.isPresent() || !mnc.isPresent() || !lac.isPresent() || !cellid.isPresent()) {
			String message = "All of these (mcc, mnc, lac, cellid) parameters must be presented!";
			CustomLogger.formatLogMessage("ERROR", loggerID, "OpenCellResource", "getCell", "CustomException", message);
			throw new InternalServerError(message, ErrorCode.internalServerErrorCode);
		}

		if (mcc.get().trim().equals("") || mnc.get().trim().equals("") || lac.get().trim().equals("") || cellid.get().trim().equals("")) {
			String message = "Each of these (mcc, mnc, lac, cellid) parameters must not be empty!";
			CustomLogger.formatLogMessage("ERROR", loggerID, "OpenCellResource", "getCell", "CustomException", message);
			throw new InternalServerError(message, ErrorCode.internalServerErrorCode);
		}

		ResponseEntity<CellModel> returnedStation = stationService.getCell(mcc, mnc, lac, cellid, config, loggerID);

		CustomLogger.formatLogMessage("INFO", loggerID, "OpenCellResource", "getCell", "Cell API finished");

		return returnedStation;
	}
}
