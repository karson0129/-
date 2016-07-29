package silverlion.com.house.forgetemail;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silverlion.com.house.forget.ForgetResult;
import silverlion.com.house.net.NetClient;
import silverlion.com.house.register.User;

/**
 * Created by k8190 on 2016/7/21.
 */
public class ForgetEmailPresenter extends MvpBasePresenter<ForgetEmailView> {
    private Call<ForgetResult> call;
    private final int EMAIL = 1;

    public void GetVerify(User user){
        getView().showProgress();
        if (call != null)call.cancel();
        call = NetClient.getInstance().getUserApi().EmailForget(user.getArea_code(),"get_code");
        call.enqueue(emailback);
    }

    private Callback<ForgetResult> emailback = new Callback<ForgetResult>() {
        @Override
        public void onResponse(Call<ForgetResult> call, Response<ForgetResult> response) {
            getView().hideProgress();
            ForgetResult result = response.body();
            Log.i("result",response.toString());
            if (result.getOutput().equals("0")){
                getView().showMessage("用户不存在");
                getView().showVerify(EMAIL);
                return;
            }
            getView().getVerifyID(result.getCode_id());
        }

        @Override
        public void onFailure(Call<ForgetResult> call, Throwable t) {
            getView().hideProgress();
            getView().showMessage("Email错误");
        }
    };

}
