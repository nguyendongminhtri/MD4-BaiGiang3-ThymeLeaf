package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    void delete(int id);
    Customer findById(int id);
    void update(int id, Customer customer);
}
