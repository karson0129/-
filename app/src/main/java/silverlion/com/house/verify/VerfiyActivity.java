package silverlion.com.house.verify;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import silverlion.com.house.R;

public class VerfiyActivity extends MvpActivity<VerfiyView,VerfiyPresenter> implements VerfiyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfiy);
    }

    @NonNull
    @Override
    public VerfiyPresenter createPresenter() {
        return new VerfiyPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void GotoLogin() {

    }
}
