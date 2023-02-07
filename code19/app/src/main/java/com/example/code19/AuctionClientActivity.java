package com.example.code19;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;

import java.lang.ref.WeakReference;

public class AuctionClientActivity extends FragmentActivity{
    private boolean mTwoPane;

    private static class MyHandler extends Handler{

        public WeakReference<AuctionClientActivity> mAuctionClientActivityWeakReference;

        public MyHandler(WeakReference<AuctionClientActivity> mAuctionClientActivityWeakReference){
            this.mAuctionClientActivityWeakReference = mAuctionClientActivityWeakReference;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if(mAuctionClientActivityWeakReference.get() != null){
                
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_client);
        if(findViewById(R.id.auction_detail_container) != null){
            mTwoPane = true;
            AuctionListFragment listFragment = (AuctionListFragment) getSupportFragmentManager().findFragmentById(R.id.auction_list);
            getSupportFragmentManager().beginTransaction().replace(R.id.auction_detail_container,listFragment).addToBackStack(null).commit();

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}