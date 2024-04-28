package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.EpreuveFullDTO;
import com.mycompany.tennis.core.dto.JoueurDTO;
import com.mycompany.tennis.core.dto.MatchDTO;
import com.mycompany.tennis.core.dto.ScoreDTO;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import com.mycompany.tennis.core.util.HibernateUtil;

public class MatchService {
	
	/* 
	 * ATTRIBUTS
	 */
	private MatchRepositoryImpl matchRepository;
	private ScoreRepositoryImpl scoreRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public MatchService() {
		this.matchRepository = new MatchRepositoryImpl();
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	/**
	 * Méthode créeation d'un match.
	 * Attention c'est une création liée à une autre entité.
	 * @param match
	 */
	public void createMatch(MatchDTO matchDTO) {
		
		// Vérification des données avant toute chose
		if(matchDTO == null) {
    		System.out.println("Merci de renseigner un match !");
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
            
    		// Création du match (sans l'entité lié => Score)
    		// Sinon boucle infini
    		Match match = new Match(matchDTO);
    		
    		// Création du score
    		Score score = new Score(
				matchDTO.getScore().getSet1(),
				matchDTO.getScore().getSet2(),
				matchDTO.getScore().getSet3(),
				matchDTO.getScore().getSet4(),
				matchDTO.getScore().getSet5()
    		);	
    		
    		//Liée les entités
    		score.setMatch(match);
    		match.setScore(score);
    		
    		// Envoi des données
    		matchRepository.create(match);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la création du match.");
            e.printStackTrace();
		}
	}	
	public MatchDTO getMatchById(Long id) {
		
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
    		Match match	= matchRepository.getMatchById(id);
    		
    		if(match == null) {
    			return null;
    		}
    		
    		// On récupère le détail des informations
    		EpreuveFullDTO 	epreuve 	= new EpreuveFullDTO(match.getEpreuve());
    		JoueurDTO 		vainqueur 	= new JoueurDTO(match.getVainqueur());
    		JoueurDTO 		finaliste 	= new JoueurDTO(match.getFinaliste());
			ScoreDTO 		score 		= new ScoreDTO(match.getScore());
			
    		// On transfert les données vers un DTO
    		//MatchDTO dto = new MatchDTO(match);
			MatchDTO dto = new MatchDTO();
			dto.setId(match.getId());
			dto.setEpreuve(epreuve);
			dto.setVainqueur(vainqueur);
			dto.setFinaliste(finaliste);
			dto.setScore(score);
			
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	return dto;
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la récupération du match.");
            e.printStackTrace();
            
            return null;
		}
	}	
	/**
	 * Méthode qui permet de déclarer un match avec dopage.
	 * Tous les set sont mis à 0 et on inverse le gagnant/perdant.
	 * Ici c'est une modification de deux entités liées.
	 * @param id
	 */
	public void tapisVert(Long id) {
		
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
            
        	// on récupère les information du match
    		Match match	= matchRepository.getMatchById(id);
    		
    		if(match == null) {
    			System.out.println("Aucun tournoi trouvé !");
    			return;
    		}
    		
    		match.getScore().setSet1((byte)0);
    		match.getScore().setSet2((byte)0);
    		match.getScore().setSet3((byte)0);
    		match.getScore().setSet4((byte)0);
    		match.getScore().setSet5((byte)0);
    		
    		Joueur ancienVainqueur = new Joueur(match.getVainqueur());
    		Joueur ancienFinaliste = new Joueur(match.getFinaliste());
    		
    		match.setVainqueur(ancienFinaliste);
    		match.setFinaliste(ancienVainqueur);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la déclaration de dopage du match.");
            e.printStackTrace();
		}
	}
	public void deleteMatch(Long id) {
		
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
    		matchRepository.deleteMatchById(id);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la suppression du match.");
            e.printStackTrace();
		}
	}
}
