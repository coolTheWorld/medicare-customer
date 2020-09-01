package yibao.yiwei.controller.customer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yibao.yiwei.common.TableName;
import yibao.yiwei.common.factory.record.IRecordFactory;
import yibao.yiwei.common.factory.record.RecordByMonthFactory;
import yibao.yiwei.common.factory.record.RecordByWeekFactory;
import yibao.yiwei.entity.*;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 定点上传数据查询（面向定点）
 *
 * @author sunshy
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    IBaseService baseService;

    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = Logger.getLogger(RecordController.class);

    /**
     * 跳转到查询入库记录页面
     *
     * @return
     */
    @RequestMapping("/toWareHouseItems")
    public String toWareHoseItems(HttpServletRequest request) {
        return "/customer/record/wareHouseItemsQuery";
    }

    /**
     * 跳转到查询出库记录页面
     *
     * @return
     */
    @RequestMapping("/toDeliveryItems")
    public String toDeliveryItems(HttpServletRequest request) {
        return "/customer/record/deliveryItemsQuery";
    }

    /**
     * 跳转到查询销售记录页面
     *
     * @return
     */
    @RequestMapping("/toSalesItems")
    public String toSalesItems(HttpServletRequest request) {
        return "/customer/record/salesItemsQuery";
    }

    /**
     * 跳转到查询库存记录页面
     *
     * @return
     */
    @RequestMapping("/toItemStock")
    public String toItemsStock(HttpServletRequest request) {
        return "/customer/record/itemStockQuery";
    }

    /**
     * 跳转到查询处方记录页面
     *
     * @return
     */
    @RequestMapping("/toPrescribe")
    public String toPrescribe(HttpServletRequest request) {
        return "/customer/record/prescribeQuery";
    }

    /**
     * 跳转到查询退货记录页面
     *
     * @return
     */
    @RequestMapping("/toReturnItems")
    public String toReturnItems(HttpServletRequest request) {
        return "/customer/record/returnItemsQuery";
    }

    /**
     * 跳转到查询诊断记录页面
     *
     * @return
     */
    @RequestMapping("/toClinicrecords")
    public String toClinicrecords(HttpServletRequest request) {
        return "/customer/record/clinicrecordsQuery";
    }

    /**
     * 跳转到查询入院记录页面
     *
     * @return
     */
    @RequestMapping("/toHospitalized")
    public String toHospitalized(HttpServletRequest request) {
        return "/customer/record/hospitalizedQuery";
    }

    /**
     * 跳转到查询出院记录页面
     *
     * @return
     */
    @RequestMapping("/toDischarged")
    public String toDischarged(HttpServletRequest request) {
        return "/customer/record/dischargedQuery";
    }

    /**
     * 获取入库记录
     *
     * @return
     */
    @RequestMapping("/wareHouseItemsData")
    @ResponseBody
    public Map getWareHouseItemsData(HttpServletRequest request, @RequestParam("page") String page,
                                     @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory wareHouseItemFactory = new RecordByMonthFactory<Warehouseitem>();
            String sql = "select WI_ID,CUS_ID,CUS_PARENTID,CUS_DAREWAY,WI_TYPE,DRUG_CODE,DRUG_NAME,DRUG_NUM,DRUG_PURCHASEPRICE,DRUG_BATCHNO,DRUG_MFRS,DRUG_MADEIN,DRUG_EXPDATE,DRUG_MFGDATE,DRUG_EID,WI_CODE,WI_DATETIME,WI_PRICE,DRUG_SPECIFICATION,DRUG_UNIT,DRUG_HCSCODE,WI_WHCODE,WI_WHNAME,WI_LOCATION,DRUG_BUYER,DRUG_BUYERNAME,DRUG_EXAMINER,DRUG_EXAMINERNAME,WI_ADDTIME,DRUG_COLLECTDATE from TBL_WAREHOUSEITEM t where t.cus_id = ?0 and t.wi_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_WAREHOUSEITEM where cus_id = ?0 and  wi_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = wareHouseItemFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_WAREHOUSEITEM.getValue(), baseService, countSql, sql, Warehouseitem.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取出库记录
     *
     * @return
     */
    @RequestMapping("/deliveryItemsData")
    @ResponseBody
    public Map getDeliveryItemsData(HttpServletRequest request, @RequestParam("page") String page,
                                    @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory deliveryItemFactory = new RecordByMonthFactory<Deliveryitem>();
            String sql = "select DI_ID,CUS_ID,CUS_PARENTID,DI_NO,DI_TYPE,DI_REASON,DRUG_CODE,DRUG_NAME,DRUG_NUM,DI_SPECIFICATION,DRUG_BATCHNO,DRUG_MFRS,DRUG_MADEIN,DRUG_EXPDATE,DRUG_MFGDATE,DRUG_EID,DI_HCSCODE,DI_WHCODE,DI_WHNAME,DI_LOCATION,DI_OPCODE,DI_OPNAME,DI_OPDATETIME,DI_PICKTIME,DI_ADDTIME,DI_UNIT from TBL_DELIVERYITEM t where t.cus_id = ?0 and t.di_opdatetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_DELIVERYITEM where cus_id = ?0 and  di_opdatetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = deliveryItemFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_DELIVERYITEM.getValue(), baseService, countSql, sql, Deliveryitem.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取销售记录
     *
     * @return
     */
    @RequestMapping("/salesItemsData")
    @ResponseBody
    public Map getSalesItemsData(HttpServletRequest request, @RequestParam("page") String page,
                                 @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory salesItemFactory = new RecordByMonthFactory<Salesitem>();
            String sql = "select SI_ID,CUS_ID,CUS_PARENTID,SO_NO,DRUG_CODE,DRUG_NUM,DRUG_SALESPRICE,DRUG_BATCHNO,DRUG_MFRS,DRUG_MADEIN,DRUG_MFGDATE,DRUG_EXPDATE,DRUG_EID,SO_CREATEDATETIME,SO_DATETIME,SO_PAYTYPE,DRUG_PICKTIME,SO_SALESPSNNAME,CUS_DAREWAY,SI_PTSSEX,SI_PTSAGE,SI_PTSIDCARD,SI_PTSHEALTHCARD,DRUG_NAME,DRUG_SPECIFICATION,SI_SETTLEMENTNAME,SI_STATUS,SI_OPCODE,SI_OPNAME,SI_UNIT,SI_TRACECODE from TBL_SALESITEM t where t.cus_id = ?0 and t.so_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_SALESITEM where cus_id = ?0 and  so_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = salesItemFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_SALESITEM.getValue(), baseService, countSql, sql, Salesitem.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取库存记录
     *
     * @return
     */
    @RequestMapping("/itemStockData")
    @ResponseBody
    public Map getItemsStockData(HttpServletRequest request, @RequestParam("page") String page,
                                 @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory itemRecordFactory = new RecordByWeekFactory<Itemstock>();
            String sql = "SELECT IS_ID,CUS_ID,CUS_PID,CUS_DAREWAY,ITEM_CODE,ITEM_NAME,ITEM_NUM,ITEM_UNIT,ITEM_SPECIFICATION,ITEM_BATCHNO,ITEM_PERMISSION,ITEM_MFGDATE,ITEM_EXPDATE,ITEM_MFRS,ITEM_MAKEIN,ITEM_HCSCODE,ITEM_WHCODE,ITEM_WHNAME,ITEM_LOCATION,ITEM_PICKTIME,ITEM_ADDTIME,ITEM_CHECKNUM,ROW_NUM FROM  (select  IS_ID,CUS_ID,CUS_PID,CUS_DAREWAY,ITEM_CODE,ITEM_NAME,ITEM_NUM,ITEM_UNIT,ITEM_SPECIFICATION,ITEM_BATCHNO,ITEM_PERMISSION,ITEM_MFGDATE,ITEM_EXPDATE,ITEM_MFRS,ITEM_MAKEIN,ITEM_HCSCODE,ITEM_WHCODE,ITEM_WHNAME,ITEM_LOCATION,ITEM_PICKTIME,ITEM_ADDTIME,ITEM_CHECKNUM,ROW_NUMBER() over (PARTITION BY ITEM_CODE,ITEM_BATCHNO ORDER BY ITEM_PICKTIME desc) ROW_NUM from TBL_ITEMSTOCK WHERE CUS_ID = ?0 AND ITEM_PICKTIME BETWEEN to_date(?1, 'yyyy-mm-dd hh24:mi:ss') and to_date(?2, 'yyyy-mm-dd hh24:mi:ss') ) t where  t.ROW_NUM = 1";
            String countSql = "SELECT COUNT(CUS_ID) FROM  (select  IS_ID,CUS_ID,CUS_PID,CUS_DAREWAY,ITEM_CODE,ITEM_NAME,ITEM_NUM,ITEM_UNIT,ITEM_SPECIFICATION,ITEM_BATCHNO,ITEM_PERMISSION,ITEM_MFGDATE,ITEM_EXPDATE,ITEM_MFRS,ITEM_MAKEIN,ITEM_HCSCODE,ITEM_WHCODE,ITEM_WHNAME,ITEM_LOCATION,ITEM_PICKTIME,ITEM_ADDTIME,ITEM_CHECKNUM,         ROW_NUMBER() over (PARTITION BY ITEM_CODE,ITEM_BATCHNO ORDER BY ITEM_PICKTIME desc) ROW_NUM from TBL_ITEMSTOCK WHERE CUS_ID = ?0 AND ITEM_PICKTIME BETWEEN to_date(?1, 'yyyy-mm-dd hh24:mi:ss') and to_date(?2, 'yyyy-mm-dd hh24:mi:ss')     ) t where  t.ROW_NUM = 1";
            resultMap = itemRecordFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_ITEMSTOCK.getValue(), baseService, countSql, sql, Itemstock.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取处方记录
     *
     * @return
     */
    @RequestMapping("/prescribeData")
    @ResponseBody
    public Map getPrescribeData(HttpServletRequest request, @RequestParam("page") String page,
                                @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory prescribeFactory = new RecordByMonthFactory<Prescribe>();
            String sql = "select RP_ID,CUS_ID,CUS_PID,CUS_DAREWAY,RP_NO,RP_DEPTNO,RP_DEPTNAME,RP_PTSNAME,RP_PTSSEX,RP_PTSAGE,RP_PTSHEALTHCARD,RP_PTSIDCARD,RP_PTSBIRTHDAY,RP_ITEMCODE,RP_ITEMNAME,RP_ITEMPRICE,RP_ITEMNUM,RP_ITEMSPECIFICATION,RP_ITEMDOSAGEFORM,RP_ITEMMFRS,RP_ITEMMAKEIN,RP_ITEMBATCHNO,RP_WHCODE,RP_WHNAME,RP_LOCATION,RP_DRUGFREQ,RP_DRUGROUTE,RP_DRUGTIME,RP_DRUGAMOUNT,RP_ANNEX,RP_DRCODE,RP_DRNAME,RP_DRTIME,RP_AUDITCODE,RP_AUDITNAME,RP_AUDITTIME,RP_CHECKCODE,RP_CHECKNAME,RP_CHECKTIME,RP_PICKTIME,RP_ADDTIME from TBL_PRESCRIBE t where t.cus_id = ?0 and t.rp_drtime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_PRESCRIBE where cus_id = ?0 and  rp_drtime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = prescribeFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_PRESCRIBE.getValue(), baseService, countSql, sql, Prescribe.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取门诊记录
     *
     * @return
     */
    @RequestMapping("/clinicrecordsData")
    @ResponseBody
    public Map getClinicrecordsData(HttpServletRequest request, @RequestParam("page") String page,
                                    @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory clinicrecordFactory = new RecordByMonthFactory<Clinicrecords>();
            String sql = "select DIAG_ID,CUS_ID,CUS_PID,CUS_DAREWAY,DIAG_NO,DIAG_DATETIME,DIAG_DEPTCODE,DIAG_DEPTNAME,DIAG_DOCTOR,DIAG_DOCTORNAME,DIAG_DOCTORTIME,SI_PTSIDCARD,SI_PTSHEALTHCARD,SI_PTSNAME,SI_PTSSEX,SI_PTSAGE,DIAG_ICDCODE,DIAG_ICDNAME,REG_TYPE,DIAG_TYPE,SEC_TYPE,DIAG_PAYTYPE,DIAG_PICKTIME,DIAG_ADDTIME from TBL_CLINICRECORDS t where t.cus_id = ?0 and t.diag_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_CLINICRECORDS where cus_id = ?0 and  diag_datetime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = clinicrecordFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_CLINICRECORDS.getValue(), baseService, countSql, sql, Clinicrecords.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取住院记录
     *
     * @return
     */
    @RequestMapping("/hospitalizedData")
    @ResponseBody
    public Map getHospitalizedData(HttpServletRequest request, @RequestParam("page") String page,
                                   @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory hospitalizedFactory = new RecordByMonthFactory<Hospitalized>();
            String sql = "select  HOSP_ID,CUS_ID,CUS_DAREWAY,DOCTOR_TYPE,CASE_NO,HOSP_NO,DIAG_TYPE,DIAG_DEPT,DIAG_DEPTNAME,DIAG_DOCTOR,DIAG_DOCTORNAME,DIAG_DATETIME,ICD_CODE,ICD_NAME,HOSP_TYPE,HOSP_WAY,HOSP_DEPT,HOSP_DEPTNAME,HOSP_DOCTOR,HOSP_DOCTORNAME,HOSP_AREAS,HOSP_WARDNO,HOSP_BEDNO,HOSP_INTIME,SI_PTSNAME,SI_PTSSEX,SI_PTSAGE,SI_PTSIDCARD,SI_PTSHEALTHCARD,HOSP_PICKTIME,HOSP_ADDTIME from TBL_HOSPITALIZED t where t.cus_id = ?0 and t.hosp_intime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_HOSPITALIZED where cus_id = ?0 and  hosp_intime  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = hospitalizedFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_HOSPITALIZED.getValue(), baseService, countSql, sql, Hospitalized.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取出院记录
     *
     * @return
     */
    @RequestMapping("/dischargedData")
    @ResponseBody
    public Map getDischargedData(HttpServletRequest request, @RequestParam("page") String page,
                                 @RequestParam("rows") String rows, @RequestParam("date") Date date) {
        Map<String, Object> resultMap = null;
        try {
            IRecordFactory dischargedFactory = new RecordByMonthFactory<Discharged>();
            String sql = "select DC_ID,CUS_ID,CUS_DAREWAY,CASE_NO,HOSP_NO,SI_PTSIDCARD,SI_PTSHEALTHCARD,SI_PTSNAME,SI_PTSSEX,SI_PTSAGE,DC_INDATE,DC_OUTDATE,DC_HOSPDAYS,DC_OUTYPE,DC_HOSPTIMES,DC_DIAGCODE,DC_DIAGNAME,DC_DOCTORCODE,DC_DOCTORNAME,DC_PICKIME,DC_ADDTIME from TBL_DISCHARGED t where t.cus_id = ?0 and t.dc_outdate  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            String countSql = "select count(cus_id) from TBL_DISCHARGED where cus_id = ?0 and  dc_outdate  between to_date( ?1,'yyyy-mm-dd hh24:mi:ss') and to_date( ?2 ,'yyyy-mm-dd hh24:mi:ss')";
            resultMap = dischargedFactory.getRecordForceByPart(request, date, date, page, rows, TableName.TBL_DISCHARGED.getValue(), baseService, countSql, sql, Discharged.class).result();
        } catch (BuildProcessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }
}
