package com.ruomm.springcloud.paymentclient.utils;

import com.ruomm.javax.corex.StringUtils;
import com.ruomm.javax.encryptx.DigestUtil;
import com.ruomm.springcloud.config.CommonConfig;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/2/1 0:11
 */
public class WebUtils {
    public static String passwordByClear(String clearText,String slatBe){
        String passwordSlatBe = StringUtils.isEmpty(slatBe)? CommonConfig.PASSWORD_SLAT_BE:slatBe;
        String pwdByFe = DigestUtil.encodingMD5(clearText+CommonConfig.PASSWORD_SLAT_FE);
        String pwdByBe = DigestUtil.encodingMD5(pwdByFe+passwordSlatBe);
        return pwdByBe;
    }
    public static String passwordByFE(String pwdByFe,String slatBe){
        String passwordSlatBe = StringUtils.isEmpty(slatBe)? CommonConfig.PASSWORD_SLAT_BE:slatBe;
        String pwdByBe = DigestUtil.encodingMD5(pwdByFe+passwordSlatBe);
        return pwdByBe;
    }

    public static String parseUserStatus(Integer userStatus){
        if (null == userStatus) {
            return "未知";
        }
        int tmpStatus = userStatus.intValue();
        if (tmpStatus == 0){
            return "等待审核";
        } else if (tmpStatus == 1){
            return "正常";
        }  else if (tmpStatus == 2){
            return "密码过期";
        } else if (tmpStatus == 3){
            return "登录限制";
        } else if (tmpStatus == 4){
            return "冻结";
        } else if (tmpStatus == 9){
            return "停用";
        } else {
            return "未知";
        }
    }
    public static String parseMsgType(String msgType){
        if (StringUtils.isEmpty(msgType)) {
            return "手机号";
        } else if (StringUtils.isEquals(msgType,"mobile")){
            return "手机号";
        } else if (StringUtils.isEquals(msgType,"email")){
            return "邮箱";
        } else if (StringUtils.isEquals(msgType,"weixin")){
            return "微信号";
        } else if (StringUtils.isEquals(msgType,"qq")){
            return "QQ号";
        } else {
            return "手机号";
        }
    }
}
