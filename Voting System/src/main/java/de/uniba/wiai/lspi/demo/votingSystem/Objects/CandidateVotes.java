package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class CandidateVotes {
	
	Candidate candidateObj;
	int voteCount;
	
	public Candidate getCandidateObj() {
		return candidateObj;
	}
	public void setCandidateObj(Candidate candidateObj) {
		this.candidateObj = candidateObj;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	@Override
	public String toString() {
		return "CandidateVotes [candidateObj=" + candidateObj + ", voteCount=" + voteCount + "]";
	}
	
	

}
