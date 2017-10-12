package com.iflytek.clientdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

public class MessengerService extends Service {
    NotificationManager notificationManager;
    int mValue=0;
    ArrayList<Messenger>  mClients=new ArrayList<Messenger>();
    static final int MSG_REGISTER_CLIENT = 1;

    static final int MSG_UNREGISTER_CLIENT = 2;

    static final int MSG_SET_VALUE = 3;

    String TAG="MessengerService";

    private Handler inComingHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG,msg.what+"-------------handleMessage----------"+msg.arg1);
            switch (msg.what){
                case MSG_REGISTER_CLIENT :
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT :
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_VALUE :
                    mValue=msg.arg1;
                    for(int i=0;i<mClients.size();i++){
                        try {
                            mClients.get(i).send(Message.obtain(null,MSG_SET_VALUE,mValue,0));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }


        }
    };



    final Messenger incomingMessenger=new Messenger(inComingHandler);


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"-------------onCreate----------");
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        showNotifition();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG,"-------------onStart----------");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG,"-------------onBind----------");
          return  incomingMessenger.getBinder();
//        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void showNotifition(){



//创建一个NotificationManager的引用
        String ns = Context.NOTIFICATION_SERVICE;
        notificationManager = (NotificationManager)getSystemService(ns);
// 定义Notification的各种属性
        CharSequence tickerText = "Hello"; //状态栏显示的通知文本提示
        long when = System.currentTimeMillis(); //通知产生的时间，会在通知信息里显示
//用上面的属性初始化 Nofification
        Context context = getApplicationContext(); //上下文
        Notification.Builder builder = new Notification.Builder(context);
        //设置通知的事件消息
        CharSequence contentTitle = "My Notification"; //通知栏标题
        CharSequence contentText = "Hello World!"; //通知栏内容
        Intent notificationIntent = new Intent(this,MainActivity.class); //点击该通知后要跳转的Activity
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        builder.setContentTitle(contentTitle);
        builder.setContentText(contentText);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(contentIntent);
      Notification notification= builder.build();
        notification.flags|=Notification.FLAG_AUTO_CANCEL;
        notification.defaults|=Notification.DEFAULT_SOUND;
        notification.defaults |=Notification.DEFAULT_LIGHTS;
        notification.defaults |=Notification.DEFAULT_VIBRATE;
       //把Notification传递给 NotificationManager
        notificationManager.notify(0, notification);

    }


}
