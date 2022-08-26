package com.gsore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gsore.domain.Product;
import com.gsore.service.ProductService;

@Controller
public class HomeController {
		
	@Autowired
	private ProductService articleService;
	
	
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {		
		List<Product> articles = articleService.findFirstArticles();
		model.addAttribute("articles", articles);
		return "index";
	}

	
}
