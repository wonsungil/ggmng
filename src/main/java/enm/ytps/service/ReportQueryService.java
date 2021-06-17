package enm.ytps.service;

import com.google.api.ads.admanager.axis.utils.v202005.StatementBuilder;
import com.google.api.ads.admanager.axis.v202005.Column;
import com.google.api.ads.admanager.axis.v202005.DateRangeType;
import com.google.api.ads.admanager.axis.v202005.Dimension;
import com.google.api.ads.admanager.axis.v202005.ReportQuery;
import enm.ytps.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ReportQueryService {
    
    public ReportQuery generateDailyReportQuery(long[] orderIds) {
        
        // 나이   : id= 322584, name=kage
        // 성별   : id=322944, name=kgender
        // First Position id=11983454, name=yt1st
        
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setDimensions(new Dimension[] {
                Dimension.ADVERTISER_ID,
                Dimension.ORDER_ID,
                Dimension.LINE_ITEM_ID,
                Dimension.CREATIVE_ID,
                Dimension.MONTH_AND_YEAR,
                Dimension.WEEK,
                Dimension.DATE,
                Dimension.DAY,
                Dimension.AD_UNIT_ID,
                Dimension.PLACEMENT_ID,
                Dimension.DEVICE_CATEGORY_ID,
                Dimension.DEVICE_CATEGORY_NAME,
                Dimension.CUSTOM_DIMENSION
        });
        reportQuery.setCustomDimensionKeyIds(new long[] {
            322584, 322944, 11983454
        });
        
        reportQuery.setColumns(new Column[] {
                Column.AD_SERVER_IMPRESSIONS,               // 노출
                Column.TOTAL_LINE_ITEM_LEVEL_IMPRESSIONS,
                Column.AD_SERVER_CLICKS,                    // 클릭
                Column.VIDEO_INTERACTION_VIDEO_SKIPS,       // 동영상 스킵 횟수
                Column.AD_SERVER_WITH_CPD_AVERAGE_ECPM,     // 확인 필요 eCPM
                                                            // CPV  
                Column.VIDEO_VIEWERSHIP_VIEW_THROUGH_RATE,  // VTR
                                                            // 10초 VTR
                                                            // 15초 VTR
                                                            // 30초 VTR
                                                            // 보장 노출수
                Column.VIDEO_VIEWERSHIP_COMPLETION_RATE,    // 달성률
                Column.VIDEO_VIEWERSHIP_VIDEO_LENGTH,       // 소재길이
                                                        // 스킵 적용 시점
                Column.VIDEO_VIEWERSHIP_FIRST_QUARTILE,     // 중간재생(25%)
                Column.VIDEO_VIEWERSHIP_MIDPOINT,           // 중간재생(50%)
                Column.VIDEO_VIEWERSHIP_THIRD_QUARTILE,     // 중간재생(75%)
                Column.VIDEO_VIEWERSHIP_COMPLETE,           // 재생완료(논스킵)
                                                        // 10초 재생
                                                        // 20초 재생
                                                        // 30초 재생
                                                        // 유튜브채널
                                                        // 플랫폼
                Column.VIDEO_VIEWERSHIP_START
        });
        
        StringBuilder whereInOrderIds = new StringBuilder()
                .append("ORDER_ID IN (")
                .append(Arrays.stream(orderIds).mapToObj(Long::toString).collect(Collectors.joining(",")))
                .append(")");
        
        reportQuery.setStatement(new StatementBuilder()
                .where(whereInOrderIds.toString())
                .toStatement());
        
        reportQuery.setDateRangeType(DateRangeType.YESTERDAY);
        
        return reportQuery;
    }
    
    public ReportQuery generateDefaultReportQuery() {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setDimensions(new Dimension[] {
                Dimension.ORDER_ID,             // 광고 주문 ID
                Dimension.LINE_ITEM_ID,         // 광고 항목 ID
                Dimension.CREATIVE_ID,          // 광고 문안 ID
                Dimension.ADVERTISER_ID,        // 광고주 ID
                Dimension.CONTENT_BUNDLE_ID,    // 콘텐츠 번들 ID
                Dimension.DEVICE_CATEGORY_ID,   // 기기 ID
//                Dimension.TARGETING,            // 타게팅 ( OS )
                Dimension.DATE,                 // 일자
                Dimension.CUSTOM_DIMENSION,
        });

        reportQuery.setCustomDimensionKeyIds(new long[] {
                322584,                         // AGE
                322944                         // GENDER
        });

        reportQuery.setColumns(new Column[] {
                Column.AD_SERVER_IMPRESSIONS,               // 노출수
                Column.AD_SERVER_CLICKS,                    // 클릭수
                Column.VIDEO_VIEWERSHIP_FIRST_QUARTILE,     // 1분위
                Column.VIDEO_VIEWERSHIP_MIDPOINT,           // 중분위
                Column.VIDEO_VIEWERSHIP_THIRD_QUARTILE,     // 3분위
                Column.VIDEO_VIEWERSHIP_COMPLETE,           // 완료
                Column.VIDEO_VIEWERSHIP_TOTAL_ERROR_COUNT,  // 총 에러 수
                Column.VIDEO_INTERACTION_VIDEO_SKIPS,       // 건너뜀 수
                Column.VIDEO_VIEWERSHIP_ENGAGED_VIEW,       // 사용자 참여 조회
                Column.TOTAL_AD_REQUESTS
        });

        reportQuery.setStatement(new StatementBuilder().toStatement());

        reportQuery.setDateRangeType(DateRangeType.CUSTOM_DATE);
        reportQuery.setStartDate(DateUtil.convertStrDateToGoogleDate("20210112"));   // 시작일자
        reportQuery.setEndDate(DateUtil.convertStrDateToGoogleDate("20210113"));       // 종료일자

        return reportQuery;
    }
    
}
