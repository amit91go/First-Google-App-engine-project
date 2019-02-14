package de.uniba.wiai.lspi.demo.votingSystem.Dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;

public class CandidateDao {
	
	private DatastoreService datastore;
	
	public CandidateDao()
	{
		datastore = DatastoreServiceFactory.getDatastoreService();

	}
	
	public Entity addCandidate(Candidate candidateObj)
	{
		Entity candidate = new Entity("Candidate");
		candidate.setProperty("firstName", candidateObj.getFirstName());
		candidate.setProperty("lastName", candidateObj.getLastName());
		candidate.setProperty("faculty", candidateObj.getFaculty());
		datastore.put(candidate);
		return candidate;
	}
	
	public Candidate getCandiadate(Key candidateKey)
	{
		Entity candidate;
		Candidate candidateObj = new Candidate(); 
		try {
				candidate = datastore.get(candidateKey);
				candidateObj.setFirstName(candidate.getProperty("firstName").toString());
				candidateObj.setLastName(candidate.getProperty("lastName").toString());
				candidateObj.setFaculty(candidate.getProperty("faculty").toString());
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return candidateObj;
	}
	
	public List<Candidate> getCandiadates()
	{
		Query q = new Query("Candidate");
		PreparedQuery pq = datastore.prepare(q);
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		
		for(Entity entity: pq.asIterable())
		{
			Candidate candidateObj = new Candidate();
			candidateObj.setFirstName(entity.getProperty("firstName").toString());
			candidateObj.setLastName(entity.getProperty("lastName").toString());
			candidateObj.setFaculty(entity.getProperty("faculty").toString());	
			candidateList.add(candidateObj);
		}
		
		return candidateList;
		
	}
	
	public Key getCandiateKey(String firstName)
	{
		Query query = new Query("Candidate").setFilter(new FilterPredicate("firstName", FilterOperator.EQUAL,firstName ));
		PreparedQuery pq = datastore.prepare(query);
		if(pq.asSingleEntity() != null)
		{
			return pq.asSingleEntity().getKey();
		}
		else
		{
		return null;
		}
		
	}

}