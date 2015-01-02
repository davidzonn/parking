package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the destination database table.
 * 
 */
@Entity
@NamedQuery(name="Destination.findAll", query="SELECT d FROM Destination d")
public class Destination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_DESTINATION")
	private int idDestination;

	@Column(name="DESTINATION_NAME")
	private String destinationName;

	@Column(name="DESTINATION_PRICE")
	private BigDecimal destinationPrice;

	//bi-directional many-to-one association to ShuttleService
	@OneToMany(mappedBy="destination")
	private List<ShuttleService> shuttleServices;

	public Destination() {
	}

	public int getIdDestination() {
		return this.idDestination;
	}

	public void setIdDestination(int idDestination) {
		this.idDestination = idDestination;
	}

	public String getDestinationName() {
		return this.destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public BigDecimal getDestinationPrice() {
		return this.destinationPrice;
	}

	public void setDestinationPrice(BigDecimal destinationPrice) {
		this.destinationPrice = destinationPrice;
	}

	public List<ShuttleService> getShuttleServices() {
		return this.shuttleServices;
	}

	public void setShuttleServices(List<ShuttleService> shuttleServices) {
		this.shuttleServices = shuttleServices;
	}

	public ShuttleService addShuttleService(ShuttleService shuttleService) {
		getShuttleServices().add(shuttleService);
		shuttleService.setDestination(this);

		return shuttleService;
	}

	public ShuttleService removeShuttleService(ShuttleService shuttleService) {
		getShuttleServices().remove(shuttleService);
		shuttleService.setDestination(null);

		return shuttleService;
	}

}