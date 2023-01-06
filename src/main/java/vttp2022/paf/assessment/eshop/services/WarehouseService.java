package vttp2022.paf.assessment.eshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.exceptions.OrderException;

@Service
public class WarehouseService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private OrderRepository orderRepo;

	// You cannot change the method's signature
	// You may add one or more checked exceptions

	@Transactional (rollbackFor = OrderException.class)
	public void dispatch(Order order) throws OrderException {
		String orderId = UUID.randomUUID().toString().substring(0,8);
		// TODO: Task 4
		order.setOrderId(orderId);
		orderRepo.inserOrder(order);
       if(order.getLineItems().size() <= 0)
            throw new OrderException("Nothing to dispatch");
       custRepo.addAllItem(order.getLineItems(), orderId);
    }


	}
