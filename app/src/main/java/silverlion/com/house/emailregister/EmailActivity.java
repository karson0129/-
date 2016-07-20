package silverlion.com.house.emailregister;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;

public class EmailActivity extends MvpActivity<EmailView,EmailPresenter> implements EmailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.cancel})
    public void onCilck(View view){
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage() {

    }

    @NonNull
    @Override
    public EmailPresenter createPresenter() {
        return new EmailPresenter();
    }
}
