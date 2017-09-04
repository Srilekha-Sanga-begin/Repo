package uk.tw.energy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.MeterReadings;
import uk.tw.energy.service.MeterReadingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readings")
public class MeterReadingController {

    private MeterReadingService meterReadingService;

    public MeterReadingController(MeterReadingService meterReadingService) {

        this.meterReadingService = meterReadingService;

    }

    @PostMapping("/store")
    public ResponseEntity storeReadings(@RequestBody MeterReadings meterReadings) {

        meterReadingService.storeReadings(meterReadings.getMeterId(), meterReadings.getElectricityReadings());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/read/{meterId}")
    public ResponseEntity readReadings(@PathVariable String meterId) {

        Optional<List<ElectricityReading>> readings = meterReadingService.getReadings(meterId);

        return readings.isPresent() ? ResponseEntity.ok(readings.get()) : ResponseEntity.notFound().build();

    }

}