package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_STATUS")
	private int idStatus;

	@Column(name="STATUS_NAME")
	private String statusName;

	//bi-directional many-to-one association to ParkingPlace
	@OneToMany(mappedBy="status")
	private List<ParkingPlace> parkingPlaces;

	public Status() {
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<ParkingPlace> getParkingPlaces() {
		return this.parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	public ParkingPlace addParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().add(parkingPlace);
		parkingPlace.setStatus(this);

		return parkingPlace;
	}

	public ParkingPlace removeParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().remove(parkingPlace);
		parkingPlace.setStatus(null);

		return parkingPlace;
	}

}