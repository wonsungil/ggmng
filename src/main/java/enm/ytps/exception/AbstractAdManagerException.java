package enm.ytps.exception;

public abstract  class AbstractAdManagerException extends RuntimeException {
    
    private int code;
    
    public AbstractAdManagerException(String message) {
        super(message);
    }
    
    public AbstractAdManagerException(String message, Throwable e) {
        super(message, e);
    }
    
    public AbstractAdManagerException(int code, String message) {
        super(message);
        this.code = code;
    }
    
    public AbstractAdManagerException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
