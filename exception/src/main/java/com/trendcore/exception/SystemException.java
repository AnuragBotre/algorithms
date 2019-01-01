package com.trendcore.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class SystemException extends RuntimeException {


    private ErrorCode errorCode;
    private Map<String, Object> properties = new TreeMap<>();
    private String errorCategory;

    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, ErrorCode errorCode,String errorCategory) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorCategory = errorCategory;
    }

    public static SystemException wrap(Throwable exception, ErrorCode errorCode,String errorCategory) {
        if (exception instanceof SystemException) {
            SystemException se = (SystemException) exception;
            if (errorCode != null && errorCode != se.getErrorCode() && errorCategory != null && !errorCategory.equals(se.getErrorCategory())) {
                return new SystemException(exception.getMessage(), exception, errorCode , errorCategory);
            }
            return se;
        } else {
            return new SystemException(exception.getMessage(), exception, errorCode , errorCategory);
        }
    }

    public static SystemException wrap(Throwable exception) {
        return wrap(exception, null , null);
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }

    public SystemException setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) properties.get(name);
    }

    public SystemException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    @Override
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            printStackTrace(new PrintWriter(s));
        }
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        synchronized (s) {
            s.println(this);
            s.println("\t-------------------------------");
            if (errorCode != null) {
                s.println("\t" + errorCode + ":" + errorCode.getClass().getName());
            }
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                s.println("\t" + entry.getKey() + "=[" + entry.getValue() + "]");
            }
            s.println("\t-------------------------------");
            StackTraceElement[] trace = getStackTrace();
            for (int i = 0; i < trace.length; i++)
                s.println("\tat " + trace[i]);

            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(s);
            }
            s.flush();
        }
    }

}
