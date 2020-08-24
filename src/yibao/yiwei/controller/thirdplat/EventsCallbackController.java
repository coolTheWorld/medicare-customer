package yibao.yiwei.controller.thirdplat;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yibao.yiwei.entity.thirdplat.Cbevents;
import yibao.yiwei.service.IBaseService;

/**
 * 平台回调处理
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/events")
public class EventsCallbackController {

	@Autowired
	private IBaseService baseService;
	
	private static Logger log = Logger.getLogger(EventsCallbackController.class);
	
	/**
	 * 获取、保存回调数据
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping("/callback")
	public String getAllRegister(HttpServletRequest request) {

		try{
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String calltime = sdf.format(new Date());
			String fromip = request.getRemoteAddr();
			//
			String jsStr = "";
			// 
			jsStr = IOUtils.toString(request.getInputStream(), "utf-8");
			//System.out.println(jsStr);
			log.info("callback:" + jsStr);
			JSONObject jsObj = JSONObject.fromObject(jsStr);
			
			/*
			{"data":
				{
					"cardType":1,
					"cardid":"370303195501263557",
					"channelCode":"1000010$1$0$1",
					"channelName":"八院血透人脸枪机",
					"city":"淄博",
					"detectTime":"2020-05-09 11:09:03",
					"deviceCode":"1000010",
					"deviceName":"IVSS",
					"faceMinUrl":"http://10.115.170.53:50000/56879ceb-24d7-11ea-a61b-08eded28a34e/20200509/1/dsf_7a5d21cc-9181-11ea-a2f9-08eded28a34e_16959734_17091884.jpg",
					"features":[8,10],
					"groupname":"八院血透库",
					"imageUrl":"http://10.115.170.53:50000/56879ceb-24d7-11ea-a61b-08eded28a34e/20200509/1/dsf_7a5d21cc-9181-11ea-a2f9-08eded28a34e_16953511_16959734.jpg",
					"libPath":"http://10.115.170.53:50000/56879ceb-24d7-11ea-a61b-08eded28a34e/20200509/1/dsf_7a5d21cc-9181-11ea-a2f9-08eded28a34e_16948049_16953511.jpg",
					"name":"张绵庆",
					"province":"山东",
					"sex":1,
					"similarity":97
				},
				"interfaceCode":"EVENT_FACE_RECOGNITION_RECORD",
				"sessionId":"07bf8799785540ad86fd246131f0041d"
			}
			*/
			
			Cbevents cbevents = new Cbevents();
			//cbevents.setEventid();    //事件ID
			cbevents.setFromip(fromip);     //来源IP
			cbevents.setCalltime(calltime);   //调用事件
			cbevents.setRawdata(jsStr);    //原始内容
			//以下字段为json解析后内容
			cbevents.setSessionid(jsObj.getString("sessionId"));      //
			cbevents.setIntfacecode(jsObj.getString("interfaceCode"));//
			////data////
			JSONObject jsdataObj = jsObj.getJSONObject("data");
			//
			cbevents.setGroupname(jsdataObj.getString("groupname"));    //
			cbevents.setChannelcode(jsdataObj.getString("channelCode"));//
			cbevents.setChannelname(jsdataObj.getString("channelName"));//
			cbevents.setDevicecode(jsdataObj.getString("deviceCode")); //
			cbevents.setDevicename(jsdataObj.getString("deviceName")); //			
			cbevents.setUsrname(jsdataObj.getString("name"));          //
			cbevents.setSex(jsdataObj.getString("sex"));               //
			cbevents.setCardtype(jsdataObj.getString("cardType"));     //
			cbevents.setCardid(jsdataObj.getString("cardid"));         //
			cbevents.setLiburl(jsdataObj.getString("libPath"));        //
			cbevents.setFaceminurl(jsdataObj.getString("faceMinUrl")); //
			cbevents.setImageurl(jsdataObj.getString("imageUrl"));     //
			cbevents.setFeatures(jsdataObj.getString("features"));     //
			cbevents.setProvince(jsdataObj.getString("province"));     //
			cbevents.setCity(jsdataObj.getString("city"));             //
			cbevents.setSimilarity(jsdataObj.getString("similarity")); //
			cbevents.setDetecttime(jsdataObj.getString("detectTime")); //

			baseService.save(cbevents);

			return "success";
		}catch(Exception e){
			log.info("callback err:" + e.getMessage());
			//System.out.println("failure：" + e.getMessage());
			return "failure";
		}

	}
		
}
