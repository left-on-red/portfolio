package edu.wctc.squirrels.repo;

import edu.wctc.squirrels.entity.Squirrel;
import org.springframework.data.repository.CrudRepository;

public interface SquirrelRepository extends CrudRepository<Squirrel, Integer> {

}
