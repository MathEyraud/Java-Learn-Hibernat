package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.util.HibernateUtil;

public class TournoiRepositoryImpl {
	
	public void create(Tournoi tournoi) {
		// Création de la variable de session
        Session session = null;
        
    	try{
    		// On récupère la session en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Création du joueur
        	session.persist(tournoi);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}
	public Tournoi getTournoiById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Récupération du joueur
        	return session.get(Tournoi.class, id);
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
    public void delete(Long id) {
    	// Création du joueur à supprimer partir de son id
    	Tournoi tournoi = getTournoiById(id);
    	
    	// Création de la variable de session
    	Session session = null;
        
    	try{
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// Suppression du joueur
    		session.remove(tournoi);
    		
    		// Ancienne méthode : session.delete(joueur);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
    }
    	
}