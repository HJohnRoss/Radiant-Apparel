package com.radiantapparel.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiantapparel.project.Repositories.PriceRepository;

@Service
public class PriceService {
    @Autowired
	private PriceRepository priceRepository;
}
