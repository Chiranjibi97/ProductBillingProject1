package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.entity.Product;

public interface IProductMgmtService {

	public Integer saveProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(Integer id);
	public Optional<Product> getProduct(Integer id);
	public List<Product> getAllProduct();
	public boolean isProductExist(Integer id);
}
