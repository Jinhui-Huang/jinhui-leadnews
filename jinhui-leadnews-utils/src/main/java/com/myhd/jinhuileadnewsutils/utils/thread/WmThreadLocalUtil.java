package com.myhd.jinhuileadnewsutils.utils.thread;

import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmUser;

/**
 * Description: WmThreadLocalUtil
 * <br></br>
 * className: WmThreadLocalUtil
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsutils.utils.thread
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 13:21
 */
public class WmThreadLocalUtil {

    private WmThreadLocalUtil() {
    }

    private static final ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    /*存入线程中*/
    public static void setUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    /*获取用户*/
    public static WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    /*清理用户*/
    public static void clear() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
