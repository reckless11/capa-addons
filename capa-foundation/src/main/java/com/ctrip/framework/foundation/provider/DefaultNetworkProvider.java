package com.ctrip.framework.foundation.provider;

import com.ctrip.framework.foundation.NetworkInterfaceManager;
import com.ctrip.framework.foundation.spi.provider.NetworkProvider;
import com.ctrip.framework.foundation.spi.provider.Provider;

/**
 * @author Reckless Xu
 * 2021/12/5
 */
public class DefaultNetworkProvider extends AbstractProvider implements NetworkProvider {
    @Override
    public String getProperty(String name, String defaultValue) {
        if ("host.address".equalsIgnoreCase(name)) {
            String val = getHostAddress();
            return val == null ? defaultValue : val;
        } else if ("host.name".equalsIgnoreCase(name)) {
            String val = getHostName();
            return val == null ? defaultValue : val;
        } else {
            return defaultValue;
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public String getHostAddress() {
        return NetworkInterfaceManager.INSTANCE.getLocalHostAddress();
    }

    @Override
    public String getHostName() {
        return NetworkInterfaceManager.INSTANCE.getLocalHostName();
    }

    @Override
    public Class<? extends Provider> getType() {
        return NetworkProvider.class;
    }

    @Override
    public String toString() {
        return "hostName [" + getHostName() + "] hostIP [" + getHostAddress() + "] (DefaultNetworkProvider)";
    }
}

