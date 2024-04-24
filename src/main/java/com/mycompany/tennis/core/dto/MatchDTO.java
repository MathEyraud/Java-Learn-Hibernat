package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
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
	//private Score 	score;
	
	/*
	 * CONSTRUCTEURS
	 */
	public MatchDTO(EpreuveFullDTO epreuve, JoueurDTO vainqueur, JoueurDTO finaliste) {
		this.epreuve 	= epreuve;
		this.vainqueur 	= vainqueur;
		this.finaliste 	= finaliste;
	}
	
	// Conversion vers un DTO
	public MatchDTO(Match newMatch) {
		this.id 		= newMatch.getId();
		this.epreuve 	= new EpreuveFullDTO(newMatch.getEpreuve());
		this.vainqueur 	= new JoueurDTO(newMatch.getVainqueur());
		this.finaliste 	= new JoueurDTO(newMatch.getFinaliste());
		//this.score		= newMatch.getScore();
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
                //"Score=" 	+ this.score 	+
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

}
