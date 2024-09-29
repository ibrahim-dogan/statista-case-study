package com.statista.taxservice.controller;

import com.statista.taxservice.model.TaxRequest;
import com.statista.taxservice.model.TaxResponse;
import com.statista.taxservice.service.TaxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping("/rate")
    public ResponseEntity<TaxResponse> getTaxRate(@RequestBody TaxRequest request) {
        TaxResponse taxResponse = taxService.getTaxRate(request);
        return new ResponseEntity<>(taxResponse, HttpStatus.OK);
    }
}
