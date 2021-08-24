package pojo;

/**
 * Created by ixfosa on 2021/3/26 18:31
 */
public class Order {
    private Integer id;
    private String ordersn; // 订单号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordersn='" + ordersn + '\'' +
                '}';
    }
}
