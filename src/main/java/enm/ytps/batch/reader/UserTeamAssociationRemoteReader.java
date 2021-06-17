package enm.ytps.batch.reader;

import com.google.api.ads.admanager.axis.v202005.User;
import com.google.api.ads.admanager.axis.v202005.UserTeamAssociation;
import enm.ytps.service.UserTeamAssociationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@StepScope
public class UserTeamAssociationRemoteReader implements ItemReader<UserTeamAssociation> {

    private final UserTeamAssociationService userTeamAssociationService;
    private List<UserTeamAssociation> utaList = new LinkedList<>();

    private int page = 1;
    private int perPage = 100;
    private int totalCount = 0;

    @Override
    public UserTeamAssociation read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(utaList.isEmpty()) {
            UserTeamAssociation[] utas = userTeamAssociationService.getUserTeamAssociations(page, perPage);

            if (utas == null) {
                return null;
            }

            utaList.addAll(Arrays.asList(utas));
            page++;
        }

        if (utaList.isEmpty()) {
            return null;
        }

        totalCount++;
        return utaList.remove(0);
    }
}
