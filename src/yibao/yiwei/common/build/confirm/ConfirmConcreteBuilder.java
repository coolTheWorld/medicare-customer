package yibao.yiwei.common.build.confirm;

import yibao.yiwei.common.ConfirmAnnotationProcessor;
import yibao.yiwei.common.ExceptionMessage;
import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfirmConcreteBuilder extends ConfirmBuilder {
    public ConfirmConcreteBuilder(HttpServletRequest request, Date startDate, Date endDate) {
        super(request, startDate, endDate);
    }

    @Override
    protected ConfirmBuilder builderDataConfirm(IBaseService<DataConfirm> dataConfirmService, String sql) {
        DataConfirm confirmResult = dataConfirmService.findUniqueSql(sql, DataConfirm.class, confirmProduct.getCustomerUser().getCusId(),
                confirmProduct.getStartDateParam(), confirmProduct.getEndDateParam());
        confirmProduct.setDataConfirmResult(confirmResult);
        return this;
    }

    @Override
    protected ConfirmBuilder builderConfirmClazz(DataConfirm dataConfirm) {
        if (dataConfirm == null) {
            dataConfirm = new DataConfirm();
        }
        confirmProduct.setConfirmResultClazz(dataConfirm.getClass());
        return this;
    }

    @Override
    protected ConfirmBuilder isFieldsNotEmpty(DataConfirm dataConfirm) throws IllegalAccessException {
        confirmProduct.setFieldsNotEmpty(ConfirmAnnotationProcessor.emptyOrNot(dataConfirm, confirmProduct.getConfirmResultClazz()));
        return this;
    }

    @Override
    protected ConfirmBuilder saveDataConfirm(IBaseService<DataConfirm> dataConfirmService, DataConfirm confirm) {
        confirm.setCusId(confirmProduct.getCustomerUser().getCusId());
        confirm.setCreateTime(new Date());
        dataConfirmService.save(confirm);
        return this;
    }

    @Override
    protected ConfirmBuilder fieldsAdpter() throws IllegalAccessException {
        DataConfirm confirmResult = confirmProduct.getDataConfirmResult();
        if (confirmResult == null) {
            confirmResult = new DataConfirm();
        }
        ConfirmAnnotationProcessor.init(confirmResult);
        confirmProduct.setDataConfirmResult(confirmResult);
        return this;
    }

    @Override
    protected ConfirmBuilder builderMes(String mes) {
        confirmProduct.setMes(mes);
        return this;
    }

    @Override
    protected ConfirmBuilder builderFlag(String flag) {
        confirmProduct.setFlag(flag);
        return this;
    }

    @Override
    protected ConfirmBuilder builderCode(String code) {
        confirmProduct.setCode(code);
        return this;
    }

    @Override
    protected ConfirmBuilder builderCount(String count) {
        confirmProduct.setCount(count);
        return this;
    }

    @Override
    protected ConfirmBuilder builderData() throws BuildProcessException {
        if (null == confirmProduct.getDataConfirmResult()) {
            throw new BuildProcessException(ExceptionMessage.DATA_CONFIRM_RESULT_IS_NULL.getValue());
        }
        List<DataConfirm> data = new ArrayList<DataConfirm>();
        data.add(confirmProduct.getDataConfirmResult());
        confirmProduct.setData(data);
        return this;
    }
}
