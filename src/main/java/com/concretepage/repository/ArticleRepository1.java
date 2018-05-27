package com.concretepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concretepage.entity.Article1;

@Repository
public interface ArticleRepository1 extends JpaRepository<Article1, Long> {

}
