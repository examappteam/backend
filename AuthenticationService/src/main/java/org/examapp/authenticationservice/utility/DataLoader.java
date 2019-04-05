package org.examapp.authenticationservice.utility;

import org.examapp.service.authentication.model.ROLE_NAME;
import org.examapp.service.authentication.model.Role;
import org.examapp.service.authentication.model.User;
import org.examapp.service.authentication.repository.RoleRepository;
import org.examapp.service.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Role studentRole = new Role(ROLE_NAME.ROLE_STUDENT);
        Role teacherRole = new Role(ROLE_NAME.ROLE_TEACHER);
        Role adminRole = new Role(ROLE_NAME.ROLE_ADMIN);

        roleRepository.save(studentRole);
        roleRepository.save(teacherRole);
        roleRepository.save(adminRole);

        User studentUser = new User();
        User teacherUser = new User();
        User adminUser = new User();

        studentUser.setFirstName("Student");
        studentUser.setLastName("Account");
        studentUser.setEmail("Student.Account@Examapp.com");
        studentUser.setUsername("StudentAccount");
        studentUser.setPassword(passwordEncoder.encode("DemoPassword"));
        studentUser.getRoles().add(studentRole);

        teacherUser.setFirstName("Teacher");
        teacherUser.setLastName("Account");
        teacherUser.setEmail("Teacher.Account@Examapp.com");
        teacherUser.setUsername("TeacherAccount");
        teacherUser.setPassword(passwordEncoder.encode("DemoPassword"));
        teacherUser.getRoles().add(teacherRole);

        adminUser.setFirstName("Admin");
        adminUser.setLastName("Account");
        adminUser.setEmail("Admin.Account@Examapp.com");
        adminUser.setUsername("AdminAccount");
        adminUser.setPassword(passwordEncoder.encode("DemoPassword"));
        adminUser.getRoles().add(adminRole);

        userRepository.save(studentUser);
        userRepository.save(teacherUser);
        userRepository.save(adminUser);
    }

}
