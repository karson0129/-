package silverlion.com.house.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;
import silverlion.com.house.components.ProgressDialogFragment;
import silverlion.com.house.emailregister.EmailActivity;

/**
 * Created by k8190 on 2016/7/20.
 */
public class RegisterActivity extends MvpActivity<RegisterView,RegisterPresenter> implements RegisterView{
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;
    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.email,R.id.cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.email:
                activityUtils.startActivity(EmailActivity.class);
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    @Override
    public void ShowProgress() {
        activityUtils.hideSoftKeyboard();
        if (progressDialogFragment == null) {
            progressDialogFragment = new ProgressDialogFragment();
        }
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(), "fragment_progress_dialog");
    }

    @Override
    public void HideProgress() {
        progressDialogFragment.dismiss();
    }

    @Override
    public void ShowMassage(String msg) {
        activityUtils.showToast(msg);
    }

}
