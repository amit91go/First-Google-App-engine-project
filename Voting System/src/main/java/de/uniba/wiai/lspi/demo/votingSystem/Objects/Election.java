package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class Election {
	
	private String electionDate;
	private String startTime;
	private String endTime;
	
	public String getElectionDate() {
		return electionDate;
	}
	public void setElectionDate(String electionDate) {
		this.electionDate = electionDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "Election [electionDate=" + electionDate + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	
}
