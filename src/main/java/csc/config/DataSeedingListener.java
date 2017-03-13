package csc.config;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import csc.models.Company;
import csc.models.Customer;
import csc.models.Invoice;
import csc.models.Role;
import csc.models.TypeInvoice;
import csc.models.Users;
import csc.repository.CompanyRepository;
import csc.repository.CustomerRepository;
import csc.repository.InvoiceRepository;
import csc.repository.RoleRepository;
import csc.repository.TypeInvoiceRepository;
import csc.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TypeInvoiceRepository typeInvoiceRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		Users tmp = new Users();
		// Admin account
		if (userRepository.findByUsername("admin02@gmail.com") == null) {
			Users admin = new Users();
			admin.setUsername("admin02@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setActive("1");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			admin.setRoles(roles);
			tmp = userRepository.save(admin);
			createCustomer(tmp);

		}

		// Admin account
		if (userRepository.findByUsername("admin01@gmail.com") == null) {
			Users admin = new Users();
			admin.setUsername("admin01@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setActive("1");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			admin.setRoles(roles);
			tmp = userRepository.save(admin);
			createCustomer(tmp);

		}

		// Member account
		if (userRepository.findByUsername("member@gmail.com") == null) {
			Users user = new Users();
			user.setUsername("member@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setActive("1");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			tmp = new Users();
			tmp = userRepository.save(user);
			createCustomer(tmp);
		}
		// Test create 100 record for function paging with role admin
		// for (int index = 0; index < 20; index++) {
		// if (userRepository.findByUsername("member" + index + "@gmail.com") ==
		// null) {
		// Users user = new Users();
		// user.setUsername("member" + index + "@gmail.com");
		// user.setPassword(passwordEncoder.encode("123456"));
		// user.setActive("1");
		// HashSet<Role> roles = new HashSet<>();
		// roles.add(roleRepository.findByName("ROLE_MEMBER"));
		// user.setRoles(roles);
		// tmp = new Users();
		// tmp = userRepository.save(user);
		// createCustomer(tmp);
		// }
		// }
		// End Test

		// Create TypeInvoice record
		System.out.println(companyRepository.findByNameCpn("Thang Long"));
		if (companyRepository.findByNameCpn("Thang Long") == null) {
			Company cpn = new Company();
			cpn.setAddress("123 Dong Khoi");
			cpn.setBankAccount("123456789");
			cpn.setFax(Integer.parseInt("085623149"));
			cpn.setNameCpn("Thang Long");
			cpn.setPhoneCpn(Integer.parseInt("0123456852"));
			cpn.setTaxCode(Integer.parseInt("105878523"));
			cpn.setIdCpn("123456");
			companyRepository.save(cpn);
		}

		// Create Company record
		TypeInvoice ti = new TypeInvoice();
		if (typeInvoiceRepository.findByNameInvoice("Electricity Bill") == null) {
			ti = new TypeInvoice();
			ti.setNameInvoice("Electricity Bill");
			ti.setDescription("Electricity Bill");
			ti.setVat(Float.parseFloat("10"));
			typeInvoiceRepository.save(ti);
		}
		if (typeInvoiceRepository.findByNameInvoice("Water Bill") == null) {
			ti = new TypeInvoice();
			ti.setNameInvoice("Water Bill");
			ti.setDescription("Water Bill");
			ti.setVat(Float.parseFloat("8"));
			typeInvoiceRepository.save(ti);
		}
		if (typeInvoiceRepository.findByNameInvoice("Internet Bill") == null) {
			ti = new TypeInvoice();
			ti.setNameInvoice("Internet Bill");
			ti.setDescription("Internet Bill");
			ti.setVat(Float.parseFloat("5"));
			typeInvoiceRepository.save(ti);
		}
		if (typeInvoiceRepository.findByNameInvoice("Phone Bill") == null) {
			ti = new TypeInvoice();
			ti.setNameInvoice("Phone Bill");
			ti.setDescription("Phone Bill");
			ti.setVat(Float.parseFloat("5"));
			typeInvoiceRepository.save(ti);
		}

		// Create Invoice record
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Date date = new Date();
			for (int index = 0; index < 2; index++) {
				ti = new TypeInvoice();
				Invoice invoice = new Invoice();
				Float vat;
				Float indexConsumed = 100F;
				BigDecimal total = new BigDecimal(indexConsumed * (index + 1));
				BigDecimal ptef = new BigDecimal(index);
				BigDecimal grandTotal;
				invoice.setContractNumber("AC123456" + index);
				invoice.setDate(date);
				invoice.setIndexConsumed(indexConsumed);
				invoice.setNameService("G20");
				invoice.setPtef(ptef);
				invoice.setTotal(total);

				// set Type Invoice
				int idType = 1;
				TypeInvoice typeInvoice = new TypeInvoice();
				typeInvoice = typeInvoiceRepository.findById(idType);
				vat = typeInvoice.getVat();
				invoice.setIdType(typeInvoice);
				invoice.setVat(vat);
				grandTotal = total.add(total.multiply(BigDecimal.valueOf(vat)));
				invoice.setGrandTotal(grandTotal);

				// set Id Company
				Company tmpCpn = new Company();
				tmpCpn = companyRepository.findById(1);
				invoice.setIdCpn(tmpCpn);

				// set Id Customer
				Customer tmpCus = new Customer();
				tmpCus = customerRepository.findById(1L);
				invoice.setIdCustomer(tmpCus);

				invoiceRepository.save(invoice);
			}
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}

	}

	public void createCustomer(Users user) {
		Customer cus = new Customer();
		cus.setUser(user);
		cus.setAddress("test");
		cus.setEmail("test");
		cus.setNameCustomer("test");
		cus.setIdCustomer("test" + user.getId());
		cus.setPhone(12345671);
		cus.setTaxCode(Integer.parseInt("123457"));
		cus.setLimitConsume(BigDecimal.valueOf(1234));
		customerRepository.save(cus);

	}

}
