package sunyu.demo.security.manager.util;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdUtil {
    @Value("${snowflake.worker-id}")
    Long workerId;
    @Value("${snowflake.datacenter-id}")
    Long datacenterId;

    public Snowflake snowflake;

    public Long getId() {
        if (snowflake == null) {
            snowflake = cn.hutool.core.util.IdUtil.createSnowflake(workerId, datacenterId);
        }
        return snowflake.nextId();
    }
}
