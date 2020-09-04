package yibao.yiwei.common;

/**
 * exception message
 *
 * @author sunshy
 */
public enum  ExceptionMessage {
    TABLE_NAME_IS_NULL("请先调用buildTablesByMonth或者buildTablesByWeek"),
    TOTAI_IS_ZERO(""),
    TOTALS_IS_NULL("未调用buildCountByTable方法或totals变量为null"),
    DATA_CONFIRM_RESULT_IS_NULL("dataConfirm变量为null"),
    NEXT_CHAIN_NULL_EXCEPTION("责任链终止异常,责任链下个节点为空");
    private String value;
    private ExceptionMessage(String value){
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
