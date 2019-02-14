package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class Vote {
	
	int voteCount;

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Vote [voteCount=" + voteCount + "]";
	}
	
	

}
