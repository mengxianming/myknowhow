package com.autonavi.tsp.workbackend.cache.jedis;

import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

/**
 * 
 * @author mengxianming
 *
 * 2015年5月11日
 */
@SuppressWarnings("rawtypes")
public final class JedisHandlerFactoryBean implements FactoryBean {	
	private boolean sharePool;
	private Pool jedisPool;

	/**
	 * @param sharePool the sharePool to set
	 */
	public void setSharePool(boolean sharePool) {
		this.sharePool = sharePool;
	}
	/**
	 * @param jedisPool the jedisPool to set
	 */
	public void setJedisPool(Pool jedisPool) {
		this.jedisPool = jedisPool;
	}


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Object getObject() throws Exception {
		if(sharePool){
			return new JedisSharedHandler((ShardedJedisPool) jedisPool);
		}
		return new JedisSimpleHandler((JedisPool) jedisPool);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return sharePool ? JedisSharedHandler.class : JedisSimpleHandler.class;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
