package yibao.yiwei.entity.thirdplat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 检查、检验结果
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_CBEVENTS", schema = "YIWEI")
public class Cbevents implements java.io.Serializable {
	
    private String eventid;    //事件ID
    private String fromip;     //来源IP
    private String calltime;   //调用时间
    private String rawdata;    //原始内容
    private String sessionid;  //以下字段为json解析后内容
    private String groupname;  //
    private String channelcode;//
    private String channelname;//
    private String devicecode; //
    private String devicename; //
    private String intfacecode;//
    private String usrname;    //
    private String sex;        //
    private String cardtype;   //
    private String cardid;     //
    private String liburl;     //
    private String faceminurl; //
    private String imageurl;   //
    private String features;   //
    private String province;   //
    private String city;       //
    private String similarity; //
    private String detecttime; //
    
	@GenericGenerator(name = "eventid", strategy = "guid")
	@Id
	@GeneratedValue(generator = "eventid")
	@Column(name = "EVENTID", unique = true, nullable = false, length = 32)
	public String getEventid() {
		return this.eventid;
	}
    
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    @Column(name="FROMIP", length=16)

    public String getFromip() {
        return this.fromip;
    }
    
    public void setFromip(String fromip) {
        this.fromip = fromip;
    }

    @Column(name="CALLTIME", length=20)

    public String getCalltime() {
        return this.calltime;
    }
    
    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    @Column(name="RAWDATA", length=2000)

    public String getRawdata() {
        return this.rawdata;
    }
    
    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
    }

    @Column(name="SESSIONID", length=32)

    public String getSessionid() {
        return this.sessionid;
    }
    
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Column(name="GROUPNAME", length=128)

    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Column(name="CHANNELCODE", length=64)

    public String getChannelcode() {
        return this.channelcode;
    }
    
    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    @Column(name="CHANNELNAME", length=128)

    public String getChannelname() {
        return this.channelname;
    }
    
    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    @Column(name="DEVICECODE", length=64)

    public String getDevicecode() {
        return this.devicecode;
    }
    
    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    @Column(name="DEVICENAME", length=128)

    public String getDevicename() {
        return this.devicename;
    }
    
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    @Column(name="INTFACECODE", length=64)

    public String getIntfacecode() {
        return this.intfacecode;
    }
    
    public void setIntfacecode(String intfacecode) {
        this.intfacecode = intfacecode;
    }

    @Column(name="USRNAME", length=32)

    public String getUsrname() {
        return this.usrname;
    }
    
    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    @Column(name="SEX", length=8)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name="CARDTYPE", length=8)

    public String getCardtype() {
        return this.cardtype;
    }
    
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    @Column(name="CARDID", length=32)

    public String getCardid() {
        return this.cardid;
    }
    
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    @Column(name="LIBURL", length=256)

    public String getLiburl() {
        return this.liburl;
    }
    
    public void setLiburl(String liburl) {
        this.liburl = liburl;
    }

    @Column(name="FACEMINURL", length=256)

    public String getFaceminurl() {
        return this.faceminurl;
    }
    
    public void setFaceminurl(String faceminurl) {
        this.faceminurl = faceminurl;
    }

    @Column(name="IMAGEURL", length=256)

    public String getImageurl() {
        return this.imageurl;
    }
    
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Column(name="FEATURES", length=32)

    public String getFeatures() {
        return this.features;
    }
    
    public void setFeatures(String features) {
        this.features = features;
    }

    @Column(name="PROVINCE", length=32)

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name="CITY", length=32)

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="SIMILARITY", length=32)

    public String getSimilarity() {
        return this.similarity;
    }
    
    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    @Column(name="DETECTTIME", length=16)

    public String getDetecttime() {
        return this.detecttime;
    }
    
    public void setDetecttime(String detecttime) {
        this.detecttime = detecttime;
    }

	@Override
	public String toString() {
		return "Cbevents [eventid=" + eventid + ", fromip=" + fromip + ", calltime=" + calltime + ", rawdata=" + rawdata
				+ ", sessionid=" + sessionid + ", groupname=" + groupname + ", channelcode=" + channelcode
				+ ", channelname=" + channelname + ", devicecode=" + devicecode + ", devicename=" + devicename
				+ ", intfacecode=" + intfacecode + ", usrname=" + usrname + ", sex=" + sex + ", cardtype=" + cardtype
				+ ", cardid=" + cardid + ", liburl=" + liburl + ", faceminurl=" + faceminurl + ", imageurl=" + imageurl
				+ ", features=" + features + ", province=" + province + ", city=" + city + ", similarity=" + similarity
				+ ", detecttime=" + detecttime + "]";
	}
    
    

}