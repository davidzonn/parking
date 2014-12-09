package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the type_reservation database table.
 * 
 */
@Entity
@Table(name="type_reservation")
@NamedQuery(name="TypeReservation.findAll", query="SELECT t FROM TypeReservation t")
public class TypeReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TYPE_RESERVATION")
	private int idTypeReservation;

	@Column(name="RESERVATION_PRICE")
	private BigDecimal reservationPrice;

	@Column(name="RESERVATION_TYPE")
	private String reservationType;

	//bi-directional many-to-one association to RegularReservation
	@OneToMany(mappedBy="typeReservation")
	private List<RegularReservation> regularReservations;

	public TypeReservation() {
	}

	public int getIdTypeReservation() {
		return this.idTypeReservation;
	}

	public void setIdTypeReservation(int idTypeReservation) {
		this.idTypeReservation = idTypeReservation;
	}

	public BigDecimal getReservationPrice() {
		return this.reservationPrice;
	}

	public void setReservationPrice(BigDecimal reservationPrice) {
		this.reservationPrice = reservationPrice;
	}

	public String getReservationType() {
		return this.reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public List<RegularReservation> getRegularReservations() {
		return this.regularReservations;
	}

	public void setRegularReservations(List<RegularReservation> regularReservations) {
		this.regularReservations = regularReservations;
	}

	public RegularReservation addRegularReservation(RegularReservation regularReservation) {
		getRegularReservations().add(regularReservation);
		regularReservation.setTypeReservation(this);

		return regularReservation;
	}

	public RegularReservation removeRegularReservation(RegularReservation regularReservation) {
		getRegularReservations().remove(regularReservation);
		regularReservation.setTypeReservation(null);

		return regularReservation;
	}

}