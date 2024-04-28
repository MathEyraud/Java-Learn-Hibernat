package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.MatchDTO;
import com.mycompany.tennis.core.dto.ScoreDTO;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import com.mycompany.tennis.core.util.HibernateUtil;

public class ScoreService {
	
	/** 
	 * ATTRIBUTS
	 */
	private ScoreRepositoryImpl scoreRepository;
	
	/**
	 * CONSTRUCTEURS
	 */
	public ScoreService() {
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	// TODO : A FAIRE
	public void createScore(Score score) {}
	public ScoreDTO getScoreById(Long id) {
		
		// Vérification des données avant toute chose
		if(id == null) {
    		System.out.println("Merci de renseigner un id !");
    		return null;
    	}
		
    	// ------------------------------------------- //
		// Méthode avec la transaction dans le service //
    	// ------------------------------------------- //
		// Pour Eviter d'avoir des méthodes différentes dans les repositories
		// On transfert la transaction dans le service
		// Pas propre mais il faut Spring pour aller plus loin
    	
        // Création de la variable pour la transaction
        Transaction transaction = null;
		
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
        	// on récupère les information du match
    		Score score = scoreRepository.getScoreById(id);
    		
    		// On transfert les données vers un DTO
    		ScoreDTO dto = new ScoreDTO(score);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	return dto;
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la récupération du score.");
            e.printStackTrace();
            
            return null;
		}
	}
	public void deleteScoreById(Long id) {
		
		// Vérification des données avant toute chose
		if(id == null) {
    		System.out.println("Merci de renseigner un id !");
    		return;
    	}
		
    	// ------------------------------------------- //
		// Méthode avec la transaction dans le service //
    	// ------------------------------------------- //
		// Pour Eviter d'avoir des méthodes différentes dans les repositories
		// On transfert la transaction dans le service
		// Pas propre mais il faut Spring pour aller plus loin
    	
        // Création de la variable pour la transaction
        Transaction transaction = null;
		
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
    		// Supprimer le match (et le score par extension)
    		scoreRepository.deleteScoreById(id);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la suppression du score.");
            e.printStackTrace();
		}
	}
}
