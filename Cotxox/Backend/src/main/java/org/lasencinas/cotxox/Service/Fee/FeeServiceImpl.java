package org.lasencinas.cotxox.Service.Fee;

import org.lasencinas.cotxox.Model.Fee;
import org.lasencinas.cotxox.Repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tarifaService")
public class FeeServiceImpl implements FeeService {
    private FeeRepository feeRepository;


    //-------------------------------------- Constructor --------------------------------------------------------//


    @Autowired
    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }


    //-------------------------------------- Interfaz pública --------------------------------------------------------//


    public List<Fee> findAll() {
        return feeRepository.findAll();
    }


    public double getCost(double multiplier, String feeName) {
        return (feeRepository.getFeeByName(feeName) != null)
                ? feeRepository.getFeeByName(feeName).getCost() * multiplier : null;
    }


}
