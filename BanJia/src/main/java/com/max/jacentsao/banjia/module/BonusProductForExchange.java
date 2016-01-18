package com.max.jacentsao.banjia.module;

import java.util.List;

/**
 * Created by JacenTsao on 2016/1/14.
 */
public class BonusProductForExchange {

    /**
     * total : 3
     * rows : [{"GiveClass":1,"Id":1,"Name":"手机话费10元","OldPrice":10,"Price":0,"ProductImg":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221543572472.jpg","ProductImg1":null,"ProductImgWX":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221543572472.jpg","SaleTotal":9,"Score":1000,"Total":491},{"GiveClass":1,"Id":2,"Name":"手机话费30元","OldPrice":30,"Price":0,"ProductImg":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221544041795.jpg","ProductImg1":null,"ProductImgWX":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221544041795.jpg","SaleTotal":1,"Score":3000,"Total":299},{"GiveClass":1,"Id":3,"Name":"手机话费50元","OldPrice":50,"Price":0,"ProductImg":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221544119588.jpg","ProductImg1":null,"ProductImgWX":"http://img.banjia.xdzhe.com/upload/product/2015-09/201509221544119588.jpg","SaleTotal":0,"Score":5000,"Total":100}]
     */

    private int total;
    /**
     * GiveClass : 1
     * Id : 1
     * Name : 手机话费10元
     * OldPrice : 10
     * Price : 0
     * ProductImg : http://img.banjia.xdzhe.com/upload/product/2015-09/201509221543572472.jpg
     * ProductImg1 : null
     * ProductImgWX : http://img.banjia.xdzhe.com/upload/product/2015-09/201509221543572472.jpg
     * SaleTotal : 9
     * Score : 1000
     * Total : 491
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int GiveClass;
        private int Id;
        private String Name;
        private int OldPrice;
        private int Price;
        private String ProductImg;
        private Object ProductImg1;
        private String ProductImgWX;
        private int SaleTotal;
        private int Score;
        private int Total;

        public void setGiveClass(int GiveClass) {
            this.GiveClass = GiveClass;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setOldPrice(int OldPrice) {
            this.OldPrice = OldPrice;
        }

        public void setPrice(int Price) {
            this.Price = Price;
        }

        public void setProductImg(String ProductImg) {
            this.ProductImg = ProductImg;
        }

        public void setProductImg1(Object ProductImg1) {
            this.ProductImg1 = ProductImg1;
        }

        public void setProductImgWX(String ProductImgWX) {
            this.ProductImgWX = ProductImgWX;
        }

        public void setSaleTotal(int SaleTotal) {
            this.SaleTotal = SaleTotal;
        }

        public void setScore(int Score) {
            this.Score = Score;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public int getGiveClass() {
            return GiveClass;
        }

        public int getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }

        public int getOldPrice() {
            return OldPrice;
        }

        public int getPrice() {
            return Price;
        }

        public String getProductImg() {
            return ProductImg;
        }

        public Object getProductImg1() {
            return ProductImg1;
        }

        public String getProductImgWX() {
            return ProductImgWX;
        }

        public int getSaleTotal() {
            return SaleTotal;
        }

        public int getScore() {
            return Score;
        }

        public int getTotal() {
            return Total;
        }
    }
}
