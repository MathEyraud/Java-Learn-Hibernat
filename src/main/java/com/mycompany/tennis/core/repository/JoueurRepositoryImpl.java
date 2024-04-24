package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.util.HibernateUtil;

public class JoueurRepositoryImpl {
	 
	public void create(Joueur joueur) {
		
        // Création de la variable de session
        Session session = null;
        
    	try{
    		// On récupère la session en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Création du joueur
        	session.persist(joueur);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}
    public Joueur getJoueurById(Long id) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		Joueur joueur = session.get(Joueur.class, id);
    		
        	// Récupération du joueur
        	return joueur;
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
    public void delete(Long id) {
    	
    	// Création du joueur à supprimer partir de son id
    	Joueur joueur = getJoueurById(id);
    	
    	// Création de la variable de session
    	Session session = null;
        
    	try{
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// Suppression du joueur
    		session.remove(joueur);
    		// Ancienne méthode : session.delete(joueur);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}

}
