package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.Price;
import com.radiantapparel.project.Repositories.PriceRepository;

@Service
public class PriceService {
    @Autowired
	private PriceRepository priceRepository;

    public List<Price> allPrices(){
        return priceRepository.findAll();
    }
}
