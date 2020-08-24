package yibao.yiwei.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yibao.yiwei.dao.IBaseDao;
import yibao.yiwei.entity.system.Page;
import yibao.yiwei.service.IBaseService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class BaseService<T> implements IBaseService<T> {
	
	@Autowired
	private IBaseDao<T> baseDao;

	/**
	 * 添加
	 */
	@Override
	public Serializable save(T entity) {
		return baseDao.save(entity);
	}

	/**
	 * 添加或修改
	 */
	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public T get(Class<T> entityClazz, Serializable id) {
		return baseDao.get(entityClazz, id);
	}
	
	/**
	 * hql增删改
	 */
	@Override
	public int updateOrDelete(String hql, Object... objects) {
		return baseDao.updateOrDelete(hql, objects);
	}
	
	/**
	 * hql查询(返回多条)
	 */
	@Override
	public List<T> find(String hql, Object... objects) {
		return baseDao.find(hql, objects);
	}
	
	/**
	 * hql查询(返回单条)
	 */
	@Override
	public T findUnique(String hql, Object... objects){
		return baseDao.findUnique(hql, objects);
	}
	
	/**
	 * hql统计
	 */
	@Override
	public int findCount(String hql, Object... objects) {
		return baseDao.findCount(hql, objects);
	}
	
	/**
	 * hql分页查询
	 */
	@Override
	public List<T> findByPage(String hql, String rows, String page, Object... objects) {
		int pageSize = Integer.parseInt((null == rows || rows.equals("0")) ? "10" : rows);
		int currentpage = Integer.parseInt((null == page || page.equals("0")) ? "1" : page);
		return baseDao.findByPage(hql, pageSize, currentpage, objects);
	}
	
	/**
	 * sql增删改
	 */
	@Override
	public int updateOrDeleteSql(String sql, Object... objects) {
		return baseDao.updateOrDeleteSql(sql, objects);
	}
	
	/**
	 * sql查询(返回多条)
	 */
	@Override
	public List<T> findSql(String sql, Object... objects) {
		return baseDao.findSql(sql, objects);
	}
	
	/**
	 * sql查询(返回单条)
	 */
	@Override
	public T findUniqueSql(String sql, Object... objects){
		return baseDao.findUniqueSql(sql, objects);
	}
	
	/**
	 * sql统计
	 */
	@Override
	public int findCountSql(String hql, Object... objects) {
		return baseDao.findCountSql(hql, objects);
	}
	
	/**
	 * sql分页查询
	 */
	@Override
	public List<T> findByPageSql(String sql, String rows, String page, Object... objects) {
		int pageSize = Integer.parseInt((null == rows || rows.equals("0")) ? "10" : rows);
		int currentpage = Integer.parseInt((null == page || page.equals("0")) ? "1" : page);
		return baseDao.findByPageSql(sql, pageSize, currentpage, objects);
	}
	
	/**
	 * sql查询转换实体
	 */
	@Override
	public List<T> findEntitySql(String sql,Class<T> entityClazz, Object... objects) {
		return baseDao.findEntitySql(sql,entityClazz,objects);
	}
	
	/**
	 * sql分页查询转换实体
	 */
	@Override
	public List<T> findByPageEntitySql(String sql,Class<T> entityClazz,String rows, String page,Object... objects) {
		int pageSize = Integer.parseInt((null == rows || rows.equals("0")) ? "10" : rows);
		int currentPage = Integer.parseInt((null == page || page.equals("0")) ? "1" : page);
		return baseDao.findByPageEntitySql(sql,entityClazz,pageSize,currentPage,objects);
	}
	
	/**
	 * sql查询转换dto
	 */
	@Override
	public List<T> findDtoSql(String sql,Class<T> entityClazz){
		return baseDao.findDtoSql(sql, entityClazz);
	}
	
	/**
	 * 分页条件查询page方式
	 */
	@Override
	public Page queryForPage(String countHql, String hql, String url, int pageNum, Object... params) {
		int allRow = baseDao.findCountSql(countHql, params);//总记录数
		int currentPage = Page.countCurrentPage(pageNum);//当前的页码
		Page page = new Page(allRow, currentPage);//分页信息保存在bean中
		int pageSize = page.getPageSize();//每页记录数
		List list = baseDao.findByPage(hql, pageSize, currentPage, params);
		page.setUrl(url);
		page.setList(list);
		return page;
	}
	
	@Override
	public List<T> findEntitySqlByTable(String sql, Class<T> entityClazz, String rows, String page,
			String oldTable,String[] newTables,int[] tablesCount, Object... objects) {
		List<T> list = new ArrayList<T>();
		int pageSize = Integer.parseInt((null == rows || rows.equals("0")) ? "10" : rows);//每页显示数据
		int currentpage = Integer.parseInt((null == page || page.equals("0")) ? "1" : page);//当前页数

		int i = 0;
		int start = (currentpage-1)*pageSize;//查询开始位置
		int prevNum = tablesCount[0];  //前面已经展示过的总数据 从第一个表开始
		while (true) {
			 
			 list = baseDao.findEntitySqlByTable(sql,entityClazz,pageSize,start,oldTable,newTables[i],objects);
			 if(list.size()==pageSize || i==newTables.length-1) {//查询出的数据符合要求直接跳出循环 返回数据
				 break;
			 }else if(list.size()>0 && list.size()<pageSize){
				 if(i==newTables.length-1) {//如果查询的是最后一个表的最后一页数据  直接跳出循环 返回数据
					 break;
				 }else {//查询出的数据不足显示条数 从下一个表中取数据 补足显示条数
					 pageSize = pageSize-list.size();//后面需要补多少条数据
					 i++;
					 start = 0;
					 List<T> listTwo = baseDao.findEntitySqlByTable(sql,entityClazz,pageSize,start,oldTable,newTables[i],objects);
					 list.addAll(listTwo);
					 while(true) {
						 if(list.size()==pageSize || i==newTables.length-1) {
							 //第二个表获取的数据满足显示条数或者已经是最后一个表了跳出循环
							 break;
						 }else if(list.size()<pageSize) {
							 //补充后的数据依然不足显示条数 继续循环从下一个表补数据
							 pageSize = pageSize-list.size();//后面需要补多少条数据
							 i++;
							 start = 0;
							 List<T> listThree = baseDao.findEntitySqlByTable(sql,entityClazz,pageSize,start,oldTable,newTables[i],objects);
							 list.addAll(listThree);
							 continue;
						 }
					 }
					 break;
				 }
			 }else if(list.size()<=0) {//计算当前展示页需要从下一个表的第几条数据开始查
				 i++;
				 start = currentpage*pageSize - prevNum-pageSize;
				 prevNum += tablesCount[i];
				 continue;
			 }
		}
		
		return list;
	}

	@Override
	public int[] findCountSqlByTable(String sql, String oldTable, String[] newTables, Object... objects) {
		// TODO Auto-generated method stub
		return baseDao.findCountSqlByTable(sql,oldTable,newTables,objects);
	}

	/**
	 * sql查询(返回单个entity)
	 * @param sql
	 * @param entity
	 * @param objects
	 * @return
	 */
	@Override
	public T findUniqueSql(String sql, Class<T> entity, Object... objects) {
		return baseDao.findUniqueSql(sql, entity, objects);
	}

	/**
	 * 合并
	 */
	@Override
	public void merge(T entity) {
		baseDao.merge(entity);
	}
	
	/**
	 * 添加或修改
	 */
	@Override
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}
}
