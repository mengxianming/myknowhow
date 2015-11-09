package com.autonavi.tsp.workbackend.cache.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Builder;
import redis.clients.util.SafeEncoder;

/**
 * @ClassName: JedisHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenqing.zhang
 * @date 2013-3-23 下午3:56:59
 * @param <T>
 */
public abstract class JedisHandler<T> {

	public abstract <O> O handle(JedisCallback<T> jedisCallback)throws Exception;

	public abstract <O> O handleWithTransaction(JedisCallback<T> jedisCallback)throws Exception;

	/**
	 * Set the string value as value of the key. The string can't be longer than
	 * 1073741824 bytes (1 GB). Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Status code reply
	 */
	public abstract String setString(String key, String value)throws Exception;

	/**
	 * Get the value of the specified key. If the key does not exist the special
	 * value 'nil' is returned. If the value stored at key is not a string an
	 * error is returned because GET can only handle string values. Time
	 * complexity: O(1)
	 * 
	 * @param key
	 * @return Bulk reply
	 */
	public abstract String getString(String key)throws Exception;

	/**
	 * This Stirng的批量更新
	 * 
	 * @param pairs
	 */
	public abstract List<Object> batchSetString(
			final List<Pair<String, String>> pairs)throws Exception;

	/**
	 * @Title: remove
	 * @Description: 删除多个元素
	 * @param keys
	 * @return
	 * @throws
	 */
	public abstract Long remove(final String... keys)throws Exception;

	public abstract Long remove(final byte[]... keys)throws Exception;

	/**
	 * @Title: setSets
	 * @Description: TODO
	 * @param key
	 * @return
	 * @throws
	 */
	public abstract Long setSets(final String key, String... values) throws Exception;

	/**
	 * This String的批量获得
	 * 
	 * @param keys
	 * @return
	 */
	public abstract List<String> batchGetString(final List<String> keys)throws Exception;

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
	public abstract long hashSet(String key, String field, String value)throws Exception;

	public abstract long hashSet(byte[] key, byte[] field, byte[] value)throws Exception;

    public abstract Long incr(String key);
	/**
	 * If key holds a hash, retrieve the value associated to the specified
	 * field. If the field is not found or the key does not exist, a special
	 * 'nil' value is returned. Time complexity:O(1)
	 * 
	 * @param key
	 * @param field
	 * @return Bulk reply
	 */
	public abstract String hashGet(String key, String field) throws Exception;

	public abstract byte[] hashGet(byte[] key, byte[] field)throws Exception;

	/**
	 * @Title: hashRemove
	 * @Description: 删除hash元素
	 * @param key
	 * @param fields
	 * @return
	 * @throws
	 */
	public abstract Long hashRemove(String key, String... fields)throws Exception;

	public abstract Long hashRemove(byte[] key, byte[]... fields)throws Exception ;

	/**
	 * Set the respective fields to the respective values. HMSET replaces old
	 * values with new values. If key does not exist, a new key holding a hash
	 * is created. Time complexity: O(N) (with N being the number of fields)
	 * 
	 * @param key
	 * @param hash
	 * @return Return OK or Exception if hash is empty
	 */
	public abstract String hashMultipleSet(String key, Map<String, String> hash)throws Exception;

	public abstract String hashMultipleSet(byte[] key, Map<byte[], byte[]> hash)throws Exception;

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
	public abstract List<String> hashMultipleGet(String key, String... fields)throws Exception;

	public abstract List<byte[]> hashMultipleGet(byte[] key, byte[]... fields)throws Exception ;

	/**
	 * This 批量的HashMultipleSet
	 * 
	 * @param pairs
	 * @return
	 */
	public abstract List<Object> batchHashMultipleSet(
			final List<Pair<String, Map<String, String>>> pairs)throws Exception;

	/**
	 * This 批量的HashMultipleGet
	 * 
	 * @param pairs
	 * @return
	 */
	public abstract List<List<String>> batchHashMultipleGet(
			final List<Pair<String, String[]>> pairs) throws Exception;

