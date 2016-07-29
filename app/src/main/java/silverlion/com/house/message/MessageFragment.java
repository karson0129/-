package silverlion.com.house.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import silverlion.com.house.R;


public class MessageFragment extends MvpFragment<MessageView,MessagePersenter> implements MessageView {

    @Override
    public MessagePersenter createPresenter() {
        return new MessagePersenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message,null);

        return view;
    }
}
