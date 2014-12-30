package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the parking database table.
 * 
 */
@Entity
@NamedQuery(name="Parking.findAll", query="SELECT p FROM Parking p")
public class Parking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PARKING")
	private int idParking;

	@Column(name="PARKING_NAME")
	private String parkingName;

	//bi-directional many-to-one association to ParkingPlace
	@OneToMany(mappedBy="parking", cascade=CascadeType.PERSIST)
	private List<ParkingPlace> parkingPlaces;

	public Parking() {
	}

	public int getIdParking() {
		return this.idParking;
	}

	public void setIdParking(int idParking) {
		this.idParking = idParking;
	}

	public String getParkingName() {
		return this.parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public List<ParkingPlace> getParkingPlaces() {
		return this.parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	public ParkingPlace addParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().add(parkingPlace);
		parkingPlace.setParking(this);

		return parkingPlace;
	}

	public ParkingPlace removeParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().remove(parkingPlace);
		parkingPlace.setParking(null);

		return parkingPlace;
	}

}