	/**
	 * Return all the fields and associated values in a hash. Time complexity:
	 * O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	public abstract Map<String, String> hashGetAll(String key)throws Exception;

	public abstract Map<byte[], byte[]> hashGetAll(byte[] key)throws Exception;

	public abstract Collection<byte[]> hashGetAllValues(byte[] key)throws Exception;

	/**
	 * This 批量的hashMultipleGet
	 * 
	 * @param keys
	 * @return
	 */
	public abstract List<Map<String, String>> batchHashGetAll(
			final List<String> keys)throws Exception;

	public static final Builder<Double> DOUBLE = new Builder<Double>() {

		@Override
		public Double build(Object data) {
			return Double.valueOf(STRING.build(data));
		}

		@Override
		public String toString() {
			return "double";
		}
	};

	public static final Builder<Boolean> BOOLEAN = new Builder<Boolean>() {

		@Override
		public Boolean build(Object data) {
			return ((Long) data) == 1;
		}

		@Override
		public String toString() {
			return "boolean";
		}
	};
	public static final Builder<Long> LONG = new Builder<Long>() {

		@Override
		public Long build(Object data) {
			return (Long) data;
		}

		@Override
		public String toString() {
			return "long";
		}

	};
	public static final Builder<String> STRING = new Builder<String>() {

		@Override
		public String build(Object data) {
			return SafeEncoder.encode(data.toString().getBytes());
		}

		@Override
		public String toString() {
			return "string";
		}

	};
	public static final Builder<List<String>> STRING_LIST = new Builder<List<String>>() {

		@Override
		@SuppressWarnings("unchecked")
		public List<String> build(Object data) {
			if (null == data) {
				return null;
			}
			List<byte[]> l = (List<byte[]>) data;
			final ArrayList<String> result = new ArrayList<String>(l.size());
			for (final byte[] barray : l) {
				if (barray == null) {
					result.add(null);
				} else {
					result.add(SafeEncoder.encode(barray));
				}
			}
			return result;
		}

		@Override
		public String toString() {
			return "List<String>";
		}

	};
	public static final Builder<Map<String, String>> STRING_MAP = new Builder<Map<String, String>>() {

		@Override
		@SuppressWarnings("unchecked")
		public Map<String, String> build(Object data) {
			final List<byte[]> flatHash = (List<byte[]>) data;
			final Map<String, String> hash = new HashMap<String, String>();
			final Iterator<byte[]> iterator = flatHash.iterator();
			while (iterator.hasNext()) {
				hash.put(SafeEncoder.encode(iterator.next()),
						SafeEncoder.encode(iterator.next()));
			}

			return hash;
		}

		@Override
		public String toString() {
			return "Map<String, String>";
		}

	};
	public static final Builder<Set<String>> STRING_SET = new Builder<Set<String>>() {

		@Override
		@SuppressWarnings("unchecked")
		public Set<String> build(Object data) {
			if (null == data) {
				return null;
			}
			List<byte[]> l = (List<byte[]>) data;
			final Set<String> result = new HashSet<String>(l.size());
			for (final byte[] barray : l) {
				if (barray == null) {
					result.add(null);
				} else {
					result.add(SafeEncoder.encode(barray));
				}
			}
			return result;
		}

		@Override
		public String toString() {
			return "Set<String>";
		}

	};
	public static final Builder<Set<String>> STRING_ZSET = new Builder<Set<String>>() {

		@SuppressWarnings("unchecked")
		@Override
		public Set<String> build(Object data) {
			if (null == data) {
				return null;
			}
			List<byte[]> l = (List<byte[]>) data;
			final Set<String> result = new LinkedHashSet<String>(l.size());
			for (final byte[] barray : l) {
				if (barray == null) {
					result.add(null);
				} else {
					result.add(SafeEncoder.encode(barray));
				}
			}
			return result;
		}

		@Override
		public String toString() {
			return "ZSet<String>";
		}

	};

	public JedisHandler() {
		super();
	}

	/**
	 * This 构造Pair
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public <K, V> Pair<K, V> makePair(K key, V value) {
		return new Pair<K, V>(key, value);
	}

	/**
	 * The Pair represents 键值对
	 * 
	 * @version $Id$
	 * @author fengjc
	 * @param <K>
	 * @param <V>
	 */
	public static final class Pair<K, V> {

		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return this.key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return this.value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}