package com.max.jacentsao.banjia.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/13.
 */
public class Banner implements Serializable{


    /**
     * cId : 21
     * cName : 冬日新款
     * cStatus : 1
     * imgUrl : http://img.banjia.xdzhe.com/upload/ad/201512221247139204.jpg
     */

    private List<AdListEntity> adList;

    public void setAdList(List<AdListEntity> adList) {
        this.adList = adList;
    }

    public List<AdListEntity> getAdList() {
        return adList;
    }

    public static class AdListEntity {
        private int cId;
        private String cName;
        private int cStatus;
        private String imgUrl;

        public void setCId(int cId) {
            this.cId = cId;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public void setCStatus(int cStatus) {
            this.cStatus = cStatus;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getCId() {
            return cId;
        }

        public String getCName() {
            return cName;
        }

        public int getCStatus() {
            return cStatus;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
}






