package bwie.example.com.day13lianxis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import bwie.example.com.day13lianxis.model.DetailBean;
import bwie.example.com.day13lianxis.utils.OkUtils;

public class DetailActivity extends AppCompatActivity {

    private SimpleDraweeView simpl_img;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        simpl_img = (SimpleDraweeView)findViewById(R.id.simpl_img);
        tv_title = (TextView) findViewById(R.id.tv_title);

     try {
         int pid = getIntent().getIntExtra("pid", 0);
         new OkUtils().get("http://www.zhaoapi.cn/product/getProductDetail?pid="+pid).setOkLisener(new OkUtils.OkListener() {
             @Override
             public void success(String data) {
                 DetailBean detailBean = new Gson().fromJson(data, DetailBean.class);
                 DetailBean.DataBean data1 = detailBean.getData();
                 simpl_img.setImageURI( data1.getImages().split("\\|")[0]);
                tv_title.setText(data1.getSubhead());
             }
         });
     }catch (Exception e){

     }


    }
}
