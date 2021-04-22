package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService{
    private static Map<Integer, Customer> customerMap = new HashMap<>();
    static {
        customerMap.put(1,new Customer(1,"Tuyen", "Ha Noi"));
        customerMap.put(2,new Customer(2,"Thien", "Sao Hoa"));
        customerMap.put(3,new Customer(3,"Ngoc", "Moon"));
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public void save(Customer customer) {
    customerMap.put(customer.getId(),customer);
    }

    @Override
    public void delete(int id) {
customerMap.remove(id);
    }

    @Override
    public Customer findById(int id) {
        return customerMap.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerMap.put(id,customer);
    }
}
