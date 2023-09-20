package com.currency.lib;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class CurrencyParameters {
	
	@JsonIgnore
	@Id
	private Long id;
	@Column(name="currency_from")
	private String from;
	@Column(name="currency_to")
	private String to;
	@Transient
	private BigDecimal totalAmount;
	public CurrencyParameters( String from, String to, BigDecimal totalAmount) {
		super();
		this.from = from;
		this.to = to;
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public CurrencyParameters() {
		super();
	}

	private Integer conversion_amount;
	public Long getId() {
		return id;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public Integer getConversion_amount() {
		return conversion_amount;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setConversion_amount(Integer conversion_amount) {
		this.conversion_amount = conversion_amount;
	}
	
	
	

}
