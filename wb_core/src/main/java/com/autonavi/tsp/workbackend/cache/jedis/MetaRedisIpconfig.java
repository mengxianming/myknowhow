package com.autonavi.tsp.workbackend.cache.jedis;

/**
 * redis IP,Port 包装类
 * 
 * @author yonghong.guo
 * 
 */
public class MetaRedisIpconfig {
	private String hostIp;
	private int port;
	private int database;
	private String password;

	
	public MetaRedisIpconfig(String hostIp, int port, int database, String password) {
		super();
		this.hostIp = hostIp;
		this.port = port;
		this.database = database;
		this.password = password;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
