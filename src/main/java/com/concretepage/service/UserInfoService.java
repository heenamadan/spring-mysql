package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserInfoDAO;
import com.concretepage.entity.Article1;
import com.concretepage.entity.Article2;
@Service
public class UserInfoService implements IUserInfoService {
	@Autowired
	private IUserInfoDAO userInfoDAO;
	@Override
	public List<Article1> getAllUserArticles(){
		return userInfoDAO.getAllUserArticles();
	}
	@Override
	public void saveArticles(List<Article2> articles) {
		 userInfoDAO.saveArticles(articles);
	}
}
