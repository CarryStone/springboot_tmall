package com.example.demo.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.ProductImage;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import com.example.demo.util.Constants;
import com.example.demo.util.ImageUtil;

@RestController
public class ProductImageController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService service;
	
	@GetMapping("ProductImages")
	public List<ProductImage> list(int pid,String type) throws Exception {
		Product product = productService.findProductById(pid);
		return service.findProductImageByProduct(product,type);
	}
	
	@PostMapping("ProductImages/{pid}")
	public ProductImage add(ProductImage bean,@PathVariable("pid") int pid,MultipartFile file,HttpServletRequest request) throws Exception {	
		Product product = new Product();
		product.setId(pid);
		bean.setProduct(product);
		service.addProductImage(bean);
		String type = bean.getType();
		String folder = "";
		if(type!=null&&Constants.single_image.equals(type)) {
		    folder = Constants.single_folder;
		}else {
			folder = Constants.detail_folder;
		}
		//保存图片，并转换成jpg格式
		ImageUtil.saveImg(bean.getId(), file, folder, request);
		//修改单个图片大小
		if(Constants.single_image.equals(type)) {
		String imageFolder_small= request.getServletContext().getRealPath(Constants.productSingle_small);
        String imageFolder_middle= request.getServletContext().getRealPath(Constants.productSingle_middle);    
        File f_small = new File(imageFolder_small, bean.getId()+".jpg");
        File f_middle = new File(imageFolder_middle, bean.getId()+".jpg");
        f_small.getParentFile().mkdirs();
        f_middle.getParentFile().mkdirs();
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
		File files = new File(imageFolder,bean.getId()+".jpg");
        ImageUtil.resizeImage(files, 56, 56, f_small);
        ImageUtil.resizeImage(files, 217, 190, f_middle); 
		}
		return bean;
	}
	
	@DeleteMapping("ProductImages/{id}")
	public Object delete(@PathVariable("id") int id) throws Exception {
		service.deleteProductImage(id);
		return null;
	}

}
