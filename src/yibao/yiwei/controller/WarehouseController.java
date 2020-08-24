package yibao.yiwei.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.entity.Warehouse;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

/**
 * 库房信息
 * @author Administrator
 *
 */
@Controller
public class WarehouseController {
	
	@Autowired
	private IBaseService<Warehouse> baseService;
	
	/**
	 * 转到库房信息
	 * @param cusId
	 * @param request
	 * @return
	 */
	@RequestMapping("toWarehouse")
	public String toWarehouse(String cusId, HttpServletRequest request) {
		request.setAttribute("cusId", cusId);
		return "/data/warehouse";
	}

	/**
	 * 库房信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getAllWarehouse")
	public void getAllWarehouse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Warehouse> list = new ArrayList<Warehouse>();
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String cusId = request.getParameter("cusId");// 当前cusId
		String hql = "from Warehouse where cusId=?0 order by whAddtime desc nulls last";
		String countHql = "select count(WH_ID) from tbl_Warehouse where cus_Id = ?0";
		int count = baseService.findCountSql(countHql, cusId);
		if (count > 0) {
			list = baseService.findByPage(hql, rows,page, cusId);
		}
		Utils.toBeJson(list, count, response);
	}
}
