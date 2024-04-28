package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.util.HibernateUtil;

public class EpreuveRepositoryImpl {
	
	public void create(Epreuve epreuve) {
		
		// Création de la variable de session
        Session session = null;
        
    	try{
    		// On récupère la session en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Création de l'épreuve
        	session.persist(epreuve);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}
    public Epreuve getEpreuveById(Long id) {
    	
    	// Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// Récupération des données
        	return session.get(Epreuve.class, id);
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }

}
