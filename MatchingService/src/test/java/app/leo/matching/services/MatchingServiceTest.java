package app.leo.matching.services;

import app.leo.matching.demo.Applicant;
import app.leo.matching.demo.MatchingService;
import app.leo.matching.demo.Recruiter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingServiceTest {

    @Test
    public void testMatchingService() {
        Map<String, Recruiter> recruiterMap = new HashMap<>();
        recruiterMap.put("Mercy", new Recruiter("Mercy", 2));

        Map<String, Applicant> applicantMap = new HashMap();

        List<Recruiter> recruiterList = new ArrayList<Recruiter>();
        recruiterList.add(recruiterMap.get("Mercy"));
        applicantMap.put("Anderson", new Applicant("Anderson", recruiterList));

        List<Applicant> applicantList = new ArrayList<Applicant>();
        applicantList.add(applicantMap.get("Anderson"));
        recruiterMap.get("Mercy").setPreferredApplicants(applicantList);

        List<Applicant> residentsToMatch = new ArrayList<Applicant>();
        residentsToMatch.add(applicantMap.get("Anderson"));

        List<Recruiter> recruiterToMatch = new ArrayList<Recruiter>();
        recruiterToMatch.add(recruiterMap.get("Mercy"));

        List<Recruiter> hospitalMatches = new MatchingService().matching(residentsToMatch, recruiterToMatch);

        Assert.assertEquals(1, hospitalMatches.size());

        Recruiter recruiter = hospitalMatches.get(0);
        Assert.assertEquals("Mercy", recruiter.getName());
        Assert.assertEquals(recruiter.getAcceptedApplicants().get(0).getName(), "Anderson");
    }
}
