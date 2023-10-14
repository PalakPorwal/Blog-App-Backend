package com.palak;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.palak.config.AppConstants;
import com.palak.entities.Category;
import com.palak.entities.Role;
import com.palak.repositories.CategoryRepo;
import com.palak.repositories.RoleRepo;
import java.util.*;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CategoryRepo categoryRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
	return new ModelMapper();	
	}
	
	public void run(String... args) throws Exception
	{
		System.out.println(this.passwordEncoder.encode("palak123"));

		try{
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<Role> roles=List.of(role,role1);

			List<Role> result=this.roleRepo.saveAll(roles);

			Category cat1 = new Category();
			cat1.setCategoryId(1);
			cat1.setCategoryTitle("Java");
			cat1.setCategoryDescription("PL");

			Category cat2 = new Category();
			cat2.setCategoryId(2);
			cat2.setCategoryTitle("Python");
			cat2.setCategoryDescription("SL");

			List<Category> cat = List.of(cat1, cat2);
			List<Category> saveCat = this.categoryRepo.saveAll(cat);

			result.forEach(r->{
				System.out.println(r.getName());
			});

			saveCat.forEach(c -> {
				System.out.println(c.getCategoryTitle());
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
