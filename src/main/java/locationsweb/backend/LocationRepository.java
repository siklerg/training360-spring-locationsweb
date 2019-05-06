package locationsweb.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import locationsweb.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}
