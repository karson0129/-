package silverlion.com.house.forgetemail;

import android.support.annotation.NonNull;
import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;

public class ForgetEmailActivity extends MvpActivity<ForgetEmailView,ForgetEmailPresenter> implements ForgetEmailView {
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_forget_email);
    }

    @NonNull
    @Override
    public ForgetEmailPresenter createPresenter() {
        return new ForgetEmailPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }
}
