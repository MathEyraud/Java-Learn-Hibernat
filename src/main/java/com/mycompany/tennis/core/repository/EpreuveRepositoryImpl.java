package com.mycompany.tennis.core.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
    // Méthode avec HQL + Jointures + Dynamic fetching
    public List<Epreuve> getEpreuvesByCodeTournoi(String codeTournoi) {
    	
        // Création de la variable de session
    	Session session = null;
        
    	try{
    		
    		// On récupère la sessions en cours
    		session = HibernateUtil.getCurrentSession();
    		
    		// On récupère les données
    		
    		/**
    		 * cette requête ne récupère que les entités Epreuve, 
    		 * sans charger les entités Tournoi associées. 
    		 * Cela signifie que lorsqu'un résultat est renvoyé, 
    		 * chaque instance d'Epreuve aura une référence à son tournoi, 
    		 * mais cette référence sera une "proxy" et ne sera pas chargée en mémoire 
    		 * tant que vous n'accéderez pas explicitement à ses propriétés.
    		 */
    		//Query<Epreuve> query = session.createQuery("SELECT e FROM Epreuve e WHERE e.tournoi.code = ?1", Epreuve.class);
    		
    		/**
    		 * Cette requête est similaire à la première, 
    		 * mais elle utilise join fetch pour récupérer les entités Tournoi associées lors de la requête. 
    		 * Cela signifie que lorsque les résultats sont récupérés, 
    		 * non seulement les entités Epreuve sont chargées en mémoire, 
    		 * mais aussi les entités Tournoi associées. 
    		 * Cela peut être avantageux si vous avez besoin d'accéder aux propriétés du tournoi 
    		 * sans déclencher de requêtes supplémentaires à la base de données.
    		 */
    		Query<Epreuve> query = session.createQuery("SELECT e FROM Epreuve e join fetch e.tournoi WHERE e.tournoi.code = ?1", Epreuve.class);
    		
    		query.setParameter(1, codeTournoi);
    		List<Epreuve> epreuves = query.getResultList();
    		
        	// Récupération des données
        	return epreuves;
        	
		} catch (Throwable e) {
            e.printStackTrace();
            return null;
		}
    }
}
