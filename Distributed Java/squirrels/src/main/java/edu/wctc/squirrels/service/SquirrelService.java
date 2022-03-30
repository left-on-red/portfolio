package edu.wctc.squirrels.service;

import edu.wctc.squirrels.entity.Squirrel;

import java.util.List;

public interface SquirrelService {
    Squirrel getSquirrel(int squirrelId);

    List<Squirrel> getSquirrelList();

    void updateSquirrel(Squirrel squirrel);
}
