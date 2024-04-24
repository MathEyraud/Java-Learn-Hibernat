package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.dto.JoueurDTO;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.util.HibernateUtil;

public class JoueurService {
	
	/** 
	 * @ATTRIBUTS
	 */
	private JoueurRepositoryImpl joueurRepository;
	
	/**
	 * @CONSTRUCTEURS
	 */
	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}
	
	/** 
	 * @METHODES
	 */
	public void createJoueur(JoueurDTO dto) {
		
		// On vérifie que le joueur n'est pas null
		// Si c'est le cas on arrête tous
		if (dto == null) {
            System.out.println("Le joueur à créer est null !");
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
    		Joueur newJoueur = new Joueur(dto);
            
    		joueurRepository.create(newJoueur);
        	
        	// Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	// Message de succès
        	System.out.println("Joueur créé avec succès !");
            
		} catch (Throwable e) {
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la création  du joueur.");
            e.printStackTrace();
		}
		
	}
	public void updateNomJoueur(Long id, String newNom) {
		
		// Vérification des données avant toute chose
		if(id == null) {
    		System.out.println("Merci de renseigner un id de joueur !");
    		return;
    	}
    	
    	if(newNom == null) {
    		System.out.println("Merci de renseigner un nom de joueur !");
    		return;
    	}
    	
    	// ---------------------------------------------- //
		// Méthode avec la transaction dans le repository //
    	// ---------------------------------------------- //
		// joueurRepository.updateNom(id, newNom);
		
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
            
        	// Récupération du joueur
    		Joueur updatedJoueur = joueurRepository.getJoueurById(id);
    		
    		// Vérification du résultat
            if (updatedJoueur == null) {
                System.out.println("Pas de joueur à cette id !");  
                return;
            }
            
            // On fait les modifications
    		updatedJoueur.setNom(newNom);
        	
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture du joueur.");
            e.printStackTrace();
		}
    	
	}
	public JoueurDTO getJoueurById(Long id) {
		
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
        Joueur joueur = null;
		
        // Variable pour gérer la connexion à la DB
        // Utilisation d'un TryWithRessource
        // On ouvre une session
    	try(Session session = HibernateUtil.getCurrentSession()) {
    		
    		// On débute la transaction
    		transaction = session.beginTransaction();
            
        	// on récupère je joueur
    		joueur = joueurRepository.getJoueurById(id);
    		JoueurDTO dto = new JoueurDTO(joueur);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
        	
        	return dto;
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture du joueur.");
            e.printStackTrace();
            return null;
		}
    	
	}
	public void deleteJoueur(Long id) {
		
		// Vérification des données avant toute chose
		if(id == null) {
    		System.out.println("Merci de renseigner un id de joueur !");
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
            
        	// on supprime je joueur
    		joueurRepository.delete(id);
    		
        	// Commit : Cela assure que les modifications apportées à la base de données sont validées
        	transaction.commit();
            
		} catch (Exception e) {
			
			if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de la lecture du joueur.");
            e.printStackTrace();
		}
	}
}
