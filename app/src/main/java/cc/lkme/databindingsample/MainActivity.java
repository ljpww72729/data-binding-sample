package cc.lkme.databindingsample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cc.lkme.databindingsample.databinding.ActivityMainBinding;
import cc.lkme.databindingsample.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认
//        setContentView(R.layout.activity_main);
        //data binding
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.helloBinding.setText("Hello data binding, you are so awesome!");
//        该种方式比较原始,不会用到反射,具体查看一下API
//      binding.setVariable(BR.defaultAddress, "无地址");
        File file = new File(Environment.getExternalStorageDirectory(), "test.txt");
//        File dir = new File(Environment.getExternalStorageDirectory(), "abc");
//        dir.mkdirs();
//        file.exists();
//        try {
//            file.createNewFile();
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
//            outputStreamWriter.write("nihao");
//            outputStreamWriter.flush();
//            outputStreamWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String abc = bufferedReader.readLine();
            Toast.makeText(MainActivity.this, abc, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        binding.getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, binding.getUser().name.get(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置Handler
        binding.setHandlers(new MainHandler(this));
        binding.recycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CRecyclerView.class));
            }
        });
    }

}
