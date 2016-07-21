package silverlion.com.house.login;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;
import silverlion.com.house.components.ProgressDialogFragment;
import silverlion.com.house.forget.ForgetActivity;
import silverlion.com.house.register.RegisterActivity;

public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> implements LoginView{
    @Bind(R.id.account_editext)EditText account_ed;
    @Bind(R.id.password_editext)EditText password_ed;
    @Bind(R.id.register)TextView register;
    @Bind(R.id.forget_password)TextView forget_password;
    private ProgressDialogFragment progressDialogFragment;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.register, R.id.forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                    activityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.forget_password:
                    activityUtils.startActivity(ForgetActivity.class);
                break;
        }
    }
    @Override
    public void ShowMassage(String msg) {
        activityUtils.showToast(msg);
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
        if (progressDialogFragment != null)progressDialogFragment.dismiss();
    }
}
