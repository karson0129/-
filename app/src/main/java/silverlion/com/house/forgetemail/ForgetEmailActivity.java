package silverlion.com.house.forgetemail;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;
import silverlion.com.house.commous.RegexUtils;
import silverlion.com.house.components.ProgressDialogFragment;
import silverlion.com.house.register.User;

public class ForgetEmailActivity extends MvpActivity<ForgetEmailView,ForgetEmailPresenter> implements ForgetEmailView {
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;
    @Bind(R.id.email)EditText email_et;
    @Bind(R.id.verification)EditText verify_et;
    @Bind(R.id.notphone)TextView not;
    @Bind(R.id.no_ver)TextView nover;
    private String email;
    private String verify;
    private String id;

    private boolean action,send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_forget_email);
    }


    @OnClick({R.id.cancel,R.id.verification_text,R.id.action})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.verification_text:
                presenter.GetVerify(new User(email,null));
                break;
            case R.id.action:

                break;
        }
    }

    @NonNull
    @Override
    public ForgetEmailPresenter createPresenter() {
        return new ForgetEmailPresenter();
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
    public void hideProgress() {progressDialogFragment.dismiss();}

    @Override
    public void showVerify(int i) {
        if (i == 1){
            not.setVisibility(View.VISIBLE);
        }else if (i == 2){
            nover.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideVerify(int i) {
        if (i == 1){
            not.setVisibility(View.INVISIBLE);
        }else if (i == 2){
            nover.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        verify_et.addTextChangedListener(listener);
        email_et.addTextChangedListener(listener);
    }

    private TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            email = email_et.getText().toString();
            verify = verify_et.getText().toString();
            action = RegexUtils.isEmail(email);
            send = RegexUtils.isEmail(email) && !TextUtils.isEmpty(verify);
        }
    };

    @Override
    public void getVerifyID(String id) {
        this.id = id;
    }
}
