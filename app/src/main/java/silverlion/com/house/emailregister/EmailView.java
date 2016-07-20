package silverlion.com.house.emailregister;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by k8190 on 2016/7/20.
 */
public interface EmailView extends MvpView {

    void showProgress();

    void hideProgress();

    void showMessage();
}
