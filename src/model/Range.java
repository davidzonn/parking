package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ranges database table.
 * 
 */
@Entity
@Table(name="ranges")
@NamedQuery(name="Range.findAll", query="SELECT r FROM Range r")
public class Range implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_RANGE")
	private int idRange;

	@Column(name="RANGE_KM")
	private int rangeKm;

	@Column(name="RANGE_NAME")
	private String rangeName;

	@Column(name="RANGE_PRICE")
	private BigDecimal rangePrice;

	//bi-directional many-to-one association to OnDemand
	@OneToMany(mappedBy="range")
	private List<OnDemand> onDemands;

	public Range() {
	}

	public int getIdRange() {
		return this.idRange;
	}

	public void setIdRange(int idRange) {
		this.idRange = idRange;
	}

	public int getRangeKm() {
		return this.rangeKm;
	}

	public void setRangeKm(int rangeKm) {
		this.rangeKm = rangeKm;
	}

	public String getRangeName() {
		return this.rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public BigDecimal getRangePrice() {
		return this.rangePrice;
	}

	public void setRangePrice(BigDecimal rangePrice) {
		this.rangePrice = rangePrice;
	}

	public List<OnDemand> getOnDemands() {
		return this.onDemands;
	}

	public void setOnDemands(List<OnDemand> onDemands) {
		this.onDemands = onDemands;
	}

	public OnDemand addOnDemand(OnDemand onDemand) {
		getOnDemands().add(onDemand);
		onDemand.setRange(this);

		return onDemand;
	}

	public OnDemand removeOnDemand(OnDemand onDemand) {
		getOnDemands().remove(onDemand);
		onDemand.setRange(null);

		return onDemand;
	}

}