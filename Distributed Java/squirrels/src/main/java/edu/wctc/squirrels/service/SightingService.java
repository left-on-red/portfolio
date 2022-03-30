package edu.wctc.squirrels.service;

import edu.wctc.squirrels.entity.Sighting;
import edu.wctc.squirrels.entity.Squirrel;

import java.util.List;

public interface SightingService {
    void saveSighting(Sighting sighting);

    List<Sighting> getSightingsForSquirrel(int squirrelId);
}
