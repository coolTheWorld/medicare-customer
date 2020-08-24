package yibao.yiwei.service;

import java.io.Serializable;
import java.util.List;

import yibao.yiwei.entity.system.Page;

public interface IBaseService<T> {

	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	public Serializable save(T entity);

	/**
	 * 添加或修改
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 添加或修改
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * 合并
	 * @param entity
	 */
	public void merge(T entity);
	
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 根据ID查询
	 * @param entityClazz
	 * @param id
	 * @return
	 */
	public T get(Class<T> entityClazz, Serializable id);
	
	/**
	 * hql增删改
	 * @param hql
	 * @param objects
	 * @return
	 */
	public int updateOrDelete(String hql, Object... objects);
	
	/**
	 * hql查询(返回多条)
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> find(String hql, Object... objects);
	
	/**
	 * hql查询(返回单条)
	 * @param hql
	 * @param objects
	 * @return
	 */
	public T findUnique(String hql, Object... objects);
	
	/**
	 * hql统计
	 * @param hql
	 * @param objects
	 * @return
	 */
	public int findCount(String hql, Object... objects);
	
	/**
	 * hql分页查询
	 * @param hql
	 * @param rows
	 * @param page
	 * @param objects
	 * @return
	 */
	public List<T> findByPage(String hql, String rows, String page, Object... objects);
	
	/**
	 * sql增删改
	 * @param sql
	 * @param objects
	 * @return
	 */
	public int updateOrDeleteSql(String sql, Object... objects);
	
	/**
	 * sql查询(返回多条)
	 * @param sql
	 * @param objects
	 * @return
	 */
	public List<T> findSql(String sql, Object... objects);
	
	/**
	 * sql查询(返回单条)
	 * @param sql
	 * @param objects
	 * @return
	 */
	public T findUniqueSql(String sql, Object... objects);
	
	/**
	 * sql查询(返回单个entity)
	 * @param sql
	 * @param entity
	 * @param objects
	 * @return
	 */
	T findUniqueSql(String sql,Class<T> entity,Object... objects);
	
	/**
	 * sql统计
	 * @param hql
	 * @param objects
	 * @return
	 */
	public int findCountSql(String hql, Object... objects);
	
	/**
	 * sql分页查询
	 * @param sql
	 * @param rows
	 * @param page
	 * @param objects
	 * @return
	 */
	public List<T> findByPageSql(String sql, String rows, String page, Object... objects);
	
	/**
	 * sql查询转换实体
	 * @param sql
	 * @param tableName
	 * @param entityClazz
	 * @param objects
	 * @return
	 */
	public List<T> findEntitySql(String sql,Class<T> entityClazz, Object... objects);
	
	/**
	 * sql分页查询转换实体
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @param entityClazz
	 * @param objects
	 * @return
	 */
	List<T> findByPageEntitySql(String sql,Class<T> entityClazz,String row,String page,Object... objects);
	
	/**
	 * sql查询转换dto
	 * @param sql
	 * @param entityClazz
	 * @return
	 */
	public List<T> findDtoSql(String sql,Class<T> entityClazz);
	
	/**
	 * 分页条件查询page方式
	 * @param countHql 统计条数
	 * @param hql 查询hql
	 * @param url 请求地址
	 * @param currentPage 当前页
	 * @param params 条件参数
	 * @return 封闭了条件查询分页信息(包括记录集list)的Bean
	 */
	public Page queryForPage(String countHql, String hql, String url, int currentPage, Object... params);
	
	List<T> findEntitySqlByTable(String sql, Class<T> entityClazz, String pageSize, String currentPage, 
			String oldTable,String[] newTable,int[] tablesCount, Object... objects);
	
	/**
	 * 替换表名 分表查询sql统计
	 */
	int[] findCountSqlByTable(String sql,String oldTable,String[] newTables,Object... objects);
}