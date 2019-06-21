package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForePageController {

	@GetMapping("/")
	public String index() {
		return "redirect:home";
	}
	
	@GetMapping("home")
	public String home() {
		return "fore/home";
	}
	
	@GetMapping("register")
	public String register() {
		return "fore/register";
	}
	
	@GetMapping("registerSuccess")
	public String registerSuccess() {
		return "fore/registerSuccess";
	}
	
	@GetMapping("login")
	public String login() {
		return "fore/login";
	}
	
	@GetMapping("forelogout")
	public String forelogout(HttpSession session) {
		session.removeAttribute("user");
		//改成fore/home有什么区别？
		return "redirect:home";
	}
	
	@GetMapping("product")
    public String product(){
        return "fore/product";
    }
	
	@GetMapping("category")
    public String category(){
        return "fore/category";
    }
	
	@GetMapping("search")
	public String search(){
        return "fore/searchResult";
    }
	
	@GetMapping("buy")
	public String buy(){
        return "fore/buy";
    }
	
	@GetMapping("cart")
	public String cart(){
        return "fore/cart";
    }
		
	@GetMapping("alipay")
	public String alipay(){
        return "fore/alipay";
    }
	
	@GetMapping("payed")
	public String payed(){
        return "fore/payed";
    }
}
