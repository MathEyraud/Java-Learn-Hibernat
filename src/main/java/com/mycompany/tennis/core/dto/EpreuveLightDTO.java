package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Epreuve;

public class EpreuveLightDTO {
	/** 
	 * ATTRIBUTS
	 */
	private Long 		id;
	private Short 		annee;
	private Character 	typeEpreuve;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public EpreuveLightDTO(){}
	public EpreuveLightDTO(EpreuveLightDTO epreuve){
		this.id 		= epreuve.getId();
		this.annee		= epreuve.getAnnee();
		this.typeEpreuve= epreuve.getTypeEpreuve();
	}
	// Conversion vers un DTO
	public EpreuveLightDTO(Epreuve epreuve){
		this.id 		= epreuve.getId();
		this.annee		= epreuve.getAnnee();
		this.typeEpreuve= epreuve.getTypeEpreuve();
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Epreuve{" +
                "Id épreuve ="		+ this.id 			+ " / " +
                "Annee=" 			+ this.annee 		+ " / " +
                "Type d'épreuve=" 	+ this.typeEpreuve	+ 
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

	public Short getAnnee() {
		return annee;
	}

	public void setAnnee(Short annee) {
		this.annee = annee;
	}

	public Character getTypeEpreuve() {
		return typeEpreuve;
	}

	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}
}
