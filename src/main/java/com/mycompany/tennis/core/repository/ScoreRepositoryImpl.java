package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.util.HibernateUtil;

public class ScoreRepositoryImpl {
	
	// TODO : A FAIRE
	public void create(Match match) {}
	public Score getScoreById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Récupération des données
        	return session.get(Score.class, id);
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
	public void deleteScoreById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		Score score = session.get(Score.class, id);
    		session.remove(score);
    		
		} catch (Throwable e) {
            e.printStackTrace();
		}
    }
}
