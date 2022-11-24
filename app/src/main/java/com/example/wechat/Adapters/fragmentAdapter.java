package com.example.wechat.Adapters;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wechat.fragments.callFragment;
import com.example.wechat.fragments.chatFragment;
import com.example.wechat.fragments.statusFragment;

import java.util.List;

@SuppressWarnings("deprecation")
public class fragmentAdapter extends FragmentPagerAdapter {

    public fragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public fragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new chatFragment();

            case 1:
                return new statusFragment();

            case 2:
                return new callFragment();

            default:
                return new chatFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Chat";
        } else if (position == 1) {
            title = "Status";
        } else if (position == 2) {
            title = "Call";
        }
        return title;
    }
}