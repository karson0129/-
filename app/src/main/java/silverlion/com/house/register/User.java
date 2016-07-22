package silverlion.com.house.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by k8190 on 2016/7/22.
 */
@SuppressWarnings("user")
public class User {
    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
    @SerializedName("area_code")
    private String account;

    @SerializedName("cellphone")
    private String password;

    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return account;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

}
