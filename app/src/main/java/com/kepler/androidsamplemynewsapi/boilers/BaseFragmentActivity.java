package com.kepler.androidsamplemynewsapi.boilers;

public abstract class BaseFragmentActivity extends BaseActivity {


    protected void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).commit();
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).addToBackStack(tag).commit();
    }

    protected abstract int getContainerId();

}
