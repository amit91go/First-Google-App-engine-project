package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class Token {
	
	private String tokenid;
	private int votingStatus;
	
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public int getVotingStatus() {
		return votingStatus;
	}
	public void setVotingStatus(int votingStatus) {
		this.votingStatus = votingStatus;
	}
	
	@Override
	public String toString() {
		return "Token [tokenid=" + tokenid + ", votingStatus=" + votingStatus + "]";
	}
	
	

}
