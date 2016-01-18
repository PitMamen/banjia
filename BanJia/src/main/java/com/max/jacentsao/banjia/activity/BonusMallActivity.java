package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.fragment.FragmentBonusMallExchange;
import com.max.jacentsao.banjia.fragment.FragmentBonusMallMyExchange;

/**
 * 积分商城
 * @author JacenTsao
 */
@ContentView(R.layout.activity_bonus_mall)
public class BonusMallActivity extends AppCompatActivity {

    @ViewInject(R.id.rg_bonus_mall)
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        radioGroup.getChildAt(0).performClick();
    }

    @OnRadioGroupCheckedChange(R.id.rg_bonus_mall)
    public void exchangeOrExchanged(RadioGroup group, int checkedId){
        if(checkedId == R.id.rb_bonus_mall_exchange){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_bonus_mall_exchange,new FragmentBonusMallExchange()).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_bonus_mall_exchange,new FragmentBonusMallMyExchange()).commit();
        }
    }
}
