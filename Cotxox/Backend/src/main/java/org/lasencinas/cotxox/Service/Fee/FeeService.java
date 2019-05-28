package org.lasencinas.cotxox.Service.Fee;

import org.lasencinas.cotxox.Model.Fee;

import java.util.List;


public interface FeeService {

    List<Fee> findAll();

    double getCost(double multiplier, String feeName);
}
