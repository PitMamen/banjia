package com.max.jacentsao.banjia.module;

import java.util.List;

/**
 * Created by JacenTsao on 2016/1/15.
 */
public class HomeTodayNew {
    private List<URLEntity> urlEntities;

    public List<URLEntity> getUrlEntities() {
        return urlEntities;
    }

    public void setUrlEntities(List<URLEntity> urlEntities) {
        this.urlEntities = urlEntities;
    }

    public static class URLEntity {
        /**
         * AId : 1
         * Name : 今日更新
         * UrlAddress : intent:#Intent;launchFlags=0x4000000;component=com.banjiatemai/.product_list;i.daynews=1;end
         * UrlClass : 2
         * imgUrl : http://img.banjia.xdzhe.com/upload/index/201512231634469772.png
         */

        private int AId;
        private String Name;
        private String UrlAddress;
        private int UrlClass;
        private String imgUrl;

        public void setAId(int AId) {
            this.AId = AId;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setUrlAddress(String UrlAddress) {
            this.UrlAddress = UrlAddress;
        }

        public void setUrlClass(int UrlClass) {
            this.UrlClass = UrlClass;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getAId() {
            return AId;
        }

        public String getName() {
            return Name;
        }

        public String getUrlAddress() {
            return UrlAddress;
        }

        public int getUrlClass() {
            return UrlClass;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
}
