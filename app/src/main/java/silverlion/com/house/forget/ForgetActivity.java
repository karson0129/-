package silverlion.com.house.forget;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;
import silverlion.com.house.components.ProgressDialogFragment;
import silverlion.com.house.forgetemail.ForgetEmailActivity;

public class ForgetActivity extends MvpActivity<ForgetView,ForgetPresenter> implements ForgetView {
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.forget_email})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.forget_email:
                    activityUtils.startActivity(ForgetEmailActivity.class);
                break;
            case R.id.cancel:
                    finish();
                break;
        }
    }

    @Override
    public void showProgress() {
        activityUtils.hideSoftKeyboard();
        if (progressDialogFragment == null) {
            progressDialogFragment = new ProgressDialogFragment();
        }
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(), "fragment_progress_dialog");
    }
    @Override
    public void hideProgress() {
        if (progressDialogFragment != null) progressDialogFragment.dismiss();
    }
    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }
    @NonNull
    @Override
    public ForgetPresenter createPresenter() {
        return new ForgetPresenter();
    }
}
