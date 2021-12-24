package com.brasilnewstart.launcher;

import android.app.Application;
import android.content.Context;

import com.brasilnewstart.launcher.core.SAMP.SAMPInstaller;

public class LauncherApplication extends Application {
    private Context _Context;
    public UserConfig userConfig;
    public SAMPInstaller Installer;

    public void onCreate(){
        super.onCreate();

        this._Context = this.getApplicationContext();
        this.userConfig = new UserConfig(this.getApplicationContext(),
                                         this.getApplicationContext().getString(R.string.user_config_name));

        this.Installer = new SAMPInstaller(this._Context);
    }
}
