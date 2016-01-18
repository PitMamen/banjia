package com.max.jacentsao.banjia.module;

/**
 * Created by Gan on 2016/1/13.
 */
public class BrandSaleProductInfo
{

    /**
     * Id : 1088802
     * IsBaoYou : 1
     * IsPromotion : 0
     * Name : 利贝拉两粒扣翻领羊毛呢子大衣
     * NewPrice : 239
     * PFrom : 1
     * ProductImg : /upload/product/2016-01/13/1201601131012188296.jpg
     * disCount : 1.7999999523162842
     * oldPrice : 1389
     */

    private int Id;
    private int IsBaoYou;
    private int IsPromotion;
    private String Name;
    private double NewPrice;
    private int PFrom;
    private String ProductImg;
    private double disCount;
    private double oldPrice;

    public void setId(int Id)
    {
        this.Id = Id;
    }

    public void setIsBaoYou(int IsBaoYou)
    {
        this.IsBaoYou = IsBaoYou;
    }

    public void setIsPromotion(int IsPromotion)
    {
        this.IsPromotion = IsPromotion;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setNewPrice(double NewPrice)
    {
        this.NewPrice = NewPrice;
    }

    public void setPFrom(int PFrom)
    {
        this.PFrom = PFrom;
    }

    public void setProductImg(String ProductImg)
    {
        this.ProductImg = ProductImg;
    }

    public void setDisCount(double disCount)
    {
        this.disCount = disCount;
    }

    public void setOldPrice(double oldPrice)
    {
        this.oldPrice = oldPrice;
    }

    public int getId()
    {
        return Id;
    }

    public int getIsBaoYou()
    {
        return IsBaoYou;
    }

    public int getIsPromotion()
    {
        return IsPromotion;
    }

    public String getName()
    {
        return Name;
    }

    public double getNewPrice()
    {
        return NewPrice;
    }

    public int getPFrom()
    {
        return PFrom;
    }

    public String getProductImg()
    {
        return ProductImg;
    }

    public double getDisCount()
    {
        return disCount;
    }

    public double getOldPrice()
    {
        return oldPrice;
    }
}
