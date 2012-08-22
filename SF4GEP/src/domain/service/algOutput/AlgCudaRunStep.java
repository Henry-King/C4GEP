package domain.service.algOutput;

import java.util.List;

import domain.core.algOutput.Population;

public class AlgCudaRunStep extends AlgRunStep{
	@Override
	public native List<Float> calculateFitness(Population population);
}
