package com.jun.billing.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderRequest {
    private Integer userId;
    private Integer page =1;
    private Integer size=10;
    private String orderBy = "date";
    private boolean sort = false;
    private Integer typeId;
    private String description;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal amount;
    private LocalDate[] dates = new LocalDate[2];
    private LocalDate dateFm;
    private LocalDate dateTo;

    public LocalDate getDateFm() {
        if(this.dates.length > 1){
            return this.dates[0];
        }
        else {
            return null;
        }
    }

    public LocalDate getDateTo() {
        if(this.dates.length > 1){
            return this.dates[1];
        }
        else {
            return null;
        }
    }
}
