package yibao.yiwei.controller.thirdplat;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yibao.yiwei.dto.TreatmentDetailDto;
import yibao.yiwei.dto.TreatmentDto;
import yibao.yiwei.dto.JsonData;
import yibao.yiwei.dto.TreatmentStatisticsDto;
import yibao.yiwei.entity.thirdplat.Cbevents;
import yibao.yiwei.entity.thirdplat.Reginfo;
import yibao.yiwei.entity.thirdplat.Relate;
import yibao.yiwei.entity.thirdplat.Treatment;
import yibao.yiwei.service.IBaseService;

import com.dahua.util.HttpClientPoolUtil;
import com.dahua.util.RSAUtils;

/**
 * 定点信息
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/face")
public class FaceRecognitionController {

	@Autowired
	private IBaseService baseService;
	//获取公钥 POST
	public static String GET_PUBKEY_URL    = "http://{0}:{1}/WPMS/getPublicKey";
	//获取TOKEN POST
	public static String GET_TOKEN_URL     = "http://{0}:{1}/WPMS/login";
	//接入方注册 POST
	public static String SYS_REG_URL       = "http://{0}:{1}/register?userId={2}&username={3}&token={4}";
	//接入方更新 POST
	public static String SYS_UPDATEREG_URL = "http://{0}:{1}/register?userId={2}&username={3}&token={4}";
	//接入方注销 DELETE
	public static String SYS_UNREG_URL     = "http://{0}:{1}/register/code/{2}/";
	//事件订阅 GET
	public static String SYS_EVENTREG_URL  = "http://{0}:{1}/register/subscribe/{2}/{3}";
	//取消订阅 GET
	public static String SYS_EVENTUNREG_URL= "http://{0}:{1}/register/cancellation/{2}/{3}";
	//获取订阅列表 GET
	public static String SYS_EVENTGET_URL  = "http://{0}:{1}/register/interfaceList/{2}/";
	//获取人脸库列表 GET
	public static String SYS_GETLIB_URL    = "http://{0}:{1}/face/groupInfo/page?userId={2}&userName={3}&token={4}";
	//视频回放地址
	public static String HLS_PLAYBACK_URL  = "http://{0}:{1}/vod/device/cameraid/{2}/substream/1/totallength/40/begintime/{3}/endtime/{4}.m3u8";

	/**
	 * 订阅列表
	 * @return
	 */
	@RequestMapping("/toReglist")
	public String toReglist() {
		return "/faceRecognition/reglist";
	}
	
	/**
	 * 获取订阅列表数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllRegdata")
	public Map<String, Object> getAllRegister(HttpServletRequest request) {
		String rows = request.getParameter("rows");  // 每页显示的记录数
		String page = request.getParameter("page");  // 当前第几页
		String countHql = "select count(regid) from Reginfo"; 
		String hql = "from Reginfo";

		int count = baseService.findCount(countHql);
		List list = baseService.findByPage(hql, rows, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 新增订阅
	 * @return
	 */
	@RequestMapping("/toAddReg")
	public String toAddReg() {
		return "/faceRecognition/addreg";
	}
	
	
	
	/**
	 * 回调事件列表
	 * @return
	 */
	@RequestMapping("/toEventlist")
	public String toEventlist() {
		return "/faceRecognition/eventlist";
	}
	

	/**
	 * 人脸数据可视化
	 * @return
	 */
	@RequestMapping("/toVisualization")
	public String toVisualization(HttpServletRequest request) {
//		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd"); 
//		//获取本月第一天日期
//		Calendar calendar = Calendar.getInstance();
//	    calendar.set(Calendar.DAY_OF_MONTH,1);
//	    String start = myFormatter.format(calendar.getTime());
//	    //获取本月最后一天
//	    Calendar calendarTWo = Calendar.getInstance();
//	    calendar.set(Calendar.DATE,1);
//	    calendar.roll(Calendar.DATE,-1);
//	    String end = myFormatter.format(calendarTWo.getTime());
//		//获取血透总人次和本月血透人次
//		String countSql = "select count(TM_ID) from TBL_DHPLAT_TREATMENT t "
//				+ " left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+ " t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+ " and r.CUS_TYPE='1'";
//		String countTwoSql = "select count(count(TM_ID)) from TBL_DHPLAT_TREATMENT t "
//				+ "left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+" t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+" and r.CUS_TYPE='1' group by t.USRNAME";
//		int hemTotal = baseService.findCountSql(countSql);//获取当月血透总人次
//		int hemTotalTwo = baseService.findCountSql(countTwoSql);//获取当月血透总人数
//		//获取当月康复人数和康复人次
//		String countRecoverySql = "select count(TM_ID) from TBL_DHPLAT_TREATMENT t "
//				+ " left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+ " t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+ " and r.CUS_TYPE='2'";
//		String countRecoverySqlTwo = "select count(count(TM_ID)) from TBL_DHPLAT_TREATMENT t "
//				+ " left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+ " t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+ " and r.CUS_TYPE='2' group by t.USRNAME";
//		int recTotal = baseService.findCountSql(countRecoverySql);//获取当月康复总人次
//		int recTotalTwo = baseService.findCountSql(countRecoverySqlTwo);//获取当月康复总人数
//		//当月血透异常人次
//		String abnormalSql = "select count(TM_ID) from TBL_DHPLAT_TREATMENT t"
//				+ " left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+ " t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+ " and r.CUS_TYPE='1' and t.TM_INTERVAL < r.SET_TREAT_MIN";
//		//当月康复异常人次
//		String abnormalSqlTwo = "select count(TM_ID) from TBL_DHPLAT_TREATMENT t"
//				+ " left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
//				+ " t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
//				+ " and r.CUS_TYPE='2' and t.TM_INTERVAL < r.SET_TREAT_MIN";
//		int abTotal = baseService.findCountSql(abnormalSql);//获取当月血透异常总人次
//		int abTotalTwo = baseService.findCountSql(abnormalSqlTwo);//获取当月康复异常总人次
//		request.setAttribute("hemTotal", hemTotal);
//		request.setAttribute("hemTotalTwo", hemTotalTwo);
//		request.setAttribute("recTotal", recTotal);
//		request.setAttribute("recTotalTwo", recTotalTwo);
//		request.setAttribute("abTotal", abTotal+abTotalTwo);
		
		return "/faceRecognition/visualization";
	}
	
	
	/**
	 * 血透、康复治疗人脸识别记录
	 * @return
	 */
	@RequestMapping("/toTreatment")
	public String toTreatment(HttpServletRequest request) {
		request.setAttribute("datatype", request.getParameter("type"));
		return "/faceRecognition/treatment";
	}
	
	//@RequestMapping(value="/getTreatment", method={RequestMethod.GET}, produces="text/plain;charset=utf-8") //返回字符串使用
	@RequestMapping("/getTreatment")
	@ResponseBody
	public Map<String, Object> getTreatment(HttpServletRequest request,String judge) {
		Map map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM"); 
		//获取本月日期
	    String curMonth = sdf2.format(new Date());
		int iStart = 1;
		int iEnd = 11;
		int pageno = 1;
		String reqtype = request.getParameter("type");
	    if (!TextUtils.isEmpty(request.getParameter("page"))){
			pageno = Integer.parseInt(request.getParameter("page"));
			
			iStart = (pageno > 0) ? pageno : 1;
			iStart = (iStart - 1) * 10;
			iEnd = iStart + 11;
	    }
	    
	    String wherestr = "";
	    if ("t1".equals(reqtype)){ //血透总计
	    	wherestr = "where platname='八院血透库'";
	    	map.put("title", "血透出、入院记录");
	    }
	    if ("t2".equals(reqtype)){ //康复总计
	    	wherestr = "where platname='六院PT库'";
	    	map.put("title", "康复出、入院记录");
	    }
	    if ("c1".equals(reqtype)){ //血透小计
	    	wherestr = "where platname='八院血透库' and to_char(tm_date, 'yyyy-MM') = '"+curMonth+"'";
	    	map.put("title", "本月血透出、入院记录");
	    }
	    if ("c2".equals(reqtype)){ //康复小计
	    	wherestr = "where platname='六院PT库' and to_char(tm_date, 'yyyy-MM') = '"+curMonth+"'";
	    	map.put("title", "本月康复出、入院记录");
	    }
	    if ("w0".equals(reqtype)){ //结算
	    	wherestr = "where 1=2";
	    	map.put("title", "医保结算记录");
	    }
	    if ("w1".equals(reqtype)){ //预警
	    	wherestr = "where platname='八院血透库' and tm_interval < 120 and to_char(tm_date, 'yyyy-MM') = '"+curMonth+"'";
	    	map.put("title", "血透住院预警");
	    }
	    
		//获取血透或者康复数据量
	   // String judge = request.getParameter("judge");
		String sql = "select RN, TM_ID TMID, PLATNAME, USRNAME, SEX, CARDID, TM_START TMSTART,  "
				   + "TM_END TMEND,  TM_INTERVAL TMINTERVAL from (select rownum rn, c.* from ("
                   + "  select t.* from tbl_dhplat_treatment t "
				   + wherestr
				   + "  order by t.tm_date desc) c"
                   + ") a where a.rn > "+iStart+" and a.rn < " +iEnd;
		List<TreatmentDetailDto> dto = baseService.findDtoSql(sql, TreatmentDetailDto.class);
		String htmlstr = "";
		for(TreatmentDetailDto t: dto){
			htmlstr += "<tr><td>"+t.getRN()+"</td><td>"
					+t.getUSRNAME()+"</td><td>"
					+("1".equals(t.getSEX()) ? "男" : "女")+"</td><td>"
					+t.getCARDID()+"</td><td>"
					+sdf.format(t.getTMSTART())+"</td><td>"
					+sdf.format(t.getTMEND())+"</td><td>"
					+t.getTMINTERVAL()+"</td><td><a href=\'javascript:;\' onclick=\"playback('"+t.getTMID()+"')\">视频查看</a></td></tr>";
		}
		
		map.put("page", pageno);
		map.put("html", htmlstr);
		
		return map;
	}
	
	
	/**
	 * 获取页面数据所有统计数据
	 * 
	 血透总人次
	本月血透人次
	康复总人次
	本月康复人次
	本月结算人次
	人脸比对预警
	 * 
	 * @return
	 */
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map<String, Object> getStatistics(HttpServletRequest request) {
		Map map = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
		//获取本月日期
	    String curMonth = sdf.format(new Date());
		//获取血透、康复总人次
		String totalSql = "select t.platname, count(t.tm_id) total from tbl_dhplat_treatment t group by t.platname";		
		
		List<TreatmentStatisticsDto> totallist = baseService.findDtoSql(totalSql, TreatmentStatisticsDto.class);
		map.put("total", totallist);
		
		//获取本月血透、康复总人次
		String monthtotalSql = "select t.platname, count(t.tm_id) total from tbl_dhplat_treatment t "
				+ "where to_char(t.tm_date, 'yyyy-MM') = '"+curMonth+"' group by t.platname";
		
		List<TreatmentStatisticsDto> monthlist = baseService.findDtoSql(monthtotalSql, TreatmentStatisticsDto.class);
		map.put("month", monthlist);
		
		//获取本月结算、人脸异常人次
		String warntotalSql = "select t.platname, count(t.tm_id) total from tbl_dhplat_treatment t "
				+ "where to_char(t.tm_date, 'yyyy-MM') = '"+curMonth+"' and t.tm_interval < 120 group by t.platname";
		
		List<TreatmentStatisticsDto> warnlist = baseService.findDtoSql(warntotalSql, TreatmentStatisticsDto.class);
		map.put("warn", warnlist);
		
		return map;
	}

	
	/**
	 * 统计当月血透或康复总数据量
	 * @param request
	 * @param judge 康复或血透判断 1血透 2康复 3其他
	 * @return
	 */
	@RequestMapping("/hemodialysis")
	@ResponseBody
	public List<TreatmentDto> hemodialysis(HttpServletRequest request,String judge) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd"); 
		//获取本月第一天日期
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH,1);
	    String start = myFormatter.format(calendar.getTime());
	    //获取本月最后一天
	    Calendar calendarTWo = Calendar.getInstance();
	    calendarTWo.set(Calendar.DATE,1);
	    calendarTWo.roll(Calendar.DATE,-1);
	    String end = myFormatter.format(calendarTWo.getTime());
		//获取血透或者康复数据量
	   // String judge = request.getParameter("judge");
		String sql = "select r.cus_name as name,count(r.cus_name) as num from TBL_DHPLAT_TREATMENT t "
				+ "left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
				+" t.TM_DATE>=to_date('"+start+"','yyyy/mm/dd') and t.TM_DATE<=to_date('"+end+"','yyyy/mm/dd')"
				+" and r.cus_type = '"+judge+"' group by r.cus_name";
		List<TreatmentDto> dto = baseService.findDtoSql(sql,TreatmentDto.class);
		return dto;
	}
	
	
	/**
	 * 统计血透人次最高的前5位
	 * @param request
	 * @return
	 */
	@RequestMapping("/ranking")
	@ResponseBody
	public List<TreatmentDto> ranking(HttpServletRequest request) {
		//获取血透或者康复数据量
		String sql = "select t.usrname as name,count(t.usrname) as num from TBL_DHPLAT_TREATMENT t "
				+ "left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
				+" r.cus_type = '1' and t.tm_date >= trunc(sysdate, 'd') + 1 and t.tm_date <= trunc(sysdate, 'd') + 7 group by t.usrname order by num desc";
		List<TreatmentDto> dto = baseService.findDtoSql(sql,TreatmentDto.class);
		return dto;
	}
	
	
	/**
	 * 实时刷新展示最新采集数据
	 * @param request
	 * @param time 刷新间隔秒数
	 * @return
	 */
	@RequestMapping("/showImg")
	@ResponseBody
	public List<Cbevents> showImg(HttpServletRequest request,Integer time) {
		/**
		 * 每隔三秒钟刷新一次，获取最新的传入数据
		 */
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar cal = Calendar.getInstance();
//		Date date = new Date();
//		String endTime = df.format(date);
//		cal.setTime(date);                 //设置参数时间
//		cal.add(Calendar.SECOND, -time/1000);     //把日期往后增加SECOND 秒.整数往后推,负数往前移动
//		date = cal.getTime();
//		String startTime = df.format(date);
//		String sql = "select * from TBL_CBEVENTS t where "
//				+ " t.detecttime>'"+startTime+"' and t.detecttime<='"+endTime+"'";
//		List<Cbevents> list = baseService.findEntitySql(sql, Cbevents.class);
//		return list;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String getnum = request.getParameter("getnum");
		String lasttime = request.getParameter("lasttime");  
		String wherestr = "";
	    if (!"".equals(lasttime) && !"null".equals(lasttime)){ 
	    	wherestr = "where to_date(t.detecttime, 'yyyy-mm-dd hh24:mi:ss') > to_date('"+lasttime+"', 'yyyy-mm-dd hh24:mi:ss')";
	    }
		
//		Calendar cal = Calendar.getInstance();
//		Date date = new Date();
//		String endTime = df.format(date);
//		cal.setTime(date);                 //设置参数时间
//		cal.add(Calendar.SECOND, -time/1000);     //把日期往后增加SECOND 秒.整数往后推,负数往前移动
//		date = cal.getTime();
//		String startTime = df.format(date);
	    //b.groupname, b.usrname, b.sex, b. cardid, b.imageurl, b.detecttime
		String sql = "select * from ("
				   + "  select rownum rn, a.* from("
				   + "    select * from TBL_CBEVENTS t "+wherestr+"order by t.detecttime desc" 
				   + "  ) a"
				   + ") b where b.rn <= "+getnum;
		List<Cbevents> list = baseService.findEntitySql(sql, Cbevents.class);
		
		return list;
		
		
	}
	
	/**
	 * 前端折线图展示
	 * @return
	 */
	@RequestMapping("/lineChart")
	@ResponseBody
	public Map<String, Object> lineChart() {
//		String sql = "select a.NAME,  decode(b.total, null, 0, b.total) NUM from("
//				   +"select '八院血透库' NAME, trunc(sysdate, 'd') + 1 + Rownum - 1 dt from dual connect by rownum < 7 + 1"
//				   +"union "
//				   +"select '六院PT库', trunc(sysdate, 'd') + 1 + Rownum - 1 dt from dual connect by rownum < 7 + 1"
//				   +") a left join ("
//				   +"  select t.platname, t.tm_date, count(*) total from TBL_DHPLAT_TREATMENT t group by t.platname, t.tm_date"
//				   +") b on b.platname = a.name and b.tm_date = a.dt"
//				   +" order by a.dt";
//		List<TreatmentDto> dto = baseService.findDtoSql(sql,TreatmentDto.class);
//		int m = 0, n = 0;
//		Integer[] data = new Integer[7];
//		Integer[] data2 = new Integer[7];
//		for(int i=0;i<dto.size();i++){
//			if("八院血透库".equals(dto.get(i).getNAME())){
//				data[m] = Integer.parseInt(dto.get(i).getNUM().toString());
//				++m;
//			}else if("六院PT库".equals(dto.get(i).getNAME())){
//				data2[n] = Integer.parseInt(dto.get(i).getNUM().toString());
//				++n;
//			}
//		}
//		map.put("y8", data);
//		map.put("y6", data2);
//		return map;
		
		Map<String, Object> map = new HashMap<String, Object>();
		//返回当周每天采集数据量
		//通过大华平台和进销存对应关系表获取定点信息  并循环获取每个定点在周一到周日的采集量
		List<JsonData> datas = new ArrayList<JsonData>();
		String hql = "from Relate where 1=1";
		List<Relate> list = baseService.find(hql);
		for(Relate relate : list) {
			JsonData jd = new JsonData(); 
			String name = relate.getCusname();
			String sql = "select count(*) NUM,r.cus_name NAME from TBL_DHPLAT_TREATMENT t "
						+"left join Tbl_Dhplat_Relate r on t.platname = r.platname "
						+"where t.tm_date in (select trunc(sysdate, 'd') + 1 + Rownum - 1 from dual connect by rownum < 7 + 1) "
						+"and r.cus_name='"+name+"' group by  r.cus_name, t.tm_date order by  t.tm_date";
			List<TreatmentDto> dtoList = baseService.findDtoSql(sql,TreatmentDto.class);
			Integer[] data = {0,0,0,0,0,0,0};
			for(int i=0;i<dtoList.size();i++) {
				data[i] = Integer.parseInt(dtoList.get(i).getNUM().toString());
			}
				
			jd.setName(name);
			jd.setData(data);
			datas.add(jd);
		}
		map.put("lineChart", datas);
		return map;
	}
	
	/**
	 * 前端折线图展示
	 * @return
	 */
	@RequestMapping("/lineChartVO")
	@ResponseBody
	public Map<String, Object> lineChartVO() {
		Map<String, Object> map = new HashMap<String, Object>();
		//返回当日0:00-8:00、8:00-12:00、12:00-16:00、16:00-24:00时间段内采集数据量
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = df.format(date);
		List<JsonData> datas = new ArrayList<JsonData>();
		//通过大华平台和进销存对应关系表获取定点信息  并循环获取每个定点在各个时间段的数据采集量
		String hql = "from Relate where 1=1";
		List<Relate> list = baseService.find(hql);
		for(Relate relate : list) {
			JsonData jd = new JsonData(); 
			String name = relate.getCusname();
			String sql = "select count(TM_ID) from TBL_DHPLAT_TREATMENT t "
					+ "left join TBL_DHPLAT_RELATE r on t.PLATNAME = r.PLATNAME where "
					+" t.TM_START>=to_date(?0,'yyyy/mm/dd hh24:mi:ss') and t.TM_START<=to_date(?1,'yyyy/mm/dd hh24:mi:ss')"
					+" and r.CUS_NAME = '"+name+"'";
			Integer count1 = baseService.findCountSql(sql,dateString+" 00:00:00",dateString+" 07:59:59");
			Integer count2 = baseService.findCountSql(sql,dateString+" 08:00:00",dateString+" 11:59:59");
			Integer count3 = baseService.findCountSql(sql,dateString+" 12:00:00",dateString+" 15:59:59");
			Integer count4 = baseService.findCountSql(sql,dateString+" 16:00:00",dateString+" 23:59:59");
			Integer[] data = {count1,count2,count3,count4};
			jd.setName(name);
			jd.setData(data);
			datas.add(jd);
		}
		map.put("lineChart", datas);
		return map;
	}
	
	
	/**
	 * 返回Cbevents对象
	 * @return
	 */
	@RequestMapping("/toImageview")
	@ResponseBody
	public Cbevents toImageview(String eventid,HttpServletRequest request) {
		if(null==eventid && "".equals(eventid)) {
			return null;
		}
		Cbevents list = (Cbevents) baseService.get(Cbevents.class, eventid);
		return list;
	}
	
	/**
	 * 获取订阅列表数据
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("/getAllEvent")
	public Map<String, Object> getAllEvent(HttpServletRequest request,
			String startDate,String endDate,Cbevents queryBean) throws ParseException {
		String rows = request.getParameter("rows");  // 每页显示的记录数
		String page = request.getParameter("page");  // 当前第几页
		String countHql = "select count(eventid) from Cbevents where 1=1";
		String hql = "from Cbevents where 1=1";
		if(queryBean!=null) {
			String username = StringUtils.trimToEmpty(queryBean.getUsrname());
			if(username!=null && !"".equals(username)) {
				hql += "and usrname like '%"+username+"%'";
				countHql += "and usrname like '%"+username+"%'";
			}
			
			String cardid = StringUtils.trimToEmpty(queryBean.getCardid());
			if(cardid!=null && !"".equals(cardid)) {
				hql += "and cardid = '"+cardid+"'";
				countHql += "and cardid = '"+cardid+"'";
			}
			
			String groupname = StringUtils.trimToEmpty(queryBean.getGroupname());
			if(groupname!=null && !"".equals(groupname)) {
				hql += "and groupname = '"+groupname+"'";
				countHql += "and groupname = '"+groupname+"'";
			}
		}
		
		//如果选择了时间段查询，则：
		if(null != startDate && !"".equals(startDate)){
			countHql += " and detecttime > '"+startDate+"'";
			hql += " and detecttime > '"+startDate+"'";
			
		}
		if(null != endDate && !"".equals(endDate)){
			countHql += " and detecttime < '"+endDate+"'";
			hql += " and detecttime < '"+endDate+"'";
			
		}
		
		hql += "order by detecttime desc nulls last";

		int count = baseService.findCount(countHql);
		List<Cbevents> list = baseService.findByPage(hql, rows, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	
	/**
	 * 1、获取公钥
	 * @param request
	 * @return 
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/getPubkey")
	public String getPubkey(HttpServletRequest request) throws Exception{
		String result  = "";
		
		String userName= request.getParameter("username"); 
		String userPass= request.getParameter("userpass");
		
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("Content-type", "application/json;charset=UTF-8");
		String publicKeyUrl = "http://10.115.170.53/WPMS/getPublicKey";
		//
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginName", userName);
			JSONObject jsObj = JSONObject.fromObject(paramMap);		
			String pubKeyResult = HttpClientPoolUtil.post(publicKeyUrl, jsObj.toString(), headMap);
			if((pubKeyResult != null && !"".equals(pubKeyResult))){
				JSONObject pubKeyResultObj = JSONObject.fromObject(pubKeyResult);
				String success = null;
				String publicKey = "";
				if(pubKeyResultObj.has("success")){
					success = pubKeyResultObj.getString("success");
					publicKey = pubKeyResultObj.getString("publicKey");
				}
				if(success == null || "".equals(success)){
					throw new Exception("获取公钥失败");
				}
				//result = pubKeyResult;
				String entryPassword =RSAUtils.encryptBASE64(RSAUtils.encryptByPublicKey(userPass.getBytes(), publicKey));
				result = entryPassword;
			}			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 2、获取token
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/getToken")
	public Map getToken(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String userName= request.getParameter("username"); 
		String userPass= request.getParameter("userpass");
		
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("Content-type", "application/json;charset=UTF-8");
		//http://10.115.170.53/WPMS/login
		String tokenUrl = "http://10.115.170.53/WPMS/login";
	//	String loginUrl = MessageFormat.format(tokenUrl, srvIP, srvPort);
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginName", userName);
			paramMap.put("loginPass", userPass);
			JSONObject jsObj = JSONObject.fromObject(paramMap);
			String tokenResult = HttpClientPoolUtil.post(tokenUrl, jsObj.toString(), headMap);
			if((tokenResult != null && !"".equals(tokenResult))){
				JSONObject tokenResultObj = JSONObject.fromObject(tokenResult);
				String success = tokenResultObj.getString("success");
				String uid = tokenResultObj.getString("id");
				String token = tokenResultObj.getString("token");
				if(success == null || "".equals(success)){
					throw new Exception("获取token失败");
				}
				map.put("id", uid);
				map.put("token", token);
			}			
		} catch (Exception e) {
			throw e;
		}
		return map;
		
	}
	
	/**
	 * 3、注册接入方
	 * @param request
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/regCallback")
	public Map regCallback(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		String userName = request.getParameter("username"); 
		String userToken= request.getParameter("usertoken");
		
		String cburl    = request.getParameter("callbackurl");
		String cbname   = request.getParameter("platname");
		String cbdesc   = request.getParameter("platdesc");
		int hasExpir = 0;
		try{
		 hasExpir = Integer.parseInt(request.getParameter("sysexpir"));
		}catch(NumberFormatException e){
			map.put("success", "false");
			map.put("errMsg", "异常，未选择是否有有效期！");
		}
		
		String expirationTime = request.getParameter("expirationTime");
		
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("Content-type", "application/json;charset=UTF-8");
		//
		String regUrl = "http://10.115.170.53:82/register";
		String registerUrl = MessageFormat.format(SYS_REG_URL, srvIP, srvPort, userId, userName, userToken);
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("callbackAddress", cburl);
			paramMap.put("hasExpir", hasExpir);
			paramMap.put("name", cbname);
			paramMap.put("memo", cbdesc);
			if(hasExpir == 1){
				paramMap.put("expirationTime", expirationTime);
			}
			
			JSONObject jsObj = JSONObject.fromObject(paramMap);
			String regResult = HttpClientPoolUtil.post(registerUrl, jsObj.toString(), headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("接入方注册失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}		
			//200:成功;400 验证问题;999:其他
		
			if("200".equals(map.get("code"))){
				//注册
				String code = (String) map.get("code");
			Reginfo reginfo = new Reginfo();
				 reginfo.setPlatip(srvIP);
				 reginfo.setPlatport(srvPort);
			     reginfo.setSysname(cbname);
				 reginfo.setSyscburl(cburl);
				 reginfo.setSysdesc(cbdesc);
				 reginfo.setUsrcode(code);
				 boolean sysExpir = false;
				 if(hasExpir ==1){
					 sysExpir = true;
				 }
				 reginfo.setSysexpir(sysExpir);
				 if(hasExpir == 1){
				 reginfo.setExpirtime(expirationTime);
				 }
				 baseService.save(reginfo);
			}
		} catch (Exception e) {
			//throw e;
		}
		return map;
		
	}
	/**
	 * 4、更新注册方
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uptCallback")
	public Map uptCallback(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		String userName = request.getParameter("username"); 
		String userToken= request.getParameter("usertoken");
		
		String cburl    = request.getParameter("callbackurl");
		String cbname   = request.getParameter("platname");
		String cbdesc   = request.getParameter("platdesc");
		String hasExpir = request.getParameter("callbackexpir");
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_REG_URL, srvIP, srvPort, userId, userName, userToken);
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("callbackAddress", cburl);
			paramMap.put("hasExpir", hasExpir);
			paramMap.put("name", cbname);
			paramMap.put("memo", cbdesc);
			
			JSONObject jsObj = JSONObject.fromObject(paramMap);
			String regResult = HttpClientPoolUtil.post(registerUrl, jsObj.toString(), headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("接入方更新失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
		
	}
	
	/**
	 * 5、事件订阅
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/regEvent")
	public Map regEvent(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		String eventName= request.getParameter("eventname"); 
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_EVENTREG_URL, srvIP, srvPort, userId, eventName);
		try {
			String regResult = HttpClientPoolUtil.get(registerUrl, headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("事件订阅失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
	}
	
	/**
	 * 6、取消事件订阅
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unregEvent")
	public Map unregEvent(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		String eventName= request.getParameter("eventname"); 
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_EVENTUNREG_URL, srvIP, srvPort, userId, eventName);
		try {
			String regResult = HttpClientPoolUtil.get(registerUrl, headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("取消事件订阅失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
	}
	
	/**
	 * 7、获取事件订阅列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEventlist")
	public Map getEventlist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_EVENTGET_URL, srvIP, srvPort, userId);
		try {
			String regResult = HttpClientPoolUtil.get(registerUrl, headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("获取订阅事件列表失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
	}
	
	/**
	 * 8、删除接入方
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unregCallback")
	public Map unregCallback(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_UNREG_URL, srvIP, srvPort, userId);
		try {
			String regResult = HttpClientPoolUtil.delete(registerUrl, headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("删除接入方失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
	}
	
	/* 
     * 将时间转换为时间戳
     */ 
    private static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
	
	/**
	 * 9、获取录像回放地址
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
    @ResponseBody
	@RequestMapping("/getPlaybackURL")
	public String getPlaybackURL(HttpServletRequest request) throws ParseException {
		//http://10.115.170.53:7086/vod/device/cameraid/1000010%240/substream/1/totallength/60/begintime/1588119001/endtime/1588119061.m3u8
				
		//String GET_PLAYBACK_URL = "http://10.115.170.53:7086/vod/device/cameraid/1000010%240/substream/1/totallength/60/begintime/1588119001/endtime/1588119061.m3u8";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String srvIP      = "10.115.170.53";//request.getParameter("serverip");
		String srvPort    = "7086";//request.getParameter("serverport");
		String eventid = request.getParameter("eventid");
		if(null==eventid || "".equals(eventid)) {
			return null;
		}
		Cbevents cbevents = (Cbevents) baseService.get(Cbevents.class, eventid);
		String detectTime = cbevents.getDetecttime();
		String channelCode = cbevents.getChannelcode();
		long time = simpleDateFormat.parse(detectTime).getTime()/1000;
		
		//String deviceCode = request.getParameter("deviceCode");
		char str = channelCode.charAt(channelCode.length()-1);
		String cameraId	  = "1000010%24"+str;
//		if("1000010$1$0$0".equals(channelCode)) {
//			cameraId = "1000010%240";//deviceCode + "%240";	1000010$1$0$0
//		}else {
//			cameraId = "1000010%241";//deviceCode + "%241";	1000010$1$0$1
//		}
		
		
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = df.parse(detectTime);
		
		////Calendar cal = new GregorianCalendar();
		//Calendar cal = Calendar.getInstance();
		////Date date = new Date();
		
		//cal.setTime(date);                 //设置参数时间
		
		//cal.add(Calendar.SECOND, -30);     //把日期往后增加SECOND 秒.整数往后推,负数往前移动
		//date = cal.getTime();              //这个时间就是日期往后推一天的结果
		//String startTime = df.format(date);
		
		//cal.add(Calendar.SECOND, 60);     //把日期往后增加SECOND 秒.整数往后推,负数往前移动
		//date = cal.getTime();              //这个时间就是日期往后推一天的结果
		//String endTime = df.format(date);
		
		//dateToStamp()
		//startTime = dateToStamp(startTime);
		//endTime   = dateToStamp(endTime);  
		String startTime = Long.toString(time - 10);  
		String endTime   = Long.toString(time + 30);
		//
		String playback = MessageFormat.format(HLS_PLAYBACK_URL, srvIP, srvPort, cameraId, startTime, endTime);
		
		return playback;
	}
    
    
    /**
	 * 9、获取录像回放地址
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
    @ResponseBody
	@RequestMapping("/getPlaybackURLVO")
	public String getPlaybackURLVO(HttpServletRequest request) throws ParseException {
				
		String srvIP      = "10.115.170.53";//request.getParameter("serverip");
		String srvPort    = "7086";//request.getParameter("serverport");
		String tmid = request.getParameter("tmid");
		if(null==tmid || "".equals(tmid)) {
			return null;
		}
		Treatment treatment = (Treatment) baseService.get(Treatment.class, tmid);
		Date detectTime = treatment.getTmstart();
		String channelCode = treatment.getFchannelcode();
		long time = detectTime.getTime()/1000;
		char str = channelCode.charAt(channelCode.length()-1);
		String cameraId	  = "1000010%24"+str;
		
		String startTime = Long.toString(time - 10);  
		String endTime   = Long.toString(time + 30);
		//
		String playback = MessageFormat.format(HLS_PLAYBACK_URL, srvIP, srvPort, cameraId, startTime, endTime);
		
		return playback;
	}
	
	
	/**
	 * 10、获取人脸库信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFacelib")
	public Map getFacelib(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String srvIP    = request.getParameter("serverip");
		String srvPort  = request.getParameter("serverport"); 
		String userId   = request.getParameter("userid"); 
		
		Map<String, String> headMap = new HashMap<String, String>();
		//
		String registerUrl = MessageFormat.format(SYS_GETLIB_URL, srvIP, srvPort, userId);
		try {
			String regResult = HttpClientPoolUtil.delete(registerUrl, headMap);
			if((regResult != null && !"".equals(regResult))){
				JSONObject regResultObj = JSONObject.fromObject(regResult);
				String success = regResultObj.getString("success");
				if(success == null || "".equals(success)){
					throw new Exception("获取人脸库列表失败");
				}
				Iterator<String> iterator = regResultObj.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = regResultObj.getString(key);
					map.put(key, value);
				}
			}			
		} catch (Exception e) {
			//throw e;
		}
		return map;
	}
	
	
	
}
