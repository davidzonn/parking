package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the parking_place database table.
 * 
 */
@Entity
@Table(name="parking_place")
@NamedQuery(name="ParkingPlace.findAll", query="SELECT p FROM ParkingPlace p")
public class ParkingPlace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_PARKING_PLACE")
	private int idParkingPlace;
	
	@Column(name="PARKING_PLACE_NUMBER")
	private int parkingPlaceNumber;
	
	public int getParkingPlaceNumber() {
		return parkingPlaceNumber;
	}

	public void setParkingPlaceNumber(int parkingPlaceNumber) {
		this.parkingPlaceNumber = parkingPlaceNumber;
	}

	//bi-directional many-to-one association to DistanceEntrance
	@ManyToOne
	@JoinColumn(name="ID_DISTANCE_ENTRANCE")
	private DistanceEntrance distanceEntrance;

	//bi-directional many-to-one association to Parking
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_PARKING")
	private Parking parking;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
	private Status status;

	//bi-directional many-to-one association to TypeReservation
	@ManyToOne
	@JoinColumn(name="ID_TYPE_RESERVATION")
	private TypeReservation typeReservation;

	//bi-directional many-to-one association to RegularReservation
	@OneToMany(mappedBy="parkingPlace")
	private List<RegularReservation> regularReservations;

	public ParkingPlace() {
	}

	public int getIdParkingPlace() {
		return this.idParkingPlace;
	}

	public void setIdParkingPlace(int idParkingPlace) {
		this.idParkingPlace = idParkingPlace;
	}

	public DistanceEntrance getDistanceEntrance() {
		return this.distanceEntrance;
	}

	public void setDistanceEntrance(DistanceEntrance distanceEntrance) {
		this.distanceEntrance = distanceEntrance;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TypeReservation getTypeReservation() {
		return this.typeReservation;
	}

	public void setTypeReservation(TypeReservation typeReservation) {
		this.typeReservation = typeReservation;
	}

	public List<RegularReservation> getRegularReservations() {
		return this.regularReservations;
	}

	public void setRegularReservations(List<RegularReservation> regularReservations) {
		this.regularReservations = regularReservations;
	}

	public RegularReservation addRegularReservation(RegularReservation regularReservation) {
		getRegularReservations().add(regularReservation);
		regularReservation.setParkingPlace(this);

		return regularReservation;
	}

	public RegularReservation removeRegularReservation(RegularReservation regularReservation) {
		getRegularReservations().remove(regularReservation);
		regularReservation.setParkingPlace(null);
		return regularReservation;
	}

}