package com.example.administrator.volleyandjson.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class Image {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-03-06 14:11","title":"美女泷泽萝拉性感壁纸图片","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_7b09f0aa22aff3b6cfbad8ceb9abecbb-760x500.jpg","url":"http://m.xxxiao.com/2099"},{"ctime":"2016-03-06 14:11","title":"王馨瑶性感写真壁纸","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_6bb61e3b7bce0931da574d19d1d82c884-760x500.jpg","url":"http://m.xxxiao.com/308"},{"ctime":"2016-03-06 14:11","title":"推女神 制服百变秀 于姬Una","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_c553709f472ec054ad91d637e2ba7857-760x500.jpg","url":"http://m.xxxiao.com/1701"},{"ctime":"2016-03-06 14:11","title":"韩国超级车模许允美 天使脸蛋魔鬼身材","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_60062ba95a4dd80a711e82cdf57dc5ab-760x500.jpg","url":"http://m.xxxiao.com/791"},{"ctime":"2016-03-06 14:11","title":"[TGOD推女神] 泳池美人虞 75F乳神于姬Una私房泳装","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_3e6ffd8abf2d3fbd1040b02edb6bcb66-760x500.jpg","url":"http://m.xxxiao.com/370"},{"ctime":"2016-03-06 14:11","title":"长发学生妹楚楚动人校服写真集","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150725/8-150H51550151B.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51905.html"},{"ctime":"2016-03-06 14:11","title":"嫩模郭南汐变身清纯美女秘书","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150727/8-150HFZ92R48.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/50564.html"},{"ctime":"2016-03-06 14:11","title":"混血美女粉嫩内衣妩媚私房照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150727/8-150HGH12Y93.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51211.html"},{"ctime":"2016-03-06 14:11","title":"美女米妮mini露乳沟展性感可爱双面魅力","description":"美女图片","picUrl":"http://t1.27270.com/uploads/tu/201507/379/slt.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/122759.html"},{"ctime":"2016-03-06 14:11","title":"美貌少妇床上冷艳魅惑人心靓照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150728/8-150HQ51225431.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49790.html"}]
     */

    private int code;
    private String msg;

    @Override
    public String toString() {
        return "Image{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }

    /**
     * ctime : 2016-03-06 14:11
     * title : 美女泷泽萝拉性感壁纸图片
     * description : 美女图片
     * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_7b09f0aa22aff3b6cfbad8ceb9abecbb-760x500.jpg
     * url : http://m.xxxiao.com/2099
     */

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "ctime='" + ctime + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
