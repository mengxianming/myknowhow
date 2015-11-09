package com.autonavi.tsp.workbackend.cache.jedis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @author mengxianming
 *
 * 2015年5月11日
 */
@SuppressWarnings("rawtypes")
public final class JedisPoolFactoryBean implements FactoryBean {
	private static Log log = LogFactory.getLog(JedisPoolFactoryBean.class);
	
	private MetaRedisConfig metaConfig;
	private boolean sharePool;

	/**
	 * @param metaConfig the metaConfig to set
	 */
	public void setMetaConfig(MetaRedisConfig metaConfig) {
		this.metaConfig = metaConfig;
	}
	/**
	 * @param sharePool the sharePool to set
	 */
	public void setSharePool(boolean sharePool) {
		this.sharePool = sharePool;
	}

	/**
	 * @Title: getSimplePool
	 * @return
	 * @throws
	 */
	private  JedisPool getSimplePool() {		
		JedisPoolConfig config = getPoolConfig();
		List<MetaRedisIpconfig> ipconfigs = metaConfig.getIpConfig();
		return new JedisPool(config, ipconfigs.get(0).getHostIp(), ipconfigs.get(0).getPort(),
				new Long(config.getMaxWaitMillis()).intValue(), ipconfigs.get(0).getPassword(),  ipconfigs.get(0).getDatabase());
	}

	/**
	 * @Title: getSharedPool
	 * @return
	 * @throws Exception
	 * @throws
	 */
	private  ShardedJedisPool getSharedPool() {
		JedisPoolConfig config = getPoolConfig();
		List<MetaRedisIpconfig> ipconfigs = metaConfig.getIpConfig();
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		if (ipconfigs.size() == 1) {
			JedisShardInfo shareInfo = new JedisShardInfo(ipconfigs.get(0).getHostIp(), ipconfigs.get(0).getPort());
			shareInfo.setTimeout(new Long(config.getMaxWaitMillis()).intValue());
			shareInfo.setPassword(ipconfigs.get(0).getPassword());
			list.add(shareInfo);
		} else {
			for (int i = 0; i < ipconfigs.size(); i++) {
				JedisShardInfo shareInfo = new JedisShardInfo(ipconfigs.get(i).getHostIp(), ipconfigs.get(i).getPort(), "redis" + i);
				shareInfo.setTimeout(new Long(config.getMaxWaitMillis()).intValue());
				shareInfo.setPassword(ipconfigs.get(i).getPassword());
				
				list.add(shareInfo);
			}
		}
		return list.isEmpty() ? null : new ShardedJedisPool(config, list);
	}

	/**
	 * @Title: getPoolConfig
	 * @return
	 * @throws
	 */
	private  JedisPoolConfig getPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(metaConfig.getMinIdle());
		config.setMaxIdle(metaConfig.getMaxIdle());
        config.setMaxTotal(metaConfig.getMaxActive());
        config.setMaxWaitMillis(metaConfig.getMaxWait());
		config.setTestOnBorrow(metaConfig.isTestOnBorrow());
		config.setTestOnReturn(metaConfig.isTestOnReturn());
		log.debug("初始化JedisPoolConfig，MaxActive:"+metaConfig.getMaxActive());
		log.debug("初始化JedisPoolConfig，MaxIdle:"+metaConfig.getMaxIdle());
		log.debug("初始化JedisPoolConfig，MaxWait:"+metaConfig.getMaxWait());
		log.debug("初始化JedisPoolConfig，TestOnBorrow:"+metaConfig.isTestOnBorrow());
		log.debug("初始化JedisPoolConfig，TestOnReturn:"+metaConfig.isTestOnReturn());
		return config;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Object getObject() throws Exception {
		if(sharePool){
			return getSharedPool();
		}
		return getSimplePool();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return sharePool ? ShardedJedisPool.class : JedisPool.class;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
