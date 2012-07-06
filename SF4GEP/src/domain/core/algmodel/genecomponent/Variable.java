package domain.core.algmodel.genecomponent;


public class Variable extends Computable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2304632426125416061L;
	private String name;
	private float value;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
