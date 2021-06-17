package enm.ytps.dto.customTargeting.mixIn;

public enum CriteriaType {
    AUDIENCESEGMENT(Values.AUDIENCESEGMENT), CMSMETADATA(Values.CMSMETADATA), CUSTOM(Values.CUSTOM), SET(Values.SET);

    private String type;
    
    private CriteriaType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public static class Values {
        public static final String AUDIENCESEGMENT = "AudienceSegment";
        public static final String CMSMETADATA = "CmsMetaData";
        public static final String CUSTOM = "Custom";
        public static final String SET = "CustomCriteriaSet";
    }
}
