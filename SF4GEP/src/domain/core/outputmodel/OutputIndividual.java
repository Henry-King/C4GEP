package domain.core.outputmodel;

import java.io.Serializable;

public class OutputIndividual implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -816734522140044494L;
	private Long id;
	private Float fitness;
	private String expression;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getFitness() {
		return fitness;
	}
	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
