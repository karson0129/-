package silverlion.com.house.personal;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silverlion.com.house.commous.UserPrefs;
import silverlion.com.house.net.NetClient;
import silverlion.com.house.verify.VerfiyResponse;

/**
 * Created by k8190 on 2016/7/25.
 */
public class PersonalPresenter extends MvpBasePresenter<PersonalView> {
    private Call<VerfiyResponse> call;

    public void keep(String id,String name ,String sex,String address,String area,String phone,String email,String url){
        getView().showProgress();
        if (call != null)call.cancel();
        UserPrefs.getInstance().setAvatar(NetClient.BASE_URL + url);
        call = NetClient.getInstance().getUserApi().Keep(id,name,sex,address,area,phone,email,url);
        call.enqueue(callback);
    }

    private Callback<VerfiyResponse> callback = new Callback<VerfiyResponse>() {
        @Override
        public void onResponse(Call<VerfiyResponse> call, Response<VerfiyResponse> response) {
            Log.i("result",response.body().toString());
            getView().hideProgress();
            getView().GotoMain();
        }

        @Override
        public void onFailure(Call<VerfiyResponse> call, Throwable t) {
            getView().hideProgress();
            getView().showMessage(t.toString());
        }
    };

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance){
            if (call != null) call.cancel();
        }
    }
}
