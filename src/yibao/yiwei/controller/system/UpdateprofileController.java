package yibao.yiwei.controller.system;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.entity.system.Updateprofile;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

@Controller
public class UpdateprofileController {
	
	@Autowired
	private IBaseService<Updateprofile> baseService;

	// 请求视图
	@RequestMapping("toUpdateprofile")
	public String toUpdateprofile(HttpServletRequest request) {
		String upType = request.getParameter("upType");
		request.setAttribute("upType", upType);
		return "/updateprofile/updateprofile";
	}

	// 新增或修改表格数据
	@RequestMapping("/addOrUpdateUpdateprofile ")
	public void addOrUpdateUpdateprofile(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		// 获取jsonArray格式的字符串 有元素为int,date类型获取Object再转换
		String jsonStr = request.getParameter("json");
		String upId = null;// 定义主键
		String json = "";// 返回json 状态
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(jsonStr);
		Updateprofile updateprofile = new Updateprofile();
		// 当jsonStr=[] 时,即没有修改任何数据jssonArray.size() =0
		if (jsonArray.size() > 0) {
			// 获取jsonArray第一个json对象 jsonObject
			JSONObject jo = jsonArray.getJSONObject(0);
			// 验证所有必填项不为空
			if (jo.size() > 0) {

				// 解析jo 并获取实体字段值
				String upType = jo.getString("upType");
				String upVersion = jo.getString("upVersion");
				String upDescription = jo.getString("upDescription");
				String upIsenabled = jo.getString("upIsenabled");
				String upIsdefault = jo.getString("upIsdefault");
				if (!(upIsenabled.equals(""))) {
					updateprofile.setUpIsenabled(Integer.parseInt(upIsenabled));
				}
				if (!(upIsdefault.equals(""))) {
					updateprofile.setUpIsdefault(Integer.parseInt(upIsdefault));
				}
				String upUpdatetime1 = jo.getString("upUpdatetime");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (upUpdatetime1.equals("")) {
					updateprofile.setUpUpdatetime(null);
				} else {
					Date upUpdatetime = df.parse(upUpdatetime1);
					updateprofile.setUpUpdatetime(upUpdatetime);
				}
				String upCreatetime1 = jo.getString("upCreatetime");
				if (upCreatetime1.equals("")) {
					updateprofile.setUpCreatetime(null);
				} else {
					Date upCreatetime = df.parse(upCreatetime1);
					updateprofile.setUpCreatetime(upCreatetime);
				}
				updateprofile.setUpType(upType);
				updateprofile.setUpVersion(upVersion);
				updateprofile.setUpDescription(upDescription);
				// 如果等于6 就表示是有主键id,update一条表格数据 , 否则是新增一条表格数据
				if (jo.size() == 8) {
					// 获取id主键
					upId = jo.getString("upId");
					updateprofile.setUpId(upId);
					baseService.update(updateprofile);
					json = "{ \"status\": \"update\" }";
				} else {
					baseService.save(updateprofile);
					json = "{\"status\":\"add\"}";
				}
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 获取datagrid 所有Updateprofile对象
	@RequestMapping("/getAllUpdateprofile")
	public String getAllUpdateprofile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Updateprofile> list;// 返回的结果集无论有没有条件查询
		String rows = request.getParameter("rows");// 每页显示的记录数
		String page = request.getParameter("page");// 当前第几页
		String hql = "from Updateprofile u where u.upType=?0";
		String upType = request.getParameter("upType");
		String countHql = "select count(u.UP_ID) from tbl_Updateprofile u where u.up_Type=?0";
		int count = baseService.findCountSql(countHql, upType);
		if (count > 0) {
			list = baseService.findByPage(hql, rows, page, upType);
			Utils.toBeJson(list, count, response);
		}
		return null;
	}

	// 批量删除信息 return null; 返回string 也可以
	@RequestMapping("/delUpdateprofile")
	public void delUpdateprofile(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");// 要批量删除的id字符串获取主键字符串 1,2,3
		String arr[] = ids.split(",");// 分隔为主键数组
		for (int i = 0; i < arr.length; i++) {
			baseService.updateOrDelete("delete Updateprofile where upId=?0", arr[i]);// 根据ID主键进行删除操作
		}
	}

}
