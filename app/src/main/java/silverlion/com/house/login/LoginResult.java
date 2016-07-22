package silverlion.com.house.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by k8190 on 2016/7/21.
 */
@SuppressWarnings("Login")
public class LoginResult {
//    app用户id	account_id
//    app用户是否填写了信息	no_file

    @SerializedName("account_id")
    private int accout;

    @SerializedName("no_file")
    private int no_file;

    public void setAccout(int accout){
        this.accout = accout;
    }

    public int getAccout(){
        return accout;
    }
    public void setNo_file(int no_file){
        this.no_file = no_file;
    }

    public int getNo_file(){
        return no_file;
    }
}
