package com.radiantapparel.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Models.PriceDatabase;
import com.radiantapparel.project.Repositories.PriceRepository;

@Service
public class PriceService {
    @Autowired
	private PriceRepository priceRepository;

    public List<PriceDatabase> allPrices(){
        return priceRepository.findAll();
    }

    public PriceDatabase createPrice(PriceDatabase price) {
        priceRepository.save(price);
        return price;
    }

    public void deletePrice(PriceDatabase price) {
        priceRepository.delete(price);
    }
}
