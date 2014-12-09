package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the regular_reservation database table.
 * 
 */
@Entity
@Table(name="regular_reservation")
@NamedQuery(name="RegularReservation.findAll", query="SELECT r FROM RegularReservation r")
public class RegularReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REGULAR_RESERVATION")
	private int idRegularReservation;

	private Timestamp endts;

	private int lenghtts;

	private Timestamp startts;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="regularReservation")
	private List<Operation> operations;

	//bi-directional many-to-one association to ParkingPlace
	@OneToMany(mappedBy="regularReservation")
	private List<ParkingPlace> parkingPlaces;

	//bi-directional many-to-one association to TypeReservation
	@ManyToOne
	@JoinColumn(name="ID_TYPE_RESERVATION")
	private TypeReservation typeReservation;

	public RegularReservation() {
	}

	public int getIdRegularReservation() {
		return this.idRegularReservation;
	}

	public void setIdRegularReservation(int idRegularReservation) {
		this.idRegularReservation = idRegularReservation;
	}

	public Timestamp getEndts() {
		return this.endts;
	}

	public void setEndts(Timestamp endts) {
		this.endts = endts;
	}

	public int getLenghtts() {
		return this.lenghtts;
	}

	public void setLenghtts(int lenghtts) {
		this.lenghtts = lenghtts;
	}

	public Timestamp getStartts() {
		return this.startts;
	}

	public void setStartts(Timestamp startts) {
		this.startts = startts;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Operation addOperation(Operation operation) {
		getOperations().add(operation);
		operation.setRegularReservation(this);

		return operation;
	}

	public Operation removeOperation(Operation operation) {
		getOperations().remove(operation);
		operation.setRegularReservation(null);

		return operation;
	}

	public List<ParkingPlace> getParkingPlaces() {
		return this.parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	public ParkingPlace addParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().add(parkingPlace);
		parkingPlace.setRegularReservation(this);

		return parkingPlace;
	}

	public ParkingPlace removeParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().remove(parkingPlace);
		parkingPlace.setRegularReservation(null);

		return parkingPlace;
	}

	public TypeReservation getTypeReservation() {
		return this.typeReservation;
	}

	public void setTypeReservation(TypeReservation typeReservation) {
		this.typeReservation = typeReservation;
	}

}