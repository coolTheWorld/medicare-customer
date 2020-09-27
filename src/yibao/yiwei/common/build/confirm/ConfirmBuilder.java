package yibao.yiwei.common.build.confirm;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据确认建造者抽象接口
 * @author sunshy
 *
 */
public abstract class ConfirmBuilder {
    protected ConfirmProduct confirmProduct = new ConfirmProduct();

    public ConfirmBuilder(HttpServletRequest request, Date startDate, Date endDate){
        buildParam(request,startDate,endDate);
    }

    /**
     * 构造建造类所需参数
     * @param request
     * @param startDate
     * @param endDate
     */
    private void buildParam(HttpServletRequest request, Date startDate, Date endDate){
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sf.format(startDate)+ " 00:00:00";
        String endDateStr = sf.format(endDate) + " 23:59:59";

        confirmProduct.setCustomerUser(user);
        confirmProduct.setStartDateParam(startDateStr);
        confirmProduct.setEndDateParam(endDateStr);
    }

    /**
     * 建造 数据确认查询结果
     * @param dataConfirmService
     * @param sql
     * @return
     */
    protected abstract ConfirmBuilder builderDataConfirm(IBaseService<DataConfirm> dataConfirmService, String sql);
    
    /**
     * 获取DataConfirm实体类 .class对象
     * @param dataConfirm
     * @return
     */
    protected abstract ConfirmBuilder builderConfirmClazz(DataConfirm dataConfirm);
    
    /**
     * DataConfirm实体内的属性是否为空
     * @param dataConfirm
     * @return
     * @throws IllegalAccessException
     */
    protected abstract ConfirmBuilder isFieldsNotEmpty(DataConfirm dataConfirm) throws IllegalAccessException;
    
    /**
     * 保存数据确认信息
     * @param dataConfirmService
     * @param confirm
     * @return
     */
    protected abstract ConfirmBuilder saveDataConfirm(IBaseService<DataConfirm> dataConfirmService,DataConfirm confirm);

    /**
     * 对DataConfirm的属性初始化
     * @return
     * @throws IllegalAccessException
     */
    protected abstract ConfirmBuilder fieldsAdpter() throws IllegalAccessException;


    protected abstract ConfirmBuilder builderMes(String mes);
    protected abstract ConfirmBuilder builderFlag(String flag);
    protected abstract ConfirmBuilder builderCode(String code);
    protected abstract ConfirmBuilder builderCount(String count);
    
    /**
     * 返回查询的结果集
     * @return
     * @throws BuildProcessException
     */
    protected abstract ConfirmBuilder builderData() throws BuildProcessException;

    public ConfirmProduct getConfirmProduct(){
        return confirmProduct;
    }
}
