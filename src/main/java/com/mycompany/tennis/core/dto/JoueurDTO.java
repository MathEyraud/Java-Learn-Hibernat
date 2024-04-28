package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Joueur;

public class JoueurDTO {
	/** 
	 * ATTRIBUTS
	 */
	private Long 		id;
	private String 		nom;
	private String 		prenom;
	private Character 	sexe;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public JoueurDTO() {}
	public JoueurDTO(String nom, String prenom, String sexe) {
		this.nom 	= nom;
		this.prenom = prenom;
		this.sexe 	= sexe.charAt(0);
	}
	public JoueurDTO(JoueurDTO joueur) {
		this.id		= joueur.getId();
		this.nom 	= joueur.getNom();
		this.prenom = joueur.getPrenom();
		this.sexe 	= joueur.getSexe();
	}
	// Conversion vers un DTO
	public JoueurDTO(Joueur joueur) {
		this.id		= joueur.getId();
		this.nom 	= joueur.getNom();
		this.prenom = joueur.getPrenom();
		this.sexe 	= joueur.getSexe();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Joueur{" +
                "id=" 		+ this.id 		+ " / " +
                "nom=" 		+ this.nom 		+ " / " +
                "prenom=" 	+ this.prenom 	+ " / " +
                "sexe=" 	+ this.sexe 	+
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Character getSexe() {
		return sexe;
	}
	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}
}
