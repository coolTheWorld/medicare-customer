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
@Table(name = "TBL_REGINFO", schema = "YIWEI")
public class Reginfo implements java.io.Serializable {

    // Fields 
    private String regid;     //注册ID
    private String platip;    //平台IP
    private String platport;  //平台端口
    private String sysname;   //系统名称
    private String syscburl;  //回调地址
    private String sysdesc;   //系统描述
    private String usrcode;   //用户code(注册后返回)
    private boolean sysexpir; //是否有有效期
    private String expirtime; //有效期：日期
    private String eventlist; //订阅事件列表(分号分割)

  
   // Property accessors
   @GenericGenerator(name = "regid", strategy = "guid")
   @Id
   @GeneratedValue(generator = "regid")
   @Column(name = "REGID", unique = true, nullable = false, length = 32)
   public String getRegid() {
       return this.regid;
   }
   
   public void setRegid(String regid) {
       this.regid = regid;
   }

   @Column(name="PLATIP", length=16)

   public String getPlatip() {
       return this.platip;
   }
   
   public void setPlatip(String platip) {
       this.platip = platip;
   }

   @Column(name="PLATPORT", length=6)

   public String getPlatport() {
       return this.platport;
   }
   
   public void setPlatport(String platport) {
       this.platport = platport;
   }

   @Column(name="SYSNAME", length=128)

   public String getSysname() {
       return this.sysname;
   }
   
   public void setSysname(String sysname) {
       this.sysname = sysname;
   }

   @Column(name="SYSCBURL", length=256)

   public String getSyscburl() {
       return this.syscburl;
   }
   
   public void setSyscburl(String syscburl) {
       this.syscburl = syscburl;
   }

   @Column(name="SYSDESC", length=256)

   public String getSysdesc() {
       return this.sysdesc;
   }
   
   public void setSysdesc(String sysdesc) {
       this.sysdesc = sysdesc;
   }

   @Column(name="USRCODE", length=512)

   public String getUsrcode() {
       return this.usrcode;
   }
   
   public void setUsrcode(String usrcode) {
       this.usrcode = usrcode;
   }

   @Column(name="SYSEXPIR", precision=1, scale=0)

   public boolean getSysexpir() {
       return this.sysexpir;
   }
   
   public void setSysexpir(boolean sysexpir) {
       this.sysexpir = sysexpir;
   }

   @Column(name="EXPIRTIME", length=20)

   public String getExpirtime() {
       return this.expirtime;
   }
   
   public void setExpirtime(String expirtime) {
       this.expirtime = expirtime;
   }

   @Column(name="EVENTLIST", length=2000)

   public String getEventlist() {
       return this.eventlist;
   }
   
   public void setEventlist(String eventlist) {
       this.eventlist = eventlist;
   }
  

}