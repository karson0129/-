package silverlion.com.house.welcome;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import silverlion.com.house.R;

/**
 * Created by k8190 on 2016/7/19.
 */
public class WelcomeActivity extends MvpActivity<WelcomeView,WelcomePresenter>{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
    }

    @NonNull
    @Override
    public WelcomePresenter createPresenter() {
        return new WelcomePresenter();
    }


}
