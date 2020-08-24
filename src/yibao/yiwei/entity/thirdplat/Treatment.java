package yibao.yiwei.entity.thirdplat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大华平台血透 康复 治疗记录
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_DHPLAT_TREATMENT", schema = "YIWEI")
public class Treatment implements java.io.Serializable {
	
	private String tmid;    //记录ID
    private String platname;     //大华平台对应名称
    private String usrname;   //姓名
    private String sex;    //性别
    private String cardid;     //身份证号
    private String fchannelcode;  //设备通道 进
    private String fliburl;  //人脸底库图 进
    private String ffaceminurl;//人脸特写 进
    private String fimageurl;//场景图 进
    private String ffeatures; //人脸特征 进
    private String fsimilarity; //相似度 进
    private String lchannelcode;//设备通道 出
    private String lliburl;    //脸底库图 出
    private String lfaceminurl;   //人脸特写 出
    private String limageurl;     //场景图 出
    private String lfeatures; //人脸特征 出
    private String lsimilarity;   //相似度 出
    private Date tmdate;   //治疗日期
    private Date tmstart;   //首次识别日期
    private Date tmend;       //最后识别日期
    private Integer tminterval; //时间间隔 分钟
    @GenericGenerator(name = "tmid", strategy = "guid")
	@Id
	@GeneratedValue(generator = "tmid")
	@Column(name = "TM_ID", unique = true, nullable = false, length = 32)
	public String getTmid() {
		return tmid;
	}
	public void setTmid(String tmid) {
		this.tmid = tmid;
	}
	@Column(name = "PLATNAME", length = 400)
	public String getPlatname() {
		return platname;
	}
	public void setPlatname(String platname) {
		this.platname = platname;
	}
	@Column(name = "USRNAME", length = 32)
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	@Column(name = "SEX", length = 32)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "CARDID", length = 32)
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	@Column(name = "F_CHANNELCODE", length = 32)
	public String getFchannelcode() {
		return fchannelcode;
	}
	public void setFchannelcode(String fchannelcode) {
		this.fchannelcode = fchannelcode;
	}
	@Column(name = "F_LIBURL", length = 256)
	public String getFliburl() {
		return fliburl;
	}
	public void setFliburl(String fliburl) {
		this.fliburl = fliburl;
	}
	@Column(name = "F_FACEMINURL", length = 256)
	public String getFfaceminurl() {
		return ffaceminurl;
	}
	public void setFfaceminurl(String ffaceminurl) {
		this.ffaceminurl = ffaceminurl;
	}
	@Column(name = "F_IMAGEURL", length = 256)
	public String getFimageurl() {
		return fimageurl;
	}
	public void setFimageurl(String fimageurl) {
		this.fimageurl = fimageurl;
	}
	@Column(name = "F_FEATURES", length = 32)
	public String getFfeatures() {
		return ffeatures;
	}
	public void setFfeatures(String ffeatures) {
		this.ffeatures = ffeatures;
	}
	@Column(name = "F_SIMILARITY", length = 32)
	public String getFsimilarity() {
		return fsimilarity;
	}
	public void setFsimilarity(String fsimilarity) {
		this.fsimilarity = fsimilarity;
	}
	@Column(name = "L_CHANNELCODE", length = 32)
	public String getLchannelcode() {
		return lchannelcode;
	}
	public void setLchannelcode(String lchannelcode) {
		this.lchannelcode = lchannelcode;
	}
	@Column(name = "L_LIBURL", length = 256)
	public String getLliburl() {
		return lliburl;
	}
	public void setLliburl(String lliburl) {
		this.lliburl = lliburl;
	}
	@Column(name = "L_FACEMINURL", length = 256)
	public String getLfaceminurl() {
		return lfaceminurl;
	}
	public void setLfaceminurl(String lfaceminurl) {
		this.lfaceminurl = lfaceminurl;
	}
	@Column(name = "L_IMAGEURL", length = 256)
	public String getLimageurl() {
		return limageurl;
	}
	public void setLimageurl(String limageurl) {
		this.limageurl = limageurl;
	}
	@Column(name = "L_FEATURES", length = 32)
	public String getLfeatures() {
		return lfeatures;
	}
	public void setLfeatures(String lfeatures) {
		this.lfeatures = lfeatures;
	}
	@Column(name = "L_SIMILARITY", length = 32)
	public String getLsimilarity() {
		return lsimilarity;
	}
	public void setLsimilarity(String lsimilarity) {
		this.lsimilarity = lsimilarity;
	}
	@Column(name = "TM_DATE", length = 32)
	public Date getTmdate() {
		return tmdate;
	}
	public void setTmdate(Date tmdate) {
		this.tmdate = tmdate;
	}
	@Column(name = "TM_START", length = 32)
	public Date getTmstart() {
		return tmstart;
	}
	public void setTmstart(Date tmstart) {
		this.tmstart = tmstart;
	}
	@Column(name = "TM_END", length = 32)
	public Date getTmend() {
		return tmend;
	}
	public void setTmend(Date tmend) {
		this.tmend = tmend;
	}
	@Column(name = "TM_INTERVAL", length = 32)
	public Integer getTminterval() {
		return tminterval;
	}
	public void setTminterval(Integer tminterval) {
		this.tminterval = tminterval;
	}
	@Override
	public String toString() {
		return "Treatment [tmid=" + tmid + ", platname=" + platname + ", username=" + usrname + ", sex=" + sex
				+ ", cardid=" + cardid + ", fchannelcode=" + fchannelcode + ", fliburl=" + fliburl + ", ffaceminurl="
				+ ffaceminurl + ", fimageurl=" + fimageurl + ", ffeatures=" + ffeatures + ", fsimilarity=" + fsimilarity
				+ ", lchannelcode=" + lchannelcode + ", lliburl=" + lliburl + ", lfaceminurl=" + lfaceminurl
				+ ", limageurl=" + limageurl + ", lfeatures=" + lfeatures + ", lsimilarity=" + lsimilarity + ", tmdate="
				+ tmdate + ", tmstart=" + tmstart + ", tmend=" + tmend + ", tminterval=" + tminterval + "]";
	}
    
    
    
	
}
