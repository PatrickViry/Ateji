package lpsolve.network;

import java.util.Set;

public class Network {

	final Set<Node> nodes;
	final Set<Arc> arcs;
	
	
	public Network(final Set<Node> nodes, final Set<Arc> arcs) {
		super();
		this.nodes = nodes;
		this.arcs = arcs;
	}
	
	@Override
	public String toString()
	{
		return "Nodes:"+nodes+"\nArcs:"+arcs;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((arcs == null) ? 0 : arcs.hashCode());
		result = PRIME * result + ((nodes == null) ? 0 : nodes.hashCode());
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
		final Network other = (Network) obj;
		if (arcs == null) {
			if (other.arcs != null)
				return false;
		} else if (!arcs.equals(other.arcs))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}
	
	
	
}
