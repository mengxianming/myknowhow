package com.autonavi.tsp.workbackend.cache.jedis;

import java.util.List;

/**
 * redis 连接配置类
 * 
 * @author yonghong.guo
 * 
 */
public class MetaRedisConfig {
	// 最大分配的对象数
	private int maxActive = 200;
	// 最大能够保持idel状态的对象数
	private int maxIdle = 100;
    // 最大能够保持idel状态的对象数
    private int minIdle = 100;
	// 当池内没有返回对象时，最大等待时间，单位毫秒
	private int maxWait = 5000;
	// 当调用borrow Object方法时，是否进行有效性检查
	private boolean testOnBorrow = true;
	// 当调用return Object方法时，是否进行有效性检查
	private boolean testOnReturn = true;
	// IP,port
	private List<MetaRedisIpconfig> ipConfig;

	public int getMaxActive() {
		return maxActive;
	}

	public List<MetaRedisIpconfig> getIpConfig() {
		return ipConfig;
	}

	public void setIpConfig(List<MetaRedisIpconfig> ipConfig) {
		this.ipConfig = ipConfig;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}
