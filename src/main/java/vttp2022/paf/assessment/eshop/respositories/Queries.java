package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    public static final String SQL_SEARCH_BY_NAME = """
        select name, address, email from customers where name = ?
        """;
    

    public static final String SQL_INSERT_LINE_ITEM = """
        insert into line_item(name, quantity, item)
        values(?,?,?)
            """;

    public static final String SQL_INSERT_PURCHASE_ORDER = """
        insert into purchase_order(order_id, delivery_id, status, status_update)
        values(?,?,?,SYSDATE())
            """;
}
