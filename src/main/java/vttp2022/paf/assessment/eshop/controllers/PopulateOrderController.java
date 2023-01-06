package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.services.WarehouseService;
import vttp2022.paf.assessment.exceptions.OrderException;

@Controller
@RequestMapping(path = "/populate")
public class PopulateOrderController {


    @Autowired
    private WarehouseService wareSvc;

    // @PostMapping
    // public String addItem(@RequestBody MultiValueMap<String, String> form, Model model){

    //     String name = form.getFirst("user");
    //     String item = form.getFirst("item");
    //     String quantity = form.getFirst("quantity");

    //     LineItem r = new LineItem(name, item, Integer.parseInt(quantity));


    //     LineItem li = orderRepo.addItem(item);
    //     model.addAttribute("review", insertedReview);

    //     return "";
    // }   

    @PostMapping
	public String postCart (@RequestBody MultiValueMap<String, String> form, HttpSession sess, Model model) throws OrderException {

		List<LineItem> li = (List<LineItem>) sess.getAttribute("name");

        if( li == null){
            li= new LinkedList<>();
            sess.setAttribute("name", li);
        }

        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        li.add(new LineItem(item, quantity));
        Order order = new Order();
        order.setName(form.getFirst("name"));
        order.setLineItems(li);

        sess.setAttribute("checkoutcart", order);
        model.addAttribute("lineItems", li);

        return "populate";
		
	}

    
	
    
}
