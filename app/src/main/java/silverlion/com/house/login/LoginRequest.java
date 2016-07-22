package silverlion.com.house.login;


/**
 * Created by k8190 on 2016/7/21.
 */
public class LoginRequest {

    private String account;
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


