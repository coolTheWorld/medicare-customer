package yibao.yiwei.utils;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;

/**
 * 分表查询拦截器  用来替换查询表名
 * @author Administrator
 *
 */
public class SQLInterceptor extends EmptyInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table;
	private String newTable;
	
	public SQLInterceptor(String table) {
		this.table = table;
	}

	public void setNewTable(String newTable) {
		this.newTable = newTable;
	}

	@Override
	public String onPrepareStatement(String sql) {
		if(StringUtils.isNotEmpty(newTable)) {
			sql = sql.replaceAll(table, newTable);
		}
		return super.onPrepareStatement(sql);
		
	}
	
	
}
