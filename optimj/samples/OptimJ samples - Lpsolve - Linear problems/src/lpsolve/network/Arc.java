package lpsolve.network;

public class Arc {

	final Node from;
	final Node to;
	
	//  cij : cost per unit of flow from i to j (may be negative),
	final double c;
	
	// uij : capacity (or upper bound) on flow from i to j,
	final double u;		

	@Override
	public String toString()
	{
		return from+" -> "+to;
	}
	

	public Arc(final Node from, final Node to, final double c, final double u) {
		super();
		this.from = from;
		this.to = to;
		this.c = c;
		this.u = u;
	}


	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((from == null) ? 0 : from.hashCode());
		result = PRIME * result + ((to == null) ? 0 : to.hashCode());
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
		final Arc other = (Arc) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	
	
}
