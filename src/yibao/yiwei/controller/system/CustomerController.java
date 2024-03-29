package yibao.yiwei.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yibao.yiwei.entity.system.CusareaRelate;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.ChineseCharToEn;
import yibao.yiwei.utils.ProjectConstant;

/**
 * 定点信息
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
public class CustomerController {

	@Autowired
	private IBaseService baseService;

	/**
	 * 转到定点信息
	 * @return
	 */
	@AuthPassport
	@RequestMapping("/toCustomer")
	public String toCustomer(HttpServletRequest request) {
		String hql = "from Areacode where acStatus=1 order by acOrder";//查询已启用的区划
		List areacodeList = baseService.find(hql);
		request.setAttribute("areacodeList", areacodeList);
		return "/system/customer";
	}
	
	/**
	 * 定点信息
	 * @param request
	 * @param cusFlag
	 * @param cusName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllCustomer")
	public Map<String, Object> getAllCustomer(HttpServletRequest request,String cusFlag,String acAreacode,String cusName) {
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String countHql = "select count(cusId) from Customer where cusStatus!=-1 ";//除已注销
		String hql = "from Customer where cusStatus!=-1 ";
		if (null != cusFlag && !cusFlag.equals("")) {
			cusFlag = cusFlag.trim();
			countHql+= " and cusFlag=" + cusFlag;
			hql+= " and cusFlag=" + cusFlag;
		}
		if (null != cusName && !cusName.equals("")) {
			cusName = cusName.trim();
			countHql+= " and (cusName like '%" + cusName + "%' or cusDareway='" + cusName + "' or cusId='" + cusName + "')";
			hql+= " and (cusName like '%" + cusName + "%' or cusDareway='" + cusName + "' or cusId='" + cusName + "')";
		}
		if(null != acAreacode && !acAreacode.equals("") && !acAreacode.equals("-1")){//根据区县查询
			acAreacode = acAreacode.trim();
			countHql+= " and cusId in (select cusId from CusareaRelate where acAreacode='"+acAreacode+"')";
			hql+= " and cusId in (select cusId from CusareaRelate where acAreacode='"+acAreacode+"')";
		}
		if(null != acAreacode && acAreacode.equals("-1")){//未划分区县
			acAreacode = acAreacode.trim();
			countHql+= " and cusId not in (select cusId from CusareaRelate)";
			hql+= " and cusId not in (select cusId from CusareaRelate)";
		}
		hql+= " order by cusRegdate desc nulls last";
		int count = baseService.findCount(countHql);
		List list = baseService.findByPage(hql, rows, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 检查上传情况
	 * @param cusId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUpload")
	public Map checkUpload(String cusId){
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Customer customer = (Customer)baseService.get(Customer.class, cusId);
		String hql = null;
		Object obj = null;
		if(customer.getCusStatus()==1){//单体、门诊、医院、连锁总店
			hql = "select max(UP_DATE) from TBL_UPLOADFILE where cus_id=?0";
			obj = baseService.findUniqueSql(hql,cusId);
			if(null != obj){
				map.put("status", customer.getCusName()+"最后上传文件时间："+sf.format(obj));
			} else {
				map.put("status", "<font color='red'>"+customer.getCusName()+"无上传文件记录</font>");
			}
		}
		if(customer.getCusStatus()==2){//连锁分店
			hql = "select max(SO_DATETIME) from TBL_SALESITEM where CUS_ID=?0";
			obj = baseService.findUniqueSql(hql,cusId);
			if (null != obj){
				map.put("status", "连锁分店："+customer.getCusName()+"最后销售时间："+sf.format(obj));
			} else {
				map.put("status", "<font color='red'>连锁分店："+customer.getCusName()+"无销售记录</font>");
			}
		}
		return map;
	}

	/**
	 * 定点区划设置
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addAreacus")
	public Map addAreacus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		String areacode = request.getParameter("areacode");
		String arr[] = ids.split(",");
		CusareaRelate cusareaRelate;
		for (int i = 0; i < arr.length; i++) {
			String cusid = arr[i];
			String hql = "delete CusareaRelate where cusId=?0";
			baseService.updateOrDelete(hql, cusid);
			cusareaRelate = new CusareaRelate();
			cusareaRelate.setAcAreacode(areacode);
			cusareaRelate.setCusId(cusid);
			baseService.save(cusareaRelate);
		}
		map.put("status", "区划设置成功");
		return map;
	}
	
	/**
	 * 显示定点区划
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cusArea ")
	public Map cusArea(String cusId){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT a.AC_AREANAME FROM TBL_AREACODE a inner join SYS_CUSAREA_RELATE cr on a.AC_AREACODE=cr.AC_AREACODE and a.AC_STATUS=1 WHERE cr.CUS_ID=?0";
		String areaName = (String)baseService.findUniqueSql(sql, cusId);
		map.put("areaName", areaName);
		return map;
	}
	
	/**
	 * 修改定点信息
	 * @param customer
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCustomer")
	public Map updateCustomer(Customer customer) {
		Map<String, Object> map = new HashMap<String, Object>();
		baseService.update(customer);
		map.put("status", "定点信息修改成功");
		return map;
	}
	
	/**
	 * 修改定点状态(通过审核或注销)
	 * @param request
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCustomerStatus")
	public Map updateCustomerStatus(HttpServletRequest request,String type){
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		String arr[] = ids.split(",");
		if(type.equals("1")){//通过审核
			for (int i = 0; i < arr.length; i++) {
				String hql = "from Customer where cusId=?0";
				Customer customer = (Customer)baseService.findUnique(hql, arr[i]);
				if(customer.getCusFlag().equals("102") && customer.getCusParentid().length()>5){//连锁分店(连锁总店parentid=0)
					hql = "update Customer c set c.cusStatus=2 where c.cusId=?0";
				} else {//其他(包括连锁总店)
					hql = "update Customer c set c.cusStatus=1 where c.cusId=?0";
				}
				baseService.updateOrDelete(hql, arr[i]);
			}
			map.put("status", "定点审核通过");
		}else {//注销定点
			for (int i = 0; i < arr.length; i++) {
				String cusId = arr[i];
				String hql = "update Customer c set c.cusStatus=-1,c.cusName=c.cusName||'-注销状态' where c.cusId=?0";//||原内容追加
				baseService.updateOrDelete(hql,  cusId);
				hql = "update Customer c set c.cusStatus=-1,c.cusName=c.cusName||'-注销状态' where c.cusParentid=?0 and c.cusStatus=2";//注销连锁分店：cusStatus=2
				baseService.updateOrDelete(hql,  cusId);
				hql = "delete CusareaRelate c where c.cusId=?0";//删除CusareaRelate区划关联
				baseService.updateOrDelete(hql, cusId);
			}
			map.put("status", "定点注销成功");
		}
		return map;
	}
	
	/**
	 * 删除已注销定点
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delCusCancle")
	public Map delCusCancle(){
		String hql = "delete Customer where cusStatus=-1";
		baseService.updateOrDelete(hql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status","已注销定点删除成功");
		return map;
	}
	
	/**
	 * 转到综合查询
	 * @param cusFlag
	 * @param areacode
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/toAreaCustomer")
	public String toAreaCustomer(String cusFlag, String areacode, HttpServletRequest request) {
		request.setAttribute("cusFlag", cusFlag);
		request.setAttribute("areacode", areacode);
		return "/system/customerArea";
	}
	
	/**
	 * 综合查询（根据区划）
	 * @param cusFlag
	 * @param areacode
	 * @param cusName
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllAreaCustomer")
	public Map getAllAreaCustomer(String cusFlag, String areacode,String cusName, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = new ArrayList();
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String countHql = "select count(cusId) from Customer where cusId  in (select cusId from CusareaRelate where acAreacode in("+areacode+")) and (cusStatus=1 or cusStatus=2)";
		String hql = "from Customer where cusId  in (select cusId from CusareaRelate where acAreacode in("+areacode+")) and (cusStatus=1 or cusStatus=2)";
		if (null != cusFlag && !cusFlag.equals("")) {
			cusFlag = cusFlag.trim();
			countHql+= " and cusFlag=" + cusFlag;
			hql+= " and cusFlag=" + cusFlag;
		}
		if (null != cusName && !cusName.equals("")) {
			cusName = cusName.trim();
			countHql+= " and (cusName like '%" + cusName + "%' or cusDareway='" + cusName + "' or cusId='" + cusName + "')";
			hql+= " and (cusName like '%" + cusName + "%' or cusDareway='" + cusName + "' or cusId='" + cusName + "')";
		}
		hql+= " order by cusRegdate desc nulls last";
		int count = baseService.findCount(countHql);
		list = baseService.findByPage(hql, rows, page);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 定点查询
	 * @param request
	 * @param cusFlag
	 * @return
	 */
	@RequestMapping("/getCustomerList")
	public String getCustomerList(HttpServletRequest request, String cusId, String cusFlag) {
		String hql;
		if(null != cusId && !cusId.equals("")){//定点信息、综合查询页面，查询单个定点
			hql = "from Customer where cusId='"+cusId+"'";
		} else {//首页批量查询定点(按定点类型)
			String acAreacode = (String) request.getSession().getAttribute("acAreacode");
			hql = "from Customer where cusId in(select cusId from CusareaRelate where acAreacode in("+ acAreacode+ ")) and cusFlag=" + cusFlag + " and (cusStatus=1 or cusStatus=2) order by cusRegdate desc nulls last";
		}
		List list = baseService.find(hql);
		request.setAttribute("customerList", list);
		request.setAttribute("cusFlag", cusFlag);
		return "/system/customerList";
	}
	
	/**
	 * 定点基本信息
	 * @param cusId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getCustomerInfo")
	public String getCustomerInfo(String cusId, HttpServletRequest request) {
		Customer customer = (Customer) baseService.get(Customer.class, cusId);
		request.setAttribute("customer", customer);
		String type = ProjectConstant.getFileFlag(customer.getCusFlag());//根据101获取"单体药店"
		request.setAttribute("cusFlag", type);
		return "/system/customerInfo";
	}
	
	/**
	 * 测试更新地址
	 */
	@RequestMapping("/ewupdate/service/testconn")
	@ResponseBody
	public void testConn(){
		System.out.println("测试更新地址");
	}
	
	/**
	 * 测试上传地址
	 */
	@RequestMapping("/medicare/service/testconn")
	@ResponseBody
	public void testConn2(){
		System.out.println("测试上传地址");
	}
	
	/**
	 * 定点注册
	 * TODO 无空值处理
	 * @param customer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/medicare/service/register")
	@ResponseBody
	public Map addCustomer(Customer customer, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!customer.getCusFlag().equals("102") && customer.getCusDareway().equals("")){//连锁药店可以没有医院编码
			map.put("errmsg", "定点编码不能为空");
			map.put("errcode", 1);
			return map;
		}
		String hql = "from Customer where (cusStatus = 1 or cusStatus=2) and cusDareway=?0 order by cusRegdate desc";//查询已经注册的信息
		List<Customer> list = baseService.find(hql, customer.getCusDareway());
		if (list.size() > 0) {//已注册
			Customer cus = list.get(0);
			cus.setCusName(customer.getCusName());
			cus.setCusRegdate(new Date());
			cus.setCusUniqure(customer.getCusUniqure());
			cus.setCusAddr(customer.getCusAddr());
			cus.setCusPhone(customer.getCusPhone());
			cus.setCusContact(customer.getCusContact());
			cus.setCusRemark(customer.getCusRemark());
			baseService.update(cus);
			map.put("errmsg", "定点信息已更新");
			map.put("errcode", 0);
			map.put("id", cus.getCusId());
		} else {//未注册
			customer.setCusRegdate(new Date());
			String cusname = null != customer.getCusName() ? customer.getCusName().trim() : "";
			ChineseCharToEn ctn = new ChineseCharToEn();//获取客户拼音简码
			customer.setCusPcode(ctn.getAllFirstLetter(cusname));
			baseService.save(customer);
			map.put("errmsg", "定点注册成功");
			map.put("errcode", 0);
			map.put("id", customer.getCusId());
			//添加区划
			String areacode;
			String cusAddr = null != customer.getCusAddr() ? customer.getCusAddr().trim() : "";
			if (cusname.contains("张店") || cusAddr.contains("张店")) {
				areacode = "370303";
			} else if (cusname.contains("淄川") || cusAddr.contains("淄川")) {
				areacode = "370302";
			} else if (cusname.contains("桓台") || cusAddr.contains("桓台")) {
				areacode = "370321";
			} else if (cusname.contains("临淄") || cusAddr.contains("临淄")) {
				areacode = "370305";
			} else if (cusname.contains("高青") || cusAddr.contains("高青")) {
				areacode = "370322";
			} else if (cusname.contains("周村") || cusAddr.contains("周村")) {
				areacode = "370306";
			} else if (cusname.contains("博山") || cusAddr.contains("博山")) {
				areacode = "370304";
			} else if (cusname.contains("沂源") || cusAddr.contains("沂源")) {
				areacode = "370323";
			} else if (cusname.contains("市直") || cusAddr.contains("市直")) {
				areacode = "370300";
			} else if (cusname.contains("高新") || cusAddr.contains("高新")) {
				areacode = "370313";
			} else {
				areacode = "370303";
			}
			CusareaRelate cusareaRelate = new CusareaRelate();
			cusareaRelate.setAcAreacode(areacode);
			cusareaRelate.setCusId(customer.getCusId());
			baseService.save(cusareaRelate);
		}
		return map;
	}

}
