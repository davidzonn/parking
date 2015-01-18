package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the operation database table.
 * 
 */
@Entity
@NamedQuery(name="Operation.findAll", query="SELECT o FROM Operation o")
public class Operation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_OPERATION")
	private int idOperation;

	//bi-directional many-to-one association to OnDemand
	@ManyToOne
	@JoinColumn(name="ID_ON_DEMAND")
	private OnDemand onDemand;

	//bi-directional many-to-one association to RegularReservation
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_REGULAR_RESERVATION")
	private RegularReservation regularReservation;

	//bi-directional many-to-one association to ShuttleService
	@ManyToOne
	@JoinColumn(name="ID_SHUTTLE_SERVICE")
	private ShuttleService shuttleService;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User user;

	public Operation() {
	}

	public int getIdOperation() {
		return this.idOperation;
	}

	public void setIdOperation(int idOperation) {
		this.idOperation = idOperation;
	}

	public OnDemand getOnDemand() {
		return this.onDemand;
	}

	public void setOnDemand(OnDemand onDemand) {
		this.onDemand = onDemand;
	}

	public RegularReservation getRegularReservation() {
		return this.regularReservation;
	}

	public void setRegularReservation(RegularReservation regularReservation) {
		this.regularReservation = regularReservation;
	}

	public ShuttleService getShuttleService() {
		return this.shuttleService;
	}

	public void setShuttleService(ShuttleService shuttleService) {
		this.shuttleService = shuttleService;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}