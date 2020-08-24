package yibao.yiwei.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 病案首页(概要)
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_MEDRECORDS", schema = "YIWEI")
public class Medrecords implements java.io.Serializable {

	private String merId;
	private String cusId;
	private String cusDareway;
	private Date merPicktime;
	private Date merAddtime;
	private String merOrgan;//医疗机构名称1
	private String merOrgancode;//组织结构代码2
	private String merPaytype;//医疗付费方式3
	private String merHosptimes;//住院次数4
	private String merCaseno;//病案号5
	private String merHospno;//住院号6
	private String merPtsname;//患者姓名7
	private String merPtssex;//患者性别8
	private String merBirthday;//出生日期9
	private String merAge;//年龄10
	private String merCountry;//国籍11
	private String merWeight;//入院体重12
	private String merBirplace;//出生地13
	private String merProplace;//籍贯14
	private String merNation;//民族15
	private String merIdnumber;//身份证号16
	private String merProfession;//职业17
	private String merMarstatus;//婚姻状况18
	private String merNowaddr;//现住地址19
	private String merNowphone;//现住地电话20
	private String merNowpost;//现住地邮编21
	private String merHukouaddr;//户口地址22
	private String merHukoupost;//户口地邮编23
	private String merWorkaddr;//工作单位及地址24
	private String merWorkphone;//单位电话25
	private String merWorkpost;//单位邮编26
	private String merContactname;//联系人姓名27
	private String merRelationship;//与患者关系28
	private String merContactaddr;//联系人地址29
	private String merContactphone;//联系人电话30
	private String merIntype;//入院途径31
	private String merIntime;//入院时间32
	private String merIndept;//入院科别33
	private String merInward;//入院病房34
	private String merTurndept;//转科科别35
	private String merOuttime;//出院时间36
	private String merOutdept;//出院科别37
	private String merOutward;//出院病房38
	private String merIndays;//住院天数39
	private String merClinicdiag;//门(急)诊诊断40
	private String merClinicdiagcode;//门(急)诊诊断疾病编码41
	private String merIndiag;//入院诊断42
	private String merInstatus;//入院时情况43
	private String merIndiagdate;//入院后确诊日期44
	private String merDemagecause;//损伤中毒的外部原因45
	private String merDamagecode;//损伤中毒 疾病编码46
	private String merPathological;//病理诊断47
	private String merPathcode;//病理编码48
	private String merPathnum;//病理号49
	private String merAllergic;//药物过敏50
	private String merAllergicname;//过敏药物名称51
	private String merClnicout;//门诊与出院52
	private String merInout;//入院与出院53
	private String merBeforafter;//术前与术后54
	private String merClinicpath;//临床与病理55
	private String merRadiopath;//放射与病理56
	private String merDirector;//科主任57
	private String merChief;//主（副主）任医师58
	private String merAttend;//主治医师59
	private String merResident;//住院医师60
	private String merStudaydoc;//进修医师61
	private String merIntern;//实习医师62
	private String merNurse;//责任护士63
	private String merCoder;//编码员64
	private String merQualty;//病案质量65
	private String merQcdoc;//质控医师66
	private String merQcnur;//质控护士67
	private String merQcdate;//质控日期68
	private String merOutway;//离院方式69
	private String merReccusname;//拟接收医疗机构名称70
	private String merBloodtype;//血型71
	private String merRh;//Rh72
	private String merImporttype;//输血品种73
	private String merImportcount;//输血量74
	private String merImportresp;//输血反应75
	private String merIncount;//住院总费用76
	private String merSelfmoney;//自付金额77
	private String merBedbill;//床费78
	private String merNursecost;//护理费79
	private String merWestern;//西药费80
	private String merChinese;//中成药81
	private String merHerb;//中草药82
	private String merRadiocost;//放射费83
	private String merTestcost;//化验费84
	private String merOxycost;//输氧费85
	private String merBloodcost;//输血费86
	private String merDiagcost;//诊疗费87
	private String merOperatcost;//手术费88
	private String merDeliverycost;//接生费89
	private String merCheckcost;//检查费90
	private String merDrugcost;//麻醉费91
	private String merInfantcost;//婴儿费92
	private String merLacost;//陪床费93
	private String merOther;//其它费用94
	private String merAutopsy;//死亡患者尸检95
	private String merSafenum;//抢救次数96
	private String merSuccnum;//成功次数97

