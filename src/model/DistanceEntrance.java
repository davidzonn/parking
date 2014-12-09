package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the distance_entrance database table.
 * 
 */
@Entity
@Table(name="distance_entrance")
@NamedQuery(name="DistanceEntrance.findAll", query="SELECT d FROM DistanceEntrance d")
public class DistanceEntrance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DISTANCE_ENTRANCE")
	private int idDistanceEntrance;

	@Column(name="DISTANCE_NAME")
	private String distanceName;

	@Column(name="DISTANCE_PRICE")
	private BigDecimal distancePrice;

	//bi-directional many-to-one association to ParkingPlace
	@OneToMany(mappedBy="distanceEntrance")
	private List<ParkingPlace> parkingPlaces;

	public DistanceEntrance() {
	}

	public int getIdDistanceEntrance() {
		return this.idDistanceEntrance;
	}

	public void setIdDistanceEntrance(int idDistanceEntrance) {
		this.idDistanceEntrance = idDistanceEntrance;
	}

	public String getDistanceName() {
		return this.distanceName;
	}

	public void setDistanceName(String distanceName) {
		this.distanceName = distanceName;
	}

	public BigDecimal getDistancePrice() {
		return this.distancePrice;
	}

	public void setDistancePrice(BigDecimal distancePrice) {
		this.distancePrice = distancePrice;
	}

	public List<ParkingPlace> getParkingPlaces() {
		return this.parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	public ParkingPlace addParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().add(parkingPlace);
		parkingPlace.setDistanceEntrance(this);

		return parkingPlace;
	}

	public ParkingPlace removeParkingPlace(ParkingPlace parkingPlace) {
		getParkingPlaces().remove(parkingPlace);
		parkingPlace.setDistanceEntrance(null);

		return parkingPlace;
	}

}