package com.concretepage.dao;
import java.util.List;

import com.concretepage.entity.Article;
import com.concretepage.entity.Article1;
import com.concretepage.entity.Article2;
import com.concretepage.entity.UserInfo;
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
	List<Article1> getAllUserArticles();
	void saveArticles(List<Article2> articles) ;
	Article getArticleById(long articleId);
	void updateArticle(Article article) ;
	
}