	@GenericGenerator(name = "Medrecords_id", strategy = "guid")
	@Id
	@GeneratedValue(generator = "Medrecords_id")
	@Column(name = "MER_ID", unique = true, nullable = false, length = 32)
	public String getMerId() {
		return this.merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(name = "CUS_ID", length = 32)
	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@Column(name = "CUS_DAREWAY", length = 32)
	public String getCusDareway() {
		return this.cusDareway;
	}

	public void setCusDareway(String cusDareway) {
		this.cusDareway = cusDareway;
	}

	@Column(name = "MER_PICKTIME", length = 7)
	public Date getMerPicktime() {
		return this.merPicktime;
	}

	public void setMerPicktime(Date merPicktime) {
		this.merPicktime = merPicktime;
	}

	@Column(name = "MER_ADDTIME", length = 7)
	public Date getMerAddtime() {
		return this.merAddtime;
	}

	public void setMerAddtime(Date merAddtime) {
		this.merAddtime = merAddtime;
	}

	@Column(name = "MER_ORGAN", length = 200)
	public String getMerOrgan() {
		return this.merOrgan;
	}

	public void setMerOrgan(String merOrgan) {
		this.merOrgan = merOrgan;
	}

	@Column(name = "MER_ORGANCODE", length = 32)
	public String getMerOrgancode() {
		return this.merOrgancode;
	}

	public void setMerOrgancode(String merOrgancode) {
		this.merOrgancode = merOrgancode;
	}

	@Column(name = "MER_PAYTYPE", length = 32)
	public String getMerPaytype() {
		return this.merPaytype;
	}

	public void setMerPaytype(String merPaytype) {
		this.merPaytype = merPaytype;
	}

	@Column(name = "MER_HOSPTIMES", length = 32)
	public String getMerHosptimes() {
		return this.merHosptimes;
	}

	public void setMerHosptimes(String merHosptimes) {
		this.merHosptimes = merHosptimes;
	}

	@Column(name = "MER_CASENO", length = 32)
	public String getMerCaseno() {
		return this.merCaseno;
	}

	public void setMerCaseno(String merCaseno) {
		this.merCaseno = merCaseno;
	}

	@Column(name = "MER_HOSPNO", length = 64)
	public String getMerHospno() {
		return this.merHospno;
	}

	public void setMerHospno(String merHospno) {
		this.merHospno = merHospno;
	}

	@Column(name = "MER_PTSNAME", length = 32)
	public String getMerPtsname() {
		return this.merPtsname;
	}

	public void setMerPtsname(String merPtsname) {
		this.merPtsname = merPtsname;
	}

	@Column(name = "MER_PTSSEX", length = 32)
	public String getMerPtssex() {
		return this.merPtssex;
	}

	public void setMerPtssex(String merPtssex) {
		this.merPtssex = merPtssex;
	}

	@Column(name = "MER_BIRTHDAY", length = 32)
	public String getMerBirthday() {
		return this.merBirthday;
	}

	public void setMerBirthday(String merBirthday) {
		this.merBirthday = merBirthday;
	}

	@Column(name = "MER_AGE", length = 32)
	public String getMerAge() {
		return this.merAge;
	}

	public void setMerAge(String merAge) {
		this.merAge = merAge;
	}

	@Column(name = "MER_COUNTRY", length = 32)
	public String getMerCountry() {
		return this.merCountry;
	}

	public void setMerCountry(String merCountry) {
		this.merCountry = merCountry;
	}

	@Column(name = "MER_WEIGHT", length = 32)
	public String getMerWeight() {
		return this.merWeight;
	}

	public void setMerWeight(String merWeight) {
		this.merWeight = merWeight;
	}

	@Column(name = "MER_BIRPLACE", length = 200)
	public String getMerBirplace() {
		return this.merBirplace;
	}

	public void setMerBirplace(String merBirplace) {
		this.merBirplace = merBirplace;
	}

	@Column(name = "MER_PROPLACE", length = 200)
	public String getMerProplace() {
		return this.merProplace;
	}

	public void setMerProplace(String merProplace) {
		this.merProplace = merProplace;
	}

	@Column(name = "MER_NATION", length = 32)
	public String getMerNation() {
		return this.merNation;
	}

	public void setMerNation(String merNation) {
		this.merNation = merNation;
	}

	@Column(name = "MER_IDNUMBER", length = 32)
	public String getMerIdnumber() {
		return this.merIdnumber;
	}

	public void setMerIdnumber(String merIdnumber) {
		this.merIdnumber = merIdnumber;
	}

	@Column(name = "MER_PROFESSION", length = 32)
	public String getMerProfession() {
		return this.merProfession;
	}

	public void setMerProfession(String merProfession) {
		this.merProfession = merProfession;
	}

	@Column(name = "MER_MARSTATUS", length = 32)
	public String getMerMarstatus() {
		return this.merMarstatus;
	}

	public void setMerMarstatus(String merMarstatus) {
		this.merMarstatus = merMarstatus;
	}

	@Column(name = "MER_NOWADDR", length = 200)
	public String getMerNowaddr() {
		return this.merNowaddr;
	}

	public void setMerNowaddr(String merNowaddr) {
		this.merNowaddr = merNowaddr;
	}

	@Column(name = "MER_NOWPHONE", length = 32)
	public String getMerNowphone() {
		return this.merNowphone;
	}

	public void setMerNowphone(String merNowphone) {
		this.merNowphone = merNowphone;
	}

	@Column(name = "MER_NOWPOST", length = 32)
	public String getMerNowpost() {
		return this.merNowpost;
	}

	public void setMerNowpost(String merNowpost) {
		this.merNowpost = merNowpost;
	}

	@Column(name = "MER_HUKOUADDR", length = 200)
	public String getMerHukouaddr() {
		return this.merHukouaddr;
	}

	public void setMerHukouaddr(String merHukouaddr) {
		this.merHukouaddr = merHukouaddr;
	}

	@Column(name = "MER_HUKOUPOST", length = 32)
	public String getMerHukoupost() {
		return this.merHukoupost;
	}

	public void setMerHukoupost(String merHukoupost) {
		this.merHukoupost = merHukoupost;
	}

	@Column(name = "MER_WORKADDR", length = 200)
	public String getMerWorkaddr() {
		return this.merWorkaddr;
	}

	public void setMerWorkaddr(String merWorkaddr) {
		this.merWorkaddr = merWorkaddr;
	}

	@Column(name = "MER_WORKPHONE", length = 32)
	public String getMerWorkphone() {
		return this.merWorkphone;
	}

	public void setMerWorkphone(String merWorkphone) {
		this.merWorkphone = merWorkphone;
	}

	@Column(name = "MER_WORKPOST", length = 32)
	public String getMerWorkpost() {
		return this.merWorkpost;
	}

	public void setMerWorkpost(String merWorkpost) {
		this.merWorkpost = merWorkpost;
	}

	@Column(name = "MER_CONTACTNAME", length = 32)
	public String getMerContactname() {
		return this.merContactname;
	}

	public void setMerContactname(String merContactname) {
		this.merContactname = merContactname;
	}

	@Column(name = "MER_RELATIONSHIP", length = 32)
	public String getMerRelationship() {
		return this.merRelationship;
	}

	public void setMerRelationship(String merRelationship) {
		this.merRelationship = merRelationship;
	}

	@Column(name = "MER_CONTACTADDR", length = 200)
	public String getMerContactaddr() {
		return this.merContactaddr;
	}

	public void setMerContactaddr(String merContactaddr) {
		this.merContactaddr = merContactaddr;
	}

	@Column(name = "MER_CONTACTPHONE", length = 32)
	public String getMerContactphone() {
		return this.merContactphone;
	}

	public void setMerContactphone(String merContactphone) {
		this.merContactphone = merContactphone;
	}

	@Column(name = "MER_INTYPE", length = 32)
	public String getMerIntype() {
		return this.merIntype;
	}

	public void setMerIntype(String merIntype) {
		this.merIntype = merIntype;
	}

	@Column(name = "MER_INTIME", length = 32)
	public String getMerIntime() {
		return this.merIntime;
	}

	public void setMerIntime(String merIntime) {
		this.merIntime = merIntime;
	}

	@Column(name = "MER_INDEPT", length = 32)
	public String getMerIndept() {
		return this.merIndept;
	}

	public void setMerIndept(String merIndept) {
		this.merIndept = merIndept;
	}

	@Column(name = "MER_INWARD", length = 32)
	public String getMerInward() {
		return this.merInward;
	}

	public void setMerInward(String merInward) {
		this.merInward = merInward;
	}

	@Column(name = "MER_TURNDEPT", length = 32)
	public String getMerTurndept() {
		return this.merTurndept;
	}

	public void setMerTurndept(String merTurndept) {
		this.merTurndept = merTurndept;
	}

	@Column(name = "MER_OUTTIME", length = 32)
	public String getMerOuttime() {
		return this.merOuttime;
	}

	public void setMerOuttime(String merOuttime) {
		this.merOuttime = merOuttime;
	}

	@Column(name = "MER_OUTDEPT", length = 32)
	public String getMerOutdept() {
		return this.merOutdept;
	}

	public void setMerOutdept(String merOutdept) {
		this.merOutdept = merOutdept;
	}

	@Column(name = "MER_OUTWARD", length = 32)
	public String getMerOutward() {
		return this.merOutward;
	}

	public void setMerOutward(String merOutward) {
		this.merOutward = merOutward;
	}

	@Column(name = "MER_INDAYS", length = 32)
	public String getMerIndays() {
		return this.merIndays;
	}

	public void setMerIndays(String merIndays) {
		this.merIndays = merIndays;
	}

	@Column(name = "MER_CLINICDIAG", length = 400)
	public String getMerClinicdiag() {
		return this.merClinicdiag;
	}

	public void setMerClinicdiag(String merClinicdiag) {
		this.merClinicdiag = merClinicdiag;
	}

	@Column(name = "MER_CLINICDIAGCODE", length = 32)
	public String getMerClinicdiagcode() {
		return this.merClinicdiagcode;
	}

	public void setMerClinicdiagcode(String merClinicdiagcode) {
		this.merClinicdiagcode = merClinicdiagcode;
	}

	@Column(name = "MER_INDIAG", length = 400)
	public String getMerIndiag() {
		return this.merIndiag;
	}

	public void setMerIndiag(String merIndiag) {
		this.merIndiag = merIndiag;
	}

	@Column(name = "MER_INSTATUS", length = 400)
	public String getMerInstatus() {
		return this.merInstatus;
	}

	public void setMerInstatus(String merInstatus) {
		this.merInstatus = merInstatus;
	}

	@Column(name = "MER_INDIAGDATE", length = 32)
	public String getMerIndiagdate() {
		return this.merIndiagdate;
	}

	public void setMerIndiagdate(String merIndiagdate) {
		this.merIndiagdate = merIndiagdate;
	}

	@Column(name = "MER_DEMAGECAUSE", length = 400)
	public String getMerDemagecause() {
		return this.merDemagecause;
	}

	public void setMerDemagecause(String merDemagecause) {
		this.merDemagecause = merDemagecause;
	}

	@Column(name = "MER_DAMAGECODE", length = 400)
	public String getMerDamagecode() {
		return this.merDamagecode;
	}

	public void setMerDamagecode(String merDamagecode) {
		this.merDamagecode = merDamagecode;
	}

	@Column(name = "MER_PATHOLOGICAL", length = 400)
	public String getMerPathological() {
		return this.merPathological;
	}

	public void setMerPathological(String merPathological) {
		this.merPathological = merPathological;
	}

	@Column(name = "MER_PATHCODE", length = 400)
	public String getMerPathcode() {
		return this.merPathcode;
	}

	public void setMerPathcode(String merPathcode) {
		this.merPathcode = merPathcode;
	}

	@Column(name = "MER_PATHNUM", length = 400)
	public String getMerPathnum() {
		return this.merPathnum;
	}

	public void setMerPathnum(String merPathnum) {
		this.merPathnum = merPathnum;
	}

	@Column(name = "MER_ALLERGIC", length = 400)
	public String getMerAllergic() {
		return this.merAllergic;
	}

	public void setMerAllergic(String merAllergic) {
		this.merAllergic = merAllergic;
	}

	@Column(name = "MER_ALLERGICNAME", length = 400)
	public String getMerAllergicname() {
		return this.merAllergicname;
	}

	public void setMerAllergicname(String merAllergicname) {
		this.merAllergicname = merAllergicname;
	}

	@Column(name = "MER_CLNICOUT", length = 400)
	public String getMerClnicout() {
		return this.merClnicout;
	}

	public void setMerClnicout(String merClnicout) {
		this.merClnicout = merClnicout;
	}

	@Column(name = "MER_INOUT", length = 400)
	public String getMerInout() {
		return this.merInout;
	}

	public void setMerInout(String merInout) {
		this.merInout = merInout;
	}

	@Column(name = "MER_BEFORAFTER", length = 400)
	public String getMerBeforafter() {
		return this.merBeforafter;
	}

	public void setMerBeforafter(String merBeforafter) {
		this.merBeforafter = merBeforafter;
	}

	@Column(name = "MER_CLINICPATH", length = 400)
	public String getMerClinicpath() {
		return this.merClinicpath;
	}

	public void setMerClinicpath(String merClinicpath) {
		this.merClinicpath = merClinicpath;
	}

	@Column(name = "MER_RADIOPATH", length = 400)
	public String getMerRadiopath() {
		return this.merRadiopath;
	}

	public void setMerRadiopath(String merRadiopath) {
		this.merRadiopath = merRadiopath;
	}

	@Column(name = "MER_DIRECTOR", length = 32)
	public String getMerDirector() {
		return this.merDirector;
	}

	public void setMerDirector(String merDirector) {
		this.merDirector = merDirector;
	}

	@Column(name = "MER_CHIEF", length = 32)
	public String getMerChief() {
		return this.merChief;
	}

	public void setMerChief(String merChief) {
		this.merChief = merChief;
	}

	@Column(name = "MER_ATTEND", length = 32)
	public String getMerAttend() {
		return this.merAttend;
	}

	public void setMerAttend(String merAttend) {
		this.merAttend = merAttend;
	}

	@Column(name = "MER_RESIDENT", length = 32)
	public String getMerResident() {
		return this.merResident;
	}

	public void setMerResident(String merResident) {
		this.merResident = merResident;
	}

	@Column(name = "MER_STUDAYDOC", length = 32)
	public String getMerStudaydoc() {
		return this.merStudaydoc;
	}

	public void setMerStudaydoc(String merStudaydoc) {
		this.merStudaydoc = merStudaydoc;
	}

	@Column(name = "MER_INTERN", length = 32)
	public String getMerIntern() {
		return this.merIntern;
	}

	public void setMerIntern(String merIntern) {
		this.merIntern = merIntern;
	}

	@Column(name = "MER_NURSE", length = 32)
	public String getMerNurse() {
		return this.merNurse;
	}

	public void setMerNurse(String merNurse) {
		this.merNurse = merNurse;
	}

	@Column(name = "MER_CODER", length = 32)
	public String getMerCoder() {
		return this.merCoder;
	}

	public void setMerCoder(String merCoder) {
		this.merCoder = merCoder;
	}

	@Column(name = "MER_QUALTY", length = 32)
	public String getMerQualty() {
		return this.merQualty;
	}

	public void setMerQualty(String merQualty) {
		this.merQualty = merQualty;
	}

	@Column(name = "MER_QCDOC", length = 32)
	public String getMerQcdoc() {
		return this.merQcdoc;
	}

	public void setMerQcdoc(String merQcdoc) {
		this.merQcdoc = merQcdoc;
	}

	@Column(name = "MER_QCNUR", length = 32)
	public String getMerQcnur() {
		return this.merQcnur;
	}

	public void setMerQcnur(String merQcnur) {
		this.merQcnur = merQcnur;
	}

	@Column(name = "MER_QCDATE", length = 32)
	public String getMerQcdate() {
		return this.merQcdate;
	}

	public void setMerQcdate(String merQcdate) {
		this.merQcdate = merQcdate;
	}

	@Column(name = "MER_OUTWAY", length = 32)
	public String getMerOutway() {
		return this.merOutway;
	}

	public void setMerOutway(String merOutway) {
		this.merOutway = merOutway;
	}

	@Column(name = "MER_RECCUSNAME", length = 32)
	public String getMerReccusname() {
		return this.merReccusname;
	}

	public void setMerReccusname(String merReccusname) {
		this.merReccusname = merReccusname;
	}

	@Column(name = "MER_BLOODTYPE", length = 32)
	public String getMerBloodtype() {
		return this.merBloodtype;
	}

	public void setMerBloodtype(String merBloodtype) {
		this.merBloodtype = merBloodtype;
	}

	@Column(name = "MER_RH", length = 32)
	public String getMerRh() {
		return this.merRh;
	}

	public void setMerRh(String merRh) {
		this.merRh = merRh;
	}

	@Column(name = "MER_IMPORTTYPE", length = 32)
	public String getMerImporttype() {
		return this.merImporttype;
	}

	public void setMerImporttype(String merImporttype) {
		this.merImporttype = merImporttype;
	}

	@Column(name = "MER_IMPORTCOUNT", length = 32)
	public String getMerImportcount() {
		return this.merImportcount;
	}

	public void setMerImportcount(String merImportcount) {
		this.merImportcount = merImportcount;
	}

	@Column(name = "MER_IMPORTRESP", length = 32)
	public String getMerImportresp() {
		return this.merImportresp;
	}

	public void setMerImportresp(String merImportresp) {
		this.merImportresp = merImportresp;
	}

	@Column(name = "MER_INCOUNT", length = 32)
	public String getMerIncount() {
		return this.merIncount;
	}

	public void setMerIncount(String merIncount) {
		this.merIncount = merIncount;
	}

	@Column(name = "MER_SELFMONEY", length = 32)
	public String getMerSelfmoney() {
		return this.merSelfmoney;
	}

	public void setMerSelfmoney(String merSelfmoney) {
		this.merSelfmoney = merSelfmoney;
	}

	@Column(name = "MER_BEDBILL", length = 32)
	public String getMerBedbill() {
		return this.merBedbill;
	}

	public void setMerBedbill(String merBedbill) {
		this.merBedbill = merBedbill;
	}

	@Column(name = "MER_NURSECOST", length = 32)
	public String getMerNursecost() {
		return this.merNursecost;
	}

	public void setMerNursecost(String merNursecost) {
		this.merNursecost = merNursecost;
	}

	@Column(name = "MER_WESTERN", length = 32)
	public String getMerWestern() {
		return this.merWestern;
	}

	public void setMerWestern(String merWestern) {
		this.merWestern = merWestern;
	}

	@Column(name = "MER_CHINESE", length = 32)
	public String getMerChinese() {
		return this.merChinese;
	}

	public void setMerChinese(String merChinese) {
		this.merChinese = merChinese;
	}

	@Column(name = "MER_HERB", length = 32)
	public String getMerHerb() {
		return this.merHerb;
	}

	public void setMerHerb(String merHerb) {
		this.merHerb = merHerb;
	}

	@Column(name = "MER_RADIOCOST", length = 32)
	public String getMerRadiocost() {
		return this.merRadiocost;
	}

	public void setMerRadiocost(String merRadiocost) {
		this.merRadiocost = merRadiocost;
	}

	@Column(name = "MER_TESTCOST", length = 32)
	public String getMerTestcost() {
		return this.merTestcost;
	}

	public void setMerTestcost(String merTestcost) {
		this.merTestcost = merTestcost;
	}

	@Column(name = "MER_OXYCOST", length = 32)
	public String getMerOxycost() {
		return this.merOxycost;
	}

	public void setMerOxycost(String merOxycost) {
		this.merOxycost = merOxycost;
	}

	@Column(name = "MER_BLOODCOST", length = 32)
	public String getMerBloodcost() {
		return this.merBloodcost;
	}

	public void setMerBloodcost(String merBloodcost) {
		this.merBloodcost = merBloodcost;
	}

	@Column(name = "MER_DIAGCOST", length = 32)
	public String getMerDiagcost() {
		return this.merDiagcost;
	}

	public void setMerDiagcost(String merDiagcost) {
		this.merDiagcost = merDiagcost;
	}

	@Column(name = "MER_OPERATCOST", length = 32)
	public String getMerOperatcost() {
		return this.merOperatcost;
	}

	public void setMerOperatcost(String merOperatcost) {
		this.merOperatcost = merOperatcost;
	}

	@Column(name = "MER_DELIVERYCOST", length = 32)
	public String getMerDeliverycost() {
		return this.merDeliverycost;
	}

	public void setMerDeliverycost(String merDeliverycost) {
		this.merDeliverycost = merDeliverycost;
	}

	@Column(name = "MER_CHECKCOST", length = 32)
	public String getMerCheckcost() {
		return this.merCheckcost;
	}

	public void setMerCheckcost(String merCheckcost) {
		this.merCheckcost = merCheckcost;
	}

	@Column(name = "MER_DRUGCOST", length = 32)
	public String getMerDrugcost() {
		return this.merDrugcost;
	}

	public void setMerDrugcost(String merDrugcost) {
		this.merDrugcost = merDrugcost;
	}

	@Column(name = "MER_INFANTCOST", length = 32)
	public String getMerInfantcost() {
		return this.merInfantcost;
	}

	public void setMerInfantcost(String merInfantcost) {
		this.merInfantcost = merInfantcost;
	}

	@Column(name = "MER_LACOST", length = 32)
	public String getMerLacost() {
		return this.merLacost;
	}

	public void setMerLacost(String merLacost) {
		this.merLacost = merLacost;
	}

	@Column(name = "MER_OTHER", length = 400)
	public String getMerOther() {
		return this.merOther;
	}

	public void setMerOther(String merOther) {
		this.merOther = merOther;
	}

	@Column(name = "MER_AUTOPSY", length = 32)
	public String getMerAutopsy() {
		return this.merAutopsy;
	}

	public void setMerAutopsy(String merAutopsy) {
		this.merAutopsy = merAutopsy;
	}

	@Column(name = "MER_SAFENUM", length = 32)
	public String getMerSafenum() {
		return this.merSafenum;
	}

	public void setMerSafenum(String merSafenum) {
		this.merSafenum = merSafenum;
	}

	@Column(name = "MER_SUCCNUM", length = 32)
	public String getMerSuccnum() {
		return this.merSuccnum;
	}

	public void setMerSuccnum(String merSuccnum) {
		this.merSuccnum = merSuccnum;
	}

}