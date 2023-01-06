package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.SQL_INSERT_LINE_ITEM;
import static vttp2022.paf.assessment.eshop.respositories.Queries.SQL_INSERT_PURCHASE_ORDER;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;

@Repository
public class OrderRepository {


	// TODO: Task 3

	@Autowired
	private JdbcTemplate temp;

	public void addAllLineItem(List <LineItem> items, String name){

		String orderId = UUID.randomUUID().toString().substring(0,8);

        List<Object[]> data = items.stream()
                                .map(li -> {
                                    Object[] i = new Object[3];
                                    i[0] = orderId;
                                    i[1] = name;
                                    i[2] = li.getItem();
                                    i[3] = li.getQuantity();
                                    return i;
                                } ).toList();

        temp.batchUpdate(SQL_INSERT_LINE_ITEM, data);

    }

	public boolean inserOrder(Order order) {

		return temp.update(SQL_INSERT_PURCHASE_ORDER, order.getOrderId(), order.getName()) >0;
	}
}
