package edu.wctc.datajpa.service;

import edu.wctc.datajpa.entity.CatalogItem;
import edu.wctc.datajpa.repo.CatalogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasicCatalogItemService implements CatalogItemService {
    private CatalogItemRepository catalogItemRepository;

    @Autowired
    public BasicCatalogItemService(CatalogItemRepository repo) { this.catalogItemRepository = repo; }

    @Override
    public CatalogItem getItem(int catalogItemId) {
        Optional<CatalogItem> item = catalogItemRepository.findById(catalogItemId);
        if (item.isPresent()) { return item.get(); }
        return null;
    }

    @Override
    public List<CatalogItem> getItems() { return catalogItemRepository.findAllByOrderByName(); }

    @Override
    public void updateItem(CatalogItem item) { catalogItemRepository.save(item); }
}
