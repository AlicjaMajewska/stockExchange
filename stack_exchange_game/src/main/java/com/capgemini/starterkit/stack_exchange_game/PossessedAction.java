package com.capgemini.starterkit.stack_exchange_game;

public class PossessedAction {
	
	private float purchasePrice;
	private String companyName;
	private int amount;
	
	
	public PossessedAction(float purchasePrice, String companyName, int amount) {
		super();
		this.purchasePrice = purchasePrice;
		this.companyName = companyName;
		this.amount = amount;
	}
	public float getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + Float.floatToIntBits(purchasePrice);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PossessedAction other = (PossessedAction) obj;
		if (amount != other.amount)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (Float.floatToIntBits(purchasePrice) != Float
				.floatToIntBits(other.purchasePrice))
			return false;
		return true;
	}
	
	

}
