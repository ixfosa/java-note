package pojo;

import java.util.List;

/**
 * Created by ixfosa on 2021/3/26 18:31
 */
public class Orders {
    private Integer id;
    private String ordersn; // 订单号

    // 多对多中的另一个一对多
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", ordersn='" + ordersn + '\'' +
                ", products=" + products +
                '}';
    }
}
