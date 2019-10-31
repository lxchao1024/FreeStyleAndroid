package com.lxchao.app.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lxchao.app.R;

import org.json.JSONObject;

import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private String json = "{\n" +
            "\t\"state\": 0,\n" +
            "\t\"content\": {\n" +
            "\t\t\"firstRecharge_goods\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"气球（首冲礼包）\",\n" +
            "\t\t\t\t\"prize_num\": 2,\n" +
            "\t\t\t\t\"url\": \"https://storage.19gofun.com/vas/goods/2019-06-10/1560162687359.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"么么哒（首冲礼包）\",\n" +
            "\t\t\t\t\"prize_num\": 2,\n" +
            "\t\t\t\t\"url\": \"https://storage.19gofun.com/vas/goods/2019-06-06/1559809722302.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"棒棒糖（首冲礼包）\",\n" +
            "\t\t\t\t\"prize_num\": 1,\n" +
            "\t\t\t\t\"url\": \"https://storage.19gofun.com/vas/goods/2019-06-10/1560162643653.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"鼓掌（首冲礼包）\",\n" +
            "\t\t\t\t\"prize_num\": 5,\n" +
            "\t\t\t\t\"url\": \"https://storage.19gofun.com/vas/goods/2019-06-10/1560162603674.jpg\"\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        try {
            RechargeGoods rechargeGoods = new RechargeGoods();

            JSONObject jsonObject = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class RechargeGoods {
        public int state;
        public List<Goods> content;
        public String message;

        public String getMessage() {
            return message;
        }

        public int getState() {
            return state;
        }

        public List<Goods> getContent() {
            return content;
        }

        public void setContent(List<Goods> content) {
            this.content = content;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setState(int state) {
            this.state = state;
        }

        public static class Goods {
            private String name;
            private String prizeNum;
            private String url;

            public String getName() {
                return name;
            }

            public String getPrizeNum() {
                return prizeNum;
            }

            public String getUrl() {
                return url;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrizeNum(String prizeNum) {
                this.prizeNum = prizeNum;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "Goods{" +
                        "name='" + name + '\'' +
                        ", prizeNum='" + prizeNum + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }
}
