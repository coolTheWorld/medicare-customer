package yibao.yiwei.controller.query;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yibao.yiwei.dto.SalesqueryDto;
import yibao.yiwei.dto.StockanalyzeDto;
import yibao.yiwei.entity.Salesitem;
import yibao.yiwei.entity.system.Customer;
import yibao.yiwei.service.IBaseService;

/**
 * 销售统计查询
 * @author Administrator
 * 按照定点查询药品销售情况
 *
 */

@SuppressWarnings("all")
@Controller
public class SaleExpController {
	
	@Autowired
	private IBaseService baseService;
	
	//销售汇总数据
	@RequestMapping("/expStatistics")
    public void exportFile1(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        OutputStream os = null;
        XSSFWorkbook xWorkbook = null;
        try {
        	List<SalesqueryDto> salesList = getAllSales(request);
        	if(null == salesList){
        		return;
        	}
        	
            String fileName = "销售统计" + df.format(new Date());
            
            os = response.getOutputStream();
            response.reset();
            
            response.setCharacterEncoding("utf-8");  
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1")+".xlsx");  
            //response.setHeader("Content-disposition", "attachment;filename = " + URLEncoder.encode(fileName, "UTF-8"));
            
            //response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/octet-streem");
            
            xWorkbook = new XSSFWorkbook();
            XSSFSheet xSheet = xWorkbook.createSheet(fileName);
            
            //set Sheet页头部
            setSheetHeader1(xWorkbook, xSheet);
            
            //set Sheet页内容
            setSheetContent1(salesList, xWorkbook, xSheet);
            
            xWorkbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            if (null != xWorkbook) {
                try {
                    xWorkbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
    /**
     * set Sheet页头部
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetHeader1(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
        xSheet.setColumnWidth(0, 20 * 256);
        xSheet.setColumnWidth(1, 40 * 256);
        xSheet.setColumnWidth(2, 10 * 256);
        xSheet.setColumnWidth(3, 10 * 256);
        xSheet.setColumnWidth(4, 30 * 256);
        xSheet.setColumnWidth(5, 40 * 256);
        xSheet.setColumnWidth(6, 40 * 256);
        
        CellStyle cs = xWorkbook.createCellStyle();
        //设置水平垂直居中
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置字体
        Font headerFont = xWorkbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontName("宋体");
        cs.setFont(headerFont);
        cs.setWrapText(true);//是否自动换行
        
        XSSFRow xRow0 = xSheet.createRow(0);
        
        //项目编码        项目名称       销售总量	  销售总额        项目规格        生产商	  产地
        
        XSSFCell xCell0 = xRow0.createCell(0);
        xCell0.setCellStyle(cs);
        xCell0.setCellValue("项目编码");
        
        XSSFCell xCell1 = xRow0.createCell(1);
        xCell1.setCellStyle(cs);
        xCell1.setCellValue("项目名称");
        
        XSSFCell xCell2 = xRow0.createCell(2);
        xCell2.setCellStyle(cs);
        xCell2.setCellValue("销售总量"); 
        
        XSSFCell xCell3 = xRow0.createCell(3);
        xCell3.setCellStyle(cs);
        xCell3.setCellValue("销售总额"); 
        
        XSSFCell xCell4 = xRow0.createCell(4);
        xCell4.setCellStyle(cs);
        xCell4.setCellValue("项目规格"); 
        
        XSSFCell xCell5 = xRow0.createCell(5);
        xCell5.setCellStyle(cs);
        xCell5.setCellValue("生产商"); 
        
        XSSFCell xCell6 = xRow0.createCell(6);
        xCell6.setCellStyle(cs);
        xCell6.setCellValue("产地"); 
        
    }

    /**
     * set Sheet页内容
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetContent1(List salesList, XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
        CellStyle cs = xWorkbook.createCellStyle();
        cs.setWrapText(true);
        
        if (null != salesList && salesList.size() > 0) {
            for (int i = 0; i < salesList.size(); i++) {
                XSSFRow xRow = xSheet.createRow(i + 1);
                SalesqueryDto saleitem = (SalesqueryDto) salesList.get(i);
                for (int j = 0; j < 7; j++) {
                    XSSFCell xCell = xRow.createCell(j);
                    xCell.setCellStyle(cs);
                    //项目编码        项目名称       销售总量	  销售总额        项目规格        生产商	     产地
                    switch (j) {
                        case 0:
                            xCell.setCellValue(saleitem.getDRUGCODE());
                            break;
                        case 1:
                            xCell.setCellValue(saleitem.getDRUGNAME());
                            break;
                        case 2:
                        	double total_quantity = ((BigDecimal) saleitem.getTOTALQUANTITY()).doubleValue();   
                            xCell.setCellValue(total_quantity);
                            break;
                        case 3:
                        	double total_amount = ((BigDecimal) saleitem.getTOTALAMOUNT()).doubleValue();
                            xCell.setCellValue(total_amount);
                            break;
                        case 4:
                            xCell.setCellValue(saleitem.getDRUGSPECIFICATION());
                            break;
                        case 5:
                            xCell.setCellValue(saleitem.getDRUGMFRS());
                            break;
                        case 6:
                            xCell.setCellValue(saleitem.getDRUGMADEIN());
                            break;             
                        default:
                            break;
                    }
                }    
            }            
        }
    }	
	
	//销售记录数据
	@RequestMapping("/expSales")
    public void exportFile2(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        OutputStream os = null;
        XSSFWorkbook xWorkbook = null;
        try {
        	List<Salesitem> salesList = getSaleslist(request);
        	if(null == salesList){
        		return;
        	}
        	
            String fileName = getCusName(request) + "_销售记录" + df.format(new Date());
            
            os = response.getOutputStream();
            response.reset();
            
            response.setCharacterEncoding("utf-8");  
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1")+".xlsx");  
            //response.setHeader("Content-disposition", "attachment;filename = " + URLEncoder.encode(fileName, "UTF-8"));
            
            //response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/octet-streem");
            
            xWorkbook = new XSSFWorkbook();
            XSSFSheet xSheet = xWorkbook.createSheet(fileName);
            
            //set Sheet页头部
            setSheetHeader2(xWorkbook, xSheet);
            
            //set Sheet页内容
            setSheetContent2(salesList, xWorkbook, xSheet);
            
            xWorkbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            if (null != xWorkbook) {
                try {
                    xWorkbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    /**
     * set Sheet页头部
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetHeader2(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
    	//0销售编号 1 项目编码  2项目名称 3销售数量 4销售价	 5单位   6规格 7患者姓名 8性别  9年龄10 结算方式 11结算状态  12操作员  13销售日期
    	xSheet.setColumnWidth(0, 20 * 256);
        xSheet.setColumnWidth(1, 10 * 256);
        xSheet.setColumnWidth(2, 40 * 256);
        xSheet.setColumnWidth(3, 10 * 256);
        xSheet.setColumnWidth(4, 10 * 256);
        xSheet.setColumnWidth(5, 10 * 256);
        xSheet.setColumnWidth(6, 10 * 256);
        xSheet.setColumnWidth(7, 12 * 256);
        xSheet.setColumnWidth(8, 6 * 256);
        xSheet.setColumnWidth(9, 6 * 256);
        xSheet.setColumnWidth(10, 10 * 256);
        xSheet.setColumnWidth(11, 10 * 256);
        xSheet.setColumnWidth(12, 10 * 256);
        xSheet.setColumnWidth(13, 20 * 256);
        
        CellStyle cs = xWorkbook.createCellStyle();
        //设置水平垂直居中
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置字体
        Font headerFont = xWorkbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontName("宋体");
        cs.setFont(headerFont);
        cs.setWrapText(true);//是否自动换行
        
        XSSFRow xRow0 = xSheet.createRow(0);
        
        //0销售编号 1 项目编码  2项目名称 3销售数量 4销售价	 5单位   6规格 7患者姓名 8性别  9年龄10 结算方式 11结算状态  12操作员  13销售日期
        
        XSSFCell xCell0 = xRow0.createCell(0);
        xCell0.setCellStyle(cs);
        xCell0.setCellValue("销售编号");
        
        XSSFCell xCell1 = xRow0.createCell(1);
        xCell1.setCellStyle(cs);
        xCell1.setCellValue("项目编码");
        
        XSSFCell xCell2 = xRow0.createCell(2);
        xCell2.setCellStyle(cs);
        xCell2.setCellValue("项目名称"); 
        
        XSSFCell xCell3 = xRow0.createCell(3);
        xCell3.setCellStyle(cs);
        xCell3.setCellValue("销售数量"); 
        
        XSSFCell xCell4 = xRow0.createCell(4);
        xCell4.setCellStyle(cs);
        xCell4.setCellValue("销售价"); 
        
        XSSFCell xCell5 = xRow0.createCell(5);
        xCell5.setCellStyle(cs);
        xCell5.setCellValue("单位"); 
        
        XSSFCell xCell6 = xRow0.createCell(6);
        xCell6.setCellStyle(cs);
        xCell6.setCellValue("规格"); 
        
        XSSFCell xCell7 = xRow0.createCell(7);
        xCell7.setCellStyle(cs);
        xCell7.setCellValue("患者姓名");
        
        XSSFCell xCell8 = xRow0.createCell(8);
        xCell8.setCellStyle(cs);
        xCell8.setCellValue("性别");
        
        XSSFCell xCell9 = xRow0.createCell(9);
        xCell9.setCellStyle(cs);
        xCell9.setCellValue("年龄");
        
        XSSFCell xCell10 = xRow0.createCell(10);
        xCell10.setCellStyle(cs);
        xCell10.setCellValue("结算方式");
        
        XSSFCell xCell11 = xRow0.createCell(11);
        xCell11.setCellStyle(cs);
        xCell11.setCellValue("结算状态");
        
        XSSFCell xCell12 = xRow0.createCell(12);
        xCell12.setCellStyle(cs);
        xCell12.setCellValue("操作员");
        
        XSSFCell xCell13 = xRow0.createCell(13);
        xCell13.setCellStyle(cs);
        xCell13.setCellValue("销售日期");
      //0销售编号 1 项目编码  2项目名称 3销售数量 4销售价	 5单位   6规格 7患者姓名 8性别  9年龄10 结算方式 11结算状态  12操作员  13销售日期
    }

    /**
     * set Sheet页内容
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetContent2(List salesList, XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
        CellStyle cs = xWorkbook.createCellStyle();
        cs.setWrapText(true);
        
        //性别编码表   1 男,2 女, 9 未知
        String[] sexarr = {"未知", "男", "女"};//性别
        //结算方式 编码表  1 医保2 银联3 现金4 转账汇款5 积分/优惠券9 其他
        String[] paytype = {"其他","医保","银联","现金","转账"}; //结算方式
        //结算状态编码表   0 未结算1 已结算2 已撤销9 其他
        String[] paystatus ={"未结算","已结算","已撤销"};  //结算状态
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != salesList && salesList.size() > 0) {
            for (int i = 0; i < salesList.size(); i++) {
                XSSFRow xRow = xSheet.createRow(i + 1);
                Salesitem saleitem = (Salesitem) salesList.get(i);
                for (int j = 0; j < 14; j++) {
                    XSSFCell xCell = xRow.createCell(j);
                    xCell.setCellStyle(cs);
                  //0销售编号 1 项目编码  2项目名称 3销售数量 4销售价	 5单位   6规格 7患者姓名 8性别  9年龄10 结算方式 11结算状态  12操作员  13销售日期
                    switch (j) {
                        case 0:
                            xCell.setCellValue(saleitem.getSoNo());
                            break;
                        case 1:
                            xCell.setCellValue(saleitem.getDrugCode());
                            break;
                        case 2:
                            xCell.setCellValue(saleitem.getDrugName());
                            break;
                        case 3:
                        	double total_amount = saleitem.getDrugNum();
                            xCell.setCellValue(total_amount);
                            break;
                        case 4:
                        	double saleprice = saleitem.getDrugSalesprice(); 
                            xCell.setCellValue(saleprice);
                            break;
                        case 5:
                            xCell.setCellValue(saleitem.getSiUnit());
                            break;
                        case 6:
                            xCell.setCellValue(saleitem.getDrugSpecification());
                            break;  
                        case 7:
                            xCell.setCellValue(saleitem.getSoSalespsnname());
                            break;
                        case 8:
                        	int sex = Integer.parseInt(saleitem.getSiPtssex());
                        	String sexstr = sexarr[sex]; 
                            xCell.setCellValue(sexstr);
                            break;
                          //7患者姓名 8性别  9年龄10 结算方式 11结算状态  12操作员  13销售日期
                        case 9:
                            xCell.setCellValue(saleitem.getSiPtsage());
                            break;
                        case 10:
                        	int type = Integer.parseInt(saleitem.getSoPaytype());
                        	String typestr = paytype[type];
                            xCell.setCellValue(typestr);
                            break;
                        case 11:
                        	int state = Integer.parseInt(saleitem.getSiStatus());
                        	String statestr = paystatus[state];
                            xCell.setCellValue(statestr);
                            break;
                        case 12:
                            xCell.setCellValue(saleitem.getSiOpname());
                            break;
                        case 13:
                        	String sodate = sdf.format(saleitem.getSoDatetime());
                            xCell.setCellValue(sodate);
                            break;
                        default:
                            break;
                    }
                }    
            }            
        }
    }	
	
	
	//销售查询数据
	private List getAllSales(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {		
			String yybm = request.getParameter("dwcode");
			String st = request.getParameter("bgdt");
			String et = request.getParameter("eddt");
			//get partition
			Date ptdt = sdf.parse(st);
			Calendar cal = Calendar.getInstance();
			cal.setTime(ptdt);
			cal.add(Calendar.MONTH, 1);		
			ptdt = cal.getTime();
			
			//String tabpart = "T_" + sdf2.format(ptdt); //T_201707
			String sql = "from Customer c where c.cusStatus != -1 and c.cusDareway=?0";
			List<Customer> cus = baseService.find(sql, yybm);
			String cusID = "0";
			if(cus.size() > 0){
				cusID = cus.get(0).getCusId();
			}
			//按销售日期查询
			String dtBegin = "and s.so_datetime >= to_date('"+st+" 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			String dtEnd   = "and s.so_datetime <= to_date('"+et+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			
			
			sql = "select count(*) from "
				+ "	(select s.drug_code drugcode, s.drug_name drugname, s.drug_specification drugspecification, sum(s.drug_num) totalquantity, "
				+ "	sum(s.drug_salesprice * s.drug_num) totalamount, "  
				+ "	s.drug_mfrs drugmfrs, s.drug_madein drugmadein from tbl_salesitem s "//partition("+tabpart+") s "
				+ "	where s.cus_id = '"+cusID+"' " + dtBegin + dtEnd
				+ "	group by s.drug_code, s.drug_name, s.drug_specification, s.drug_mfrs, s.drug_madein "
				+ ") ";
			 
			//Number total = baseService.findCountSql(sql);
			
			sql = "select drugcode, drugname, drugspecification, totalquantity,"
				+ "totalamount, drugmfrs, drugmadein, 0 remark from ("
				+ "select rownum rid, a.* from "
				+ "	(select s.drug_code drugcode, s.drug_name drugname, s.drug_specification drugspecification, sum(s.drug_num) totalquantity, "
				+ "	sum(s.drug_salesprice * s.drug_num) totalamount, "  
				+ "	s.drug_mfrs drugmfrs, s.drug_madein drugmadein from tbl_salesitem s "//partition("+tabpart+") s "
				+ "	where s.cus_id = '"+cusID+"' " + dtBegin + dtEnd
				+ "	group by s.drug_code, s.drug_name, s.drug_specification, s.drug_mfrs, s.drug_madein "
				+ "	order by totalquantity desc"
				+ ") a) ";//where rid >= " + fstRow + " and rid <= " + maxRow;
			
			List<SalesqueryDto> sals = baseService.findDtoSql(sql, SalesqueryDto.class);
			return sals;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//销售记录数据
	public List getSaleslist(HttpServletRequest request) {
		try {		
			String drug  = request.getParameter("dc");
			String cusid = request.getParameter("cus");
			String st    = request.getParameter("bgdt");
			String et    = request.getParameter("eddt");
			
			//按销售日期查询
			String dtBegin = "and s.so_datetime >= to_date('"+st+" 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			String dtEnd   = "and s.so_datetime <= to_date('"+et+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			
			
			String sql = "select si_id, cus_id, cus_parentid, so_no, drug_code, drug_num,"
				+ "drug_salesprice, drug_batchno, drug_mfrs, drug_madein, drug_mfgdate, drug_expdate,"
				+ "drug_eid, so_createdatetime, so_datetime, so_paytype, drug_picktime, so_salespsnname,"
				+ "cus_dareway, si_ptssex, si_ptsage, si_ptsidcard, si_ptshealthcard, drug_name,"
				+ "drug_specification, si_settlementname, si_status, si_opcode, si_opname, si_unit " 
				+ "from ("
				+ "	select rownum rid, s.* from tbl_salesitem s" //partition("+tabpart+") s "
				+ "	where s.cus_id = '"+cusid+"' and s.drug_code = '"+drug+"' " + dtBegin + dtEnd
				+ ") a order by so_datetime desc";
			
			List<Salesitem> sals = baseService.findEntitySql(sql, Salesitem.class);
			return sals;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//销售查询数据
	private String getCusName(HttpServletRequest request) {
		try {		
			String cusid = request.getParameter("cus");
			String sql = "from Customer c where c.cusId=?0";
			List<Customer> cus = baseService.find(sql, cusid);
			if(cus.size() > 0){
				return cus.get(0).getCusName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	//库存分析数据
	public Map getStockanalyze(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();
		try {		
			String drugcode = request.getParameter("dc");
			String cusid    = request.getParameter("cus");
			String st    = request.getParameter("bgdt");
			String et    = request.getParameter("eddt");

			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(st));
			int day1 = cal.get(Calendar.DAY_OF_YEAR);
			cal.setTime(sdf.parse(et));
	        int day2 = cal.get(Calendar.DAY_OF_YEAR);
	        int days = day2 - day1 + 1;
			
	        cal.setTime(sdf.parse(st));
	        List<Map> fields = new LinkedList<Map>();

	        for(int i=1; i<=days; i++){
	        	Map field = new HashMap();
	        	field.put("field", "day"+String.valueOf(i));
	        	field.put("title", sdf.format(cal.getTime()));
	        	field.put("width", 80);
	        	field.put("align", "center");
	        	fields.add(field);
	        	
	        	cal.add(Calendar.DAY_OF_MONTH, 1);
	        }	
	        //fields.putAll(field);
	        map.put("cols", fields);
	        
			String sql;
			Map data = new HashMap();
			List<Map> rows = new LinkedList<Map>();
			//String cusid = "53756274933F4814E053D8AA730A00F3";
			//String drugcode = "Y02334";
			//每天库存汇总  : 同一药品一天中只取最后一次上传数据，并且计算合计值（不同批次求和）
			sql = "select TO_CHAR(item_picktime, 'YYYY-MM-DD') dt, sum(item_num)||'' num from tbl_itemstock "
				+ "where cus_id = '"+cusid+"' and item_code = '"+drugcode+"' "
				+ "and item_picktime in "
				+ "	(select max(item_picktime) from "
				+ "		(select TO_CHAR(item_picktime, 'YYYY-MM-DD') item_pickdate,item_picktime from tbl_itemstock" 
				+ "		 where cus_id = '"+cusid+"' and item_code = '"+drugcode+"')"
				+ "	group by item_pickdate) "
				+ "group by item_picktime order by item_picktime";
			
			List<StockanalyzeDto> allstocks = baseService.findDtoSql(sql, StockanalyzeDto.class);
			cal.setTime(sdf.parse(st));
			LinkedHashMap stocks = new LinkedHashMap();
			stocks.put("colflag", "库存情况");
	        for(int i=1; i<=days; i++){
	        	Boolean b = false;
	        	for(int j=0; j<allstocks.size(); j++){
	        		String d1, d2;
	        		d1 = allstocks.get(j).getDT();
	        		d2 = sdf.format(cal.getTime());
	        		if(d1.equals(d2)){
	        			stocks.put("day"+String.valueOf(i), allstocks.get(j).getNUM());
	        			b = true;
	        			break;
	        		}
	        	}
	        	if (!b) {
	        		stocks.put("day"+String.valueOf(i), "--");
	        	}
	        	cal.add(Calendar.DAY_OF_MONTH, 1);
	        }	        
	        //map.put("stocks", stocks);
	        rows.add(stocks);
			
	        
			//按入库日期查询
			String dtBegin = "and wi_datetime >= to_date('"+st+" 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			String dtEnd   = "and wi_datetime <= to_date('"+et+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			//每天入库汇总
			sql = "select wi_date dt, sum(drug_num)||'' num from " 
				+ "	(select drug_num,TO_CHAR(wi_datetime, 'YYYY-MM-DD') wi_date from tbl_warehouseitem "
				+ "	 where cus_id = '"+cusid+"' and drug_code = '"+drugcode+"' "+dtBegin + dtEnd+")"
				+ "group by wi_date order by wi_date";
			
			List<StockanalyzeDto> allstockin = baseService.findDtoSql(sql, StockanalyzeDto.class);
			cal.setTime(sdf.parse(st));
			LinkedHashMap stockin = new LinkedHashMap();
			int totalin = 0;
        	for(int j=0; j<allstockin.size(); j++){
        		totalin = totalin + Integer.parseInt(allstockin.get(j).getNUM());
        	}
			stockin.put("colflag", "入库合计(<span style='color:green'>+"+String.valueOf(totalin)+"</span>)");			
			
			Boolean b = false;
			String d1, d2, num;
	        for(int i=1; i<=days; i++){
	        	b = false;
	        	d2 = sdf.format(cal.getTime());
	        	for(int j=0; j<allstockin.size(); j++){
	        		d1 = allstockin.get(j).getDT();
	        		if(d1.equals(d2)){
	        			num = allstockin.get(j).getNUM();
	        			stockin.put("day"+String.valueOf(i), "<span style='color:green'>+" + num +"</span>");
	        			b = true;
	        			break;
	        		}
	        	}
	        	if (!b) {
	        		stockin.put("day"+String.valueOf(i), "--");
	        	}
	        	cal.add(Calendar.DAY_OF_MONTH, 1);
	        }
	        //map.put("stockin", stockin);
	        rows.add(stockin);
			
	        
			//按销售日期查询
			dtBegin = "and so_datetime >= to_date('"+st+" 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			dtEnd   = "and so_datetime <= to_date('"+et+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			//每天出库汇总	        
			sql = "select so_date dt, sum(drug_num)||'' num from " 
				+ "	(select drug_num,TO_CHAR(so_datetime, 'YYYY-MM-DD') so_date from tbl_salesitem" 
				+ "	 where cus_id = '"+cusid+"' and drug_code = '"+drugcode+"' "+dtBegin + dtEnd +") "
				+ "group by so_date order by so_date";
			
			List<StockanalyzeDto> allstockout = baseService.findDtoSql(sql, StockanalyzeDto.class);
			cal.setTime(sdf.parse(st));
			LinkedHashMap stockout = new LinkedHashMap();
			int totalout = 0;
			for(int j=0; j<allstockout.size(); j++){
        		totalout = totalout + Integer.parseInt(allstockout.get(j).getNUM());
        	}
			stockout.put("colflag", "销售合计(<span style='color:red'>-"+String.valueOf(totalout)+"</span>)");
	        for(int i=1; i<=days; i++){
	        	b = false;
	        	d2 = sdf.format(cal.getTime());
	        	for(int j=0; j<allstockout.size(); j++){
	        		d1 = allstockout.get(j).getDT();
	        		num = allstockout.get(j).getNUM();
	        		if(d1.equals(d2)){
	        			stockout.put("day"+String.valueOf(i), "<span style='color:red'>-" + num+"</span>");
	        			b = true;
	        			break;
	        		}
	        	}
	        	if (!b) {
	        		stockout.put("day"+String.valueOf(i), "--");
	        	}
	        	cal.add(Calendar.DAY_OF_MONTH, 1);
	        }      
	        rows.add(stockout);
	        data.put("total", 3);
	        data.put("rows", rows);
	        map.put("data", data);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
