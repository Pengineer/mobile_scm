package hust.dao;

import hust.tool.Pager;
import hust.tool.SystemContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

//约定 namespace值是该实体类的全路径

public class BaseDao<T> {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void add(T obj) {
		this.sqlSession.insert(obj.getClass().getName() + ".insert", obj);
	}
	
	public void delete(Class<T> clazz,String id) {
		this.sqlSession.delete(clazz.getName() + ".delete", id);
	}

	public void modify(T obj) {
		this.sqlSession.update(obj.getClass().getName() + ".update", obj);
	}
	
	/**
	 * 根据Id查找
	 */
	public T getById(Class<T> clazz, String id) {
		return this.sqlSession.selectOne(clazz.getName() + ".getById", id);
	}

	/**
	 * 根据指定的多个条件获取一条记录
	 */
	public T getByConditions(String sqlId, Map<String,Object> params) {
		T obj = null;
		obj = this.sqlSession.selectOne(sqlId, params);
		return obj;
	}

	/**
	 * 根据指定的一个条件获取一条记录（前提是该条件在表中具有唯一性）
	 */
	public T getByUnique(String sqlId, Object param) {
		T obj = this.sqlSession.selectOne(sqlId, param);
		return obj;
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
		List<T> list=null;
		list = this.sqlSession.selectList(sqlId, params);
		return list;
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
		List<T> lists = this.sqlSession.selectList(sqlId, params);
		
		pages.setDatas(lists);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		//获取当前条件下的所有记录数
		//查询记录的命名是 XXX，那么约定记录条数命名是 XXX_count
		int count = this.sqlSession.selectOne(sqlId+"_count", params);
		pages.setTotalRecord(count);
		return pages;
	}

}
