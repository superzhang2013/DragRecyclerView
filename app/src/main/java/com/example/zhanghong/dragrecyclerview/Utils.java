package com.example.zhanghong.dragrecyclerview;

import android.content.Context;

/**
 * Created by zhanghong on 2017/11/2.
 */
public class Utils {

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }
}
