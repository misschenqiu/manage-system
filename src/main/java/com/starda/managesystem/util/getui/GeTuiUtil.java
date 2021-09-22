package com.starda.managesystem.util.getui;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.starda.managesystem.common.LocalCache;
import com.starda.managesystem.constant.Constant;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util.getui
 * @ClassName: GeTuiUtil
 * @Author: chenqiu
 * @Description: 个推
 * @Date: 2021/9/21 12:21
 * @Version: 1.0
 */
@Configuration
public class GeTuiUtil {

    private static final String BASEURL = "https://restapi.getui.com/v2/" + Constant.AppNotice.APP_ID;

    private static String TOKEN = "863c322f1aa51abb7d40b843c254777272af56ce75d20af60fc19dbaad4355ca";

    private static final String TOKEN_KEY = "getuiToken";

    /**
     * 创建token
     */
    public static String getToken(){

        long timestamp = System.currentTimeMillis();

        // 1.生成 sign
        String signString = Constant.AppNotice.APP_KEY + timestamp + Constant.AppNotice.MASTER_SECRET;
        String sign = Sha256.sha256(signString);

        // 2.参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("sign", sign);
        paramMap.put("timestamp", timestamp);
        paramMap.put("appkey", Constant.AppNotice.APP_KEY);

        String result = HttpRequest.post(BASEURL + "/auth")
                //头信息，多个头信息多次调用此方法即可
                .header(Header.CONTENT_TYPE, "application/json;charset=utf-8")
                //表单内容
                .body(JSONObject.toJSONString(paramMap))
                //超时，毫秒
                .timeout(20000)
                .execute().body();
        return result;
    }

    /**
     * 组建发送信息
     * @param title
     * @param content
     * @param phoneSeres
     * @return
     * @throws Exception
     */
   public static String messageInfoGetui(String title, String content, List<String> phoneSeres) throws Exception{
       HashMap<String, Object> paramMap = new HashMap<>();
       paramMap.put("request_id", IdUtil.simpleUUID());

       HashMap<String, Object> audience = new HashMap<>();
       audience.put("cid", phoneSeres);
       paramMap.put("audience", audience);

       HashMap<String, Object> pushMessage = new HashMap<>();
       HashMap<String, Object> notification = new HashMap<>();
       notification.put("title", title);
       notification.put("body", content);
       notification.put("click_type", "startapp");
       notification.put("channel_level", 4);
       pushMessage.put("notification", notification);

       paramMap.put("push_message", pushMessage);

       // 获取授权token
       String token = LocalCache.get(TOKEN_KEY, false) + "";
        if(sendOut(token, paramMap)){
            return Constant.ResultCodeMessage.SUCCESS;
        }
       String newToken = getToken();
       GeTuiResult geTuiResult = JSONObject.parseObject(newToken, GeTuiResult.class);
       if (sendOut(geTuiResult.getData().getToken(), paramMap)){
           // 保存token
            if(StrUtil.isNotBlank(geTuiResult.getData().getExpire_time())){
                LocalCache.put(TOKEN_KEY, geTuiResult.getData().getToken(), Long.valueOf(geTuiResult.getData().getExpire_time()) / 1000 / 60);
            }else {
                LocalCache.put(TOKEN_KEY, geTuiResult.getData().getToken());
            }
           return Constant.ResultCodeMessage.SUCCESS;
       }
       return Constant.ResultCodeMessage.ERROR_STRING;
   }

    /**
     * 发送个推
     * @param token
     * @param paramMap
     * @return
     * @throws Exception
     */
   private static boolean sendOut(String token, Map<String, Object> paramMap) throws Exception{
       String result = HttpRequest.post(BASEURL + "/push/single/cid")
               //头信息，多个头信息多次调用此方法即可
               .header(Header.CONTENT_TYPE, "application/json;charset=utf-8")
               .header("token", token)
               //表单内容
               .body(JSONObject.toJSONString(paramMap))
               //超时，毫秒
               .timeout(20000)
               .execute().body();

       return result.contains(Constant.ResultCodeMessage.SUCCESS);
   }

}
