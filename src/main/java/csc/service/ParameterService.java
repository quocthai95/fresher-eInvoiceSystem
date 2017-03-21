package csc.service;

import csc.models.Parameter;

public interface ParameterService {
	
	Parameter findById(Integer id);
	
	Parameter findByParaKey(String parakey);
	
	void saveParameter(Parameter parameter);
	
	void updateParameter(Parameter parameter);
}
