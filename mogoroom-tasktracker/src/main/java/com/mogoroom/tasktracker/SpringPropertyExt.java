package com.mogoroom.tasktracker;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.mogoroom.util.MogoAESUtil;

public class SpringPropertyExt extends PropertyPlaceholderConfigurer {
	
	
	@Override 
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) 
                    throws BeansException { 
		    
		
		    //这里说明一下Url 里面的分隔符“&amp;”必须手动过滤掉，否则spring使用会无法转义，导致数据库乱码
		    String url = props.getProperty("jdbc.url");
		   
		    int x = url.indexOf("&amp;");
		    if(x!=-1){
		    	url = url.replace("&amp;", "&");
		    }
		    
            if (url != null) { 
                //解密jdbc.password属性值，并重新设置 
                props.setProperty("jdbc.url",url); 
            } 
            
            
            url = props.getProperty("jdbc.url_web");
 		   	if(url!=null&&url.length()>0&&url.indexOf("filter.jdbc")<0){
			    x = url.indexOf("&amp;");
			    if(x!=-1){
			    	url = url.replace("&amp;", "&");
			    }
			    
	            if (url != null) { 
	                //解密jdbc.password属性值，并重新设置 
	                props.setProperty("jdbc.url_web",url); 
	            } 
 		   	}
            
            String password = props.getProperty("jdbc.password"); 
            if (password != null) { 
                    //解密jdbc.password属性值，并重新设置 
                    props.setProperty("jdbc.password",MogoAESUtil.decrypt(password)); 
            } 
            String userName = props.getProperty("jdbc.username"); 
            if (userName != null) { 
                    //解密jdbc.userName属性值，并重新设置 
                    props.setProperty("jdbc.username",MogoAESUtil.decrypt(userName)); 
            } 
            
            String passwordWeb = props.getProperty("jdbc.password_web"); 
            System.out.println(passwordWeb);
            if (passwordWeb != null&&passwordWeb.trim().length()>0&&passwordWeb.indexOf("filter.jdbc")<0) { 
                    //解密jdbc.password属性值，并重新设置 
                    props.setProperty("jdbc.password_web",MogoAESUtil.decrypt(passwordWeb)); 
            } 
            String userNameWeb = props.getProperty("jdbc.username_web"); 
            if (userNameWeb != null&&userNameWeb.length()>0&&userNameWeb.indexOf("filter.jdbc")<0) { 
                    //解密jdbc.userName属性值，并重新设置 
                    props.setProperty("jdbc.username_web",MogoAESUtil.decrypt(userNameWeb)); 
            } 
            
            /**********************老数据库*************************/
            String userName_old = props.getProperty("jdbc.username_old");
            if(StringUtils.isNotBlank(userName_old)){
            	props.setProperty("jdbc.username_old", MogoAESUtil.decrypt(userName_old));
            }
            
            String password_old = props.getProperty("jdbc.password_old");
            if(StringUtils.isNotBlank(password_old)){
            	props.setProperty("jdbc.password_old", MogoAESUtil.decrypt(password_old));
            }
            
            
            String userName_wj = props.getProperty("jdbc.username_wj");
            if(StringUtils.isNotBlank(userName_wj)){
            	props.setProperty("jdbc.username_wj", MogoAESUtil.decrypt(userName_wj));
            }
            
            String password_wj = props.getProperty("jdbc.password_wj");
            if(StringUtils.isNotBlank(password_wj)){
            	props.setProperty("jdbc.password_wj", MogoAESUtil.decrypt(password_wj));
            }
            
            /**********************balanceDB*************************/
            String userName_bl = props.getProperty("jdbc.username_bl");
            if(StringUtils.isNotBlank(userName_bl)){
            	props.setProperty("jdbc.username_bl", MogoAESUtil.decrypt(userName_bl));
            }
            
            String password_bl = props.getProperty("jdbc.password_bl");
            if(StringUtils.isNotBlank(password_bl)){
            	props.setProperty("jdbc.password_bl", MogoAESUtil.decrypt(password_bl));
            }
            
            super.processProperties(beanFactory, props); 
    } 
	
}
