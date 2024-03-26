package org.jeecg.biz.salary.exception;

public class JeecgSalaryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JeecgSalaryException(String message){
        super(message);
    }

    public JeecgSalaryException(Throwable cause)
    {
        super(cause);
    }

    public JeecgSalaryException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
