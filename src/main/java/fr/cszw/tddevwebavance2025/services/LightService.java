package fr.cszw.tddevwebavance2025.services;

import fr.cszw.tddevwebavance2025.exceptions.NotFoundException;
import fr.cszw.tddevwebavance2025.models.Light;
import fr.cszw.tddevwebavance2025.repositories.LightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class LightService {

    private final LightRepository lightRepository;

    private long lastId = 0;

    public List<Light> getLights() {
        return this.lightRepository.findAll();
    }

    public Light updateLight(Light light) throws NotFoundException {
        if (light.getId() == null) {
            light = this.lightRepository.save(light);
            return light;
        } else {
            Light finalLight = light;
            Optional<Light> found = this.getLights()
                    .stream()
                    .filter(lightKnown -> lightKnown.getId().compareTo(finalLight.getId()) == 0)
                    .findFirst();
            if (found.isEmpty()) {
                log.error("Light with id {} not found", light.getId());
                throw new NotFoundException("Can't update light", "Can't find Light with id : " + light.getId());
            }

            found.get().setName(light.getName());
            found.get().setState(light.getState());
            this.lightRepository.save(found.get());
            return found.get();
        }
    }

    public void deleteLight(Light light) throws NotFoundException {
        if (light.getId() == null) {
            log.error("Light with id null not found");
            throw new NotFoundException("Can't delete light", "Can't delete Light with id null");
        }

        Optional<Light> found = this.getLights()
                .stream()
                .filter(lightKnown -> lightKnown.getId().compareTo(light.getId()) == 0)
                .findFirst();
        if (found.isEmpty()) {
            log.error("Light with id {} not found", light.getId());
            throw new NotFoundException("Can't delete light", "Can't find Light with id : " + light.getId());
        }

        this.lightRepository.delete(found.get());
    }

    public List<Light> invertAllLights() {
        List<Light> lights = this.getLights();
        lights.forEach(light -> light.setState(!light.getState()));
        this.lightRepository.saveAll(lights);
        return lights;
    }
}
