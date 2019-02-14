package de.uniba.wiai.lspi.demo.votingSystem.Dao;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Token;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;
import static java.lang.Math.toIntExact;

import java.util.List;

public class TokenDao {
	
	private DatastoreService datastore;
	

	
	public TokenDao()
	{
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Entity addToken(Token tokenObj, Key voterKey)
	{
		Entity token = new Entity("Token", voterKey);
		token.setProperty("token", tokenObj.getTokenid());
		token.setProperty("votingStatus", tokenObj.getVotingStatus());
		datastore.put(token);
		return token;
	}
	
	public Token getToken(Key parentKey)
	{
		Query query = new Query("Token",parentKey);
		PreparedQuery pq = datastore.prepare(query);
		if(pq.asSingleEntity() != null)
		{
		Entity token = pq.asSingleEntity();
		Token tokenObj = new Token();
		tokenObj.setTokenid(token.getProperty("token").toString());
		tokenObj.setVotingStatus(Integer.parseInt(token.getProperty("votingStatus").toString()));
		return tokenObj;
		}
		else
		{
			return null;
		}
	}
	
	public void updateTokenStatus(Key parentKey)
	{
		Query query = new Query("Token",parentKey);
		PreparedQuery pq = datastore.prepare(query);
		if(pq.asSingleEntity() != null)
		{
			Entity token = pq.asSingleEntity();
			Entity tokenNew = new Entity("Token",parentKey);
			tokenNew.setProperty("token", token.getProperty("token").toString());
			tokenNew.setProperty("votingStatus", 0);
			datastore.put(tokenNew);
			datastore.delete(token.getKey());
		}
		
	}
	
	public boolean checkTokenStatus(Key parentKey, String token)
	{
		Query query = new Query("Token",parentKey).setFilter(new FilterPredicate("token", FilterOperator.EQUAL,token ));
		PreparedQuery pq = datastore.prepare(query);
		if(pq.asSingleEntity() != null)
		{
			Entity tokenEntity = pq.asSingleEntity();
			if(Integer.parseInt(tokenEntity.getProperty("votingStatus").toString()) == 1)
				return true;
			else
				return false;
		}
		return false;	
	}
	
	public int getExpiredTokens()
	{
		Query q = new Query("Token").setFilter(new FilterPredicate("votingStatus", FilterOperator.EQUAL,0));
		List<Entity> pq = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		return pq.size();
	}
}

	
