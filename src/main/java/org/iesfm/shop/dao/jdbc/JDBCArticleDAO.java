package org.iesfm.shop.dao.jdbc;

import org.iesfm.shop.Article;
import org.iesfm.shop.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

public class JDBCArticleDAO implements ArticleDAO {


    private NamedParameterJdbcTemplate jdbc;

    private static final String ARTICLE_LIST = "SELECT * FROM Article";

    private static final String ARTICLE_TAGS = "SELECT name FROM Tag WHERE article_id=:article_id";

    private final RowMapper<Article> ARTICLE_ROW_MAPPER =
            ((rs, rowNum) -> new Article(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    getArticleTags(rs.getInt("id"))
            ));

    public JDBCArticleDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Set<String> getArticleTags(int article_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("article_id", article_id);
        return new HashSet<>(jdbc.query(ARTICLE_TAGS, params, (rs, rowNum) ->
                rs.getString("name")
        ));
    }

    @Override
    public List<Article> list() {
        return jdbc.query(ARTICLE_LIST, ARTICLE_ROW_MAPPER);
    }

    @Override
    public List<Article> list(String tag) {
        return null;
    }

    @Override
    public Article get(int id) {
        return null;
    }

    @Override
    public boolean insert(Article article) {
        return false;
    }

    @Override
    public boolean update(Article article) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
