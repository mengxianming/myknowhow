package com.autonavi.tsp.workbackend.cache.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

public class JedisSimpleHandler extends JedisHandler<Jedis> {
	private static Log log = LogFactory.getLog(JedisSimpleHandler.class);

	private JedisPool jedisPool;

	public JedisSimpleHandler(JedisPool jedisPool) {
		super();
		this.jedisPool = jedisPool;
	}

	public final <O> O handle(JedisCallback<Jedis> jedisCallback)throws Exception {
		Jedis jedis=null;
		try {
			jedis = jedisPool.getResource();
			@SuppressWarnings("unchecked")
			O o = (O) jedisCallback.doJedis(jedis);
			return o;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public <O> O handleWithTransaction(JedisCallback<Jedis> jedisCallback)throws Exception {
		Jedis jedis=null;
		try {
			jedis = jedisPool.getResource();
			jedis.multi();
			@SuppressWarnings("unchecked")
			O o = (O) jedisCallback.doJedis(jedis);
			return o;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Set the string value as value of the key. The string can't be longer than
	 * 1073741824 bytes (1 GB). Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Status code reply
	 */
	public String setString(String key, String value) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			String status = jedis.set(key, value);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long remove(String... keys)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Long status = jedis.del(keys);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long remove(byte[]... keys) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Long status = jedis.del(keys);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Get the value of the specified key. If the key does not exist the special
	 * value 'nil' is returned. If the value stored at key is not a string an
	 * error is returned because GET can only handle string values. Time
	 * complexity: O(1)
	 * 
	 * @param key
	 * @return Bulk reply
	 */
	public String getString(String key)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * This Stirng的批量更新
	 * 
	 * @param pairs
	 */
	public List<Object> batchSetString(final List<Pair<String, String>> pairs)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Pipeline p = jedis.pipelined();
			for (Pair<String, String> pair : pairs) {
				p.set(pair.getKey(), pair.getValue());
			}
			List<Object> status = p.syncAndReturnAll();
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long setSets(String key, String... members)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Long status = jedis.sadd(key, members);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * This String的批量获得
	 * 
	 * @param keys
	 * @return
	 */
	public List<String> batchGetString(final List<String> keys) throws Exception{
		Jedis jedis =null;
		try {
			jedis = this.jedisPool.getResource();
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.get(key);
			}
			List<Object> dataList = p.syncAndReturnAll();
			List<String> rtnList = new ArrayList<String>();
			for (Object o : dataList) {
				rtnList.add((String) o);
			}
			return rtnList;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Set the specified hash field to the specified value. If key does not
	 * exist, a new key holding a hash is created. Time complexity: O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return If the field already exists, and the HSET just produced an update
	 *         of the value, 0 is returned, otherwise if a new field is created
	 *         1 is returned.
	 */
	public long hashSet(String key, String field, String value)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			long count = jedis.hset(key, field, value);
			return count;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public long hashSet(byte[] key, byte[] field, byte[] value)throws Exception {
		
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			long count = jedis.hset(key, field, value);
			return count;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * If key holds a hash, retrieve the value associated to the specified
	 * field. If the field is not found or the key does not exist, a special
	 * 'nil' value is returned. Time complexity:O(1)
	 * 
	 * @param key
	 * @param field
	 * @return Bulk reply
	 */
	public String hashGet(String key, String field)  throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			String value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public byte[] hashGet(byte[] key, byte[] field)throws Exception {
		Jedis jedis =null;
		try {
			jedis = this.jedisPool.getResource();
			byte[] value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long hashRemove(String key, String... fields)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Long value = jedis.hdel(key, fields);
			return value;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long hashRemove(byte[] key, byte[]... fields) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Long value = jedis.hdel(key, fields);
			return value;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Set the respective fields to the respective values. HMSET replaces old
	 * values with new values. If key does not exist, a new key holding a hash
	 * is created. Time complexity: O(N) (with N being the number of fields)
	 * 
	 * @param key
	 * @param hash
	 * @return Return OK or Exception if hash is empty
	 */
	public String hashMultipleSet(String key, Map<String, String> hash)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			String status = jedis.hmset(key, hash);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public String hashMultipleSet(byte[] key, Map<byte[], byte[]> hash) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			String status = jedis.hmset(key, hash);
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Retrieve the values associated to the specified fields. If some of the
	 * specified fields do not exist, nil values are returned. Non existing keys
	 * are considered like empty hashes. Time complexity: O(N) (with N being the
	 * number of fields)
	 * 
	 * @param key
	 * @param fields
	 * @return Multi Bulk Reply specifically a list of all the values associated
	 *         with the specified fields, in the same order of the request.
	 */
	public List<String> hashMultipleGet(String key, String... fields) throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			List<String> dataList = jedis.hmget(key, fields);
			return dataList;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public List<byte[]> hashMultipleGet(byte[] key, byte[]... fields)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			List<byte[]> dataList = jedis.hmget(key, fields);
			return dataList;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的HashMultipleSet
	 * 
	 * @param pairs
	 * @return
	 */
	public List<Object> batchHashMultipleSet(
			final List<Pair<String, Map<String, String>>> pairs)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Pipeline p = jedis.pipelined();

			for (Pair<String, Map<String, String>> pair : pairs) {
				p.hmset(pair.getKey(), pair.getValue());
			}
			List<Object> status = p.syncAndReturnAll();
			return status;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的HashMultipleGet
	 * @param pairs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<String>> batchHashMultipleGet(
			final List<Pair<String, String[]>> pairs) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Pipeline p = jedis.pipelined();

			for (Pair<String, String[]> pair : pairs) {
				p.hmget(pair.getKey(), pair.getValue());
			}
			@SuppressWarnings("rawtypes")
			List dataList = p.syncAndReturnAll();
			return dataList;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * Return all the fields and associated values in a hash. Time complexity:
	 * O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	public Map<String, String> hashGetAll(String key) throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Map<String, String> hash = jedis.hgetAll(key);
			return hash;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public Map<byte[], byte[]> hashGetAll(byte[] key)throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Map<byte[], byte[]> hash = jedis.hgetAll(key);
			return hash;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	public List<byte[]> hashGetAllValues(byte[] key)throws Exception {
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			List<byte[]> list = jedis.hvals(key);
			return list;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的hashMultipleGet
	 * 
	 * @param keys
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> batchHashGetAll(final List<String> keys) throws Exception{
		Jedis jedis=null;
		try {
			jedis = this.jedisPool.getResource();
			Pipeline p = jedis.pipelined();

			for (String key : keys) {
				p.hgetAll(key);
			}
			@SuppressWarnings("rawtypes")
			List dataList = p.syncAndReturnAll();
			
			return (List<Map<String, String>>) dataList;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}
	

	
	
	/**
	 * 获取jedis连接
	 * @return
	 */
	public Jedis getConnection(){
		Jedis jedis = this.jedisPool.getResource();
		return jedis;
	}
	/**
	 * 开始事务
	 * @param key
	 * @param jedis
	 * @return
	 */
	public String beginTransaction(byte[] key,Jedis jedis){
		return jedis.watch(key);
	}
	/**
	 * 通过事务将key值写入redis,如果写入失败，返回值为null
	 * @return
	 */
	public List<Object> executeTransaction(byte[] key,byte[] field,byte[] value,Jedis jedis)
			throws Exception {
		List<Object> list = null;
		Transaction t = null;
		try {
			t = jedis.multi();
			t.hset(key, field, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (t != null) {
				t.discard();
			}
			this.jedisPool.returnResource(jedis);
		} finally {
			list = t.exec();
		}
		return list;
	}
	
	/**
	 * 开始事务
	 */
	public String closeTransaction(Jedis jedis)throws Exception{
		try {
			String result = jedis.unwatch();
			return result;
		} catch (Exception e) {
			this.jedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.jedisPool.returnResource(jedis);
		}
	}

    /**
     * redis计数器
     * @param key   计数器key
     * @return  返回计数器+1后的值。
     */
    public Long incr(String key){
        Long incr = -1L;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            incr = jedis.incr(key);
        } catch (Exception e) {
            this.jedisPool.returnBrokenResource(jedis);
        }finally{
            this.jedisPool.returnResource(jedis);
        }
        return incr;
    }
}
