package enm.ytps.exception;

public enum ErrorMessages {
    /**
     * FIXME ERROR CODE 정의 필요
     */
    // Common
    CONNECTION_ERROR(408, "Ad Manager Api Remote IO ERROR"), // AdManager Remote Service 연결 실패시
    INVALID_API_RESPONSE_ERROR(400, "Ad Manager Response ERROR"),
    
    // Order
    ORDER_NOT_FOUND(400, "Order not found"),
    ORDER_CREATE_FAILED(400, "Order create failed"),
    ORDER_UPDATE_FAILED(400, "Order update failed"),
    
    // LineItem
    LINE_ITEM_NOT_FOUND(400, "LineItem not found"),
    
    // Creative
    CREATIVE_NOT_FOUND(400, "Order not found"),
    
    // Team
    TEAM_NOT_FOUND(400, "Team not found"),
    TEAM_CREATE_FAILED(400, "Team create failed"),
    TEAM_UPDATE_FAILED(400, "Team update failed"),
    
    // User
    USER_NOT_FOUND(400, "User not found"),
    
    // Company
    COMPANY_NOT_FOUND(400, "Company not found"),
    COMPANY_CREATE_FAILED(400, "Company create failed"),
    COMPANY_UPDATE_FAILED(400, "Company update failed"),

    // Report
    REPORT_NOT_FOUND(400, "Report not found");
    

    private int code;
    private String message;
    
    ErrorMessages(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}
