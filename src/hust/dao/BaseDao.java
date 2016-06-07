package hust.dao;

import hust.tool.Pager;
import hust.tool.SystemContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

//约定 namespace值是该实体类的全路径

public class BaseDao<T> {
	
	private SqlSession sqlSession;
	
	public void add(T obj) {
		this.getSqlSession().insert(obj.getClass().getName() + ".insert", obj);
	}
	
	public void delete(Class<T> clazz, String id) {
		this.getSqlSession().delete(clazz.getName() + ".delete", id);
	}

	public void modify(T obj) {
		this.getSqlSession().update(obj.getClass().getName() + ".update", obj);
	}
	
	/**
	 * 根据Id查找
	 */
	public T getById(Class<T> clazz, String id) {
		return this.getSqlSession().selectOne(clazz.getName() + ".getById", id);
	}

	/**
	 * 根据指定的多个条件获取一条记录
	 */
	public T getByConditions(String sqlId, Map<String,Object> params) {
		return this.getSqlSession().selectOne(sqlId, params);
	}

	/**
	 * 根据指定的一个条件获取一条记录（前提是该条件在表中具有唯一性）
	 */
	public T getByUnique(String sqlId, Object param) {
		return this.getSqlSession().selectOne(sqlId, param);
	}

	/**
	 * 不带分页的查询 （默认是list）
	 */
	public List<T> list(Class<T> clazz, Map<String,Object> params) {
		return this.list(clazz.getName() + ".list", params);
	}

	/**
	 * 不带分页的查询 ，自行指定sqlId
	 * 排序依然带着
	 */
	public List<T> list(String sqlId, Map<String,Object> params) {
		return this.getSqlSession().selectList(sqlId, params);
	}

	/**
	 * 有分页的查询
	 */
	public Pager<T> find(Class<T> clazz, Map<String,Object> params) {
		return this.find(clazz.getName()+".listPage", params);
	}

	/**
	 * 有分页的查询 （自行指定sqlId）
	 */
	public Pager<T> find(String sqlId, Map<String,Object> params) {
		int pageOffset=SystemContext.getPageOffset();
		int pageSize=SystemContext.getPageSize();
		String order=SystemContext.getOrder();
		String sort=SystemContext.getSort(); 
		Pager<T> pages=new Pager<T>();
		if(params==null) params=new HashMap<String,Object>();

		params.put("pageOffset", pageOffset);
		params.put("pageSize", pageSize);
		params.put("order", order);
		params.put("sort", sort);
		//约定 namespace值是该实体类的全路径，并且所有的分页查询都是find
		List<T> lists = this.getSqlSession().selectList(sqlId, params);
		
		pages.setDatas(lists);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		//获取当前条件下的所有记录数
		//查询记录的命名是 XXX，那么约定记录条数命名是 XXX_count
		int count = this.getSqlSession().selectOne(sqlId+"_count", params);
		pages.setTotalRecord(count);
		return pages;
	}
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
