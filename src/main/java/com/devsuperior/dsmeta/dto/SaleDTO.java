package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleDTO {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String name;

    public SaleDTO(Long id, Double amount, LocalDate date, String name) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    public SaleDTO(Sale entity) {
        id = entity.getId();
        amount = entity.getAmount();
        date = entity.getDate();
        name = entity.getSeller().getName();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}

