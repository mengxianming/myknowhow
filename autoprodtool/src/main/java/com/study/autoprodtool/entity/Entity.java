package com.study.autoprodtool.entity;

/**
 * DBテーブルを表すエンティティ。
 * 全テーブルが共有している属性はこのクラスに格納する。
 * @author mengxm 2013-5-27 created
 *
 */
public abstract class Entity {
	protected Long id;
	
	public Long getId() {		
		return id;
	}

	public void setId(Long key) {
		this.id = key;
	}
		 
	
	public static <E extends Entity> E emptyEntity(Class<E> clazz, Long id){
		E ent;
		try {
			ent = clazz.newInstance();
		}
		catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
		
		ent.setId(id);
		return ent;
	}
}
 
