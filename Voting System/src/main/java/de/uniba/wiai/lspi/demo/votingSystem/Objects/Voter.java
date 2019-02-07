package de.uniba.wiai.lspi.demo.votingSystem.Objects;

public class Voter {
	
	/*private String firstName;
	private String lastName;
	private String token;
	private int votingStatus;*/
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Voter [emailId=" + emailId + "]";
	}

	

	
	

}
