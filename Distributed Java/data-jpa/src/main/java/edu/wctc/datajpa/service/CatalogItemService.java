package edu.wctc.datajpa.service;

import edu.wctc.datajpa.entity.CatalogItem;

import java.util.List;

public interface CatalogItemService {
    CatalogItem getItem(int catalogItemId);

    List<CatalogItem> getItems();

    void updateItem(CatalogItem item);
}
