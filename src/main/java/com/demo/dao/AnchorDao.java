package com.demo.dao;

import com.demo.domain.Anchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("fanzengbaoAnchorDao")
public class AnchorDao<T> {
    private JdbcTemplate jdbcTemplate;
    private Anchor anchor;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Anchor findAnchorById(int Id){
        String sql=" select * from anchor where userId=? ";
        anchor=new Anchor();
        try {
            jdbcTemplate.query(sql, new Object[]{Id}, new RowCallbackHandler() {
                public void processRow(ResultSet resultSet) throws SQLException {
                    anchor.setUserId(resultSet.getInt("userId"));
                    anchor.setTotalUpvoteNum(resultSet.getInt("totalUpvoteNum"));
                    anchor.setTotalGiftsNumber(resultSet.getInt("totalGiftsNumber"));
                    anchor.setApplyAnchorTime(resultSet.getDate("applyAnchorTime"));
                    anchor.setPlayTime(resultSet.getInt("playTime"));
                    anchor.setFollowedNumber(resultSet.getInt("followedNumber"));
                    anchor.setAnchorCategory(resultSet.getString("anchorCategory"));
                }
            });
            return anchor;
        }catch (Exception e){
            System.out.println(e.toString());
            anchor=null;
            return anchor;
        }
    }
    public int getFollowedNumber(int Id) {
        String sql = " select followedNumber from anchor where userId=? ";
        int number;
        try {
            number = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{Id});
            return number;
        } catch (Exception e) {
            number = -1;
            return number;
        }
    }
}
