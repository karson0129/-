package silverlion.com.house.forgetemail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.cancel,R.id.action})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancel:
                    finish();
                break;
            case R.id.action:

                break;
        }
    }
}
