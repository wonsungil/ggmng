package enm.ytps.exception;

public class AdMngNotFoundException extends AbstractAdManagerException {
    public AdMngNotFoundException(String message) {
        super(message);
    }
    
    public AdMngNotFoundException(String message, Throwable e) {
        super(message, e);
    }

    public AdMngNotFoundException(int code, String message) {
        super(code, message);
    }

    public AdMngNotFoundException(int code, String message, Throwable e) {
        super(code, message, e);
    }
}
