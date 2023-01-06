package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.SQL_SEARCH_BY_NAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.CustomerRowMapper;
import vttp2022.paf.assessment.eshop.models.LineItem;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate temp;

	// You cannot change the method's signature
	public Customer findCustomerByName(String name) {
		// TODO: Task 3 
		
		List<Customer> custs = temp.query(SQL_SEARCH_BY_NAME, new CustomerRowMapper(), new Object[] {name});

        return (Customer) custs.get(0);

		
    }

    public void addAllItem(List<LineItem> lineItems, String orderId) {
    }


	}

