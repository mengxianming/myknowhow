package com.mogoroom.service.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
* @Description: 分页查询
* @author alex (90167)
* @date 2014-1-9 下午7:18:04
* @param <T>
 */
public interface BaseMapper<T> {

	public String PO_KEY = "po";
	public static interface Page<T>{}
	/**
	* @Title: listPageInfo
	* @Description: 分页查询
	* @param @param page
	* @param @return    设定文件
	* @return List<T>    返回类型
	* @author alex (90167)
	* @throws
	 */
	public List<T> selectListPageInfo(@Param("page") Page<T> p, @Param("po") T obj);
	
	
	/**
	* @Title: insert 
	* @Description: 添加一条数据
	* @param t    设定文件 
	* @return void    返回类型 
	* @author alex (90167)
	* @date 2013年12月16日 下午7:00:30
	 */
	public int insert(T t);

	/**
	* @Title: update 
	* @Description: 修改一条数据
	* @param t    设定文件 
	* @return void    返回类型 
	* @author alex (90167)
	* @date 2013年12月16日 下午7:01:37
	 */
	public int update(T t);

	/**
	* @Title: delete 
	* @Description: 删除一条数据
	* @param id    设定文件 
	* @return void    返回类型 
	* @author alex (90167)
	* @date 2013年12月16日 下午7:01:55
	 */
	public int delete(Integer id);

	/**
	* @Title: findById 
	* @Description: 根据编号查找对象
	* @param @param id
	* @param @return    设定文件 
	* @return T    返回类型 
	* @throws 
	* @author alex (90167)
	* @date 2013年12月17日 下午12:03:16
	 */
	public T selectById(Integer id);
	
	/**
	* @Title: findAll 
	* @Description: 查询所有数据
	* @param @return    设定文件 
	* @return List<T>    返回类型 
	* @author alex (90167)
	* @date 2013年12月17日 下午12:03:44
	 */
	public List<T> selectAll(String _status); 
	
	
}
