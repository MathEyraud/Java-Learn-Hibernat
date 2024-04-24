package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.util.HibernateUtil;

public class MatchRepositoryImpl {
	
	// TODO : A FAIRE
	public void create(Match match) {
		
	}
	public Match getMatchById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Récupération du joueur
        	return session.get(Match.class, id);
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }

}
