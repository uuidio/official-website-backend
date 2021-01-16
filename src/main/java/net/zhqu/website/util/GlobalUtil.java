package net.zhqu.website.util;

import net.zhqu.framework.exception.CURDException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By yong On 2018/6/21
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Component
public class GlobalUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.equals("0:0:0:0:0:0:0:1") || ip.equals("0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static Date addHour(Date date, int hour) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR, hour);// num为增加的天数，可以改变的
        return ca.getTime();
    }

    public static Date addDay(Date date, int day) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, day);// num为增加的天数，可以改变的
        return ca.getTime();
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date strToDate(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static boolean isMobile(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void assertBlank(Object object, String msg) throws CURDException {
        if (object == null) {
            throw new CURDException(msg);
        }
        if (object instanceof String) {
            if (StringUtils.isBlank((String) object)) {
                throw new CURDException(msg);
            }
        } else if (object instanceof Collection) {
            if (CollectionUtils.isEmpty((Collection) object)) {
                throw new CURDException(msg);
            }
        } else {
            if (org.springframework.util.StringUtils.isEmpty(object)) {
                throw new CURDException(msg);
            }
        }
    }

    /**
     * 获取当前请求session
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 获取当前请求session
     *
     * @return
     */
    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();

    }

    public static long subTime(Date startDate, Date endDate) {
        return endDate.getTime() - startDate.getTime();
    }

    public static String getAppVersion() {
        return getAppVersion(getHttpServletRequest());
    }

    public static String getAppVersion(HttpServletRequest request) {
        String app = request.getHeader("App-Version");
        return app;
    }

    public static int compareVersion(HttpServletRequest request, String version2) {
        String version1 = getAppVersion(request);
        if (StringUtils.isBlank(version1)) {
            return -1;
        }
        return compareVersion(version1, version2);
    }

    public static int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public static String getJwtToken(String tokenHeaderName) {
        return getHttpServletRequest().getHeader(tokenHeaderName);
    }

    public static String getApp() {
        String app = getHttpServletRequest().getHeader("App");
        return app;
    }
    public static String getApp(HttpServletRequest request) {
        String app = request.getHeader("App");
        return app;
    }

}