/**
 * 
 */
package com.autonavi.tsp.workbackend.cache.jedis;

import java.io.UnsupportedEncodingException;

import com.autonavi.tsp.workbackend.dto.SessionDto;

/**
 * @author mengxianming
 *
 * 2015年5月11日
 */
public class SessionCacheExecutor extends AbstractCacheExecutor<SessionDto, String>{

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.cache.jedis.AbstractCacheExecutor#getCacheKey()
	 */
	@Override
	public String getCacheKey() {		
		return KEY_PREFIX + "session";
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.cache.jedis.AbstractCacheExecutor#getCacheKeysBytes()
	 */
	@Override
	public byte[] getCacheKeysBytes() {		
		try {
			return getCacheKey().getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}
