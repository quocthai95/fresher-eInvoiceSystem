package csc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csc.models.TypeInvoice;
import csc.service.TypeInvoiceService;

/**
 * A class to test interactions with the SQLSERVER database using the UserDao
 * class.
 *
 */
@RestController
public class TypeInvoiceController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	@Autowired
	TypeInvoiceService typeInvoiceService;
		
		
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/invoice/getTypeAll", method = RequestMethod.GET)
    public ResponseEntity<List<TypeInvoice>> listAllTypeInvoice() {
    			
    	List<TypeInvoice> typeInvoice = typeInvoiceService.findAll();
    	
        if(typeInvoice == null){
            return new ResponseEntity<List<TypeInvoice>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TypeInvoice>>(typeInvoice, HttpStatus.OK);
    }
       

} // class UserController
