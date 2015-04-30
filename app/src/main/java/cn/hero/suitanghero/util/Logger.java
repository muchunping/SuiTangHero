package cn.hero.suitanghero.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 日志
 * Created by chunping on 2014/10/21.
 */
public final class Logger {
    private static boolean LOG_HIGN_OPEN = true;    //控制异常、错误等情况的日志
    private static boolean LOG_MEDIUM_OPEN = true;  //控制关键点的日志
    private static boolean LOG_LOW_OPEN = true;     //控制详细的日志

    public static void dH(String tag, String message){
        if(LOG_HIGN_OPEN)
            Log.d(tag, message);
    }

    public static void dM(String tag, String message){
        if(LOG_MEDIUM_OPEN)
            Log.d(tag, message);
    }

    public static void dL(String tag, String message){
        if(LOG_LOW_OPEN)
            Log.d(tag, message);
    }

    public static void eH(String tag, String message){
        if(LOG_HIGN_OPEN)
            Log.e(tag, message);
    }

    public static void eM(String tag, String message){
        if(LOG_MEDIUM_OPEN)
            Log.e(tag, message);
    }

    public static void eL(String tag, String message){
        if(LOG_LOW_OPEN)
            Log.e(tag, message);
    }

    public static void showToast(Context cxt, String message){
        Toast.makeText(cxt, message, Toast.LENGTH_SHORT).show();
    }
}
