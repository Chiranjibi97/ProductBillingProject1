package com.nt.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Product_Info")
public class Product {
	@Id
	@GeneratedValue
	private Integer prodId;
	@NonNull
	private String productName;
	@NonNull
	private String prodCode;
	@NonNull
	private Double prodCost;
	@NonNull
	private Double prodDiscount;
	@NonNull
	private Double prodGst;

}
