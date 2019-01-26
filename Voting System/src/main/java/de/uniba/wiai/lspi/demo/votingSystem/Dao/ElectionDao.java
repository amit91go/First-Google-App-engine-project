package de.uniba.wiai.lspi.demo.votingSystem.Dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Election;

public class ElectionDao {
	
	private DatastoreService datastore;

	public ElectionDao()
	{
		datastore = DatastoreServiceFactory.getDatastoreService();

	}
	
	public Entity setElectionDetails(Election electionObj)
	{
		Entity election = new Entity("Election", "ElectionDetails");
		election.setProperty("electionDate", electionObj.getElectionDate());
		election.setProperty("startTime", electionObj.getStartTime());
		election.setProperty("endTime", electionObj.getEndTime());
		datastore.put(election);
		return election;
		
	}
	
	public Election getElectionDetails()
	{
		Query q = new Query("Election");
		PreparedQuery pq = datastore.prepare(q);
		Entity election = pq.asSingleEntity();
		
		Election electionObj = new Election();
		electionObj.setElectionDate(election.getProperty("electionDate").toString());
		electionObj.setStartTime(election.getProperty("startTime").toString());
		electionObj.setEndTime(election.getProperty("endTime").toString());
		
		return electionObj;
		
		
	}
}
