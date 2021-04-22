package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CustomerServiceImpl;

import java.util.List;

@Controller
public class DemoThymLeafController {
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    @RequestMapping("/")
    public String getListCustomer(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/list";
    }

    @GetMapping("/detail/{id}")
    public String detailCustomer(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("detail", customer);
        return "/detail";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm",customer);
        System.out.println("name"+customer.getName());
        return "/add";
    }

    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("customerForm") Customer customer, Model model) {
        List<Customer> customerList = customerService.findAll();
        int id = customerList.size() + 1;
        String name = customer.getName();
        String address = customer.getAddress();
        if(name!=null&&name.length()>0&&address!=null&&address.length()>0){
            Customer customer1 = new Customer(id,name,address);
            customerService.save(customer1);
            return "redirect:/";
        }
        String error = "Please fill the field in the form!";
       model.addAttribute("status",error);
       return "/add";
    }
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable int id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("edit", customer);
        return "/edit";
    }
@PostMapping("/edit")
    public String editCustomer(@ModelAttribute("edit") Customer customer, Model model){
        System.out.println("id === "+customer.getId());
        System.out.println("name"+customer.getName());
        if(customer.getName()==null||customer.getName().equals("")||customer.getAddress()==null||customer.getAddress().equals("")){
            String error = "The name or The Address is required! Please fill in the form!";
            model.addAttribute("status", error);
            return "/edit";
        } else {
            customerService.update(customer.getId(),customer);
            return "redirect:/";
        }

}
@GetMapping("/delete/{id}")
    public String getFormDelete(@PathVariable int id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("delete",customer);
        return "/delete";
}
@PostMapping("/delete")
    public String deleteCustomer(Customer customer){
        System.out.println("id =="+customer.getId());
        customerService.delete(customer.getId());
        return "redirect:/";
}
//    @PostMapping("/edit")
//
//    public String editCustomer(Model model, @ModelAttribute("edit")Customer customer){
//
//        System.out.println("id c"+customer.getId());
//
//        Customer customer1 = customerService.findById(customer.getId());
//System.out.println("id Customer = "+customer.getId());
//System.out.println("id Customer 1 = "+customer1.getId());
//        System.out.println("cu1"+customer1);
//
//        System.out.println("name"+customer.getName().length());
//
//        if(customer1==null||customer.getName()==null||customer.getName().equals("")
//                ||customer.getAddress()==null||customer.getAddress().equals("")||customer.getId()!=customer1.getId()
//
//                ){
//
//            System.out.println("vô đây!");
//
//            String messenger = "error";
//
//            model.addAttribute("messenger",messenger);
//
//            return "/edit";
//
//        } else {
//
//            System.out.println("id"+customer.getId());
//
//            customerService.update(customer.getId(),customer);
//
//            return "redirect:/";
//
//        }
//
//    }
}
