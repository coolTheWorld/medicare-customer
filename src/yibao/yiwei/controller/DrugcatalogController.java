package yibao.yiwei.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import yibao.yiwei.entity.Drugcatalog;
import yibao.yiwei.entity.system.Uploadfile;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

/**
 * 项目编码
 */
@SuppressWarnings("all")
@Controller
public class DrugcatalogController {
	
	@Autowired
	private IBaseService baseService;

	/**
	 * 转到项目编码
	 * @param request
	 * @param cusId
	 * @param cusFlag
	 * @return
	 */
	@RequestMapping("/toDrugcatalog")
	public String toDrugcatalog(HttpServletRequest request, String cusId, String cusFlag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		request.setAttribute("startDate", sdf.format(cal.getTime()));
		request.setAttribute("endDate", sdf.format(Utils.getDate(1)));
		request.setAttribute("cusId", cusId);
		request.setAttribute("cusFlag", cusFlag);
		return "/data/drugcatalog";
	}
	
	/**
	 * 项目编码
	 * @param request
	 * @param cusId
	 * @param cusFlag
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getAllDrugcatalog")
	public void getAllDrugcatalog(HttpServletRequest request, HttpServletResponse response,String startDate,String endDate,String cusId, String cusFlag,String dcCode) throws IOException, ParseException {
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		List<Drugcatalog> list = new ArrayList<Drugcatalog>();
		//药品目录,供应商二表共用信息listchainpharmacy.jsp,drugcatalog.jsp,drugcatalog.js等要添加参数cusFlag
		if (null != cusFlag && cusFlag.equals("102")) {//连锁店，查询总店的项目编码
			String hql1 = "select cusParentid from Customer where cusId=?0";
			String parentid = (String)baseService.findUnique(hql1, cusId);
			if (null != parentid && parentid.length() > 1) {
				cusId = parentid;
			}
		}
		int count = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String countSql = "select count(DC_ID) from tbl_Drugcatalog where cus_Id=?0 ";
		String hql = "from Drugcatalog where cusId=?0 ";
		if(null != dcCode && !dcCode.equals("")){
			dcCode = dcCode.trim();
			countSql+=" and (DC_CODE = '"+dcCode+"' or DC_COMMERCIAL like '%"+dcCode+"%')";
			hql+=" and (dcCode = '"+dcCode+"' or dcCommercial like '%"+dcCode+"%')";
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
			countSql+=" and DC_PICKTIME between ?1 and ?2";
			hql+=" and dcPicktime between ?1 and ?2 order by dcPicktime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime,itemPicktime2);
		}
		if (startDate.equals("") && endDate.equals("")) {
			hql+=" order by dcPicktime desc nulls last";
			count = baseService.findCountSql(countSql,cusId);
			list = baseService.findByPage(hql, rows, page,cusId);
		}
		if (startDate.equals("") && !endDate.equals("")) {
			itemPicktime2 = sf.parse(endDate);
			countSql+="and DC_PICKTIME <?1";
			hql+="and dcPicktime <?1 order by dcPicktime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime2);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime2);
		}
		if (!startDate.equals("") && endDate.equals("")) {
			itemPicktime = sf.parse(startDate);
			countSql+="and DC_PICKTIME >?1";
			hql+="and dcPicktime >?1 order by dcPicktime desc nulls last";
			count = baseService.findCountSql(countSql,cusId,itemPicktime);
			list = baseService.findByPage(hql, rows, page,cusId,itemPicktime);
		}
		Utils.toBeJson(list, count, response);
	}

}