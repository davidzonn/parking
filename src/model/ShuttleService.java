package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shuttle_service database table.
 * 
 */
@Entity
@Table(name="shuttle_service")
@NamedQuery(name="ShuttleService.findAll", query="SELECT s FROM ShuttleService s")
public class ShuttleService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SHUTTLE_SERVICE")
	private int idShuttleService;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="shuttleService")
	private List<Operation> operations;

	//bi-directional many-to-one association to Destination
	@ManyToOne
	@JoinColumn(name="ID_DESTINATION")
	private Destination destination;

	public ShuttleService() {
	}

	public int getIdShuttleService() {
		return this.idShuttleService;
	}

	public void setIdShuttleService(int idShuttleService) {
		this.idShuttleService = idShuttleService;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Operation addOperation(Operation operation) {
		getOperations().add(operation);
		operation.setShuttleService(this);

		return operation;
	}

	public Operation removeOperation(Operation operation) {
		getOperations().remove(operation);
		operation.setShuttleService(null);

		return operation;
	}

	public Destination getDestination() {
		return this.destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}