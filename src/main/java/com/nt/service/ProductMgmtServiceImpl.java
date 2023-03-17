package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Product;
import com.nt.repository.IProductRepository;

@Service
public class ProductMgmtServiceImpl implements IProductMgmtService {

	@Autowired
	private IProductRepository pRepo;
	
	@Override
	public Integer saveProduct(Product product) {
		return pRepo.save(product).getProdId();
	}

	@Override
	public void updateProduct(Product product) {
        pRepo.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		pRepo.deleteById(id);
	}

	@Override
	public Optional<Product> getProduct(Integer id) {
		return pRepo.findById(id);
	}

	@Override
	public List<Product> getAllProduct() {
		return pRepo.findAll();
	}

	@Override
	public boolean isProductExist(Integer id) {
		return pRepo.existsById(id);
	}

}
