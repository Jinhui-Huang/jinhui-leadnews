package com.myhd.jinhuileadnewswemedia.interceptor;

import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmUser;
import com.myhd.jinhuileadnewsutils.utils.thread.WmThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: WmTokenInterceptor
 * <br></br>
 * className: WmTokenInterceptor
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.interceptor
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 13:17
 */
public class WmTokenInterceptor implements HandlerInterceptor {

    /**
     * Description: 前置处理器 preHandle 得到header中的用户信息， 并且存入到当前线程中
     * @return boolean
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        if (userId != null) {
            /*存入到当前线程中*/
            WmUser wmUser = new WmUser();
            wmUser.setId(Integer.valueOf(userId));
            WmThreadLocalUtil.setUser(wmUser);
        }
        return true;
    }

    /**
     * Description: postHandle 后置处理器 清理线程中的数据
     * @return void
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        WmThreadLocalUtil.clear();
    }
}
