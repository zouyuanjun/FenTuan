package com.lejiaokeji.fentuan.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.adapter.Recommend_Rv_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.databean.Item_Shop_RecommentBean;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class RecommendTabFragment extends LazyLoadFragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private PullLoadMoreRecyclerView mRecyclerView;
    List<Item_Shop_RecommentBean> myListData = new ArrayList<Item_Shop_RecommentBean>();
    Recommend_Rv_Adapter adapter;
    Home_Page_Control home_page_control;
    ProgressBar progressBar;
    public static RecommendTabFragment newInstance(String title) {
        RecommendTabFragment tabFragment = new RecommendTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            Log.d("当前标签","haha "+mTitle);
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_recommend_tab;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    protected void lazyLoad() {
        home_page_control=Home_Page_Control.getInstance();
        home_page_control.setlistener(new Home_Page_Control.Home_Page_Listener() {

            @Override
            public void loadsuccefful(Shop_Data shop_data) {

            }

            @Override
            public void loadfail(String t) {

            }

            @Override
            public void searchresult(Shop_Data shop_data) {

            }
            @Override
            public void severerr() {

            }

            @Override
            public void connecttimeout() {

            }

            @Override
            public void connectfail() {

            }
        });
        adapter=new Recommend_Rv_Adapter(getContext(),myListData);
        mRecyclerView = findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(adapter);
        Log.d("55555","内标签正在渲染");
        // mTextView = (TextView) view.findViewById(R.id.id_info);
        // mTextView.setText(mTitle);
        adapter.notifyDataSetChanged();
        mRecyclerView.setPullRefreshEnable(true);
        mRecyclerView.setPushRefreshEnable(true);
        getdata();
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getdata();
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                Log.d("555","刷新更多");
            }
            @Override
            public void onLoadMore() {
                //   getdata();
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                Log.d("555","加载更多");
            }
        });
        adapter.setOnItemClickListener(new Recommend_Rv_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
            @Override
            public void onButtonClick(int position) throws IOException, ExecutionException, InterruptedException {
                Log.d("点击了",position+"g ");
                List<String> urlList=new ArrayList<>();
                urlList.clear();
                urlList.add(myListData.get(position).getHeaderurl());
                urlList.add(myListData.get(position).getHeaderurl());
                urlList.add(myListData.get(position).getHeaderurl());
               getBitmap(urlList,getContext());
            }
        });
    }
    public void getdata(){
        for (int i = 0; i < 10; i++) {
            String url="http://img4.imgtn.bdimg.com/it/u=2507102532,3144382040&fm=27&gp=0.jpg";
            String head="http://img1.imgtn.bdimg.com/it/u=3772666604,3699575277&fm=27&gp=0.jpg";
            String url2="data:image/jpeg;base64,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";
            myListData.add(new Item_Shop_RecommentBean(head,"不才","2小时前"+i,"已分享52次",url,url2,url,"25","1","暂无内容 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 暂无内容"));
        }
    }
    public static void getBitmap(final List<String> urlList, final Context context) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String filename="123";
                ArrayList<String> imageUris = new ArrayList<>();
                try {
        for (int i=0;i<urlList.size();i++){
            saveFile(context,urlList.get(i),filename+i+".jpg");
            imageUris.add("/data/user/0/com.lejiaokeji.fentuan/cache/"+filename+i+".jpg");
        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("不存在","");
                sharePhotoToWX(context,"dsfasdfa",imageUris);
            }
        }).start();

    }
        //分享到朋友圈
        public static void sharePhotoToWX(Context context, String text, ArrayList<String> imageUris) {
            ArrayList<Uri> Uris = new ArrayList<>();
            Uris.clear();
            if (!uninstallSoftware(context, "com.tencent.mm")) {
                Toast.makeText(context, "微信没有安装！", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i=0;i<imageUris.size();i++){
                File file = new File(imageUris.get(i));
                if (!file.exists()) {
                    return;
                }
                Uris.add(FileProvider.getUriForFile(context, "com.lejiaokeji.fentuan.fileprovider", file));
            }
            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.tencent.mm",
                    "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.setComponent(comp);
            intent.setType("image/*");
            intent.setAction("android.intent.action.SEND");
            intent.putExtra(Intent.EXTRA_STREAM, Uris.get(2));
//            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
//            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, Uris);
            intent.putExtra("Kdescription", "sfdafasfa");
            context.startActivity(intent);
        }
    //保存bitmap为文件
    public static void saveFile(Context context, String imageurl,String fileName) throws IOException, ExecutionException, InterruptedException {
        Bitmap bmp = null;
        bmp = Glide.with(context)
                .load(imageurl)
                .asBitmap() //必须
                .centerCrop()
                .into(200, 200)
                .get();
        File dirFile = new File("/data/user/0/com.lejiaokeji.fentuan/cache/");
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File("/data/user/0/com.lejiaokeji.fentuan/cache/" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }
    //判断是否安装微信客户端
    private static boolean uninstallSoftware(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
