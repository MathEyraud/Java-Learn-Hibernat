package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.MatchDTO;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
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
	// TODO : A FAIRE
	public void createMatch(MatchDTO match) {
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
    		
    		// On récupère le détail des informations
    		Epreuve epreuve = new Epreuve(
    			match.getEpreuve().getId(), 
    			match.getEpreuve().getAnnee(), 
    			match.getEpreuve().getTypeEpreuve(), 
    			match.getEpreuve().getTournoi()
    		);	match.setEpreuve(epreuve);
    		
    		Joueur vainqueur = new Joueur(
				match.getVainqueur().getId(),
				match.getVainqueur().getNom(),
				match.getVainqueur().getPrenom(),
				match.getVainqueur().getSexe()
			);	match.setVainqueur(vainqueur);
			
    		Joueur finaliste = new Joueur(
				match.getFinaliste().getId(),
				match.getFinaliste().getNom(),
				match.getFinaliste().getPrenom(),
				match.getFinaliste().getSexe()
			);	match.setFinaliste(finaliste);
    		
    		// On transfert les données vers un DTO
    		MatchDTO dto = new MatchDTO(match);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	return dto;
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture du tournoi.");
            e.printStackTrace();
            
            return null;
		}
	}	
}
