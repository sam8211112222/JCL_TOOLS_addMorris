package com.csi.jcl.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdJclExeroderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AdJclExeroderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //  查詢
    public List<Map<String, Object>> query(String sql, Map<String, ?> paramMap) {
        List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, paramMap);
        return result;
    }

}
