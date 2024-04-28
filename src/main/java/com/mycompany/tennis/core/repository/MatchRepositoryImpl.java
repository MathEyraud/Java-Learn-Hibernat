package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.util.HibernateUtil;

public class MatchRepositoryImpl {
	
	public void create(Match match) {
		// Création de la variable de session
        Session session = null;
        
    	try{
    		// On récupère la session en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Création de l'entité
        	session.persist(match);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}
	public Match getMatchById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// Récupération des données
        	return session.get(Match.class, id);
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
	public void deleteMatchById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		Match match = session.get(Match.class, id);
    		session.remove(match);
    		
		} catch (Throwable e) {
            e.printStackTrace();
		}
    }
}
