package fr.cszw.tddevwebavance2025.services;

import fr.cszw.tddevwebavance2025.exceptions.NotFoundException;
import fr.cszw.tddevwebavance2025.models.Light;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
@Slf4j
public class LightService {

    private List<Light> lights = new ArrayList<>();
    private long lastId = 0;

    public List<Light> getLights() {
        return this.lights;
    }

    public Light updateLight(Light light) throws NotFoundException {
        if (light.getId() == null) {
            light.setId(lastId);
            lastId++;
            this.lights.add(light);
            return light;
        } else {
            Optional<Light> found = this.lights
                    .stream()
                    .filter(lightKnown -> lightKnown.getId().compareTo(light.getId()) == 0)
                    .findFirst();
            if (found.isEmpty()) {
                log.error("Light with id {} not found", light.getId());
                throw new NotFoundException("Can't update light", "Can't find Light with id : " + light.getId());
            }

            found.get().setName(light.getName());
            found.get().setState(light.getState());
            return found.get();
        }
    }
}
