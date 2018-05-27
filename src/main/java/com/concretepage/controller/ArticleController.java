package com.concretepage.controller;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Article;
import com.concretepage.service.IArticleService;
import com.concretepage.service.JMailSender;


@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	@GetMapping("articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addarticle",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("article") Article article){  
		
		if(Objects.isNull(articleService.getArticleById(article.getArticleId()))){
		if (articleService.addArticle(article)) {
			return new ModelAndView("viewrequested","article",article);  
		}
		}
		return new ModelAndView("viewrequested","article",article);  
	
		//return new ModelAndView("redirect:/article/viewRequestedArticles");
		
        //will redirect to viewrequested request mapping  
    }  
	
	@RequestMapping("/viewAllArticles")  
    public ModelAndView viewAllArticles(){  
        List<Article> list=articleService.getAllArticles();  
        return new ModelAndView("viewall","list",list);  //userArticles
    } 
	
	@RequestMapping("/viewRequestedArticles")  
    public ModelAndView viewRequestedArticles(){  
        List<Article> list=articleService.getAllArticles();  
        return new ModelAndView("viewrequested","list",list);  //userArticles
    } 
	
	@RequestMapping(value="/editarticle/{id}")  
    public ModelAndView edit(@PathVariable int id){ 
		Article article = articleService.getArticleById(id);
        return new ModelAndView("edit","article",article);  
    }
	
	@RequestMapping(value="/deletearticle/{id}")  
    public ModelAndView delete(@PathVariable int id){ 
		 articleService.deleteArticle(id);
		 List<Article> list=articleService.getAllArticles();  
        return new ModelAndView("viewall","list",list);  
    }
	
	@RequestMapping(value="/updateA/{id}/{quantity}/{category}",method = RequestMethod.POST)  
    public ModelAndView update(@PathVariable long id,@PathVariable String quantity,@PathVariable String category){  
    		
		System.out.println("id.."+id);
		System.out.println(quantity);
		System.out.println("category..."+category);
					//articleService.updateArticle(article);
		return null;
			
    } 
	
	@RequestMapping(value="/update",method = RequestMethod.POST)  
    public ModelAndView update1(@ModelAttribute("article") Article article){  
    		
		System.out.println("id.."+article.getArticleId());
		System.out.println(article.getQuantity());
		System.out.println("category..."+article.getCategory());
		articleService.updateArticle(article);
		return new ModelAndView("redirect:/article/editarticle/"+article.getArticleId());
			  
    } 
	
	/*@RequestMapping(value="/sendEmail/{email}")  
    public ModelAndView sendEmail(@PathVariable String email){ 
		String from = "heena.madan4@gmail.com";
		String to = email;
		String subject = "JavaMailSender";
		String body = "Just-Testing!";
		
		this.sendMail(from, to, subject, body);
		return null;
    }*/
	
	@RequestMapping(value="/sendEmail")  
	 public ModelAndView sendEmail(){ 
			String from = "heena.madan4@gmail.com";
			String to = "heena.madan4@gmail.com";
			String subject = "JavaMailSender";
			String body = "Just-Testing!";
			
			this.sendMail(from, to, subject, body);
			return new ModelAndView("emailnotification","emailmessage", "email send for approval");  
	    }
	
public void sendMail(String from, String to, String subject, String body) {
		
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		//logger.info("Sending...");
		
		javaMailSender.send(mail);
		
		//logger.info("Done!");
	}
	
	
	
	
	
	/*@DeleteMapping("article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
		Article article = articleService.getArticleById(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@PostMapping("/addarticle")
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.addArticle(article);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	*/	
} 