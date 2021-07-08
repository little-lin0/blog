package xyz.kit00.blog.content;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import org.json.JSONObject;
import org.junit.Test;

/**
 * 百度智能云内容审核
 */
public class Sample{
    //设置APPID/AK/SK
    public static final String APP_ID = "24225422";
    public static final String API_KEY = "OjA5f2FPIE7QSZ3mpnm2rm9H";
    public static final String SECRET_KEY = "Rv0diB7gDQm7wMc46SvtjtOnzqYWNXEN";
    @Test
    public void test(){
        System.out.println(checkText("狗东西"));
    }

    public static Boolean checkText(String ...text) {
        // 初始化一个AipContentCensor
        AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);

        for(String s:text){
            JSONObject res = client.textCensorUserDefined(s);
            if(res.get("conclusionType").toString().equals("2")){
                return false;
            }
        }


        return true;
    }
}