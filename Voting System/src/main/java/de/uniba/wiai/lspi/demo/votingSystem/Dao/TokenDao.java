package de.uniba.wiai.lspi.demo.votingSystem.Dao;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import de.uniba.wiai.lspi.demo.votingSystem.Objects.Token;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;

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

}
