package yibao.yiwei.controller.customer;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

/**
 * 数据确认接口
 * 
 * @author sunshy
 *
 */

@RequestMapping("/dataConfirm")
@Controller
public class DataConfirmController {

	@Autowired
	IBaseService<DataConfirm> dataConfirmService;
	@Autowired
	IBaseService<Customer> customerService;

	/**
	 * 跳转到数据确认页面
	 * 
	 * @return
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());
		String sql = "select CUS_ID, CUS_PARENTID, CUS_STATUS, CUS_NAME,CUS_REGIP,CUS_PHONE,CUS_CONTACT,CUS_ADDR,CUS_FLAG,CUS_UNIQURE,CUS_REGDATE,CUS_DAREWAY,CUS_BRANCHCODE,CUS_REMARK,CUS_PCODE from TBL_CUSTOMER where CUS_ID = ?0";
		Customer customer = customerService.findUniqueSql(sql, Customer.class, user.getCusId());
		request.setAttribute("customer", customer);
		return "customer/confirm/home";
	}

	/**
	 * 数据确认提交
	 * 
	 * @return
	 */
	@RequestMapping("/confirm")
	@ResponseBody
	public Map confirm(@RequestBody DataConfirm confirm, HttpServletRequest request) {
		Map<String, Object> resultMap = null;
		// 获取DataConfirm反射对象
		Class confirmResultClazz;
		if (confirm == null) {
			confirm = new DataConfirm();
		}
		confirmResultClazz = confirm.getClass();
		
		boolean notNull = false;//判断是否都为空
		// 判断是否所有属性都为空
		Field[] fields = confirmResultClazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(confirm) != null && field.get(confirm).equals("1")) {
					notNull = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//未选中数据直接返回
		if(!notNull) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("flag", "0");
			resultMap.put("mes", "请至少选中一条记录确认");
			return resultMap;
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sf.format(new Date());

		// 日期参数拼接
		StringBuffer startDateParam = new StringBuffer(dateString).append(" 00:00:00");
		StringBuffer endDateParam = new StringBuffer(dateString).append(" 23:59:59");

		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());

		// 查询数据库中是否有当天数据
		String sql = "select CONFIRM_ID, CONFIRM_DATE, CREATE_TIME, CUS_ID, RECORD_BAK, RECORD_BAK1, RECORD_BAK2, RECORD_BAK3, RECORD_BAK4, RECORD_BAK5, RECORD_BAK6, RECORD_BAK7, RECORD_CLINICRECORDS, RECORD_DELIVERY, RECORD_DISCHARGED, RECORD_HOSPITALIZED, RECORD_ITEMSTOCK, RECORD_PRESCRIBE, RECORD_SALES, RECORD_WAREHOUSE from TBL_DATA_CONFIRM where CUS_ID = ?0 and CREATE_TIME between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
		DataConfirm confirmResult = dataConfirmService.findUniqueSql(sql, DataConfirm.class, user.getCusId(),
				startDateParam.toString(), endDateParam.toString());

		if (confirmResult != null && confirmResult.getConfirmId() != null) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("flag", "0");
			resultMap.put("mes", "今天的数据已确认");
		} else {
			// 添加
			confirm.setCusId(user.getCusId());
			confirm.setCreateTime(new Date());
			dataConfirmService.save(confirm);
			resultMap = new HashMap<String, Object>();
			resultMap.put("flag", "1");
			resultMap.put("mes", "数据确认成功");
		}
		return resultMap;
	}

	/**
	 * 查询当天确认数据信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findByCustomer")
	@ResponseBody
	public Map findDataConfirm(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sf.format(new Date());

		// 日期参数拼接
		StringBuffer startDateParam = new StringBuffer(dateString).append(" 00:00:00");
		StringBuffer endDateParam = new StringBuffer(dateString).append(" 23:59:59");

		CustomerUser user = (CustomerUser) request.getSession().getAttribute(SessionKey.USER.getValue());

		// 查询数据库中是否有当天数据
		String sql = "select CONFIRM_ID, CONFIRM_DATE, CREATE_TIME, CUS_ID, RECORD_BAK, RECORD_BAK1, RECORD_BAK2, RECORD_BAK3, RECORD_BAK4, RECORD_BAK5, RECORD_BAK6, RECORD_BAK7, RECORD_CLINICRECORDS, RECORD_DELIVERY, RECORD_DISCHARGED, RECORD_HOSPITALIZED, RECORD_ITEMSTOCK, RECORD_PRESCRIBE, RECORD_SALES, RECORD_WAREHOUSE from TBL_DATA_CONFIRM where CUS_ID = ?0 and CREATE_TIME between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
		DataConfirm confirmResult = dataConfirmService.findUniqueSql(sql, DataConfirm.class, user.getCusId(),
				startDateParam.toString(), endDateParam.toString());
		// 获取DataConfirm反射对象
		Class confirmResultClazz;
		if (confirmResult == null) {
			confirmResult = new DataConfirm();
		}
		confirmResultClazz = confirmResult.getClass();

		// 获取属性,并对属性进行加工
		Field[] fields = confirmResultClazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(confirmResult) != null && field.get(confirmResult).equals("1")) {
					//field.get(confirmResult);
					field.set(confirmResult, "已确认");
				} else if (field.getType().getSimpleName().equals("String")) {
					field.set(confirmResult, "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<DataConfirm> data = new ArrayList<DataConfirm>();
		data.add(confirmResult);
		resultMap.put("code", "0");
		resultMap.put("mes", "成功");
		resultMap.put("count", "1");
		resultMap.put("data", data);
		return resultMap;
	}
}
