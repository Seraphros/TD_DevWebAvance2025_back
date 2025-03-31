package fr.cszw.tddevwebavance2025.repositories;

import fr.cszw.tddevwebavance2025.models.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
}
