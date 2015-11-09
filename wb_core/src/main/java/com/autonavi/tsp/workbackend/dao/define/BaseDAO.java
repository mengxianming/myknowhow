package com.autonavi.tsp.workbackend.dao.define;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.autonavi.tsp.workbackend.util.page.Page;

public interface BaseDAO<Entity, Example, Key> {
    
    int countByExample(Example example);

    
    int deleteByExample(Example example);

    
    int deleteByPrimaryKey(Key key);

    
    int insert(Entity record);

    
    int insertSelective(Entity record);

    
    List<Entity> selectByExample(Example example);

    
    Entity selectByPrimaryKey(Key key);

    
    int updateByExampleSelective(@Param("record") Entity record, @Param("example") Example example);

    
    int updateByExample(@Param("record") Entity record, @Param("example") Example example);

    
    int updateByPrimaryKeySelective(Entity record);

    int updateByPrimaryKey(Entity record);
    /**
     * 分页查询匹配example条件的记录。
     * 
     * @param example
     * @param pageNo 第几页。从1开始。 
     * @param pageSize 每页记录个数
     * @return
     */
    public Page<Entity> selectByExampleWithPager(Example example, Integer pageNo, Integer pageSize);
    /**
     * 分页查询匹配example条件的指定条数的记录。
     * 
     * @param example
     * @param limit 指定条数
     * @return
     */
    public List<Entity> selectByExampleWithLimit(Example example, Integer limit);
    /**
     * 获取匹配example条件的唯一条记录。若不存在匹配记录则返回null。
     * 若匹配记录不止一条、则抛出RuntimeException.
     * @param example
     * @return
     */
    public Entity selectOneByExample(Example example);
    /**
     * 获取匹配example条件的一条记录。若不存在匹配记录则返回null。
     * 若匹配记录不止一条、则返回第一条记录。
     * @param example
     * @return
     */
    public Entity selectFirstOneByExample(Example example);
    
    public int insertBatch(List<Entity> entities);
	public int updateBatchByPrimaryKeySelective(List<Entity> list);
	
}