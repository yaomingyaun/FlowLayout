package com.bw.ymy.demo1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

/**
 *
 *
 * */
public class MainActivity extends AppCompatActivity {
    private Banner banner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner1=findViewById(R.id.banner1);
        banner1.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Bean.DataBean bena= (Bean.DataBean) path;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(bena.getDetailUrl(),imageView);

            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        inlodata();
    }
    private  void inlodata()
    {
        Netuil.getInstance().getRequest("http://www.zhaoapi.cn/product/getProductDetail",Bean.class,new Netuil.Callback<Bean>()
        {
            @Override
            public void onSuccess(Bean bean) {
              banner1.setImages((List<?>) bean.getData());
                banner1.start();
            }
        });
    }
}
