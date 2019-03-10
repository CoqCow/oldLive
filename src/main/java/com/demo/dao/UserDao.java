package com.demo.dao;

import com.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository("fanzengbaoUserDao")
public class UserDao<T> {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public User findUserByUserName(final String name){
        String sql=" select * from user where name=? ";
        final User user = new User();
        jdbcTemplate.query(sql,new Object[]{name}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                    user.setAvatar(resultSet.getString("avatar"));
                    user.setCollege(resultSet.getString("college"));
                    user.setFollowNumber(resultSet.getInt("followNumber"));
                    user.setName(name);
                    user.setNickName(resultSet.getString("nickName"));
                    user.setNumber(resultSet.getInt("number"));
                    user.setOrganizationName(resultSet.getString("organizationName"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setRegistrationTime(resultSet.getTime("registrationTime"));
                    user.setSignature(resultSet.getString("signature"));
                    user.setWhetherAnchor(resultSet.getInt("whetherAnchor"));
                    user.setUserId(resultSet.getInt("userId"));
                }
            });

       return user;
    }

    public int checkUserByUserId(int userId){
        String sql=" select userId from user where userId=? ";
        try{
            return jdbcTemplate.queryForObject(sql,new Object[]{userId},Integer.class);
        }catch (Exception e){
            return 0;
        }
    }
    public List<T> getAllMyLikes(int Id, Class<T> classT) {
        String sql=" select * from follow where userId=? ";
        List<T> list;
        try {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(classT), new Object[]{Id});
        }catch (EmptyResultDataAccessException e)
        {
            list=null;
        }
        return list;
    }

    //用于更新表，每次只能更新一个字段
    private int update(String sql,Object str,int userId){
        int i;
        if(this.checkUserByUserId(userId)>0) {
            try {
                jdbcTemplate.update(sql, new Object[]{str, userId});
                i = 1;
            } catch (EmptyResultDataAccessException e) {
                i = 0;
            }
        }else {
            i=2;
        }
        return i;
    }
    public int setAvatar(String avatar,int userId) {
       // String sql =" UPDATE \"user\" SET avatar=? WHERE userId=? ";
        String sql ="update user set avatar=? where userId=?";
        return update(sql,avatar,userId);
    }
    public int changeNickName(String nickName,int userId) {
        //String sql =" UPDATE \"user\" SET nickName=? WHERE userId=? ";
        String sql ="update user set nickName=? where userId=?";
        return update(sql,nickName,userId);
    }
    public int changePWD(String pass,int userId){
       // String sql= " update \"user\" set password=? where userId=? ";
        String sql ="update user set password=? where userId=?";
        return update(sql,pass,userId);
    }
    public int changeDescription(String des,int userId){
       // String sql=" update \"user\" set signature=? where userId=?";
        String sql ="update user set signature=? where userId=?";
        return update(sql,des,userId);
    }


}
