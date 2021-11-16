package org.iesfm.shop.dao;

import org.iesfm.shop.dao.inmemory.InMemoryArticleDAO;
import org.iesfm.shop.dao.jdbc.JDBCArticleDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class DAOConfiguration {

//    @Bean
//    public ArticleDAO articleDAO(NamedParameterJdbcTemplate jdbc){
//        return new JDBCArticleDAO(jdbc);
//    }

    @Bean
    public ArticleDAO articleDAO(){
        return new InMemoryArticleDAO();
    }

    @Bean
    public DataSource dataSource(
            @Value("${database.url}") String url,
            @Value("${database.user}") String user,
            @Value("${database.password}") String password
    ){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(user);
        return dataSource;
    }
}
