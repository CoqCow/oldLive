package com.hbu.whtk.util;

import com.hbu.whtk.req.AnchorRequestParams;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
//对象转数组，可以有父类属性，但是类属性必须是公有的
public class ObjecttoArray {
    public static Object[] getFieldValues(Object object) {
        if (object == null)
            return null;
        Field[] fields = object.getClass().getFields();
        List<Object> fieldValueList = new ArrayList<Object>();
//Map<String, Object> fieldValueMap = new HashMap<String, Object>();
        try {
            for (Field f : fields) {

                fieldValueList.add(f.get(object));
//fieldValueMap.put(f.getName(), f.get(object));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValueList.toArray();
//return fieldValueMap.values().toArray();
    }
    @Test
    public void testAA()
    {
        AnchorRequestParams p=new AnchorRequestParams();
        p.setUserId(1);
        p.setAnchorCategory("ddd");
       Object[] a= ObjecttoArray.getFieldValues(p);
    }
}
