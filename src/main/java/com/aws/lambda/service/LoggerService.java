package com.aws.lambda.service;

public interface LoggerService {

    /**
     * Save the info log into file
     *
     * @param logInfo
     */
    void info(String logInfo);

    /**
     * Save the error log into file
     *
     * @param logInfo
     */
    void error(String logError);

}
