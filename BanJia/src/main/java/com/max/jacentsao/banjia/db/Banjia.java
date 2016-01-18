package com.max.jacentsao.banjia.db;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * Created by JacenTsao on 2016/1/10.
 */
@Table(name = "t_user_collect")
public class Banjia {
    @Id(column = "_id")
    public int id;

    @Column(column = "username")
    @NotNull
    @Unique
    public String username;

    @Column(column = "password")
    @NotNull
    public String password;

    @Column(column = "bonus_point")
    @NotNull
    public int bonusPoint;
}
