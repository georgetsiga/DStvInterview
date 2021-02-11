package com.sample.dstvinterview.data;

import android.content.Context;

import com.sample.dstvinterview.data.model.LoggedInUser;
import com.sample.dstvinterview.util.Constants;
import com.sample.dstvinterview.util.SharedPreferenceManagers;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout(Context context) {
        user = null;
        dataSource.logout();
        SharedPreferenceManagers sharedPreferenceManagers = new SharedPreferenceManagers(context);
        sharedPreferenceManagers.clear();
    }

    public Result<LoggedInUser> login(String username, String password, Context context) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData(), context);
        }
        return result;
    }

    private void setLoggedInUser(LoggedInUser user, Context context) {
        this.user = user;
        SharedPreferenceManagers sharedPreferenceManagers = new SharedPreferenceManagers(context);
        sharedPreferenceManagers.setString(Constants.USER_ID_KEY, user.getUserId());
        sharedPreferenceManagers.setString(Constants.DISPLAY_NAME_KEY, user.getDisplayName());
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}