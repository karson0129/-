package silverlion.com.house.houselist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import silverlion.com.house.R;
import silverlion.com.house.commous.ActivityUtils;

/**

 */
public class HouseListFragment extends MvpFragment<HouseListView,HouseListPersenter> implements HouseListView {
    private ActivityUtils activityUtils;
    private List<HouseListResult> housedata = new ArrayList<HouseListResult>();
    private final String HOUSELIST = "http://casadiario.com/OpenDC/index.php/App/House/house_list";
    private HouseListAdapter adapter;
    private ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        activityUtils = new ActivityUtils(this);
        View view = inflater.inflate(R.layout.fragment_house_list, container, false);
        list = (ListView) view.findViewById(R.id.list);
        init();
        return view;
    }

    private void init(){
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.POST, HOUSELIST, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("result",responseInfo.result);
                if (responseInfo.result == null){
                    activityUtils.showToast("unknow erron");
                    return;
                }
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONObject house_1 = object.getJSONObject("h2016052410413");
                    housedata.add(new HouseListResult(house_1.getString("id"),house_1.getString("0"),
                            house_1.getString("place"),house_1.getString("address"),house_1.getString("reg_time")));
                    JSONObject house_2 = object.getJSONObject("h2016052474236");
                    housedata.add(new HouseListResult(house_2.getString("id"),house_2.getString("0"),
                            house_2.getString("place"),house_2.getString("address"),house_2.getString("reg_time")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                activityUtils.showToast("链接失败，请检查网络连接");
            }
        });
        if (housedata != null){
        adapter = new HouseListAdapter(getContext(),housedata);
        list.setAdapter(adapter);
        }
    }
    //更新数据源
    public void UpdateList(List<HouseListResult> results){
        adapter.updateListView(results);
    }

    @Override
    public HouseListPersenter createPresenter() {
        return new HouseListPersenter();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }
}
