package lpsolve.network;

public class Node {

	final String id;	
	
	//b : net flow generated at i.
	final double b;
	
	public Node(final String id,double b) {
		super();
		this.id = id;
		this.b  = b;
	}


	public String toString()
	{
		return id;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + id.hashCode();
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
		final Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
