package csc.config;

import java.math.BigDecimal;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import csc.repository.CompanyRepository;
import csc.repository.CustomerRepository;
import csc.repository.RoleRepository;
import csc.repository.TypeInvoiceRepository;
import csc.repository.UserRepository;
import csc.models.Company;
import csc.models.Customer;
import csc.models.Role;
import csc.models.TypeInvoice;
import csc.models.Users;

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
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			tmp = new Users();
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

		//
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
