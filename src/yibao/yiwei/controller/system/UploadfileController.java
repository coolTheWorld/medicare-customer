package yibao.yiwei.controller.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.entity.system.Errorlog;
import yibao.yiwei.entity.system.Uploadfile;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.service.ITimerTask;
import yibao.yiwei.utils.RC2Coder;

/**
 * 文件解析
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
public class UploadfileController {

	@Autowired
	private IBaseService baseService;
	
	@Autowired
	private ITimerTask timerTask;
	
	private static Logger log = Logger.getLogger(UploadfileController.class);
	private static final HashMap<String, String> TypeMap = new HashMap<String, String>();
	static {
		TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
		TypeMap.put("flash", "swf,flv");
		TypeMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		TypeMap.put("file", "txt");
	}

	/**
	 * 转到文件解析
	 * @return
	 */
	@AuthPassport
	@RequestMapping("/toUploadfile")
	public String toUploadfile(HttpServletRequest request) {
		return "/system/uploadfile";
	}

	/**
	 * 文件解析
	 * @param request
	 * @param upFileflag
	 * @param cusName
	 * @param filename
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllUploadfile")
	public Map<String, Object> getAllUploadfile(HttpServletRequest request, String upFileflag, String cusName,String filename,String isHandle) {
		Map<String, Object> map = new HashMap<String, Object>();
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		String sql = "from Uploadfile where 1=1 ";
		String countSql = "select count(upId) from Uploadfile where 1=1 ";
		if (null != upFileflag && !upFileflag.equals("")) {
			upFileflag = upFileflag.trim();
			sql+= " and upFileflag in(" + upFileflag + ")";
			countSql+=" and upFileflag in (" + upFileflag + ")";
		}
		if (null != filename && !filename.equals("")) {
			filename = filename.trim();
			sql+= " and filename like '%" + filename + "%'";
			countSql+=" and filename like '%" + filename + "%'";
		}
		if (null != cusName && !cusName.equals("")) {
			cusName = cusName.trim();
			sql+= " and cusId in(select cusId from Customer where cusName like '%" + cusName + "%' or cusDareway = '"+cusName+"')";
			countSql+=" and cusId in(select cusId from Customer where cusName like '%" + cusName + "%' or cusDareway = '"+cusName+"')";
		}
		if (null != isHandle && !isHandle.equals("")) {
			isHandle = isHandle.trim();
			sql+= " and isHandle = " + isHandle;
			countSql+=" and isHandle = " + isHandle;
		}
		sql+= " order by upId desc";
		List list = baseService.findByPage(sql, rows, page);
		int total = baseService.findCount(countSql);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 查看文件内容
	 * @param path
	 * @param fileName
	 * @param encrypt
	 */
	@RequestMapping("/fileContent")
	@ResponseBody
	public List fileContent(String path, String fileName, Integer encrypt){
		List list = null;
		try {
			File file;
			String newPath;
			if(System.getProperty("os.name").indexOf("Windows") != -1){//Windows
				path = "D:" + path;
			}
			if(encrypt == 1){//加密文件处理
				file = new File(path.split("var")[0] + "Ming");//截取MI前面的字符串
				if (!file.exists() && !file.isDirectory()) {
					file.mkdirs();
				}
				newPath = file + "/De" + fileName;//解密文件路径：原路径+/De+加密文件名
				RC2Coder rc = new RC2Coder("12345678");
				rc.decrypt(path, newPath);//新路径生成解密文件
				path = newPath;
			}
			file = new File(path);
			list = new ArrayList();
			if (file.isFile()) {
				BufferedReader bufr = new BufferedReader(new FileReader(file));
				String line = null;
				String[] name = null;
				int count = 0;
				while ((line = bufr.readLine()) != null) {
					name = line.split("##");
					String line2 = "";
					count++;
					for(int i = 0; i < name.length;i++){
						line2+=(i+1) + "="+name[i];
						if(i != name.length-1){
							line2+="，";
						}
					}
					list.add(count + "："+line2);
				}
			}
		} catch (Exception e) {
			
		}
		return list;
	}
	
