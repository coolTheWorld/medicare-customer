package yibao.yiwei.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public interface ICouWarehouseitemManager {


	/**
	 * 保存 药品入库统计信息
	 * 
	 * @param cusId 客户id
	 * @param totalDrugkind 按药品编码分组统计当天上传 药品种类
	 * @param upCollectdate 文件解析日期
	 */
	public void saveCouWarehouseitem(String cusId, int totalDrugkind, Date upCollectdate);

	/** 一天可能统计多次(取最大值)
	 * @param cusIds 客户id字符串
	 * @param startDate 要循环相减的起始日期
	 * @param count 循环的天数
	 * @return 药品入库每日上传药品种类统计数集合
	 * @throws ParseException
	 */
	public List getCWIKindTotals(List cusIds, int count, String startDate) throws ParseException;

}
