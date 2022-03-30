package edu.wctc.squirrels.service;

import edu.wctc.squirrels.entity.Squirrel;
import edu.wctc.squirrels.repo.SquirrelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasicSquirrelService implements SquirrelService {
    private SquirrelRepository squirrelRepository;

    @Autowired
    public BasicSquirrelService(SquirrelRepository sr) {
        this.squirrelRepository = sr;
    }

    @Override
    public Squirrel getSquirrel(int squirrelId) {
        Optional<Squirrel> s = squirrelRepository.findById(squirrelId);
        if (s.isPresent()) {
            return s.get();
        }

        return null;
    }

    @Override
    public List<Squirrel> getSquirrelList() {
        List<Squirrel> list = new ArrayList<>();
        squirrelRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void updateSquirrel(Squirrel squirrel) {
        squirrelRepository.save(squirrel);
    }
}
