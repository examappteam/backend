package org.examapp.authenticationservice.utility;

import org.examapp.authenticationservice.model.Account;
import org.examapp.authenticationservice.model.ROLE_NAME;
import org.examapp.authenticationservice.model.Role;
import org.examapp.authenticationservice.repository.AccountRepository;
import org.examapp.authenticationservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.save(new Role(ROLE_NAME.ROLE_STUDENT));
        roleRepository.save(new Role(ROLE_NAME.ROLE_TEACHER));
        roleRepository.save(new Role(ROLE_NAME.ROLE_ADMIN));

        Account student = new Account("Student", "A","student@examapp.com", "student", encoder.encode("examapp"));
        student.getRoles().add(roleRepository.findByName(ROLE_NAME.ROLE_STUDENT).get());

        Account teacher = new Account("Teacher", "A", "teacher@examapp.com", "teacher", encoder.encode("examapp"));
        teacher.getRoles().add(roleRepository.findByName(ROLE_NAME.ROLE_TEACHER).get());

        Account admin = new Account("Admin", "A","admin@examapp.com", "admin", encoder.encode("examapp"));
        admin.getRoles().add(roleRepository.findByName(ROLE_NAME.ROLE_ADMIN).get());

        accountRepository.save(student);
        accountRepository.save(teacher);
        accountRepository.save(admin);
    }
}
