package enm.ytps.controller.api;

import enm.ytps.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/team")
@Controller
public class TeamController {
    
    private TeamService teamService;
    
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
    public ResponseEntity team(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamService.getTeamById(teamId), HttpStatus.OK);
    }
}
