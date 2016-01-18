package com.max.jacentsao.banjia.module;

import java.util.List;

/**
 * Created by Gan on 2016/1/13.
 */
public class BrandSale
{
    private int total;

    private List<RowsEntity> rows;

    public void setTotal(int total)
    {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows)
    {
        this.rows = rows;
    }

    public int getTotal()
    {
        return total;
    }

    public List<RowsEntity> getRows()
    {
        return rows;
    }

    public static class RowsEntity
    {
        private Double DisCount;
        private String EndDate;
        private String EndDateStr;
        private String ImgUrlSml;
        private String Name;
        private String ProductInfo;
        private int brandId;

        public void setDisCount(Double DisCount)
        {
            this.DisCount = DisCount;
        }

        public void setEndDate(String EndDate)
        {
            this.EndDate = EndDate;
        }

        public void setEndDateStr(String EndDateStr)
        {
            this.EndDateStr = EndDateStr;
        }

        public void setImgUrlSml(String ImgUrlSml)
        {
            this.ImgUrlSml = ImgUrlSml;
        }

        public void setName(String Name)
        {
            this.Name = Name;
        }

        public void setProductInfo(String ProductInfo)
        {
            this.ProductInfo = ProductInfo;
        }

        public void setBrandId(int brandId)
        {
            this.brandId = brandId;
        }

        public Double getDisCount()
        {
            return DisCount;
        }

        public String getEndDate()
        {
            return EndDate;
        }

        public String getEndDateStr()
        {
            return EndDateStr;
        }

        public String getImgUrlSml()
        {
            return ImgUrlSml;
        }

        public String getName()
        {
            return Name;
        }

        public String getProductInfo()
        {
            return ProductInfo;
        }

        public int getBrandId()
        {
            return brandId;
        }
    }
}
