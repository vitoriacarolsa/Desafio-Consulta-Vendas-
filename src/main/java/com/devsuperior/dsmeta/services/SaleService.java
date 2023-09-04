package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleDTO> getReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate minDate = parseMinDate(minDateStr, maxDateStr);
		LocalDate maxDate = parseMaxDate(maxDateStr);

		Page<Sale> result = repository.searchSalesByParams(minDate, maxDate, name, pageable);
		return result.map(SaleDTO::new);
	}

	public Page<SaleSumaryDTO> getSumary(String minDateStr, String maxDateStr, Pageable pageable){
		LocalDate minDate = parseMinDate(minDateStr, maxDateStr);
		LocalDate maxDate = parseMaxDate(maxDateStr);

		return repository.searchSalesSumary(minDate, maxDate, pageable);
	}

	private LocalDate parseMinDate(String minDateStr, String maxDateStr) {
		LocalDate maxDate = parseMaxDate(maxDateStr);
		return minDateStr.isEmpty() ? maxDate.minusYears(1L) : LocalDate.parse(minDateStr);
	}

	private LocalDate parseMaxDate(String maxDateStr) {
		return maxDateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(maxDateStr);
	}

}
