package com.concretepage.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.concretepage.ItemsProperties;
import com.concretepage.ItemsProperties.Items;
import com.concretepage.entity.Article2;
import com.concretepage.entity.ArticleForm;
import com.concretepage.service.IUserInfoService;

@Controller
@RequestMapping("app")
@Configuration
@ComponentScan(basePackages = { "com.concretepage.*" })
@PropertySource("classpath:items.properties")
public class UserInfoController {
	private ItemsProperties items;
	@Value("${app.items}")
	  private String[] myValues;
	@Autowired
	private IUserInfoService userInfoService;
	@GetMapping("login")
	public ModelAndView login() {
		    ModelAndView mav = new ModelAndView();
		    mav.setViewName("custom-login");
		    return mav;
    }	
	@GetMapping("secure/article-details")
	public ModelAndView getArticlesPage() {
		    //ModelAndView mav = new ModelAndView();
		    /*String itemProperties = items.toString();
		    Map<String,String> itemList = new LinkedHashMap<String,String>();
		    String[] array = itemProperties.split(",");
		    for(int i=0;i<array.length;i++) {
		    	System.out.println(array.length);
		    	System.out.println("array0000"+array[0]);
		    	String value[] = array[i].split("=");
		    	 itemList.put(value[0], value[1]);
		    	 System.out.println("item1 value"+value[0] + value[1]);
		    }*/
		Map referenceData = new HashMap();
		   System.out.println("myValues"+myValues);
		   System.out.println("myValues size"+myValues.length);
		   List<String> list = Arrays.asList(myValues);
		   Map<String,String> itemMap = new LinkedHashMap<String,String>();
		   for(int i=0;i<myValues.length;i++) {
			   //System.out.println("list value..."+list.get(i));
			   itemMap.put(myValues[i], myValues[i]);
		   }
		  
		   // mav.addObject("itemList", Arrays.asList(myValues));
		    //mav.setViewName("articles");
		  // referenceData.put("itemMap", itemMap);
		    return new ModelAndView("articles","itemsList",itemMap);
    }
	@GetMapping("error")
	public ModelAndView error() {
		    ModelAndView mav = new ModelAndView();
		    String errorMessage= "You are not authorized for the requested data.";
		    mav.addObject("errorMsg", errorMessage);
		    mav.setViewName("403");
		    return mav;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("articleForm") ArticleForm articleForm) {
		System.out.println(articleForm);
		System.out.println(articleForm.getArticles());
		List<Article2> articles = articleForm.getArticles();
		
		if(null != articles && articles.size() > 0) {
			userInfoService.saveArticles(articles);
			
		}
		
		return new ModelAndView("show_contact", "articleForm", articleForm);
	}
	public ItemsProperties getItems() {
		return items;
	}
	@Autowired
	public void setItems(ItemsProperties items) {
		this.items = items;
	}
	
} 