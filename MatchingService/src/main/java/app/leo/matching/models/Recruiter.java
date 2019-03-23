package app.leo.matching.models;

import java.util.ArrayList;
import java.util.List;

public class Recruiter {

    private String name;
    private int capacity;
    private List<Applicant> acceptedApplicants = new ArrayList<>();
    private List<Applicant> preferredApplicants = new ArrayList<>();

    public Recruiter(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Applicant acceptApplicant(Applicant res) {
        Applicant removedApplicant = null;
        if (atCapacity()) {
            removedApplicant = removeLastAcceptedApplicant();
        }
        if (acceptedApplicants.isEmpty() || isRankedBetterThan(lastAcceptedApplicant(), res)) {
            acceptedApplicants.add(res);
        } else {
            for (int i = 0; i < acceptedApplicants.size(); i++) {
                if (isRankedBetterThan(res, acceptedApplicants.get(i))) {
                    acceptedApplicants.add(i, res);
                    break;
                }
            }
        }
        return removedApplicant;
    }

    public boolean willAcceptApplicant(Applicant res) {
        if (preferredApplicants.contains(res)) {
            if (!atCapacity()) {
                return true;
            } else {
                // Find the acceptedApplicants to see if an existing one
                // is ranked lower.
                if (isRankedBetterThan(res, lastAcceptedApplicant())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean atCapacity() {
        return (acceptedApplicants.size() >= capacity);
    }

    public boolean isRankedBetterThan(Applicant res1, Applicant res2) {
        return preferredApplicants.indexOf(res1) < preferredApplicants.indexOf(res2);
    }

    public Applicant lastAcceptedApplicant() {
        return acceptedApplicants.get(acceptedApplicants.size() - 1);
    }

    public Applicant removeLastAcceptedApplicant() {
        return acceptedApplicants.remove(acceptedApplicants.size() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Applicant> getAcceptedApplicants() {
        return acceptedApplicants;
    }

    public void setAcceptedApplicants(List<Applicant> acceptedApplicants) {
        this.acceptedApplicants = acceptedApplicants;
    }

    public List<Applicant> getPreferredApplicants() {
        return preferredApplicants;
    }

    public void setPreferredApplicants(List<Applicant> preferredApplicants) {
        this.preferredApplicants = preferredApplicants;
    }
}
