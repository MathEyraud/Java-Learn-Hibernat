package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Match;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="match_tennis")
public class MatchDTO {
	
	/* 
	 * ATTRIBUTS
	 */
	private Long 			id;
	private EpreuveFullDTO 	epreuve;
	private JoueurDTO 		vainqueur;
	private JoueurDTO 		finaliste;
	private ScoreDTO 		score;
	
	/*
	 * CONSTRUCTEURS
	 */
	public MatchDTO() {}
	public MatchDTO(EpreuveFullDTO epreuve, JoueurDTO vainqueur, JoueurDTO finaliste, ScoreDTO score) {
		this.epreuve 	= epreuve;
		this.vainqueur 	= vainqueur;
		this.finaliste 	= finaliste;
		this.score 		= score;
	}
	
	// Conversion vers un DTO
	public MatchDTO(Match match) {
		this.id 		= match.getId();
		this.epreuve 	= new EpreuveFullDTO(match.getEpreuve());
		this.vainqueur 	= new JoueurDTO(match.getVainqueur());
		this.finaliste 	= new JoueurDTO(match.getFinaliste());
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Match{" +
                "id=" 		+ this.id 		+ " / " +
                "Epreuve="	+ this.epreuve 	+ " / " +
                "Vainqueur="+ this.vainqueur+ " / " +
                "Finaliste="+ this.finaliste+ " / " +
                "Score=" 	+ this.score 	+
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

	public EpreuveFullDTO getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(EpreuveFullDTO epreuve) {
		this.epreuve = epreuve;
	}

	public JoueurDTO getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(JoueurDTO vainqueur) {
		this.vainqueur = vainqueur;
	}

	public JoueurDTO getFinaliste() {
		return finaliste;
	}

	public void setFinaliste(JoueurDTO finaliste) {
		this.finaliste = finaliste;
	}

	public ScoreDTO getScore() {
		return score;
	}

	public void setScore(ScoreDTO score) {
		this.score = score;
	}

}
