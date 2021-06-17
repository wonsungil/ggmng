package enm.ytps.service;

import com.google.api.ads.admanager.axis.v201908.VideoPositionType;

import com.google.api.ads.admanager.axis.v202005.VideoPositionTarget;
import enm.ytps.service.ads.GoogleAdsPqlService;
import org.springframework.stereotype.Service;

@Service
public class VideoPositionTargetService {
    
    private GoogleAdsPqlService googleAdsPqlService;
    
    public VideoPositionTargetService(GoogleAdsPqlService googleAdsPqlService) {
        this.googleAdsPqlService = googleAdsPqlService;
    }
    
    public VideoPositionType[] getVideoPositionList() {
        /**
         * TODO : 포지션 별 위치에 대한 처리 추가 필요, 단순히 rollIndex 값만 채우는 건지 확인 필요
         */
        return new VideoPositionType[] {
                VideoPositionType.ALL,
                VideoPositionType.PREROLL,
                VideoPositionType.MIDROLL,
                VideoPositionType.POSTROLL,
                VideoPositionType.UNKNOWN
        };
    }
}
