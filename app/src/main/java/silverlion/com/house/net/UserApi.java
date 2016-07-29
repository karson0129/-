package silverlion.com.house.net;

import retrofit2.http.Query;
import silverlion.com.house.forget.ForgetResult;
import silverlion.com.house.forget.ForgetVerResult;
import silverlion.com.house.login.LoginResult;
import silverlion.com.house.register.RegisterResult;
import retrofit2.Call;
import retrofit2.http.POST;
import silverlion.com.house.verify.VerfiyResponse;
import silverlion.com.house.verify.VerfiyResult;

/**
 * 用户相关接口
 */
public interface UserApi {

    @POST("/OpenDC/index.php/App/Account/reg_check")
    Call<RegisterResult> Register(@Query("area_code")String ara_code,@Query("cellphone")String cellphone);

    @POST("/OpenDC/index.php/App/Account/reg_check")
    Call<RegisterResult> EmailRegister(@Query("email")String email);

    @POST("/OpenDC/index.php/App/Account/code_check")
    Call<VerfiyResult> Verfiy(@Query("code_id")String code_id,@Query("verify_code")String verfiy_code);

    @POST("/OpenDC/index.php/App/Account/register")
    Call<VerfiyResponse> LastPhoneRegister(@Query("area_code")String area,
                                           @Query("cellphone")String phone,
                                           @Query("password")String password);

    @POST("/OpenDC/index.php/App/Account/register")
    Call<VerfiyResponse> LastEmailRegister(@Query("email")String email, @Query("password")String password);

    @POST("/OpenDC/index.php/App/Account/info_confirm")
    Call<VerfiyResponse> Keep(@Query("account_id")String account,@Query("real_name")String name,
                              @Query("sex")String sex,@Query("place")String address,
                              @Query("area_code")String area,@Query("contact_phone")String phone,
                              @Query("contact_email")String email,@Query("account_head")String url);

    @POST("/OpenDC/index.php/App/Account/login")
    Call<LoginResult> Login(@Query("account")String account,@Query("password")String password);

    @POST("/OpenDC/index.php/App/Account/psd_back")
    Call<ForgetResult> Forget(@Query("area_code")String area,@Query("cellphone")String phone,@Query("action1")String action);

    @POST("/OpenDC/index.php/App/Account/psd_back")
    Call<ForgetVerResult> ForgetVer(@Query("code_id")String code_id,@Query("verify_code")String verify,
                                    @Query("cellphone")String phone,@Query("action2")String action);

    @POST("/OpenDC/index.php/App/Account/psd_back")
    Call<ForgetVerResult> NewPass(@Query("cellphone")String phone,@Query("new_password")String password,@Query("action3")String action);

    @POST("/OpenDC/index.php/App/Account/psd_back")
    Call<ForgetResult> EmailForget(@Query("email")String email,@Query("action1")String action);
}
