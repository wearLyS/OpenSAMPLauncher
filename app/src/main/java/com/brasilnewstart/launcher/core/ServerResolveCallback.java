package com.brasilnewstart.launcher.core;

public interface ServerResolveCallback {
    void OnFinish(ServerConfig OutConfig);
    void OnPingFinish(ServerConfig OutConfig);
}
