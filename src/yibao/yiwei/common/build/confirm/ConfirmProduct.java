package yibao.yiwei.common.build.confirm;


import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.entity.system.CustomerUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmProduct {
    protected ConfirmProduct (){}
    private String startDateParam;// 日期拼接参数
    private String endDateParam;// 日期拼接参数
    private CustomerUser customerUser;// 获取user

    private DataConfirm dataConfirmResult;// DataConfirm实体
    private Class<?> confirmResultClazz;// DataConfirm反射对象
    private boolean isFieldsNotEmpty;

    {
        isFieldsNotEmpty = false;
    }

    private String mes;
    private String flag;
    private String code;
    private String count;
    private List data;

    public boolean isFieldsNotEmpty() {
        return isFieldsNotEmpty;
    }

    public void setFieldsNotEmpty(boolean fieldsNotEmpty) {
        isFieldsNotEmpty = fieldsNotEmpty;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public DataConfirm getDataConfirmResult() {
        return dataConfirmResult;
    }

    public void setDataConfirmResult(DataConfirm dataConfirmResult) {
        this.dataConfirmResult = dataConfirmResult;
    }

    public Class<?> getConfirmResultClazz() {
        return confirmResultClazz;
    }

    public void setConfirmResultClazz(Class<?> confirmResultClazz) {
        this.confirmResultClazz = confirmResultClazz;
    }

    public String getStartDateParam() {
        return startDateParam;
    }

    public void setStartDateParam(String startDateParam) {
        this.startDateParam = startDateParam;
    }

    public String getEndDateParam() {
        return endDateParam;
    }

    public void setEndDateParam(String endDateParam) {
        this.endDateParam = endDateParam;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    public Map<String,Object> getResult(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("mes",mes);
        resultMap.put("flag",flag);
        resultMap.put("code",code);
        resultMap.put("count",count);
        resultMap.put("data",data);
        return resultMap;
    }
}
