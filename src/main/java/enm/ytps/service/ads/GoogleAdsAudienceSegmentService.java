package enm.ytps.service.ads;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleAdsAudienceSegmentService {

    @Autowired
    private AudienceSegmentServiceInterface amAudienceSegmentService;

    /**
     * AM360 오디언스 세그먼트 리스트 조회
     * @param name 오디언스 타게팅 명
     * @param page 현재 페이지
     * @param perPage 페이지 사이즈
     * @return 오디언스 세그먼트 리스트
     * @throws RemoteException
     */
    public AudienceSegment[] getFirstPartyAudienceSegmentList(String name, String status, int page, int perPage) throws RemoteException {
        AudienceSegmentPage audienceSegmentPage = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .where("type = 'FIRST_PARTY' AND status = :status")
                .withBindVariableValue("status", status);

        if (perPage > 0) {
            statementBuilder.limit(perPage);
        }

        if (name!=null && !name.equals("")) {
            statementBuilder.where("name LIKE '%:name%'").withBindVariableValue("name", name);
        }

        if (page > 1) {
            statementBuilder.increaseOffsetBy((page - 1) * perPage);
        }

        try {
            audienceSegmentPage = amAudienceSegmentService.getAudienceSegmentsByStatement(statementBuilder.toStatement());

        } catch (ApiException e) {
            log.error("[GoogleAdsAudienceSegmentService] ERROR : {}", e.getMessage());
            if (e.getErrors() != null) {
                for (ApiError apiError : e.getErrors()) log.error(apiError.toString());
                throw new RuntimeException(e.getErrors(0).toString());
            }
            throw e;
        }  catch (RemoteException e) {
            log.error("[GoogleAdsAudienceSegmentService] ERROR : {}", e.getMessage());
            throw e;
        }

        if (audienceSegmentPage == null) {
            log.error("[GoogleAdsContentBundleService] ERROR PAGE IS NULL");
            throw new NullPointerException("[GoogleAdsContentBundleService] ERROR PAGE IS NULL");
        }

        return audienceSegmentPage.getResults();
    }

    /**
     * AM360 오디언스 세그먼트 리스트 조회
     * @param name 오디언스 타게팅 명
     * @return 오디언스 세그먼트 리스트
     * @throws RemoteException
     */
    public AudienceSegment[] getFirstPartyAudienceSegmentList(String name, String status) throws RemoteException {
        return getFirstPartyAudienceSegmentList(name, status, 0, 0);
    }

    public ArrayList<RuleBasedFirstPartyAudienceSegment> getFirstPartyAudienceSegmentList2(String name, String status) throws RemoteException {
        ArrayList<RuleBasedFirstPartyAudienceSegment> list = new ArrayList<>();
        for (AudienceSegment audienceSegment : getFirstPartyAudienceSegmentList(name, status, 0, 0)) {
            list.add((RuleBasedFirstPartyAudienceSegment) audienceSegment);
        }
        return list;
    }
    
    public AudienceSegment[] getFirstPartyAudienceSegmentListWhereInIds(long[] ids) throws RemoteException {
        AudienceSegmentPage audienceSegmentPage = null;

        StatementBuilder statementBuilder = new StatementBuilder()
                .where("type = 'FIRST_PARTY' AND id in ("+ Arrays.stream(ids).mapToObj(Long::toString).collect(Collectors.joining(","))+")");
        try {
            audienceSegmentPage = amAudienceSegmentService.getAudienceSegmentsByStatement(statementBuilder.toStatement());

        } catch (ApiException e) {
            log.error("[GoogleAdsAudienceSegmentService] ERROR : {}", e.getMessage());
            if (e.getErrors() != null) {
                for (ApiError apiError : e.getErrors()) log.error(apiError.toString());
                throw new RuntimeException(e.getErrors(0).toString());
            }
            throw e;
        }  catch (RemoteException e) {
            log.error("[GoogleAdsAudienceSegmentService] ERROR : {}", e.getMessage());
            throw e;
        }

        if (audienceSegmentPage == null) {
            log.error("[GoogleAdsContentBundleService] ERROR PAGE IS NULL");
            throw new NullPointerException("[GoogleAdsContentBundleService] ERROR PAGE IS NULL");
        }

        return audienceSegmentPage.getResults();
    }
    
}
