package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Tournoi;

public class TournoiDTO {
	/** 
	 * ATTRIBUTS
	 */
	private Long 	id;
	private String 	nom;
	private String 	code;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public TournoiDTO() {}
	
	public TournoiDTO(String nom, String code) {
		this.nom 	= nom;
		this.code = code;
	}
	
	public TournoiDTO(Long id, String nom, String code) {
		this.id 	= id;
		this.nom 	= nom;
		this.code 	= code;
	}
	
	// Conversion vers un DTO
	public TournoiDTO(Tournoi tournoi) {
		this.id 	= tournoi.getId();
		this.nom 	= tournoi.getNom();
		this.code 	= tournoi.getCode();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Tournoi{" 	+
                "id=" 		+ this.id 	+ " / " +
                "Nom="		+ this.nom 	+ " / " +
                "Code=" 	+ this.code	+
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
