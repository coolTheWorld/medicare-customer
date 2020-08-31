package yibao.yiwei.common.factory.page;

import javax.servlet.http.HttpServletRequest;

public interface IPageFactory {
    String pageInit(HttpServletRequest request);
}
