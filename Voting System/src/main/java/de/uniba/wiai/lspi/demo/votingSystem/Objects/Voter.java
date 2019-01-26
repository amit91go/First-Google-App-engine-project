package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class Voter {
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String token;
	private int votingStatus;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}	
	public int getVotingStatus() {
		return votingStatus;
	}
	public void setVotingStatus(int votingStatus) {
		this.votingStatus = votingStatus;
	}
	@Override
	public String toString() {
		return "Voter [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", token=" + token
				+ "]";
	}
	
	

}
