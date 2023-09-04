package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query("SELECT obj " +
          "FROM Sale obj " +
          "WHERE obj.date BETWEEN :minDate AND :maxDate " +
          "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
  Page<Sale> searchSalesByParams(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

  @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumaryDTO(obj.seller.name, SUM(obj.amount)) " +
          "FROM Sale obj " +
          "WHERE obj.date BETWEEN :minDate AND :maxDate " +
          "GROUP BY obj.seller.name")
  Page<SaleSumaryDTO> searchSalesSumary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
