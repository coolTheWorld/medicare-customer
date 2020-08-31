package yibao.yiwei.common.build.confirm;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ConfirmBuilder {
    protected ConfirmProduct confirmProduct = new ConfirmProduct();

    public ConfirmBuilder(HttpServletRequest request, Date startDate, Date endDate){
        buildParam(request,startDate,endDate);
    }

    private void buildParam(HttpServletRequest request, Date startDate, Date endDate){
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sf.format(startDate)+ " 00:00:00";
        String endDateStr = sf.format(endDate) + " 23:59:59";

        confirmProduct.setCustomerUser(user);
        confirmProduct.setStartDateParam(startDateStr);
        confirmProduct.setEndDateParam(endDateStr);
    }

    protected abstract ConfirmBuilder builderDataConfirm(IBaseService<DataConfirm> dataConfirmService, String sql);
    protected abstract ConfirmBuilder builderConfirmClazz(DataConfirm dataConfirm);
    protected abstract ConfirmBuilder isFieldsNotEmpty(DataConfirm dataConfirm) throws IllegalAccessException;
    protected abstract ConfirmBuilder saveDataConfirm(IBaseService<DataConfirm> dataConfirmService,DataConfirm confirm);

    protected abstract ConfirmBuilder fieldsAdpter() throws IllegalAccessException;


    protected abstract ConfirmBuilder builderMes(String mes);
    protected abstract ConfirmBuilder builderFlag(String flag);
    protected abstract ConfirmBuilder builderCode(String code);
    protected abstract ConfirmBuilder builderCount(String count);
    protected abstract ConfirmBuilder builderData() throws Exception;

    public ConfirmProduct getConfirmProduct(){
        return confirmProduct;
    }
}
