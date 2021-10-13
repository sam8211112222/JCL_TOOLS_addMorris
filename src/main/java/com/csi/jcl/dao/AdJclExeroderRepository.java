package com.csi.jcl.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AdJclExeroderRepository 注入NamedParameterJdbcTemplate對資料庫做JDBC操作
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Repository
public class AdJclExeroderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AdJclExeroderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //  原生sql查詢
    public List<Map<String, Object>> query(String sql, Map<String, ?> paramMap) {
        List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, paramMap);
        return result;
    }

}
