package model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the on_demand database table.
 * 
 */
@Entity
@Table(name="on_demand")
@NamedQuery(name="OnDemand.findAll", query="SELECT o FROM OnDemand o")
public class OnDemand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_ON_DEMAND")
	private int idOnDemand;

	private Timestamp startts;

	//bi-directional many-to-one association to Range
	@ManyToOne
	@JoinColumn(name="ID_RANGE")
	private Range range;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="onDemand")
	private List<Operation> operations;

	public OnDemand() {
	}

	public int getIdOnDemand() {
		return this.idOnDemand;
	}

	public void setIdOnDemand(int idOnDemand) {
		this.idOnDemand = idOnDemand;
	}

	public Timestamp getStartts() {
		return this.startts;
	}

	public void setStartts(Timestamp startts) {
		this.startts = startts;
	}

	public Range getRange() {
		return this.range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Operation addOperation(Operation operation) {
		getOperations().add(operation);
		operation.setOnDemand(this);

		return operation;
	}

	public Operation removeOperation(Operation operation) {
		getOperations().remove(operation);
		operation.setOnDemand(null);

		return operation;
	}

}