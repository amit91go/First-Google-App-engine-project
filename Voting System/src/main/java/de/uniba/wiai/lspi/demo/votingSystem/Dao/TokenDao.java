package de.uniba.wiai.lspi.demo.votingSystem.Dao;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Token;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;
import static java.lang.Math.toIntExact;

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

}
