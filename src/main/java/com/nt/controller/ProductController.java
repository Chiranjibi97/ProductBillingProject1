package com.nt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Product;
import com.nt.service.IProductMgmtService;

@RestController
@RequestMapping("/rest")
public class ProductController {

	@Autowired
	private IProductMgmtService service;

	// save Product
	@PostMapping("/save")
	public ResponseEntity<String> registerProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;

		try {
			if (product.getProdId() != null && service.isProductExist(product.getProdId())) {
				resp = new ResponseEntity<String>("Product Already Exist with this given id " + product.getProdId(),
						HttpStatus.BAD_REQUEST);
			} else {
				int pid = service.saveProduct(product);
				resp = new ResponseEntity<String>("Product Register successfully with id " + pid, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Problem while saving Product ", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	// For update operation
	@PutMapping("/modify")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {

		ResponseEntity<String> resp = null;
		try {
			if (service.isProductExist(product.getProdId()) && product.getProdId() != null) {
				service.updateProduct(product);
				resp = new ResponseEntity<String>("Product value Modifyed by this given id " + product.getProdId(),
						HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(
						"Product not found for modification with given id " + product.getProdId(),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>(" Can not perform Update Operation ", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	// Fetch One Product
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Integer id) {
		ResponseEntity<?> res = null;
		try {
			Optional<Product> opt = service.getProduct(id);
			if (opt.isPresent()) {
				Product product = opt.get();
				res = new ResponseEntity<Product>(product, HttpStatus.OK);
			} else {
				res = new ResponseEntity<String>("Product not exist in this given id " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			res = new ResponseEntity<String>(" Internal Problem try again ", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return res;
	}

	// FetchAllProduct
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProduct() {
		ResponseEntity<?> res = null;
		try {
			List<Product> list = service.getAllProduct();
			if (list != null) {
				res = new ResponseEntity<List<Product>>(list, HttpStatus.OK);
			} else {
				res = new ResponseEntity<String>("No Product found ", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			res = new ResponseEntity<String>("Internal Server Error ,Try Again Latter",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return res;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable Integer id){
		ResponseEntity<String> res=null;
		try {
			if(service.isProductExist(id)) {
				service.deleteProduct(id);
				res = new ResponseEntity<>("Product deleted of this given id "+id,HttpStatus.OK);
			}else {
				res = new ResponseEntity<>("Product not found on this given id "+id,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			res = new ResponseEntity<>(" Internal Problem, Try Again "+id,HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return res;
	}
}
