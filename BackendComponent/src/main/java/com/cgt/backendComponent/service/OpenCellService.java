package com.cgt.backendComponent.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.exception.InternalServerError;
import com.cgt.backendComponent.helper.CallOpenCellAPI;
import com.cgt.backendComponent.helper.CustomLogger;
import com.cgt.backendComponent.helper.ErrorCode;
import com.cgt.backendComponent.model.CellModel;

@Service
public class OpenCellService {

	public ResponseEntity<CellModel> getCell(Optional<String> mcc, Optional<String> mnc, Optional<String> lac,
			Optional<String> cellid, ConfigUtils config, UUID loggerID) {

		CellModel station = CallOpenCellAPI.getCell(mcc, mnc, lac, cellid, config, loggerID);

		if (station.getMessage() != null) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "OpenCellService", "getCell",
					"CustomException", station.getMessage());
			throw new InternalServerError(station.getMessage(), ErrorCode.internalServerErrorCode);
		}

		if (station.getError() != null) {
			CustomLogger.formatLogMessage("ERROR", loggerID, "OpenCellService", "getCell",
					"CustomException", station.getError());
			throw new InternalServerError(station.getError(), station.getCode());
		}

		return new ResponseEntity<CellModel>(station, HttpStatus.OK);
	}

}
