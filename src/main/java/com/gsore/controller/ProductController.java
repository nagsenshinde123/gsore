package com.gsore.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsore.domain.Product;
import com.gsore.domain.ProductBuilder;
import com.gsore.domain.StorageSize;
import com.gsore.domain.MobileColor;
import com.gsore.domain.RamSize;
import com.gsore.service.ProductService;

@Controller
@RequestMapping("/article")
public class ProductController {

	@Autowired
	private ProductService articleService;
	
	@RequestMapping("/add")
	public String addArticle(Model model) {
		Product article = new Product();
		model.addAttribute("article", article);
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allCategories", articleService.getAllCategories());
		return "addArticle";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addArticlePost(@ModelAttribute("article") Product article, HttpServletRequest request) {
		Product newArticle = new ProductBuilder()
				.withTitle(article.getTitle())
				.stockAvailable(article.getStock())
				.withPrice(article.getPrice())
				.imageLink(article.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.build();		
		articleService.saveArticle(newArticle);	
		return "redirect:article-list";
	}
	
	@RequestMapping("/article-list")
	public String articleList(Model model) {
		List<Product> articles = articleService.findAllArticles();
		model.addAttribute("articles", articles);
		return "articleList";
	}
	
	
}
