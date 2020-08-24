package yibao.yiwei.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dahua.util.HttpClientPoolUtil;
import com.dahua.util.RSAUtils;

import yibao.yiwei.entity.Salesitem;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;


@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/testSales")
public class TestSalesItemController {
	
	@Autowired
	private IBaseService baseService;
	
	/**
	 * 回调事件列表
	 * @return
	 */
	@RequestMapping("/toSalesItem")
	public String toEventlist(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH,1);
		request.setAttribute("startDate", sdf.format(calendar.getTime()));
		request.setAttribute("endDate", sdf.format(new Date()));
		return "/test/testSalesItem";
	}
	
	
	@ResponseBody
	@RequestMapping("/getSalesitemVO")
	public Map<String,Object> getSalesitemVO(HttpServletRequest request,
			String startDate,String endDate,Salesitem queryBean) throws ParseException {
		
		String rows = request.getParameter("rows");  // 每页显示的记录数
		String page = request.getParameter("page");  // 当前第几页
		String[] newTables = Utils.getTablesByMonth("TBL_SALESITEM", startDate, endDate);
		String sql = "select * from TBL_SALESITEM where 1=1";
		String countSql = "select count(*) from TBL_SALESITEM where 1=1";
	    
		if(null != startDate && !"".equals(startDate)){
			String date = startDate+" 00:00:00";
			countSql += " and so_datetime >=to_date('"+date+"','yyyy/mm/dd hh24:mi:ss')";
			sql += " and so_datetime >=to_date('"+date+"','yyyy/mm/dd hh24:mi:ss')";
		}
		if(null != endDate && !"".equals(endDate)){
			String date = endDate+" 23:59:59";
			countSql += " and so_datetime <=to_date('"+date+"','yyyy/mm/dd hh24:mi:ss')";
			sql += " and so_datetime <=to_date('"+date+"','yyyy/mm/dd hh24:mi:ss')";
		}
		sql += "order by drug_salesprice desc nulls last";
		
		long start = new Date().getTime();
		System.out.println("开始："+start);
		//int[] totals = baseService.findCountByTable(countSql,"TBL_SALESITEM",newTables);
		int[] totals = baseService.findCountSqlByTable(countSql,"TBL_SALESITEM",newTables);
		System.out.println("查询条数耗时："+(new Date().getTime()-start));
		long start2 = new Date().getTime();
		List<Salesitem> salesList = baseService.findEntitySqlByTable(sql,Salesitem.class,rows,page,"TBL_SALESITEM",newTables,totals);
		System.out.println("分页查询耗时："+(new Date().getTime()-start2));
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		for(int i : totals) {
			count += i;
		}
		map.put("total", count);
		map.put("rows", salesList);
		return map;
	}
}
