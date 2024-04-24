package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.TournoiDTO;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.util.HibernateUtil;

public class TournoiService {
	
	/* 
	 * ATTRIBUTS
	 */
	private TournoiRepositoryImpl tournoiRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public TournoiService() {
		this.tournoiRepository = new TournoiRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createTournoi(TournoiDTO dto) {
		
		// On vérifie que le tournoi n'est pas null
		// Si c'est le cas on arrête tous
		if (dto == null) {
            System.out.println("Le tournois à créer est null !");
            return;
        }
		
		// Création de la variable pour la transaction
        Transaction transaction = null;
        
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
    		
    		// On transfert les informations du DTO vers l'entity
    		Tournoi newTournoi = new Tournoi(dto);
            
    		// On crée le nouveau tournoi
    		tournoiRepository.create(newTournoi);
        	
        	// Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	// Message de succès
        	System.out.println("Tournoi créé avec succès !");
            
		} catch (Throwable e) {
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la création du tournoi.");
            e.printStackTrace();
		}
	}	
	public TournoiDTO getTournoiById(Long id) {
		
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
            
        	// on supprime je joueur
    		Tournoi tournoi = tournoiRepository.getTournoiById(id);
    		TournoiDTO dto = new TournoiDTO(tournoi);
    		
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
