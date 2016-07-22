package silverlion.com.house.net;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import silverlion.com.house.login.LoginResult;
import silverlion.com.house.register.RegisterResult;
import silverlion.com.house.register.User;
import silverlion.com.house.register.sortlistview.SortModel;

/**
 * 用户相关接口
 */
public interface UserApi {

    @POST("/OpenDC/index.php/App/Account/login")
    Call<LoginResult> Login(@Body User user);

    @GET("/OpenDC/index.php/App/Account/reg_check")
    Call<RegisterResult> Register(@Body User user);

}
