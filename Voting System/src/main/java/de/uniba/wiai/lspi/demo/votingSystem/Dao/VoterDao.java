package de.uniba.wiai.lspi.demo.votingSystem.Dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;
import java.util.logging.Logger;

public class VoterDao {
	
	private DatastoreService datastore;
	private static final Logger log = Logger.getLogger(VoterDao.class.getName());
	

	
	public VoterDao()
	{
		datastore = DatastoreServiceFactory.getDatastoreService();

	}
	
	public Entity addVoter(Voter voterObj)
	{
		Entity voter = new Entity("Voter");
		voter.setProperty("firstName", voterObj.getFirstName());
		voter.setProperty("lastName", voterObj.getLastName());
		voter.setProperty("emailId", voterObj.getEmailId());
		datastore.put(voter);
		return voter;
	}

	public Key retrieveKey(String emailId)
	{
		Query q = new Query("Voter").setFilter(new FilterPredicate("emailId", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		return result.getKey();
		
	}
	
	public Entity updateToken(Key voterKey,Voter voterObj)
	{
		Entity voter = new Entity(voterKey);
		voter.setProperty("firstName", voterObj.getFirstName());
		voter.setProperty("lastName", voterObj.getLastName());
		voter.setProperty("emailId", voterObj.getEmailId());
		voter.setProperty("token", voterObj.getToken());
		voter.setProperty("votingStatus", voterObj.getVotingStatus());
		datastore.put(voter);
		return voter;
	}
	
	public List<Voter> getVoters()
	{
		Query q = new Query("Voter");
		log.info("inside getVoters");
		PreparedQuery pq = datastore.prepare(q);
		log.info("inside getVoters2");
		List<Entity> entityList = pq.asList(FetchOptions.Builder.withLimit(5));
		log.info("inside getVoters3");
		List<Voter> voterList =  new ArrayList<Voter>();
		log.info("inside getVoters4");
		Voter voterObj = new Voter();
		log.info("inside getVoters5");
		for(Entity entity: entityList)
		{
			log.info("inside getVoters6");
			voterObj.setFirstName(entity.getProperty("firstName").toString());
			voterObj.setLastName(entity.getProperty("lastName").toString());
			voterObj.setEmailId(entity.getProperty("emailId").toString());
			voterList.add(voterObj);
			log.info("inside getVoters8"+ voterList);
		}
		
		return voterList;
		
	}
}