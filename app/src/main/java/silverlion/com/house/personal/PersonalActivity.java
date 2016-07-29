package silverlion.com.house.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;
import silverlion.com.house.commous.UserPrefs;
import silverlion.com.house.components.ProgressDialogFragment;
import silverlion.com.house.login.LoginActivity;
import silverlion.com.house.register.sortlistview.SoetiListActivity;


import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

public class PersonalActivity extends MvpActivity<PersonalView,PersonalPresenter> implements PersonalView{
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;
    private IconSelectWindow iconSelectWindow;
    private final int REQUEST = 0x12;
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String area = "0086";
    private String sex = "男";
    private String url;
    private boolean commit;
    @Bind(R.id.name)EditText name_et;
    @Bind(R.id.address)EditText address_et;
    @Bind(R.id.phone)EditText phone_et;
    @Bind(R.id.email)EditText email_et;
    @Bind(R.id.area)TextView area_tv;
    @Bind(R.id.user_hand)ImageView hand_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_personal);
        Intent intent = getIntent();
        id = intent.getStringExtra("accout_id");

        updateAvatar(UserPrefs.getInstance().getAvatar());
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        name_et.addTextChangedListener(mTextWatcher);
        address_et.addTextChangedListener(mTextWatcher);
        phone_et.addTextChangedListener(mTextWatcher);
        email_et.addTextChangedListener(mTextWatcher);
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            name =name_et.getText().toString();
            address = address_et.getText().toString();
            phone = phone_et.getText().toString();
            email = email_et.getText().toString();

            commit = TextUtils.isEmpty(name)&&TextUtils.isEmpty(address)&&TextUtils.isEmpty(phone)&&TextUtils.isEmpty(email);
        }
    };

    @OnClick({R.id.cancel,R.id.keep,R.id.area,R.id.sex,R.id.hand})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.keep:
                presenter.keep(id,name,sex,address,area,phone,email,url);
                break;
            case R.id.area:
                Intent intent = new Intent(PersonalActivity.this, SoetiListActivity.class);
                startActivityForResult(intent,REQUEST);
                break;
            case R.id.sex:

                break;
            case R.id.hand:
                if (iconSelectWindow == null) iconSelectWindow = new IconSelectWindow(this, listener);

                if (iconSelectWindow.isShowing()) {
                    iconSelectWindow.dismiss();
                } else{
                    iconSelectWindow.show();
                }
                break;
        }
    }
    //待完成
    private final CropHandler cropHandler = new CropHandler() {
        @Override public void onPhotoCropped(Uri uri) {
            url = uri.getPath();
            Log.i("result",uri.getPath());
            updateAvatar("file://"+uri.getPath());
        }

        @Override public void onCropCancel() {
            activityUtils.showToast("onCropCancel");
        }

        @Override public void onCropFailed(String message) {
            activityUtils.showToast("onCropCancel");
        }

        @Override public CropParams getCropParams() {
            CropParams params = new CropParams();
            params.aspectX = 300;
            params.aspectY = 300;
            return params;
        }

        @Override public Activity getContext() {
            return PersonalActivity.this;
        }
    };

    private final IconSelectWindow.Listener listener = new IconSelectWindow.Listener() {
        @Override public void toGallery() {
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCropFromGalleryIntent(cropHandler.getCropParams());
            startActivityForResult(intent, CropHelper.REQUEST_CROP);
        }

        @Override public void toCamera() {
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode){
            area  = data.getStringExtra("area");
            area_tv.setText(data.getStringExtra("name")+"  +"+data.getStringExtra("area"));
        }
        CropHelper.handleResult(cropHandler, requestCode, resultCode, data);
    }

    @Override
    public void updateAvatar(String url) {
        // ImageLoader使用前必须初始化
        ImageLoader.getInstance().displayImage(url,hand_iv);
    }

    @NonNull
    @Override
    public PersonalPresenter createPresenter() {
        return new PersonalPresenter();
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
        progressDialogFragment.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void GotoMain() {
        activityUtils.startActivity(LoginActivity.class);
        finish();
    }
}
