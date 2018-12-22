/*
 * LogIpConfig.java    1.0  2018年7月12日
 *
 * 沈阳成林健康科技有限公司
 *
 */

package com.github.bus.common.configure;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * swagger2Config
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-09-28 zy 初版
 */
@Slf4j
public class LogIpConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        return "Unknown";
    }

}
