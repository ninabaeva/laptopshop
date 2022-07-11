package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.CustomerDto;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMERS_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CUSTOMERS_PATH)));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();

        CustomerDto[] customerDtos = this.gson.fromJson(this.readCustomersFileContent(), CustomerDto[].class);

        for (CustomerDto customerDto : customerDtos) {
            if (this.validationUtil.isValid(customerDto)
                    && customerRepository.findByEmail(customerDto.getEmail()) == null
                    && townRepository.findByName(customerDto.getTown().getName()) != null) {

                Customer customer = this.modelMapper.map(customerDto, Customer.class);
                customer.setTown(townRepository.findByName(customerDto.getTown().getName()));

                this.customerRepository.saveAndFlush(customer);

                sb.append(String
                        .format("Successfully imported Customer - %s - %s", customer.getFirstName() + " " + customer.getLastName(), customer.getEmail()))
                        .append(System.lineSeparator());

            } else {
                sb.append("Invalid customer").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
