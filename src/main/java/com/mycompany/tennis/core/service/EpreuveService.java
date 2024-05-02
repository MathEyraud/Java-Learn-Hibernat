package com.mycompany.tennis.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.EpreuveFullDTO;
import com.mycompany.tennis.core.dto.EpreuveLightDTO;
import com.mycompany.tennis.core.dto.JoueurDTO;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.util.HibernateUtil;

public class EpreuveService {
	
	/* 
	 * ATTRIBUTS
	 */
	private EpreuveRepositoryImpl epreuveRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	// TODO : A FAIRE
	public void createEpreuve(Epreuve newEpreuve) {
		
	}	
	public EpreuveFullDTO getEpreuveByIdWithDetails(Long id) {
		
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
        Transaction 	transaction 	= null;
		Epreuve 		epreuve 		= null;
        
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
        	// on récupère l'épreuve
    		epreuve = epreuveRepository.getEpreuveById(id); 
    		
    		// On récupère aussi les informations du tournoi
    		// INUTILE : Hibernate.initialize(epreuve.getTournoi()); 
    		// On fait la requête en appelant getTournoi()
        	
        	// On associe chaque valeur à notre DTO
        	if (epreuve != null) {
            	
            	// Il faut également créer une association avec tournoi
        		// On récupère les informations du tournoi
            	Tournoi tournoi = new Tournoi(epreuve.getTournoi().getId(), epreuve.getTournoi().getNom(), epreuve.getTournoi().getCode());
            	epreuve.setTournoi(tournoi);
            	
            	// On récupère les participants
            	Set<JoueurDTO> participantsDTO = new HashSet<>();
            	for (Joueur participant : epreuve.getParticipants()) {
            		JoueurDTO joueurDTO = new JoueurDTO(participant);
            		participantsDTO.add(joueurDTO);
				}
            	
            	// On crée l'objet à retourner
            	EpreuveFullDTO epreuveDTO = new EpreuveFullDTO(epreuve);
            	epreuveDTO.setParticipants(participantsDTO);
            	
            	// Commit : Cela assure que les modifications apportées à la base de données sont validées
            	transaction.commit();
            	
            	// On retourne l'objet
            	return epreuveDTO;
            
            // Si pas d''épreuve trouvé on retourne un null
			}else {
				return null;
			}
        	
        // Gestion des erreurs
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture de l'épreuve.");
            e.printStackTrace();
            
            return null;
		}
    	
	}
	public EpreuveLightDTO getEpreuveByIdWithoutDetails(Long id) {
		
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
        Transaction 	transaction 	= null;
        EpreuveLightDTO epreuveLightDTO = new EpreuveLightDTO();
        Epreuve 		epreuve 		= null;
		
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
        	// on récupère l'épreuve
    		epreuve= epreuveRepository.getEpreuveById(id);
    		
        	// On associe chaque valeur à notre DTO
        	if (epreuve != null) {
        		epreuveLightDTO.setId(epreuve.getId());
        		epreuveLightDTO.setAnnee(epreuve.getAnnee());
        		epreuveLightDTO.setTypeEpreuve(epreuve.getTypeEpreuve());
			}
        	
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture de l'épreuve.");
            e.printStackTrace();
		}
    	
		return epreuveLightDTO;
	}
	// Méthode avec HQL + Jointures + Dynamic fetching
	public List<EpreuveFullDTO> getEpreuvesByCodeTournoi(String codeTournoi){
    	// ------------------------------------------- //
		// Méthode avec la transaction dans le service //
    	// ------------------------------------------- //
		// Pour Eviter d'avoir des méthodes différentes dans les repositories
		// On transfert la transaction dans le service
		// Pas propre mais il faut Spring pour aller plus loin
    	
        // Création de la variable pour la transaction
        Transaction transaction = null;
        List<Epreuve> epreuves = null;
		
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
        	// on récupère les joueurs
    		epreuves = epreuveRepository.getEpreuvesByCodeTournoi(codeTournoi);
    		
    		List<EpreuveFullDTO> epreuvesDTO = new ArrayList<>();
    		for(Epreuve epreuve: epreuves) {
    			EpreuveFullDTO epreuveDTO = new EpreuveFullDTO(epreuve);
    			epreuvesDTO.add(epreuveDTO);
    		}
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	return epreuvesDTO;
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture des épreuves.");
	            e.printStackTrace();
	            return null;
			}
		}
}
