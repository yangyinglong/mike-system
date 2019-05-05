package cn.hdu.fragmentTax.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final String DEFAULT_PROPERTIES= "application.properties";
    /**
     * 获取properties属性值
     * @param str
     * @return
     */
    public static String prop(String str){
        try {
            Resource resource = new ClassPathResource(DEFAULT_PROPERTIES);
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            return props.getProperty(str);
        }catch (IOException e){
            System.out.println(e); // todo
        }
        return null;
    }

}