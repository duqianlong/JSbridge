package com.example.duqianlong.jsbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/***
 * https://www.imooc.com/article/70143?block_id=tuijian_wz  //使用方法
 * */

public class MainActivity extends AppCompatActivity {
    private BridgeWebView bridgeWebView;
    String log = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化文birdgewebview
        bridgeWebView = findViewById(R.id.mY_bridge);
        //TODO  加载webview
        bridgeWebView.getSettings().setJavaScriptEnabled(true);
        bridgeWebView.loadUrl("http://172.25.35.107:8082");
        //给JS的提供方法
        initbridgection();
    }

    private void initbridgection() {
        /**
         * 提供给JS调用的方法  //TODO 最常用的
         * */
        bridgeWebView.registerHandler("camera", new BridgeHandler() {//camera ：方法名
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    Log.e(log, "接收到JS的数据====camera====" + data);
                    Toast.makeText(MainActivity.this, "接收到JS的数据====camera====" + data, Toast.LENGTH_SHORT).show();
                    //给JS回传的数据
                    function.onCallBack("收到JS调用相机需求，一拍照，返回照片地址，收到了么666666666666666666");
                } else {
                    Toast.makeText(MainActivity.this, "JS传参为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         *js调用方式
         * */
//        window.WebViewJavascriptBridge.callHandler(
//                'camera'  //方法名
//                , {'appKey':appKey, 'appSecret':secret}  //给移动端的值
//            ,function(responseData) {//移动端回传的数据
//            alert(responseData);
//            document.getElementById('location').innerHTML = responseData;
//        }
//);


        /**
         * 提供给JS调用  默认   的方法
         * */
        bridgeWebView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    Log.e(log, "接收到JS的数据========" + data);
//                    给JS回传的数据
                    function.onCallBack("要返回给JS的数据");
                } else {
                    Toast.makeText(MainActivity.this, "JS传参为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 调用JS的方法
         * */
        bridgeWebView.callHandler("fangfaming", "传给JS的值", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (!TextUtils.isEmpty(data)) {
                    Log.e(log, "JS返回的值=====" + data);
                }
            }
        });
        /**
         * 调用 JS 默认方法
         * */
        bridgeWebView.send("发送给JS默认接受的值", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (!TextUtils.isEmpty(data)) {
                    Log.e(log, "JS返回的值=====" + data);
                }
            }
        });
    }

    //JS注册监听及初始化
//    var message = JSON.parse(railsMobilePlatform.getPhoneMessage());  //TODO 因为android跟IOS初始化方式不同，所以需要原生方法给JS传参，告诉JS当前设备是android还是IOS
//if (message.deviceSystem == "android") {
//// ①在 Android 设备上初始化
//        function connectWebViewJavascriptBridge(callback) {
////已经初始化
//        if (window.WebViewJavascriptBridge) {
//            callback(WebViewJavascriptBridge);
////do something
//        } else {
////未初始化，执行初始化操作
//            document.addEventListener(
//                    'WebViewJavascriptBridgeReady'
//                    , function() {
//                callback(WebViewJavascriptBridge);
////do something
//            },
//            false
//);
//        }
//}
//// 注册回调函数，第一次连接时调用 初始化函数
//        connectWebViewJavascriptBridge(function(bridge) {
////初始化
//            bridge.init(function(message, responseCallback) {
//                var data = {
//                        'Javascript Responds': 'Hello jarry!'
//};
//                responseCallback(data);
//            });
//        })
//    } else {
//// 注册事件监听
//// ②在 ios 设备上初始化
//        function setupWebViewJavascriptBridge(callback) {
//        if (window.WebViewJavascriptBridge) { return
//                callback(WebViewJavascriptBridge); }
//        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
//        window.WVJBCallbacks = [callback];
//        var WVJBIframe = document.createElement('iframe');
//        WVJBIframe.style.display = 'none';
//        WVJBIframe.src = 'https://__bridge_loaded__';
//        document.documentElement.appendChild(WVJBIframe);
//        setTimeout(function()
//        { document.documentElement.removeChild(WVJBIframe) }, 0)
//}
//        setupWebViewJavascriptBridge(function(bridge) {
////初始化
//            bridge.init(function(message, responseCallback) {
//                var data = {
//                        'Javascript Responds': 'Hello jarry!'
//};
//                responseCallback(data);
//            });
//        });
//    }

}
