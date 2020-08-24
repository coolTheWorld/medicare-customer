package yibao.yiwei.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yibao.yiwei.dao.IBaseDao;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.utils.SQLInterceptor;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDao<T> implements IBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * getSessionFactory
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 添加
	 */
	@Override
	public Serializable save(T entity) {
		return getSessionFactory().getCurrentSession().save(entity);
	}

	/**
	 * 添加或修改
	 */
	@Override
	public void update(T entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	/**
	 * session merge方法
	 */
	@Override
	public void merge(T entity) {
		getSessionFactory().getCurrentSession().merge(entity);
	}
	
	
	/**
	 * 添加或修改
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(T entity) {
		
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		//getSessionFactory().getCurrentSession().clear();
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	/**
	 * 根据ID查询
	 */
	@Override
	public T get(Class<T> entityClazz, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
	}

	/**
	 * hql增删改
	 */
	@Override
	public int updateOrDelete(String hql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.executeUpdate();
	}

	/**
	 * hql查询(返回多条)
	 */
	@Override
	public List<T> find(String hql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * hql查询(返回单条)
	 */
	@Override
	public T findUnique(String hql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return (T)query.uniqueResult();
	}
	
	/**
	 * hql统计
	 */
	@Override
	public int findCount(String hql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	/**
	 * hql分页查询
	 */
	@Override
	public List<T> findByPage(String hql, int pageSize, int currentPage, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
	}
	
	/**
	 * sql增删改
	 */
	@Override
	public int updateOrDeleteSql(String sql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.executeUpdate();
	}
	
	/**
	 * sql查询(返回多条)
	 */
	@Override
	public List<T> findSql(String sql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * sql查询(返回单条)
	 */
	@Override
	public T findUniqueSql(String sql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return (T)query.uniqueResult();
	}
	
	/**
	 * sql统计
	 */
	@Override
	public int findCountSql(String sql, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	/**
	 * sql分页查询
	 */
	@Override
	public List<T> findByPageSql(String sql, int pageSize, int currentPage, Object...objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
	}
	
	/**
	 * sql查询转换实体
	 */
	@Override
	public List<T> findEntitySql(String sql,Class<T> entityClazz, Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(entityClazz);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * sql分页查询转换实体
	 */
	@Override
	public List<T> findByPageEntitySql(String sql,Class<T> entityClazz,int pageSize, int currentPage,Object... objects) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(entityClazz);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return query.setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
	}
	
	/**
	 * sql查询转换dto
	 */
	@Override
	public List<T> findDtoSql(String sql,Class<T> entityClazz){
		return getSessionFactory().getCurrentSession().createSQLQuery(sql)
						.setResultTransformer(Transformers.aliasToBean(entityClazz))
						.list();
	}
	
	/**
	 * sql查询替换表名 分表查询
	 */
	@Override
	public List<T> findEntitySqlByTable(String sql,Class<T> entityClazz,
			int pageSize, int start,String oldTable,String newTable,Object... objects) {
		SQLInterceptor interceptor = new SQLInterceptor(oldTable);
		interceptor.setNewTable(newTable);
		//Session session = sessionFactory.withOptions().interceptor(interceptor).openSession();
		Session session = getSessionFactory().withOptions().interceptor(interceptor).openSession();
		Query query = session.createSQLQuery(sql).addEntity(entityClazz);
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		List<T> list = query.setFirstResult(start)
				.setMaxResults(pageSize).list();
		session.close();//关闭连接
		return list;
	}
	
	/**
	 * 分表查询进行sql统计
	 */
	@Override
	public int[] findCountSqlByTable(String sql,String oldTable,String[] newTables,Object... objects) {
		
		int[] count = new int[newTables.length];
		Session session = null;
		for(int i=0;i<newTables.length;i++) {
			String newTable = newTables[i];
			SQLInterceptor interceptor = new SQLInterceptor(oldTable);
			interceptor.setNewTable(newTable);
			session = getSessionFactory().withOptions().interceptor(interceptor).openSession();
			Query query = session.createSQLQuery(sql);
			if (null != objects) {
				for (int j = 0; j < objects.length; j++) {
					query.setParameter(j + "", objects[j]);
				}
			}
			count[i] = Integer.parseInt(query.uniqueResult().toString());
		}
		if(session != null) {
			session.close();//关闭连接
		}
		return count;
	}

	@Override
	public T findUniqueSql(String sql, Class<T> entity, Object... objects) {
		Query query = getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(sql)
				.addEntity(entity);
				//.setResultTransformer(Transformers.aliasToBean(entity));
		if (null != objects) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + "", objects[i]);
			}
		}
		return (T)query.uniqueResult();
	}
	
}