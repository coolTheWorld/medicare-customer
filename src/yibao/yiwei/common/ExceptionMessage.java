package yibao.yiwei.common;

/**
 * @author sunshy
 * exception message
 */
public enum  ExceptionMessage {
    TABLE_NAME_IS_NULL("请先调用buildTablesByMonth或者buildTablesByWeek"),
    TOTAI_IS_ZERO(""),
    TOTALS_IS_NULL("未调用buildCountByTable方法或totals变量为null");
    private String value;
    private ExceptionMessage(String value){
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}