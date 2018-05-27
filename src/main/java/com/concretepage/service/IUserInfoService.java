package com.concretepage.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.concretepage.entity.Article1;
import com.concretepage.entity.Article2;

public interface IUserInfoService {
	 @Secured ({"ROLE_ADMIN"})
     List<Article1> getAllUserArticles();
	 
	 void saveArticles(List<Article2> articles);
}
