package com.mycompany.tennis.core.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.util.HibernateUtil;

public class JoueurRepositoryImpl {
	 
	public void create(Joueur joueur) {
		
        // Création de la variable de session
        Session session = null;
        
    	try{
    		// On récupère la session en cours
    		session = HibernateUtil.getCurrentSession();
    		
        	// Création de l'entité
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
    		
        	// Récupération des données
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
    		
    		// Suppression des données
    		session.remove(joueur);
    		// Ancienne méthode : session.delete(joueur);
        	
		} catch (Throwable e) {
            e.printStackTrace();
		}
	}
    // Méthode avec HQL
    public List<Joueur> getJoueurs() {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// On récupère les données
    		Query<Joueur> query = session.createQuery("SELECT j FROM Joueur j", Joueur.class);
    		List<Joueur> joueurs = query.getResultList();
    		
        	// Récupération des données
        	return joueurs;
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
    // Méthode avec HQL
    public List<Joueur> getJoueursBySexe(char sexe) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// On récupère les données
    		/**
    		 * Utilisation de createQuery avec une requête HQL explicite
    		 * Cette ligne crée une requête HQL explicitement définie 
    		 * pour sélectionner les entités Joueur en fonction du sexe. 
    		 * Dans cette requête, vous définissez la logique de sélection en utilisant une syntaxe similaire au SQL, 
    		 * mais en utilisant les noms des entités et de leurs attributs plutôt que les noms des tables et des colonnes.
    		 */
    		//Query<Joueur> query = session.createQuery("SELECT j FROM Joueur j WHERE j.sexe=?1", Joueur.class);
    		
    		/**
    		 * Utilisation de createNamedQuery avec une requête nommée
    		 * Cette ligne crée une requête nommée prédéfinie dans les métadonnées de l'entité Joueur. 
    		 * Une requête nommée est une requête HQL précompilée qui est associée à une clé unique 
    		 * dans le contexte de persistance. Cette approche offre l'avantage de la réutilisabilité 
    		 * et de la modularité, car vous pouvez définir des requêtes HQL dans des fichiers de configuration XML 
    		 * ou des annotations et les référencer par leur nom dans le code. 
    		 * Cela permet également une meilleure séparation des préoccupations, 
    		 * car la logique de la requête est définie ailleurs dans le code ou dans des fichiers de configuration.
    		 */
    		Query<Joueur> query = session.createNamedQuery("getJoueursBySexe", Joueur.class);
    		
    		query.setParameter(1, sexe);
    		List<Joueur> joueurs = query.getResultList();
    		
        	// Récupération des données
        	return joueurs;
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
}
