/****************************
 * Copyright (c) 2009 ATEJI *
 * All rights reserved.     *
 ****************************/
package lpsolve.columngeneration;

/**
 * 
 * Order Width
 * Quantity Ordered
 * 
 *
 */
public class Order {

	final int width; 
	final int quantity;
	
	public Order(final int width, final int quantity) {
		super();
		this.width = width;
		this.quantity = quantity;
	}

	@Override
	public String toString()
	{
		return "<"+width+","+quantity+">";
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + quantity;
		result = PRIME * result + width;
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
		final Order other = (Order) obj;
		if (quantity != other.quantity)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
	
}
