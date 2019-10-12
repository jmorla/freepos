package com.freelancer.billing.service.impl;

import com.freelancer.billing.domain.Customer;
import com.freelancer.billing.domain.Location;
import com.freelancer.billing.domain.Province;
import com.freelancer.billing.exception.DuplicatedResourceException;
import com.freelancer.billing.exception.FieldType;
import com.freelancer.billing.exception.ResourceNotFoundException;
import com.freelancer.billing.model.CustomerDTO;
import com.freelancer.billing.repository.CustomerRepository;
import com.freelancer.billing.repository.LocationRepository;
import com.freelancer.billing.repository.ProvinceRepository;
import com.freelancer.billing.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Customer> findCustomerByNameOrSocialId(String value) {
        if(value == null && value.trim().isEmpty())
            throw new IllegalArgumentException("Invalid query param");

        return customerRepository.findByFullNameOrSocialIdLike(value);
    }

    @Override
    public Customer findCustomerById(String customerId){
        return customerRepository.findById(customerId).get();
    }

    @Override
    public CustomerDTO findCustomerDetailsById(String customerId) {
        if(customerId==null || customerId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid id");

        Optional<Customer> customer = customerRepository.findById(customerId);
        if(!customer.isPresent())
            throw new ResourceNotFoundException();

        Customer c = customer.get();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(c.getFirstName());
        customerDTO.setLastName(c.getLastName());
        customerDTO.setEmail(c.getEmail());
        customerDTO.setPhoneNumber(c.getPhoneNumber());
        customerDTO.setBirthDate(c.getBirthDate());
        customerDTO.setGender(c.getGender());
        customerDTO.setSocialId(c.getSocialId());
        customerDTO.setTaxExemption(c.isTaxExemption());
        customerDTO.setApplyDiscount(c.isApplyDiscount());

        if(c.getLocation() != null) {
            Optional<Location> location = locationRepository.findById(c.getLocation().getId());

            if (location.isPresent()) {
                Location l = location.get();

                int provinceId = l.getProvince()!=null ? l.getProvince().getId():0;
                int countryId = l.getProvince()!=null ? l.getProvince().getCountry().getId():0;
                customerDTO.setAddressLine1(l.getStreet1());
                customerDTO.setAddressLine2(l.getStreet2());
                customerDTO.setCity(l.getCity());
                customerDTO.setPostalCode(l.getPostalCode());
                customerDTO.setProvinceId(provinceId);
                customerDTO.setCountryId(countryId);
            }
        }
        return customerDTO;

    }

    @Override
    public void saveCustomer(CustomerDTO customer) {
        logger.info("saving customer with values {}", customer);

        Customer c = new Customer(customer.getFirstName(), customer.getLastName(), customer.getSocialId(),
                customer.getPhoneNumber(), customer.getEmail(), customer.getBirthDate(), customer.getGender(), customer.isTaxExemption(), customer.isApplyDiscount());

        Optional<Boolean> existSocialId = customerRepository.existBySocialId(customer.getSocialId());
        if(existSocialId.isPresent() && existSocialId.get())
            throw new DuplicatedResourceException(FieldType.SOCIAL_ID, customer.getSocialId());

        Optional<Boolean> existPhone  = customerRepository.existByPhoneNumber(customer.getPhoneNumber());
        if(existPhone.isPresent() && existPhone.get())
            throw new DuplicatedResourceException(FieldType.PHONE, customer.getPhoneNumber());

        Optional<Boolean> existEmail = customerRepository.existByEmail(customer.getEmail());
        if(existEmail.isPresent() && existEmail.get())
            throw new DuplicatedResourceException(FieldType.EMAIL, customer.getEmail());

        Location location;

        if(customer.getProvinceId()>0) {// find province
            Optional<Province> province = provinceRepository.findById(customer.getProvinceId());
            if (!province.isPresent())
                throw new IllegalArgumentException("Province with name [" + customer.getProvinceId() + "] does not exist");


            location = new Location(customer.getAddressLine1(),
                    customer.getAddressLine2(), customer.getCity(), customer.getPostalCode(), province.get());
        }else {
            location = new Location();
        }

        c.setLocation(location);
        locationRepository.save(location);

        c.setCreateAt(LocalDateTime.now());
        customerRepository.save(c);
    }

    @Override
    public void updateCustomer(CustomerDTO customer, String id) {
        logger.info("updating customer with values {}", customer);

        Optional<Customer> c = customerRepository.findById(id);

        if(!c.isPresent())
            throw new IllegalArgumentException("Invalid customer id");

        Customer toUpdate = c.get();

        toUpdate.setFirstName(customer.getFirstName());
        toUpdate.setLastName(customer.getLastName());
        toUpdate.setPhoneNumber(customer.getPhoneNumber());
        toUpdate.setEmail(customer.getEmail());
        toUpdate.setSocialId(customer.getSocialId());
        toUpdate.setGender(customer.getGender());
        toUpdate.setBirthDate(customer.getBirthDate());
        toUpdate.setTaxExemption(customer.isTaxExemption());
        toUpdate.setApplyDiscount(customer.isApplyDiscount());

        if(toUpdate.getSocialId() != customer.getSocialId()){
            Optional<Boolean> existSocialId = customerRepository.existBySocialId(customer.getSocialId());
            if(existSocialId.isPresent() && existSocialId.get())
                throw new DuplicatedResourceException(FieldType.SOCIAL_ID, customer.getSocialId());
        }

        if(toUpdate.getEmail() != customer.getEmail()) {
            Optional<Boolean> existPhone  = customerRepository.existByPhoneNumber(customer.getPhoneNumber());
            if(existPhone.isPresent() && existPhone.get())
                throw new DuplicatedResourceException(FieldType.PHONE, customer.getPhoneNumber());
        }

        if(toUpdate.getPhoneNumber() != customer.getPhoneNumber()){
            Optional<Boolean> existPhone  = customerRepository.existByPhoneNumber(customer.getPhoneNumber());
            if(existPhone.isPresent() && existPhone.get())
                throw new DuplicatedResourceException(FieldType.PHONE, customer.getPhoneNumber());
        }

        Location location = toUpdate.getLocation();

        if(customer.getProvinceId()>0) {// find province
            Optional<Province> province = provinceRepository.findById(customer.getProvinceId());

            if (!province.isPresent())
                throw new IllegalArgumentException("Province with name [" + customer.getProvinceId() + "] does not exist");


            location.setProvince(province.get());
        }

        location.setStreet1(customer.getAddressLine1());
        location.setStreet2(customer.getAddressLine2());
        location.setPostalCode(customer.getPostalCode());
        location.setCity(customer.getCity());

        locationRepository.save(location);

        customerRepository.save(toUpdate);
    }

    @Override
    public List<Customer> findAll() {
        logger.info("finding all customers");

        return customerRepository.findAllByOrderByCreateAtAsc();
    }

    @Override
    public void removeCustomer(String customerId) {
        Customer customer = new Customer(customerId);

        customerRepository.delete(customer);
    }
}
