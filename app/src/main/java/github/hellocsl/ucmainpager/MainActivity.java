package github.hellocsl.ucmainpager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.ucmainpager.adapter.TestFragmentAdapter;
import github.hellocsl.ucmainpager.behavior.uc.UcNewsHeaderPagerBehavior;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, UcNewsHeaderPagerBehavior.OnPagerStateListener {

    private static final String TAG = "MainActivity";
    private ViewPager mNewsPager;
    private TabLayout mTableLayout;
    private List<TestFragment> mFragments;
    private UcNewsHeaderPagerBehavior mPagerBehavior;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc_main_pager);
        initView();
    }

    protected void initView() {
        findViewById(R.id.iv_github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyGitHub();
            }


        });
        mPagerBehavior = (UcNewsHeaderPagerBehavior) ((CoordinatorLayout.LayoutParams) findViewById(R.id.id_uc_news_header_pager).getLayoutParams()).getBehavior();
        mPagerBehavior.setPagerStateListener(this);
        mNewsPager = (ViewPager) findViewById(R.id.id_uc_news_content);
        mTableLayout = (TabLayout) findViewById(R.id.id_uc_news_tab);
        mFragments = new ArrayList<TestFragment>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(TestFragment.newInstance(String.valueOf(i), false));
            mTableLayout.addTab(mTableLayout.newTab().setText("Tab" + i));
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mTableLayout.setOnTabSelectedListener(this);
        mNewsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mNewsPager.setAdapter(new TestFragmentAdapter(mFragments, getSupportFragmentManager()));
    }

    private void openMyGitHub() {
        Uri uri = Uri.parse("https://github.com/BCsl");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mNewsPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPagerClosed() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPagerClosed: ");
        }
        Snackbar.make(mNewsPager, "pager closed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerOpened() {
        Snackbar.make(mNewsPager, "pager opened", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mPagerBehavior != null && mPagerBehavior.isClosed()) {
            mPagerBehavior.openPager();
        } else {
            super.onBackPressed();
        }
    }
}
