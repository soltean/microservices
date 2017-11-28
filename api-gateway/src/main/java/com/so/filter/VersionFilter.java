package com.so.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.so.service.InvalidVersionException;
import com.so.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VersionFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private VersionService versionService;

    @Autowired
    public VersionFilter(VersionService versionService) {
        this.versionService = versionService;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String version = ctx.getRequest().getHeader("X-Protocol-Version");
        String versionUrl = versionService.findUrl(version);
        if (versionUrl == null) {
            throw new InvalidVersionException();
        }
        String url = ctx.getRequest().getRequestURI() + versionUrl;
        ctx.set("requestURI", url);
        return null;
    }
}
