package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.services.WarehouseService;
import vttp2022.paf.assessment.exceptions.OrderException;

@RestController
@RequestMapping(path ="/api/eshop" , produces =  MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	//TODO: Task 3

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private WarehouseService wareSvc;

	@GetMapping(path = "{name}")
	public ResponseEntity<String> findCustomerByName(@PathVariable String name){

		JsonObject result = null;

        try {

            Customer customer = custRepo.findCustomerByName(name);
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();

            objBuilder.add("name", customer.toJson());
            result = objBuilder.build();
            
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body("{\"error\": \"Customer "+name+" \"not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());


	}

	@PostMapping
    public String postCheckout(Model model, HttpSession sess)throws OrderException{



       Order po = (Order) sess.getAttribute("checkoutcart");
       List<LineItem> item = (List<LineItem>) po.getLineItems();
       sess.invalidate();
       wareSvc.dispatch(po);
       model.addAttribute("total", item.size());



        return "checkout";

    }

	

	


	

}
