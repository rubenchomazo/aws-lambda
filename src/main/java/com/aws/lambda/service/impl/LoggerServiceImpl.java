package com.aws.lambda.service.impl;

import com.aws.lambda.service.LoggerService;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerServiceImpl implements LoggerService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoggerServiceImpl.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private LoggerServiceImpl instance;

    public LoggerServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoggerServiceImpl();
        }
        return instance;
    }

    @Override
    public void info(String logInfo) {
        StringBuffer logInfoBuff = new StringBuffer("Info - ");
        logInfoBuff.append(sdf.format(new Date()) + " - ");
        logInfoBuff.append("Message: " + logInfo);
        log.info(logInfoBuff.toString());
    }

    @Override
    public void error(String logError) {
        StringBuffer logErrBuff = new StringBuffer("Error - ");
        logErrBuff.append(sdf.format(new Date()) + " - ");
        logErrBuff.append("Message: " + logError);
        log.info(logErrBuff.toString());
    }

}
