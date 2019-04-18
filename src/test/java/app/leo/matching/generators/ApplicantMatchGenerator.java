package app.leo.matching.generators;

import app.leo.matching.models.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicantMatchGenerator {

    public static long normalCount = 1;

    public static long noSwapCount = 1;

    public static long rankBetterCount = 1;

    public static long noMatchCount = 1;


    public static ApplicantMatch generateApplicantMatch(long matchId, String positionName, int capacity) {
        RecruiterMatch recruiter = new RecruiterMatch(matchId, matchId);
        Position position = new Position(positionName, capacity, recruiter);

        ApplicantMatch applicant = new ApplicantMatch(normalCount, matchId);
        ApplicantRanking applicantRank1 = new ApplicantRanking(1, matchId, applicant, position);
        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicantRank1);
        applicant.setApplicantRanking(applicantRankingList);

        RecruiterRanking recruiterRank1 = new RecruiterRanking(1, matchId, position, applicant);
        List<RecruiterRanking> recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiterRank1);
        position.setRecruiterRankings(recruiterRankingList);

        normalCount++;
        return applicant;
    }

    public static List<ApplicantMatch> generateApplicantMatchForAtCapacityNoSwap(Long matchId,String position1Name , String position2Name, int capacity1, int capacity2){
        RecruiterMatch recruiter = new RecruiterMatch(matchId, matchId);
        Position position1 = new Position(position1Name, capacity1, recruiter);
        Position position2 = new Position(position2Name, capacity2,recruiter);

        //applicants 1
        ApplicantMatch applicant1 = new ApplicantMatch(noSwapCount,matchId );
        ApplicantRanking applicant1Ranking1 = new ApplicantRanking(1,matchId,applicant1,position1);
        ApplicantRanking applicant1Ranking2 = new ApplicantRanking(2,matchId,applicant1,position2);
        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicant1Ranking1);
        applicantRankingList.add(applicant1Ranking2);
        applicant1.setApplicantRanking(applicantRankingList);
        noSwapCount++;


        ApplicantMatch applicant2 = new ApplicantMatch(noSwapCount,matchId );
        ApplicantRanking applicant2Ranking1 = new ApplicantRanking(1,matchId,applicant2,position1);
        ApplicantRanking applicant2Ranking2 = new ApplicantRanking(2,matchId,applicant2,position2);
         applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicant1Ranking1);
        applicantRankingList.add(applicant1Ranking2);
        applicant2.setApplicantRanking(applicantRankingList);
        noSwapCount++;

        //position1 Ranking
        RecruiterRanking recruiter1Ranking1 = new RecruiterRanking(1,matchId,position1,applicant1);
        RecruiterRanking recruiter1Ranking2 = new RecruiterRanking(2,matchId,position1,applicant2);
        List<RecruiterRanking> recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiter1Ranking1);
        recruiterRankingList.add(recruiter1Ranking2);
        position1.setRecruiterRankings(recruiterRankingList);

        //position2 Ranking
        RecruiterRanking recruiter2Ranking1 = new RecruiterRanking(1,matchId,position2,applicant1);
        RecruiterRanking recruiter2Ranking2 = new RecruiterRanking(2,matchId,position2,applicant2);
        recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiter2Ranking1);
        recruiterRankingList.add(recruiter2Ranking2);
        position2.setRecruiterRankings(recruiterRankingList);

        List<ApplicantMatch> applicantMatchList = new ArrayList<ApplicantMatch>();
        applicantMatchList.add(applicant1);
        applicantMatchList.add(applicant2);

        return applicantMatchList;

    }

    public static List<ApplicantMatch> generateApplicantMatchForRankisBetter(Long matchId,String position1Name , String position2Name, int capacity1, int capacity2){
        RecruiterMatch recruiter = new RecruiterMatch(matchId, matchId);
        Position position1 = new Position(position1Name, capacity1, recruiter);
        Position position2 = new Position(position2Name, capacity2,recruiter);

        //applicants 1
        ApplicantMatch applicant1 = new ApplicantMatch(rankBetterCount,matchId );
        ApplicantRanking applicant1Ranking1 = new ApplicantRanking(1,matchId,applicant1,position1);
        ApplicantRanking applicant1Ranking2 = new ApplicantRanking(2,matchId,applicant1,position2);
        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicant1Ranking1);
        applicantRankingList.add(applicant1Ranking2);
        applicant1.setApplicantRanking(applicantRankingList);

        rankBetterCount++;


        ApplicantMatch applicant2 = new ApplicantMatch(rankBetterCount,matchId );
        ApplicantRanking applicant2Ranking1 = new ApplicantRanking(1,matchId,applicant2,position1);
        ApplicantRanking applicant2Ranking2 = new ApplicantRanking(2,matchId,applicant2,position2);
        applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicant1Ranking1);
        applicantRankingList.add(applicant1Ranking2);
        applicant2.setApplicantRanking(applicantRankingList);

        rankBetterCount++;

        //position1 Ranking
        RecruiterRanking recruiter1Ranking1 = new RecruiterRanking(2,matchId,position1,applicant1);
        RecruiterRanking recruiter1Ranking2 = new RecruiterRanking(1,matchId,position1,applicant2);
        List<RecruiterRanking> recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiter1Ranking1);
        recruiterRankingList.add(recruiter1Ranking2);
        position1.setRecruiterRankings(recruiterRankingList);

        //position2 Ranking
        RecruiterRanking recruiter2Ranking1 = new RecruiterRanking(1,matchId,position2,applicant1);
        RecruiterRanking recruiter2Ranking2 = new RecruiterRanking(2,matchId,position2,applicant2);
        recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiter2Ranking1);
        recruiterRankingList.add(recruiter2Ranking2);
        position2.setRecruiterRankings(recruiterRankingList);

        List<ApplicantMatch> applicantMatchList = new ArrayList<ApplicantMatch>();
        applicantMatchList.add(applicant1);
        applicantMatchList.add(applicant2);
        return applicantMatchList;

    }

    public static ApplicantMatch generateApplicationMatchForNoMatchWereMade(Long matchId, String positionName, int capacity){
        RecruiterMatch recruiter = new RecruiterMatch(matchId, matchId);
        Position position = new Position(positionName, capacity, recruiter);

        ApplicantMatch applicant = new ApplicantMatch(noMatchCount, matchId);
        ApplicantRanking applicantRank1 = new ApplicantRanking(1, matchId, applicant, position);
        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicantRank1);
        applicant.setApplicantRanking(applicantRankingList);

        noMatchCount++;

        List<RecruiterRanking> recruiterRankingList = new ArrayList<>();
        position.setRecruiterRankings(recruiterRankingList);

        return applicant;
    }

    public static List<Position> getAllPositionsFromMatch(List<ApplicantMatch> applicantMatchList){
        List<ApplicantMatch> applicantMatches= applicantMatchList;
        List<Position> positions = new ArrayList<Position>();

        for(ApplicantMatch applicantMatch:applicantMatches){
            List<ApplicantRanking> applicantRankingList =applicantMatch.getApplicantRanking();

            for(ApplicantRanking applicantRanking:applicantRankingList){

                Position position = applicantRanking.getPosition();

                if(positions.contains(position)){
                    continue;
                }

                positions.add(position);
            }
        }

        return positions;
    }
}
