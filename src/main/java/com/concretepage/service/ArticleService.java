package com.concretepage.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserInfoDAO;
import com.concretepage.entity.Article;
import com.concretepage.repository.ArticleRepository;
@Service
public class ArticleService implements IArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private IUserInfoDAO articleDAO;
	/*@Override
	public Article getArticleById(long articleId) {
	
		Article obj = articleRepository.findById(articleId).get();
		return obj;
	}*/	
	@Override
	public List<Article> getAllArticles(){
		List<Article> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addArticle(Article article){
		
	  // List<Article> list = articleRepository.findByTitleAndCategory(article.getQuantity(), article.getCategory()); 	
       
    	   articleRepository.save(article);
    	   return true;
       
	}
	
	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}
	
	
	@Override
	public Article getArticleById(long articleId) {
		return articleDAO.getArticleById(articleId);
	}
	
	@Override
	public void deleteArticle(long articleId) {
		articleRepository.delete(getArticleById(articleId));
	}
}
