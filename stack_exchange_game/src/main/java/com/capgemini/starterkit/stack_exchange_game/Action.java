package com.capgemini.starterkit.stack_exchange_game;

import java.util.Date;

public class Action implements Comparable<Action> {

	private String companyName;
	private Date date;
	private float price;

	public Action(String companyName, Date date, float price) {
		super();
		this.companyName = companyName;
		this.date = date;
		this.price = price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Date getDate() {
		return date;
	}

	public float getPrice() {
		return price;
	}

	
	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(price);
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}

	public int compareTo(Action action) {

		return date.compareTo(action.date);
	}

}
