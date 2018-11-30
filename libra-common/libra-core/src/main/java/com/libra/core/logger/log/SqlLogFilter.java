package com.libra.core.logger.log;

import com.libra.core.logger.context.SqlHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * @author Libra
 * @date 2018/11/28
 * @description
 */
@Slf4j
public class SqlLogFilter implements Log {
    public SqlLogFilter(String clazz) {

    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
        SqlHolder.addSqlInfo(s);
    }

    @Override
    public void error(String s) {
        log.error(s);
        SqlHolder.addSqlInfo(s);
    }

    @Override
    public void debug(String s) {
        if (log.isDebugEnabled()) {
            log.debug(s);
        }
        SqlHolder.addSqlInfo(s);
    }

    @Override
    public void trace(String s) {
        if (log.isDebugEnabled()) {
            log.debug(s);
        }
        SqlHolder.addSqlInfo(s);
    }

    @Override
    public void warn(String s) {
        if (log.isDebugEnabled()) {
            log.warn(s);
        }
        SqlHolder.addSqlInfo(s);
    }
}
