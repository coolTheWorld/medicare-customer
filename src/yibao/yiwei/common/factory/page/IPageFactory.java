package yibao.yiwei.common.factory.page;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面初始化接口
 * @author sunshy
 *
 */
public interface IPageFactory {
    String pageInit(HttpServletRequest request);
}
