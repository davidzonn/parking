package model;

import java.io.Serializable;
import javax.persistence.*;


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
	@Column(name="ID_PARKING_PLACE")
	private int idParkingPlace;

	private int time;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
	private Status status;

	//bi-directional many-to-one association to Parking
	@ManyToOne
	@JoinColumn(name="ID_PARKING")
	private Parking parking;

	//bi-directional many-to-one association to DistanceEntrance
	@ManyToOne
	@JoinColumn(name="ID_DISTANCE_ENTRANCE")
	private DistanceEntrance distanceEntrance;

	//bi-directional many-to-one association to RegularReservation
	@ManyToOne
	@JoinColumn(name="ID_REGULAR_RESERVATION")
	private RegularReservation regularReservation;

	public ParkingPlace() {
	}

	public int getIdParkingPlace() {
		return this.idParkingPlace;
	}

	public void setIdParkingPlace(int idParkingPlace) {
		this.idParkingPlace = idParkingPlace;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public DistanceEntrance getDistanceEntrance() {
		return this.distanceEntrance;
	}

	public void setDistanceEntrance(DistanceEntrance distanceEntrance) {
		this.distanceEntrance = distanceEntrance;
	}

	public RegularReservation getRegularReservation() {
		return this.regularReservation;
	}

	public void setRegularReservation(RegularReservation regularReservation) {
		this.regularReservation = regularReservation;
	}

}