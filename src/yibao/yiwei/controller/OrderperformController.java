package yibao.yiwei.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.entity.Orderperform;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

/**
 * 医嘱执行记录
 */
@Controller
public class OrderperformController {
	
	@Autowired
	private IBaseService<Orderperform> baseService;
	
	/**
	 * 转到医嘱执行记录
	 * @param cusId
	 * @param request
	 * @return
	 */
	@RequestMapping("toOrderperform")
	public String toOrderperform(String cusId, HttpServletRequest request) {
		request.setAttribute("cusId", cusId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		request.setAttribute("startDate", sdf.format(cal.getTime()));
		request.setAttribute("endDate", sdf.format(Utils.getDate(1)));
		return "/data/orderperform";
	}

	/**
	 * 医嘱执行记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getAllOrderperform")
	public void getAllOrderperform(HttpServletRequest request, HttpServletResponse response,String startDate,String endDate,String hospNo,String doNo,String itemCode) throws IOException ,ParseException{
		List<Orderperform> list = new ArrayList<Orderperform>();// 返回的结果集无论有没有条件查询
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String cusId = request.getParameter("cusId");// 当前cusId
		int count = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String hql = "from Orderperform where cusId=?0 ";
		String countSql = "select count(DO_ID) from tbl_Orderperform where cus_Id = ?0";
		
		if(null != hospNo && !hospNo.equals("")){
			hospNo = hospNo.trim();
			countSql+=" and HOSP_NO = '"+hospNo+"'";
			hql+=" and hospNo = '"+hospNo+"'";
		}
		
		if(null != doNo && !doNo.equals("")){
			doNo = doNo.trim();
			countSql+=" and DO_NO = '"+doNo+"'";
			hql+=" and doNo = '"+doNo+"'";
		}
		
		if(null != itemCode && !itemCode.equals("")){
			itemCode = itemCode.trim();
			countSql+=" and (ITEM_CODE = '"+itemCode+"' or ITEM_NAME like '%"+itemCode+"%')";
			hql+=" and (itemCode = '"+itemCode+"' or itemName like '%"+itemCode+"%')";
		}
		
		Date itemPicktime = null;
		Date itemPicktime2 = null;
		if(null != startDate && !startDate.equals("")){
			startDate = startDate.trim() + " 00:00:00";
		}
		if(null != endDate && !endDate.equals("")){
			endDate = endDate.trim() + " 23:59:59";
		}
		if (!startDate.equals("") && !endDate.equals("")) {
			itemPicktime = sf.parse(startDate);
			itemPicktime2 = sf.parse(endDate);
			countSql+=" and DO_PERFORMTIME between ?1 and ?2";
			hql+=" and doPerformtime between ?1 and ?2 order by doPerformtime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime,itemPicktime2);
		}
		if (startDate.equals("") && endDate.equals("")) {
			hql+=" order by doPerformtime desc nulls last";
			count = baseService.findCountSql(countSql,cusId);
			list = baseService.findByPage(hql, rows, page,cusId);
		}
		if (startDate.equals("") && !endDate.equals("")) {
			itemPicktime2 = sf.parse(endDate);
			countSql+="and DO_PERFORMTIME <?1";
			hql+="and doPerformtime <?1 order by doPerformtime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime2);
		}
		if (!startDate.equals("") && endDate.equals("")) {
			itemPicktime = sf.parse(startDate);
			countSql+="and DO_PERFORMTIME >?1";
			hql+="and doPerformtime >?1 order by doPerformtime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime);
		}
		Utils.toBeJson(list, count, response);
	}

}
