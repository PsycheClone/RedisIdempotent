package com.test.camel.RedisIdempotent;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouteOne extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:copyFromOne?idempotentRepository=#redisIdempotentOne&recursive=true&noop=true&readLock=rename&delay=5000")
                .log("Moving file ${file:name}")
                .to("file:copyToOne");
    }
}
