package test.dmdfchina.com.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * Created by mkt on 2018/6/27.
 */

public class CustomViewPager extends ViewPager {
    private Context mContext;
    private int imgWidth;
    private int imgHeigth;
    public CustomViewPager(@NonNull Context context) {
        this(context,null);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initStyle(attrs);
    }

    //设置格式
    private void initStyle(AttributeSet attrs) {
        TypedArray typedArray=mContext.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
        int indexCount = typedArray.getIndexCount();
        switch (indexCount){
            case R.styleable.CustomViewPager_imageWidth:
            imgWidth=typedArray.getInt(indexCount,0);
            break;
            case R.styleable.CustomViewPager_imageHeight:
                imgHeigth=typedArray.getInt(indexCount,0);
                break;

            default:
                    break;
        }
        typedArray.recycle();
    }

    public int getScreenWidth(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
