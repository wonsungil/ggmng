package enm.ytps.exception;

public class AdMngNetworkException extends AbstractAdManagerException{
    public AdMngNetworkException(String message) {
        super(message);
    }

    public AdMngNetworkException(String message, Throwable e) {
        super(message, e);
    }

    public AdMngNetworkException(int code, String message) {
        super(code, message);
    }

    public AdMngNetworkException(int code, String message, Throwable e) {
        super(code, message, e);
    }
    
}
