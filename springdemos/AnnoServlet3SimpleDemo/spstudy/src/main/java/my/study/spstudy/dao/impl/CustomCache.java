package my.study.spstudy.dao.impl;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.mybatis.caches.memcached.MemcachedCache;

public class CustomCache implements Cache{	
	private static Log log = LogFactory.getLog(CustomCache.class);
	private static class EmptyCache implements Cache{
		String id;
		
		public EmptyCache(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public void putObject(Object key, Object value) {
			
		}

		@Override
		public Object getObject(Object key) {
			return null;
		}

		@Override
		public Object removeObject(Object key) {
			return null;
		}

		@Override
		public void clear() {
			
		}

		@Override
		public int getSize() {
			return 0;
		}

		@Override
		public ReadWriteLock getReadWriteLock() {
			return null;
		}
		
	}
	
	private Cache wrapped;
	private Integer clearIntervalInSec;
	private Boolean doLog;

	public CustomCache(String id) {		
		try {
			Cache cache = new MemcachedCache(id);
			ScheduledCache sc= new ScheduledCache(cache);
			if(clearIntervalInSec != null){
				sc.setClearInterval(clearIntervalInSec * 1000);
			}else{
				//永不过期
				sc.setClearInterval(Integer.MAX_VALUE);
			}
			if(doLog != null && doLog){
				cache = new LoggingCache(sc);
			}
			wrapped = cache;
		} catch (Exception e) {
			log.warn("初始化缓存管理器出错、将不使用缓存：" + e.getMessage(), e);		
			wrapped = new EmptyCache(id);
		}
		
	}
	
	public void clear() {
		wrapped.clear();
	}

	public String getId() {
		return wrapped.getId();
	}

	public Object getObject(Object key) {
		try {
			return wrapped.getObject(key);
		} catch (Exception e) {
			log.warn("读取缓存失败。key=" + key);
			return null;
		}
	}

	public ReadWriteLock getReadWriteLock() {
		return wrapped.getReadWriteLock();
	}

	public int getSize() {
		return wrapped.getSize();
	}

	public void putObject(Object key, Object value) {
		wrapped.putObject(key, value);
	}

	public Object removeObject(Object key) {
		return wrapped.removeObject(key);
	}
	
	public Integer getClearIntervalInSec() {
		return clearIntervalInSec;
	}
	public void setClearIntervalInSec(Integer clearIntervalInSec) {
		this.clearIntervalInSec = clearIntervalInSec;
	}
	public Boolean getDoLog() {
		return doLog;
	}
	public void setDoLog(Boolean doLog) {
		this.doLog = doLog;
	}
	
	
	
}