//	/**
//	 * 解析全部文件
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/parseAllFile")
//	public Map parseAllFile(){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("status", "开始解析全部文件...");
//		TimerTask task = new TimerTask(){
//			@Override
//			public void run() {
//				Uploadfile uploadfile = null;
//            	try {
//	            	String sql = "select * from (select * from TBL_UPLOADFILE where IS_HANDLE=0 order by up_id desc) where rownum <=1";
//					List<Uploadfile> list = baseService.findEntitySql(sql,Uploadfile.class);
//					if(list.size()>0){
//						uploadfile = list.get(0);
//						timerTask.parseFile(list.get(0));
//					}
//            	} catch (Exception e) {
//            		if(null != uploadfile) {
//    					String sql = "update Uploadfile set isHandle=-1,upCount=0,upName=?0 where upId=?1";
//    					baseService.updateOrDelete(sql,"<font color='blue'>列值太大或服务器异常</font>", uploadfile.getUpId());
//            		}
//				}
//			}
//		};
//		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);//最多支1个线程同时运行（设为1，防止页面多次点击）
//		pool.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);//0秒后开始执行，任务结束时间+1秒，循环执行
//		return map;
//	}
//	
//	/**
//	 * 解析选中文件
//	 * @param request
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/parseSelFile")
//	public Map parseSelFile(HttpServletRequest request,String ids){
//		String hql = "from Uploadfile where upId in ("+ids+")";
//		List<Uploadfile> list = baseService.find(hql);
//		for(int i = 0; i < list.size(); i++){
//			try {
//				timerTask.parseFile(list.get(i));
//			} catch (Exception e) {
//				Uploadfile uploadfile = list.get(i);
//				String sql = "update Uploadfile set isHandle=-1,upCount=0,upName=?0 where upId=?1";
//				baseService.updateOrDelete(sql,"<font color='blue'>列值太大或服务器异常</font>", uploadfile.getUpId());
//			}
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("status", "解析选中文件完成");
//		return map;
//	}
	
	/**
	 * 删除文件
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fileDelete")
	public Map fileDelete(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String hql = "delete Uploadfile where upId in ("+ids+")";
		baseService.updateOrDelete(hql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "文件删除成功");
		return map;
	}
	
	/**
	 * 删除重名文件，只保留一条
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/repeatFile")
	public Map repeatFile(){
		String sql = "delete from tbl_uploadfile where up_id not in (select min(up_id) from tbl_uploadfile group by cus_id, filename)";
		baseService.updateOrDeleteSql(sql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "同一客户的重名文件删除成功");
		return map;
	}

	/**
	 * 删除已注销定点的文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delCancleFile")
	public Map delCancleFile(){
		String hql = "delete Uploadfile where cusId not in (select cusId from Customer where cusStatus!=-1)";
		baseService.updateOrDelete(hql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status","已注销定点的文件删除成功");
		return map;
	}
	
	/**
	 * 定点上传统计生成
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/couUploadfile")
	public Map couUploadfile(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "正在生成定点上传统计...");
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				try {
					timerTask.couUploadfile();
				} catch (Exception e) {
					Errorlog errorlog = new Errorlog();
    				errorlog.setErrAddtime(new Date());
    				errorlog.setErrLog(e.toString());
    				errorlog.setErrType("task统计异常");
    				baseService.save(errorlog);
				}
			}
		};
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(task, 0, 1, TimeUnit.DAYS);//0秒后开始执行，任务开始时间+1天，循环执行
		return map;
	}
	
	/**
	 * 定点上传文件
	 * @param file
	 * @param request
	 * @param response
	 * @throws ParseException
	 */
	@RequestMapping("/medicare/service/fileupload")
	public void fileupload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String cusId = request.getParameter("cusId");
		if(null == cusId){
			cusId = "";
		}
		String hql = "from Customer where cusId=?0 and cusStatus=1";
		Customer customer = (Customer)baseService.findUnique(hql, cusId);
		if (null == customer) {
			backInfo(response, -1, "您是新注册定点尚未经过审核，请联系淄博市医保处信息科，审核通过后再上传文件");
			return;
		}
		if (!file.isEmpty()) {
			if (file.getSize() > 20 * 1024 * 1024) {
				backInfo(response, 2, "文件大小不能超过20兆");
				return;
			}
			String OriginalFilename = file.getOriginalFilename();//获取上传文件的原名
			String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
			if (!fileSuffix.equals("txt")) {
				backInfo(response, 3, "文件格式只能是txt");
				return;
			}
			if (!ServletFileUpload.isMultipartContent(request)) {//判断<form>标记的enctype属性是否是“multipart/form-data"
				backInfo(response, 4, "上传类型必须是文件");
				return;
			}
			String uploadPath = getDirectory(cusId, "MI");//创建文件夹路径
			File uploadDir = new File(uploadPath);
			if (!uploadDir.isDirectory()) {
				if (!uploadDir.mkdir()) {
					backInfo(response, 5, "上传目录不存在");
					return;
				}
			}
			if (!uploadDir.canWrite()) {
				backInfo(response, 6, "无上传权限");
				return;
			}
			String upFileflag = request.getParameter("filetag");
			String filename = upFileflag + OriginalFilename;//文件名称=文件标识+原始上传文件名
			File saveFile = new File(uploadPath, filename);
			try {
				file.transferTo(saveFile);
				backInfo(response, 0, "上传成功");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				backInfo(response, 1, "未知错误");
				return;
			}
			Uploadfile uploadfile = new Uploadfile();
			uploadfile.setCusId(cusId);
			uploadfile.setFilesize((int) (file.getSize()));
			uploadfile.setUpFileflag(upFileflag);
			uploadfile.setIsHandle(0);
			String isEncrypt = request.getParameter("isencrypt");
			if (null == isEncrypt) {
				uploadfile.setIsEncrypt(1); // 默认为1
			} else {
				uploadfile.setIsEncrypt(Integer.parseInt(isEncrypt));
			}
			String sep = System.getProperty("file.separator");//linux只能是/，windows系统/\都可以
			if (uploadPath.startsWith("D:")) {//windowns
				String[] arr = uploadPath.split("D:");
				uploadfile.setUpFilepath(arr[1] + sep + filename);
			} else {//linux
				uploadfile.setUpFilepath(uploadPath + sep + filename);
			}
			uploadfile.setFilename(filename);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String upCollectdate = request.getParameter("upCollectdate");
			Date date;
			if (null == upCollectdate || upCollectdate.equalsIgnoreCase("null")) {// 如果没有解析日期
				date = new Date();
			} else {
				if (upCollectdate.trim().length() == 10) {
					df = new SimpleDateFormat("yyyy-MM-dd");
				}
				date = df.parse(upCollectdate);
			}
			uploadfile.setUpDate(new Date());
			uploadfile.setUpCollectiondate(date);
			uploadfile.setUpCount(0);
			uploadfile.setCusName(customer.getCusName());
			baseService.save(uploadfile);
		} else {
			backInfo(response, -1, "上传文件不能为空");
			return;
		}
	}

	/**
	 * 返回客户端json信息
	 * @param response
	 * @param errcode
	 * @param errmsg
	 */
	private void backInfo(HttpServletResponse response, int errcode, String errmsg) {
		String json = "{ \"errcode\": " + errcode + ",\"errmsg\": \"" + errmsg + "\" }";
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 创建文件夹路径
	 * @param cusId
	 * @param dirType 明文/密文
	 * @return
	 */
	public static String getDirectory(String cusId, String dirType) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		File file;
		String sep = System.getProperty("file.separator");//linux只能是/，windows系统/\都可以
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {//Windows
			file = new File("D:/var/uploaddir/" + cusId + sep + year + sep + month + sep + day + sep + dirType + sep);
		} else {//Linux
			file = new File("/var/uploaddir/" + cusId + sep + year + sep + month + sep + day + sep + dirType + sep);
		}
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		return file.getPath();
	}
	
}