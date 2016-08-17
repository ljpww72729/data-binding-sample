package cc.lkme.databindingsample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cc.lkme.databindingsample.databinding.ActivityMainBinding;
import cc.lkme.databindingsample.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认
//        setContentView(R.layout.activity_main);
        //data binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.helloBinding.setText("Hello data binding, you are so awesome!");
//        该种方式比较原始,不会用到反射,具体查看一下API
//      binding.setVariable(BR.defaultAddress, "无地址");
        binding.setDefaultAddress("地址不存在!");

//        //Observable Objects
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName("lipeng");
//        userInfo.setAge("28");
//        userInfo.setShow(true);
//        userInfo.setAddress(null);

        //ObservableFields
        UserInfo userInfo = new UserInfo();
        userInfo.name.set("lipeng");
        userInfo.age.set("28");
        userInfo.show.set(true);
        userInfo.address.set("北京市");

        binding.setUser(userInfo);
        //设置Handler
        binding.setHandlers(new MainHandler(this));
//        binding.executePendingBindings();
        binding.recycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CRecyclerView.class));
            }
        });
    }

}
