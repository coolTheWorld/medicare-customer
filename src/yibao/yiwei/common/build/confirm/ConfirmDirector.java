package yibao.yiwei.common.build.confirm;

import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

/**
 * @interface IConfirmDirector
 * @author sunshy
 *
 */
public class ConfirmDirector implements IConfirmDirector {

	/**
	 * 数据确认建造类
	 */
    private ConfirmBuilder builder;

    public ConfirmDirector(ConfirmBuilder builder) {
        this.builder = builder;
    }

    @Override
    public ConfirmProduct saveConfirm(DataConfirm confirm, IBaseService<DataConfirm> dataConfirmService, String sql) throws IllegalAccessException {
        builder.builderConfirmClazz(confirm).isFieldsNotEmpty(confirm);
        if (!builder.getConfirmProduct().isFieldsNotEmpty()) {
            return builder.builderFlag("0").builderMes("请至少选中一条记录确认").getConfirmProduct();
        } else {
            DataConfirm dataConfirm = builder.builderDataConfirm(dataConfirmService, sql).getConfirmProduct().getDataConfirmResult();
            if (dataConfirm != null && dataConfirm.getConfirmId() != null) {
                return builder.builderFlag("0").builderMes("当天的数据已确认").getConfirmProduct();
            } else {
                return builder.saveDataConfirm(dataConfirmService, confirm).builderFlag("1").builderMes("数据确认成功").getConfirmProduct();
            }
        }

    }

    @Override
    public ConfirmProduct findConfirmByConditon(IBaseService<DataConfirm> dataConfirmService,String sql) throws BuildProcessException, IllegalAccessException {
        DataConfirm confirm = builder.builderDataConfirm( dataConfirmService,sql).getConfirmProduct().getDataConfirmResult();
        return builder.builderConfirmClazz(confirm).fieldsAdpter().builderCode("0").builderMes("成功").builderCount("1").builderData().getConfirmProduct();
    }
}
