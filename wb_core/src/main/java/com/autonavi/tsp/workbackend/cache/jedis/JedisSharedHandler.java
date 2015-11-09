package com.autonavi.tsp.workbackend.cache.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
public class JedisSharedHandler extends JedisHandler<ShardedJedis> {

	/**
	 * @Fields shardedJedisPool : TODO
	 */
	protected ShardedJedisPool shardedJedisPool;

	public JedisSharedHandler(ShardedJedisPool shardedJedisPool) {
		super();
		this.shardedJedisPool = shardedJedisPool;
	}

	@SuppressWarnings("unchecked")
	public final <O> O handle(JedisCallback<ShardedJedis> jedisCallback) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = shardedJedisPool.getResource();
			Object o = jedisCallback.doJedis(jedis);
			return (O) o;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public <O> O handleWithTransaction(JedisCallback<ShardedJedis> jedisCallback) {
		
		return null;
	}

	/**
	 * Set the string value as value of the key. The string can't be longer than
	 * 1073741824 bytes (1 GB). Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Status code reply
	 */
	public String setString(String key, String value)throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			String status = jedis.set(key, value);
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
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
	public String getString(String key) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long remove(String... keys) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Long status = 0L;
			for (String key : keys) {
				status = jedis.del(key);
			}
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long remove(byte[]... keys) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Collection<Jedis> coll = jedis.getAllShards();
			Long status = 0L;
			for (Jedis jedis2 : coll) {
				for (byte[] key : keys) {
					status = jedis2.del(key);
				}
			}
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * This Stirng的批量更新
	 * 
	 * @param pairs
	 * @throws Exception 
	 */
	public List<Object> batchSetString(final List<Pair<String, String>> pairs) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			ShardedJedisPipeline p = jedis.pipelined();
			for (Pair<String, String> pair : pairs) {
				p.set(pair.getKey(), pair.getValue());
			}
			List<Object> status = p.syncAndReturnAll();
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long setSets(String key, String... members) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Long status = jedis.sadd(key, members);
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * This String的批量获得
	 * 
	 * @param keys
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<String> batchGetString(final List<String> keys) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			ShardedJedisPipeline p = jedis.pipelined();
			for (String key : keys) {
				p.get(key);
			}
			Object dataList = p.syncAndReturnAll();
			return (List<String>) dataList;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
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
	public long hashSet(String key, String field, String value) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			long count = jedis.hset(key, field, value);
			return count;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public long hashSet(byte[] key, byte[] field, byte[] value)throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			long count = jedis.hset(key, field, value);
			return count;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
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
	public String hashGet(String key, String field) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			String value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public byte[] hashGet(byte[] key, byte[] field) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			byte[] value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long hashRemove(String key, String... fields)throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Long value = jedis.hdel(key, fields);
			return value;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public Long hashRemove(byte[] key, byte[]... fields)throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Long value = jedis.hdel(key, fields);
			return value;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
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
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			String status = jedis.hmset(key, hash);
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public String hashMultipleSet(byte[] key, Map<byte[], byte[]> hash)throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			String status = jedis.hmset(key, hash);
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
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
	public List<String> hashMultipleGet(String key, String... fields)throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			List<String> dataList = jedis.hmget(key, fields);
			return dataList;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public List<byte[]> hashMultipleGet(byte[] key, byte[]... fields)throws Exception  {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			List<byte[]> dataList = jedis.hmget(key, fields);
			return dataList;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的HashMultipleSet
	 * 
	 * @param pairs
	 * @return
	 * @throws Exception 
	 */
	public List<Object> batchHashMultipleSet(
			final List<Pair<String, Map<String, String>>> pairs) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();

			ShardedJedisPipeline p = jedis.pipelined();

			for (Pair<String, Map<String, String>> pair : pairs) {
				p.hmset(pair.getKey(), pair.getValue());
			}
			List<Object> status = p.syncAndReturnAll();
			return status;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的HashMultipleGet
	 * 
	 * @param pairs
	 * @return
	 * @throws Exception 
	 */
	public List<List<String>> batchHashMultipleGet(
			final List<Pair<String, String[]>> pairs)throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			ShardedJedisPipeline p = jedis.pipelined();

			for (Pair<String, String[]> pair : pairs) {
				p.hmget(pair.getKey(), pair.getValue());
			}

			List<Object> dataList = p.getResults();

			List<List<String>> rtnDataList = new ArrayList<List<String>>();
			for (Object data : dataList) {
				rtnDataList.add(STRING_LIST.build(data));
			}
			return rtnDataList;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * Return all the fields and associated values in a hash. Time complexity:
	 * O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	public Map<String, String> hashGetAll(String key) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Map<String, String> hash = jedis.hgetAll(key);
			return hash;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public Map<byte[], byte[]> hashGetAll(byte[] key)throws Exception {
		ShardedJedis jedis =null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Map<byte[], byte[]> hash = jedis.hgetAll(key);
			return hash;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	public Collection<byte[]> hashGetAllValues(byte[] key) throws Exception{
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			Collection<byte[]> collection = jedis.hvals(key);
			return collection;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

	/**
	 * This 批量的hashMultipleGet
	 * 
	 * @param keys
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> batchHashGetAll(final List<String> keys) throws Exception {
		ShardedJedis jedis=null;
		try {
			jedis = this.shardedJedisPool.getResource();
			ShardedJedisPipeline p = jedis.pipelined();
			for (String key : keys) {
				p.hgetAll(key);
			}

			List<Object> dataList = p.getResults();
			List<Map<String, String>> rtnDataList = new ArrayList<Map<String, String>>();
			for (Object data : dataList) {
				rtnDataList.add(STRING_MAP.build(data));
			}
			return rtnDataList;
		} catch (Exception e) {
			this.shardedJedisPool.returnBrokenResource(jedis);
			throw e;
		}finally{
			this.shardedJedisPool.returnResource(jedis);
		}
	}

    /**
     * redis计数器
     * @param key   计数器key
     * @return  返回计数器+1后的值。
     */
    public Long incr(String key){
        Long incr = -1L;
        ShardedJedis jedis = null;
        try {
            jedis = this.shardedJedisPool.getResource();
            incr = jedis.incr(key);
        }  catch (Exception e) {
            this.shardedJedisPool.returnBrokenResource(jedis);
        }finally{
            this.shardedJedisPool.returnResource(jedis);
        }
        return incr;
    }
}
