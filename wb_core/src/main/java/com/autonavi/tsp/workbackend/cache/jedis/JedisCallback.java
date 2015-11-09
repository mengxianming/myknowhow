package com.autonavi.tsp.workbackend.cache.jedis;

/**
 * @ClassName: JedisCallback
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenqing.zhang
 * @date 2013-3-22 上午10:01:27
 */
public interface JedisCallback<T> {
	/**
	 * @Title: doJedis
	 * @Description: TODO
	 * @param shardedJedis
	 * @return
	 * @throws
	 */
	Object doJedis(T jedis);
}
