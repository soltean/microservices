package com.so.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class VersionInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> infos = new HashMap<>();
        infos.put("1.0.0","v1.0.0");
        infos.put("2.0.0","v2.0.0");

        builder.withDetail("versions", infos);
    }

}
