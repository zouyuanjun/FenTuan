package com.lejiaokeji.fentuan.wxapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lejiaokeji.fentuan.MainActivity;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.activity.WX_Signin_Activity;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.databean.Userinfo_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;
	static Context context;
	Network network;
	Activity activity;
	String token="";
	String openid="";
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what=msg.what;
			String result= (String) msg.obj;
			Log.d("555",result);
			if (what==1){
				JsonElement je = new JsonParser().parse(result);
				String refresh_token = je.getAsJsonObject().get("refresh_token").getAsString();
				String url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx46b14ff64afefa78&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
				url=url.replace("REFRESH_TOKEN",refresh_token);
				//获取到token后刷新token
				network.connectnet("",url,handler,2);
			}else if (what==2){
				//获取到OPENid
				JsonElement je = new JsonParser().parse(result);
				token = je.getAsJsonObject().get("access_token").getAsString();
				openid= je.getAsJsonObject().get("openid").getAsString();

				String weixinid="{\"weixinid\":\"%openid\"}";
				weixinid=weixinid.replace("%openid",openid);
				String url=Constants.URL+"/user/bindPhone";
				//请求该微信号是否已注册
//				Intent intent=new Intent(activity, MainActivity.class);
//				intent.putExtra("phone","44");
//				startActivity(intent);
//				activity.finish();
				network.connectnet(weixinid,url,handler,3);
			}else if (what==3){
				JsonElement je = null;
				String code="-2";
				try {
					je = new JsonParser().parse(result);
					code = je.getAsJsonObject().get("retCode").getAsString();
				}catch (JsonSyntaxException e){
					Toast.makeText(context,"与服务器连接异常",Toast.LENGTH_LONG).show();
					activity.finish();
				}
				if (code.equals("0")){
					//查询微信号是否注册，如果返回是0则说明已注册，直接跳到主页
					String data = JSON.parseObject(result).getString("data");;
					Constants.USERINFO = JSON.parseObject(data, new TypeReference<Userinfo_Bean>() {});
					Intent intent=new Intent(activity, MainActivity.class);
					startActivity(intent);
					activity.finish();
				}else if(code.equals("-1")){
					Log.d("错误码",code);
					Toast.makeText(context,"与服务器连接异常",Toast.LENGTH_LONG).show();
					activity.finish();
				} else if(code.equals("-2")){
					Log.d("错误码",code);
					Toast.makeText(context,"与服务器连接超时",Toast.LENGTH_LONG).show();
					activity.finish();
				}else {
					//没有注册。跳到绑定手机号页面
					Intent intent=new Intent(activity, WX_Signin_Activity.class);
					intent.putExtra("openid",openid);
					intent.putExtra("token",token);
					startActivity(intent);
					activity.finish();
				}
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wxentry);
		context=this;
		activity=this;
		network=Network.getnetwork();
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		//注意：
		//第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
		try {
			Constants.api.handleIntent(getIntent(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		Constants.api.handleIntent(intent, this);
	}
	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) {
		switch (req.getType()) {
			case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
				//goToGetMsg();
				break;
			case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
			//	goToShowMsg((ShowMessageFromWX.Req) req);
				break;
			default:
				break;
		}
	}
	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {
		int result = 0;
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				Log.d("5555","用户授权");
				//授权后拿到code，再用code请求tokn
				String code = ((SendAuth.Resp) resp).code;
				String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx46b14ff64afefa78&secret=daa23fdc483f72080916c7955b6e26b5&code=%CODE&grant_type=authorization_code";
				url=url.replace("%CODE",code);
				network.connectnet("",url,handler,1);
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				Log.d("5555","用户取消");
				activity.finish();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				Log.d("5555","用户拒绝");
				activity.finish();
				break;
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				Log.d("5555","不支持");
				activity.finish();
				break;
			default:
				break;
		}

	}

//	private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
//	}

//	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
//		WXMediaMessage wxMsg = showReq.message;
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//
//		StringBuffer msg = new StringBuffer(); // 组织一个待显示的消息内容
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//
//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
//	}
	public static void weixinLogin(){

	}

}