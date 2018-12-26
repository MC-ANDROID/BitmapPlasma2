package com.example.plasma;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

public class ViewUtils {

    public static Point canvasSizeWH(Activity activity) {
        final DisplayMetrics metrics = new DisplayMetrics();
        Display display = activity.getWindowManager().getDefaultDisplay();
        Method mGetRawH = null, mGetRawW = null;
            // For JellyBeans and onward
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

                display.getRealMetrics(metrics);
                return new Point(metrics.widthPixels, metrics.heightPixels);
            } else {
                // Below Jellybeans you can use reflection method

                try {
                    mGetRawH = Display.class.getMethod("getRawHeight");
                    mGetRawW = Display.class.getMethod("getRawWidth");

                    return new Point((Integer) mGetRawW.invoke(display),
                            (Integer) mGetRawH.invoke(display));
                } catch (Exception e) {
                    throw new IllegalStateException("Nowhere to draw: ",e);
                }
            }

    }
}
