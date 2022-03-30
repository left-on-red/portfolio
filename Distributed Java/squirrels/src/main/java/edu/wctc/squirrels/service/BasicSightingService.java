package edu.wctc.squirrels.service;

import edu.wctc.squirrels.entity.Sighting;
import edu.wctc.squirrels.entity.Squirrel;
import edu.wctc.squirrels.repo.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicSightingService implements SightingService {
    private SightingRepository sightingRepository;

    @Autowired
    public BasicSightingService(SightingRepository sr) {
        this.sightingRepository = sr;
    }

    @Override
    public void saveSighting(Sighting sighting) {
        sightingRepository.save(sighting);
    }

    @Override
    public List<Sighting> getSightingsForSquirrel(int squirrelId) {
        return sightingRepository.findAllBySquirrelIdOrderBySpottedAt(squirrelId);
    }
}
