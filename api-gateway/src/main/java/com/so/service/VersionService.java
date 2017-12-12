package com.so.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class VersionService {

    private Map<String, String> versionAndUrl = new HashMap<>();

    @PostConstruct
    private void buildVersions(){
        versionAndUrl.put("ic1.9.0", "/ic/v1.9.0");
        versionAndUrl.put("ic2.1.0", "/ic/v2.1.0");
    }

    public String findUrl(String version){
        return versionAndUrl.get(version);
    }
}
