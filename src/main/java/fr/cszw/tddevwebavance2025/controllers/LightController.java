package fr.cszw.tddevwebavance2025.controllers;

import fr.cszw.tddevwebavance2025.exceptions.NotFoundException;
import fr.cszw.tddevwebavance2025.models.Light;
import fr.cszw.tddevwebavance2025.services.LightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/light")
@RequiredArgsConstructor
public class LightController {

    private final LightService lightService;

    @GetMapping
    public List<Light> getAllLights() {
        return this.lightService.getLights();
    }

    @PostMapping
    public ResponseEntity<Light> addLight(@RequestBody Light light) {
        try {
            return new ResponseEntity<>(this.lightService.updateLight(light), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
