package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;

/***
** Dubbo版本:2.5.7
**/
public abstract class DubboDirectConnetion {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private static final LoadingCache<String, Object> keyLocks = CacheBuilder.newBuilder()
	        .expireAfterAccess(30, TimeUnit.SECONDS) // max lock time ever expected
	        .build(CacheLoader.from(Object::new));	
	
	private String getJoinUrl(String urlport,String className){
		String[] urlports = StringUtils.split(urlport,";");
		List<String> strList = new ArrayList<String>();
		for(String url:urlports){
			strList.add("dubbo://"+url+"/"+className);
		}
        return StringUtils.join(strList,";");		
	}
	
	/**
	** 服务消费者直连服务提供者,protocol=dubbo前提下
	** 
	** clazz   接口名称
	** urlport 地址端口，比如127.0.0.1:10001,如果有多个用;进行分割
	** appName 服务提供者的applicationName
	**/
	protected <T> T getService(Class<T> clazz,String urlport,String appName){
        ReferenceConfig<T> rc = new ReferenceConfig<T>();
        String className = clazz.getName();
        rc.setUrl(getJoinUrl(urlport,className));
        rc.setInterface(className);
        rc.setRetries(0);
        rc.setTimeout(90000);
        rc.setApplication(new ApplicationConfig(appName));

        ReferenceConfigCache cache = ReferenceConfigCache.getCache(); 
        T t = cache.get(rc);
        if(null==t){
            synchronized(keyLocks.getUnchecked(className)) {
        	   cache.destroy(rc);
			}
        	return cache.get(rc);
        }else{
        	return t;
        }
	}
}
