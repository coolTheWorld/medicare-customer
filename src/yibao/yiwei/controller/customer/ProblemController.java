package yibao.yiwei.controller.customer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import yibao.yiwei.common.FilePath;
import yibao.yiwei.common.FileType;
import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.utils.FileTypeJudge;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题反馈
 * 
 * @author sunshy
 *
 */
@RequestMapping("/problem")
@Controller
public class ProblemController {

	// 获取日志记录器Logger，名字为本类类名
	private static Logger logger = Logger.getLogger(ProblemController.class);

	/**
	 * 问题首页
	 * 
	 * @return
	 */
	@RequestMapping("/home")
	public String home() {
		return "customer/problem/home";
	}

	/**
	 * 问题反馈上传接口
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("/upload")
	public Map upload(HttpServletRequest request, String code, MultipartFile[] files) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 没有上传文件
		if (files == null || files.length < 1) {
			resultMap.put("flag", "success");
			resultMap.put("mes", "未上传文件");
			resultMap.put("count", 0);
			resultMap.put("path", "");
			return resultMap;
		}
		MultipartFile checkFile = null;
		//验证文件
		int num = 1;//上传文件个数限制
		int size = 512;//单个文件大小
		for (int i = 0; i < files.length; i++) {
			// 超出文件个数限制
			if (i >= num) {
				resultMap.put("flag", "error");
				resultMap.put("mes", "超出文件个数限制,只能上传"+num+"个文件");
				resultMap.put("count", files.length);
				resultMap.put("path", "");
				return resultMap;
			}
			checkFile= files[i];
			//非jpeg和png格式
			if(!FileTypeJudge.isImage(checkFile)) {
				resultMap.put("flag", "error");
				resultMap.put("mes", "文件格式不正确,只支持jpg和png格式");
				resultMap.put("count", i+1);
				resultMap.put("path", "");
				return resultMap;
			}
			//超出文件大小限制
			if (checkFile.getSize() > size) {
				resultMap.put("flag", "error");
				resultMap.put("mes", "文件超过"+size+"KB");
				resultMap.put("count", i+1);
				resultMap.put("path", "");
				return resultMap;
			}
		}
		
		//文件名 cusId+yyyyMMddHHmmss+i+后缀
		CustomerUser user  = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());
		StringBuffer fileName = new StringBuffer(user.getCusId());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		fileName.append(df.format(new Date()));
		
		MultipartFile file;
		
		String path = FilePath.FEEDBACK.getValue();//文件路径
		
		for(int i = 0; i < files.length; i++) {
			fileName.append(i);
			file = files[i];
			if(FileTypeJudge.isJpeg(file)) {
				fileName.append(FileType.JPEG.getValue());
			}else if(FileTypeJudge.isPng(file)) {
				fileName.append(FileType.PNG.getValue());
			}
			File filePath = new File(path,fileName.toString());
			
			//文件夹不存在则创建
			if (!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			try {
				file.transferTo(filePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * 添加问题反馈信息
	 * 
	 * @return
	 */
	@RequestMapping("/addInfo")
	public String addInfo() {
		return "customer/problem/home";
	}
}
