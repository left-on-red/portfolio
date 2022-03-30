package edu.wctc.datajpa.repo;

import edu.wctc.datajpa.entity.CatalogItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogItemRepository extends CrudRepository<CatalogItem, Integer> {
    List<CatalogItem> findAllByOrderByName();
}
