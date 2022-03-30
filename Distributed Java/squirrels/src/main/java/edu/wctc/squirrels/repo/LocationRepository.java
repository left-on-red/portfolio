package edu.wctc.squirrels.repo;

import edu.wctc.squirrels.entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    List<Location> findAllByOrderByCountryAscName();
}
