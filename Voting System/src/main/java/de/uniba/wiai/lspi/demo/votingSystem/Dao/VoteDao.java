package de.uniba.wiai.lspi.demo.votingSystem.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Vote;

public class VoteDao {
	
	private static final Logger log = Logger.getLogger(VoteDao.class.getName());
	private DatastoreService datastore;
	
	public VoteDao()
	{
		datastore = DatastoreServiceFactory.getDatastoreService();

	}
	
	public void addVote(Key parentKey)
	{
		Query query = new Query("Vote",parentKey);
		PreparedQuery pq = datastore.prepare(query);
		if(pq.asSingleEntity() != null)
		{
			Entity vote = pq.asSingleEntity();
			int prvVoteCount = Integer.parseInt(vote.getProperty("voteCount").toString());
			log.warning("Count " +prvVoteCount);
			Entity voteNew = new Entity("Vote",parentKey);
			voteNew.setProperty("voteCount", prvVoteCount+1);
			datastore.put(voteNew);
			datastore.delete(vote.getKey());
		}
		else
		{
		Entity vote = new Entity("Vote",parentKey);
		vote.setProperty("voteCount", 1);
		datastore.put(vote);
		}
		
	}
	
	public List<com.google.appengine.api.datastore.Entity> getVoteCount()
	{
		Query q = new Query("Vote").addSort("voteCount", SortDirection.DESCENDING);
		PreparedQuery pq = datastore.prepare(q);
		
		List<Entity> voteList = new ArrayList<Entity>();		
		
		for(Entity entity: pq.asIterable())
		{

			voteList.add(entity);
		}
		
		return voteList;
		
	}
	

	
	

}
