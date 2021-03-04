package com.cgt.backendComponent.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.exception.InternalServerError;
import com.cgt.backendComponent.helper.CallOpenCellAPI;
import com.cgt.backendComponent.model.CellModel;

@Service
public class OpenCellService {

	public ResponseEntity<CellModel> getCell(Optional<String> mcc, Optional<String> mnc, Optional<String> lac,
			Optional<String> cellid, ConfigUtils config, UUID loggerID) {

		CellModel station = CallOpenCellAPI.getCell(mcc, mnc, lac, cellid, config, loggerID);

		try {
			if (station.getMessage() != null) {
				throw new InternalServerError(station.getMessage(), "5");
			}

			if (station.getError() != null) {
				throw new InternalServerError(station.getError(), station.getCode());
			}

			
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage(), "5");
		}
		
		 
		return new ResponseEntity<CellModel>(station, HttpStatus.OK);
	}

}
