package com.mycompany.tennis.core.dto;

import java.util.Set;

import com.mycompany.tennis.core.entity.Epreuve;

public class EpreuveFullDTO extends EpreuveLightDTO{
	
	/** 
	 * ATTRIBUTS
	 */
	private TournoiDTO 	tournoi;
	private Set<JoueurDTO> participants;
	
	/** 
	 * CONSTRUCTEUR
	 */
	// Conversion vers un DTO
	public EpreuveFullDTO(Epreuve epreuve){
		super(epreuve);
		this.tournoi = new TournoiDTO(epreuve.getTournoi());
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Epreuve{" +
                "Id épreuve="			+ super.getId()			+ " / " +
                "Annee=" 				+ super.getAnnee() 		+ " / " +
                "Type d'épreuve=" 		+ super.getTypeEpreuve()+ " / " +
                this.tournoi			+ " / " +
                "Liste particiapnts =" 	+ this.participants 	+
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public TournoiDTO getTournoi() {
		return tournoi;
	}

	public void setTournoi(TournoiDTO tournoi) {
		this.tournoi = tournoi;
	}

	public Set<JoueurDTO> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<JoueurDTO> participants) {
		this.participants = participants;
	}
}
