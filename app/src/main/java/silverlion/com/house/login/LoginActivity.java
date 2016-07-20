package silverlion.com.house.login;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.Window;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import silverlion.com.house.R;

public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

}
