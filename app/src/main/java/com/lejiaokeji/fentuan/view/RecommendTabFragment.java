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
    // private TextView mTextView;
    List<Item_Shop_RecommentBean> myListData = new ArrayList<Item_Shop_RecommentBean>();
    Recommend_Rv_Adapter adapter;
    Home_Page_Control home_page_control;
    private ActionMode actionMode;
    public Set<Integer> positionSet = new HashSet<>();

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
        return R.layout.fragment_home_tab;
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
            String url="http://img.zcool.cn/community/01635d571ed29832f875a3994c7836.png@900w_1l_2o_100sh.jpg";
            myListData.add(new Item_Shop_RecommentBean(url,"不才","2小时前"+i,"已分享52次",url,url,url,"25",url,"按实际学生考试地点"));
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
//            intent.setAction("android.intent.action.SEND");
//            intent.putExtra(Intent.EXTRA_STREAM, Uris.get(2));
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, Uris);
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
