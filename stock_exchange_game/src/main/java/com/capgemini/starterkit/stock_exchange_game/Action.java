package com.capgemini.starterkit.stock_exchange_game;


public class Action  {

	private String companyName;
	private Double price;

	public Action(String companyName, Double price) {
		super();
		this.companyName = companyName;
		this.price = price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Double getPrice() {
		return price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Action other = (Action) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Action [companyName=" + companyName + ", price=" + price + "]";
	}
	
	

}
