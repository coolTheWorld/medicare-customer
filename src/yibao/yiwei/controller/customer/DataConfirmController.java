package yibao.yiwei.controller.customer;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.common.build.confirm.*;
import yibao.yiwei.common.factory.page.ConfirmPageFactory;
import yibao.yiwei.common.factory.page.IPageFactory;
import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.exception.BuildProcessException;
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
	@Qualifier("ConfirmPageFactory")
	IPageFactory pageFactory;

	//获取日志记录器Logger，名字为本类类名
	private static Logger logger = Logger.getLogger(DataConfirmController.class);

	/**
	 * 跳转到数据确认页面
	 * 
	 * @return
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return pageFactory.pageInit(request);
	}

	/**
	 * 数据确认提交
	 * 
	 * @return
	 */
	@RequestMapping("/confirm")
	@ResponseBody
	public Map confirm(@RequestBody DataConfirm confirm, HttpServletRequest request) {
		ConfirmProduct confirmProduct = null;
		try {
			// 查询数据库中是否有当天数据
			String sql = "select CONFIRM_ID, RECORD_DATE, CREATE_TIME, CUS_ID, RECORD_BAK, RECORD_BAK1, RECORD_BAK2, RECORD_BAK3, RECORD_BAK4, RECORD_BAK5, RECORD_BAK6, RECORD_BAK7, RECORD_CLINICRECORDS, RECORD_DELIVERY, RECORD_DISCHARGED, RECORD_HOSPITALIZED, RECORD_ITEMSTOCK, RECORD_PRESCRIBE, RECORD_SALES, RECORD_WAREHOUSE from TBL_DATA_CONFIRM where CUS_ID = ?0 and RECORD_DATE between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
			ConfirmBuilder confirmBuilder = new ConfirmConcreteBuilder(request,confirm.getRecordDate(),confirm.getRecordDate());
			IConfirmDirector confirmDirector = new ConfirmDirector(confirmBuilder);
			confirmProduct = confirmDirector.saveConfirm(confirm,dataConfirmService,sql);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return confirmProduct.getResult();
	}

	/**
	 * 查询当天确认数据信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findByCustomer")
	@ResponseBody
	public Map findDataConfirm(HttpServletRequest request, Date date) {
		if(date == null){
			date = new Date();
		}
		ConfirmProduct confirmProduct = null;
		try {
			String sql = "select CONFIRM_ID, RECORD_DATE, CREATE_TIME, CUS_ID, RECORD_BAK, RECORD_BAK1, RECORD_BAK2, RECORD_BAK3, RECORD_BAK4, RECORD_BAK5, RECORD_BAK6, RECORD_BAK7, RECORD_CLINICRECORDS, RECORD_DELIVERY, RECORD_DISCHARGED, RECORD_HOSPITALIZED, RECORD_ITEMSTOCK, RECORD_PRESCRIBE, RECORD_SALES, RECORD_WAREHOUSE from TBL_DATA_CONFIRM where CUS_ID = ?0 and RECORD_DATE between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
			ConfirmBuilder builder = new ConfirmConcreteBuilder(request, date, date);
			IConfirmDirector confirmDirector = new ConfirmDirector(builder);
			confirmProduct = confirmDirector.findConfirmByConditon(dataConfirmService,sql);
		} catch (BuildProcessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return confirmProduct.getResult();
	}
}
