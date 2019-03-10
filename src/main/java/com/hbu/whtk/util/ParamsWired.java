package com.hbu.whtk.util;

import com.hbu.whtk.base.User;
import com.hbu.whtk.core.DaoImpl;
import com.hbu.whtk.req.RegRequestParams;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by whtk210 on 2017-07-19.
 */
public class ParamsWired {
    //组织sql参数
   public  static String  paramsWired(String method,String sql, String[] colums,int insertOffset,int updateOffset) {
        if (method.equals("delete")) {
            for (int i = 0; i < colums.length; i++) {
                sql = " "+sql + " " + colums[i] + "=? ";
                if (i != colums.length - 1) sql = sql + " and ";
            }
            return sql;
        }
        else if(method.equals("insert"))
        {
            if(colums!=null)//如果colums不空
            {
                if(colums.length!=0) {
                    sql = sql + "( ";
                    for (int i = 0; i < colums.length; i++) {
                        sql = sql + " " + colums[i];
                        if (i != colums.length - 1) sql += ",";
                    }
                    sql = sql + ") values ";
                    sql = sql + "( ";
                    for (int i = 0; i < colums.length; i++) {
                        sql = sql + " ? ";
                        if (i != colums.length - 1) sql += ",";
                    }

                }
            }
            else {//如果colums空
                sql = sql + " values ( ";
                for (int i = 0; i < insertOffset; i++) {
                    sql = sql + " ? ";
                    if (i != insertOffset - 1) sql += ",";
                }
            }
            sql += ")";
            return sql;

        }
        else  //update
        {

            if(updateOffset==0) return "";
            for (int i = 0; i < updateOffset; i++) {
                sql = sql + colums[i]+"= ? ";
                if(i!=updateOffset-1) sql+=" , ";
            }
            sql+=" where ";
            for (int i = updateOffset; i < colums.length; i++) {
                sql = sql + colums[i]+"= ? ";
                if(i!=colums.length-1) sql+=" and ";
            }



            return sql;
        }
    }
    @Test
    public void testAAA()
    {
      //  String sql=paramsWired("delete","delete from user where",new String[]{"name","abbb"},0);
       // String   sql=paramsWired("update","update user set ",new String[]{"name","abbb","ccc","ddd"},2);
        String   sql=paramsWired("insert","insert user",new String[]{"name","bbb"},3,0);

                System.out.println(sql);
    }
///得到类的各个属性 返回String数组
    public  static String[] conv(Object o) {

        Field[] fields = o.getClass().getFields();
        String[]str = new String[fields.length];
        for(int i = 0 , len = fields.length; i < len; i++) {
// 对于每个属性，获取属性名
            str[i] = fields[i].getName();
        }
        return str;
    }
// 数据库操作函数

//删除 下面三个函数是不是应该放在DaoImpl里。。回头再改
    public static <T> int Delete(String tnames,String[] colums, Object[] params,DaoImpl<T> dao) {

        int num=-1;
        String sql="delete from "+tnames+" where ";
        if(params==null&&params.length==0) return -2;

        if(params.length!=colums.length) return -3;
        sql= ParamsWired.paramsWired("delete",sql,colums,0,0);
        if(!sql.equals(""))
            num=dao.addOrUpdateOrDelete(sql,params,null);
        return num;
    }


    public static  <T> int Update(String tnames,String[] colums,Object[] params,int updateOffset ,DaoImpl<T> dao) {
        int num=-1;
        String sql="update "+tnames+" set ";
        if(params==null&&params.length==0) return -2;
        sql= ParamsWired.paramsWired("update",sql,colums,0,updateOffset);
        if(!sql.equals(""))
            num=dao.addOrUpdateOrDelete(sql,params,User.class);
        return num;
    }

//key=true 返回值是主键。key=false返回值是影响行数
    public static <T> int Insert(String tnames,String[] colums,Object[] params,int insertOffset,DaoImpl<T> dao,boolean key) {
        int num=-1;
        String sql="insert "+tnames+" ";
        if(params==null&&params.length==0) return -2;
        sql= ParamsWired.paramsWired("insert",sql,colums,insertOffset,0);
        if(!sql.equals(""))
        {
            if(key)  num=dao.addOrUpdateOrDelete(sql,params,User.class,key);
           else num=dao.addOrUpdateOrDelete(sql,params,User.class);
        }


        return num;
    }

}
