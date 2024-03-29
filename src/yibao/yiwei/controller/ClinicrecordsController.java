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

import yibao.yiwei.entity.Clinicrecords;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

/**
 * 诊断记录
 * 
 * @author Administrator
 * 
 */
@Controller
public class ClinicrecordsController {
	
	@Autowired
	private IBaseService<Clinicrecords> baseService;

	/**
	 * 转到诊断记录
	 * @param cusId
	 * @param request
	 * @return
	 */
	@RequestMapping("toClinicrecords")
	public String toClinicrecords(String cusId, HttpServletRequest request) {
		request.setAttribute("cusId", cusId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		request.setAttribute("startDate", sdf.format(cal.getTime()));
		request.setAttribute("endDate", sdf.format(Utils.getDate(1)));
		return "/data/clinicrecords";
	}

	/**
	 * 诊断记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getAllClinicrecords")
	public void getAllClinicrecords(HttpServletRequest request, HttpServletResponse response,String startDate,String endDate,String diagNo,String siPtsname) throws IOException,ParseException {
		List<Clinicrecords> list = new ArrayList<Clinicrecords>();// 返回的结果集无论有没有条件查询
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String cusId = request.getParameter("cusId");// 当前cusId
		int count = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String hql = "from Clinicrecords where cusId=?0 ";
		String countSql = "select count(DIAG_ID) from tbl_Clinicrecords where cus_Id = ?0 ";
		
		if(null != diagNo && !diagNo.equals("")){
			diagNo = diagNo.trim();
			countSql+=" and DIAG_NO = '"+diagNo+"'";
			hql+=" and diagNo = '"+diagNo+"'";
		}
		
		if(null != siPtsname && !siPtsname.equals("")){
			siPtsname = siPtsname.trim();
			countSql+=" and (SI_PTSNAME like '%"+siPtsname+"%' or DIAG_ICDNAME like '%"+siPtsname+"%')";
			hql+=" and (siPtsname like '%"+siPtsname+"%' or diagIcdname like '%"+siPtsname+"%')";
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
			countSql+=" and DIAG_DOCTORTIME between ?1 and ?2";
			hql+=" and diagDoctortime between ?1 and ?2 order by diagDoctortime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime,itemPicktime2);
		}
		if (startDate.equals("") && endDate.equals("")) {
			hql+=" order by diagDoctortime desc nulls last";
			count = baseService.findCountSql(countSql,cusId);
			list = baseService.findByPage(hql, rows, page,cusId);
		}
		if (startDate.equals("") && !endDate.equals("")) {
			itemPicktime2 = sf.parse(endDate);
			countSql+="and DIAG_DOCTORTIME <?1";
			hql+="and diagDoctortime <?1 order by diagDoctortime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime2);
		}
		if (!startDate.equals("") && endDate.equals("")) {
			itemPicktime = sf.parse(startDate);
			countSql+="and DIAG_DOCTORTIME >?1";
			hql+="and diagDoctortime >?1 order by diagDoctortime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime);
		}
		Utils.toBeJson(list, count, response);
	}
}
