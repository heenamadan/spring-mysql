package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Article;
import com.concretepage.entity.Article1;
import com.concretepage.entity.Article2;
import com.concretepage.entity.UserInfo;
import com.concretepage.repository.ArticleRepository1;
@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	ArticleRepository1 articleRepository;
	
	public UserInfo getActiveUser(String userName) {
		UserInfo activeUserInfo = new UserInfo();
		short enabled = 1;
		List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=? and enabled=?")
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		if(!list.isEmpty()) {
			activeUserInfo = (UserInfo)list.get(0);
		}
		return activeUserInfo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Article1> getAllUserArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		return (List<Article1>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public void saveArticles(List<Article2> articles) {
		//articleRepository.save();
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		//return (List<Article>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public Article getArticleById(long articleId) {
		return entityManager.find(Article.class, articleId);
	}
	@Override
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		System.out.println("artcl"+artcl.getArticleId());
		System.out.println("artclw"+artcl.getQuantity());
		artcl.setQuantity(article.getQuantity());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
}