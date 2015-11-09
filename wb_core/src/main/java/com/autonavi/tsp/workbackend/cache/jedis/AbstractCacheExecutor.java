package com.autonavi.tsp.workbackend.cache.jedis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.SerializationUtils;

import com.autonavi.tsp.workbackend.dto.SerializableEntity;
public abstract class AbstractCacheExecutor<T extends SerializableEntity<K>, K>{
	protected static final String KEY_PREFIX = "wb_table_";
	
	private JedisHandler<?> handler;
	
	
	/**
	 * @param handler the handler to set
	 */
	public void setHandler(JedisHandler<?> handler) {
		this.handler = handler;
	}
	
	public abstract String getCacheKey();
	public abstract byte[] getCacheKeysBytes();
	
    
    @SuppressWarnings("unchecked")
	public void add(T... entities) throws Exception{
        if (entities != null && entities.length > 0) {
            Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
            for (T entity : entities) {
                map.put(serializeString(String.valueOf(entity.getKey())), SerializationUtils.serialize(entity));
            }
            handler.hashMultipleSet(getCacheKeysBytes(), map);
        }
    }

	@SuppressWarnings("unchecked")
	public Long remove(K... keys)throws Exception {
        if (keys != null && keys.length > 0) {
        	String[] ids = new String[keys.length];
            for (int i = 0; i < keys.length; i++) {
                ids[i] = String.valueOf(keys[i]);
            }
            return handler.hashRemove(getCacheKey(), ids);
        }
        return 0L;
    }

	
    @SuppressWarnings("unchecked")
	public T find(K key)throws Exception {
        byte[] bytes = handler.hashGet(getCacheKeysBytes(),
                serializeString(String.valueOf(key)));

        return bytes != null ? (T) SerializationUtils.deserialize(bytes) : null;
    }


    /**
	 * @param key
	 * @return
     * @throws UnsupportedEncodingException 
	 */
	private static byte[] serializeString(String key) throws UnsupportedEncodingException {		
		return key.getBytes("utf-8");
	}

    
    @SuppressWarnings("unchecked")
	public Set<T> findAll()throws Exception {
        Collection<byte[]> list = handler.hashGetAllValues(getCacheKeysBytes());
        Set<T> results = new HashSet<T>();
        for (byte[] bytes : list) {
            results.add((T) SerializationUtils.deserialize(bytes));
        }
        return results;
    }

    
    @SuppressWarnings("unchecked")
	public List<T> find(K... keys)throws Exception {
        byte[][] bytes = new byte[keys.length][];
        int count = 0;
        for (Object key : keys) {
            bytes[count++] = serializeString(String.valueOf(key));
        }
        List<byte[]> list = handler.hashMultipleGet(getCacheKeysBytes(), bytes);
        List<T> vos = new ArrayList<T>();
        for (byte[] by : list) {
            vos.add((T) SerializationUtils.deserialize(by));
        }
        return vos;
    }
}
