package yibao.yiwei.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
@Component("iBaseDao")
public interface IBaseDao<T> {
	
	/**
	 * getSessionFactory
	 * @return
	 */
	SessionFactory getSessionFactory();

	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	Serializable save(T entity);

	/**
	 * 添加或修改
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 修改
	 * @param entity
	 */
	void merge(T entity);
	
	/**
	 * 添加或修改
	 * @param entity
	 */
	void saveOrUpdate(T entity);
	
	/**
	 * 删除
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 根据ID查询
	 * @param entityClazz
	 * @param id
	 * @return
	 */
	T get(Class<T> entityClazz, Serializable id);
	
	/**
	 * hql增删改
	 * @param hql
	 * @param objects
	 * @return
	 */
	int updateOrDelete(String hql, Object... objects);
	
	/**
	 * hql查询(返回多条)
	 * @param hql
	 * @param objects
	 * @return
	 */
	List<T> find(String hql, Object... objects);
	
	/**
	 * hql查询(返回单条)
	 * @param hql
	 * @param objects
	 * @return
	 */
	T findUnique(String hql, Object... objects);
	
	/**
	 * hql统计
	 * @param hql
	 * @param objects
	 * @return
	 */
	int findCount(String hql, Object... objects);
	
	/**
	 * hql分页查询
	 * @param hql
	 * @param pageSize
	 * @param currentPage
	 * @param objects
	 * @return
	 */
	List<T> findByPage(String hql, int pageSize, int currentPage, Object... objects);
	
	/**
	 * sql增删改
	 * @param sql
	 * @param objects
	 * @return
	 */
	int updateOrDeleteSql(String sql, Object... objects);
	
	/**
	 * sql查询(返回多条)
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<T> findSql(String sql, Object... objects);
	
	/**
	 * sql查询(返回单条)
	 * @param sql
	 * @param objects
	 * @return
	 */
	T findUniqueSql(String sql, Object... objects);
	
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
	 * @param sql
	 * @param objects
	 * @return
	 */
	int findCountSql(String sql, Object... objects);
	
	/**
	 * sql分页查询
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @param objects
	 * @return
	 */
	List<T> findByPageSql(String sql, int pageSize, int currentPage, Object... objects);
	
	/**
	 * sql查询转换实体
	 * @param sql
	 * @param tableName
	 * @param entityClazz
	 * @param objects
	 * @return
	 */
	List<T> findEntitySql(String sql,Class<T> entityClazz, Object... objects);
	
	/**
	 * sql分页查询转换实体
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @param entityClazz
	 * @param objects
	 * @return
	 */
	List<T> findByPageEntitySql(String sql,Class<T> entityClazz,int pageSize, int currentPage,Object... objects);
	
	/**
	 * sql查询转换dto
	 * @param sql
	 * @param entityClazz
	 * @return
	 */
	List<T> findDtoSql(String sql,Class<T> entityClazz);
	
	/**
	 * sql查询替换表名 分表查询
	 * @param sql
	 * @param entityClazz
	 * @param pageSize
	 * @param currentPage
	 * @param oldTable
	 * @param newTables
	 * @param objects
	 * @return
	 */
	List<T> findEntitySqlByTable(String sql, Class<T> entityClazz, int pageSize, int currentPage, 
			String oldTable,String newTable,Object... objects);
	/**
	 * 替换表名 分表查询sql统计
	 */
	int[] findCountSqlByTable(String sql,String oldTable,String[] newTables,Object... objects);
	
}