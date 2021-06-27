package com.example.myapplicationoflift;
import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Administrator on 2020-05-06.
 */
public class AppException extends Exception implements UncaughtExceptionHandler {

    private static final long serialVersionUID = -6262909398048670705L;

    private String message;

    private UncaughtExceptionHandler mDefaultHandler;

    private AppException() {
        super();
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public AppException(String message, Exception excp) {
        super(message, excp);
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * &#x83b7;&#x53d6;APP&#x5f02;&#x5e38;&#x5d29;&#x6e83;&#x5904;&#x7406;&#x5bf9;&#x8c61;
     *
     * @param context
     * @return
     */
    public static AppException getAppExceptionHandler() {
        return new AppException();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        }

    }

    /**
     * 自定义异常处理
     *
     * @param ex
     * @return true:处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        final Activity activity = AppManager.getAppManager().currentActivity();

        if (activity == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(activity, "提示：该程序崩溃ing", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(activity).setTitle("提示")
                        .setCancelable(false).setMessage("温馨提醒：程序出现debug，即将崩溃关闭！")
                        .setNeutralButton("知道了", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                AppManager.getAppManager().exitApp(activity);
                            }
                        }).create().show();
                Looper.loop();
            }
        }.start();

        return true;
    }

}
