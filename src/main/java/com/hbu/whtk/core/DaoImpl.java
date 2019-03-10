package com.hbu.whtk.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by whtk210 on 2017-07-08.
 */
public class DaoImpl<T> implements Dao<T> {

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    JdbcTemplate jdbcTemplate;
    public <T> List<T> find(String sql, Object[] params, Class<T> tClass) {
        List<T> resultList = null;
        try {
            if (params != null && params.length > 0)
                resultList = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(tClass));
            else
                // BeanPropertyRowMapper是自动映射实体类的
                resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(tClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public <T> int addOrUpdateOrDelete(String sql, final Object[] params, Class<T> tClass) {
        int num = 0;
        try {
            if (params == null || params.length == 0)
                num = jdbcTemplate.update(sql);
            else
                num = jdbcTemplate.update(sql, new PreparedStatementSetter() {
                    public void setValues(PreparedStatement ps) throws SQLException {
                        for (int i = 0; i < params.length; i++)
                            ps.setObject(i + 1, params[i]);
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
            num = -1;
        }
        return num;

    }
    /////////////////////////////
    public <T> int addOrUpdateOrDelete(final String sql, final Object[] params, Class<T> tClass,boolean key1) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int num = 0;
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < params.length; i++)
                    ps.setObject(i + 1, params[i]);
                //....
                return ps;
            }
        }, keyHolder);
        num=keyHolder.getKey().intValue();
        return num;
    }
    /**
     * @param sql
     * @param args
     * @param classT 注意该参数，jdbcTemplate.queryForObject传入的不能是自定义的classType，
     *               如果是自定义的，需要经过new BeanPropertyRowMapper<T>(classT)转换，默认支持的只有比如String，int等类型
     * @param <T>
     * @return
     */
    public <T> T findForObject(String sql, Object[] args, Class<T> classT) {
        if (sql == null || sql.length() <= 0) {
            return null;
        }
        if (args == null || args.length <= 0) {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(classT));
        }
        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<T>(classT));
    }

    public Map<String, Object> find(String sql, Object[] params) {
        return jdbcTemplate.queryForMap(sql,params);
    }

    public List<Map<String, Object>> queryList(String sql, Object[] params) {
        return jdbcTemplate.queryForList(sql,params);
    }
}
