package com.github.lernejo.sample.spring.httpdatabase.adapters.http;

import org.slf4j.MDC;

class ErrorInfo {
    public final String exMessage;
    public final String correlationId;

    ErrorInfo(Exception ex) {
        this.exMessage = ex.getLocalizedMessage();
        this.correlationId = MDC.get(CorrelationIdFilter.MDC_KEY);
    }
}
