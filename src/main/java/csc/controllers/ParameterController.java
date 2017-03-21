package csc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.Parameter;
import csc.service.ParameterService;

@RestController
public class ParameterController {

	@Autowired
	ParameterService parameterService;
		
	@Autowired
	PasswordEncoder passwordEncoder;

		   
    //-------------------Retrieve Single Parameter--------------------------------------------------------
      
    @RequestMapping(value = "/parameter/getByKey/key={key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parameter> getParameterByKey(@PathVariable("key") String parakey) {
    			
		Parameter para = parameterService.findByParaKey(parakey);
				
        System.out.println("Fetching Customer with id " + para.getId());
        
        if (para == null) {
            System.out.println("Para with id " + para.getId() + " not found");
            return new ResponseEntity<Parameter>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Parameter>(para, HttpStatus.OK);
    } 
    
    //------------------- Update a Customer--------------------------------------------------------
    
    @RequestMapping(value = "/parameter/update/key={key}", method = RequestMethod.POST)
    public ResponseEntity<Parameter> updateParameter(@PathVariable("key") String key, @RequestBody Parameter parameter) {
          
    	Parameter currentParameter = parameterService.findByParaKey(key);
          
        if (currentParameter==null) {
            return new ResponseEntity<Parameter>(HttpStatus.NOT_FOUND);
        }
        
        if(!parameter.getParaValue().isEmpty()){
        	currentParameter.setParaValue(parameter.getParaValue());
        }
  
        if(!parameter.getParaKey().isEmpty()){
        	currentParameter.setParaKey(parameter.getParaKey());
        }
        
        if(!parameter.getDescription().isEmpty()){
        	currentParameter.setDescription(parameter.getDescription());
        }
                            
        parameterService.updateParameter(currentParameter);
        return new ResponseEntity<Parameter>(currentParameter, HttpStatus.OK);
    }
    
}
