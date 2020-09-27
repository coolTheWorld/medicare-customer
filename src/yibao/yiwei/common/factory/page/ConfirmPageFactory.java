package yibao.yiwei.common.factory.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yibao.yiwei.common.facade.page.PageFacade;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据确认页面
 * @author sunshy
 *
 */
@Component("ConfirmPageFactory")
public class ConfirmPageFactory implements IPageFactory{
    @Autowired
    PageFacade pageFacade;
    @Override
    public String pageInit(HttpServletRequest request) {
        pageFacade.pageInit(request);
        return "customer/confirm/home";
    }
}
