package yibao.yiwei.common;

/**
 * 定点查询上传数据 表名
 * @author sunshy
 *
 */
public enum TableName {
	TBL_WAREHOUSEITEM("TBL_WAREHOUSEITEM"),//入库
	TBL_SALESITEM("TBL_SALESITEM"),//销售
	TBL_DELIVERYITEM("TBL_DELIVERYITEM"),//出库
	TBL_CLINICRECORDS("TBL_CLINICRECORDS"),//门诊
	TBL_PRESCRIBE("TBL_PRESCRIBE"),//处方
	TBL_HOSPITALIZED("TBL_HOSPITALIZED"),//住院
	TBL_DISCHARGED("TBL_DISCHARGED"),//出院
	TBL_ITEMSTOCK("TBL_ITEMSTOCK");//库存
	
	private String value;
	
	private  TableName(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	};
	
//	public void setValue(String value) {
//		this.value = value;
//	}
}
