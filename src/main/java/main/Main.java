package main;

import configuration.JPAConfig;
import entity.Order;
import entity.OrderDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.OrderDetailsRepository;
import repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static void main(String[] args) {
//        createOrderDetails();
//        createOrder();
//        createOrderWithOrderDetails();
//        getAllOrder();
//        findByIdOrder(1);
//        getAllOrderDetails();
//        findByOrderMonth(9);
        findByProductName("hien");
    }

    private static Order createOrder() {
        Order o = new Order();
        o.setOrderDate(LocalDate.now());
        o.setCustomerName("Hai");
        o.setCustomerAddress("123");
        orderRepository.save(o);
        return o;
    }

    private static void getAllOrder() {
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        System.out.println("They are: ");
        for (Order order : orderList) {
            System.out.println(order.toString());
        }
    }

    private static void getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = (List<OrderDetails>) orderDetailsRepository.findAll();
        System.out.println("They are: ");
        for (OrderDetails orderDetails : orderDetailsList) {
            System.out.println(orderDetails.toString());
        }
    }

    private static OrderDetails createOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(10);
        orderDetails.setProductName("hien");
        orderDetails.setUnitPrice(1000);

        orderDetailsRepository.save(orderDetails);

        return orderDetails;
    }

    public static void createOrderWithOrderDetails() {
        Order order = new Order();
        orderRepository.save(order);

        OrderDetails orderDetails = createOrderDetails();
        orderDetails.setOrder(order);
    }

    private static void findByIdOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            System.out.println("Find order which id = " + id);
            System.out.println(orderRepository.toString());
        } else {
            System.out.println("not find");
        }
    }

    private static void findByIdOrderDetails(int id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        if (orderDetails.isPresent()) {
            System.out.println("Find orderDetails which id = " + id);
            System.out.println(orderDetailsRepository.toString());
        } else {
            System.out.println("not find");
        }
    }
//    private static void findByOrderMonth(int i){
//        LocalDate localDate = LocalDate.now();
//        int month = localDate.getMonthValue();
//        List<Order> orderList = orderRepository.findByOrderMonth(month);
//        if(!orderList.isEmpty()){
//            System.out.println("Find order in the month = " +month);
//            for (Order order:orderList){
//                System.out.println(order.toString());
//            }
//        }else {
//            System.out.println("not find");
//        }
//    }
    private static void findByProductName(String name){
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findByProductName(name);
        if(!orderDetailsList.isEmpty()){
            System.out.println("find order book" + name );
            for (OrderDetails orderDetails:orderDetailsList){
                System.out.println(orderDetails.toString());
            }
        }
    }
}
