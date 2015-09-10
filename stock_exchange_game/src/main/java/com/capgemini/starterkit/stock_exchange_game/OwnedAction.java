package com.capgemini.starterkit.stock_exchange_game;

public class OwnedAction {

	private Double purchasePrice;
	private Double salePrice = 0.0;
	private String companyName;
	private int amount;

	public OwnedAction(Double purchasePrice, String companyName, int amount) {
		super();
		this.purchasePrice = purchasePrice;
		this.companyName = companyName;
		this.amount = amount;
	}

	public Double calculateSaleValue() {
		return salePrice * amount;
	}

	public Double calculatePurchasedValue() {
		return purchasePrice * amount;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((purchasePrice == null) ? 0 : purchasePrice.hashCode());
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
		OwnedAction other = (OwnedAction) obj;
		if (amount != other.amount)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (purchasePrice == null) {
			if (other.purchasePrice != null)
				return false;
		} else if (!purchasePrice.equals(other.purchasePrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OwnedAction [purchasePrice=" + purchasePrice + ", salePrice="
				+ salePrice + ", companyName=" + companyName + ", amount="
				+ amount + "]";
	}

}
