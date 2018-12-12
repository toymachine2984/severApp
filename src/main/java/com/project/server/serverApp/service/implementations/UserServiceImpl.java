package com.project.server.serverApp.service.implementations;


import com.project.server.serverApp.entity.system.Role;
import com.project.server.serverApp.entity.system.User;
import com.project.server.serverApp.entity.system.VerificationToken;
import com.project.server.serverApp.repository.systemRepository.RoleRepository;
import com.project.server.serverApp.repository.systemRepository.UserRepository;
import com.project.server.serverApp.repository.systemRepository.VerificationTokenRepository;
import com.project.server.serverApp.service.interfaces.UserService;
import com.project.server.serverApp.util.exceptions.EmailExistsException;
import com.project.server.serverApp.util.exceptions.UserIdNotFoundException;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Service("userService")
@Repository
@Transactional("systemTransactionManager")
public class UserServiceImpl implements  UserService {

    private UserRepository userRepository;

    private VerificationTokenRepository verificationTokenRepository;

    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public <S extends User> S save(S s) {
        return userRepository.save(s);
    }

    @Transactional
    @Override
    public Optional<User> update(User s) {
        Optional<User> byId = userRepository.findById(s.getId());
        byId.ifPresent(u -> {
            u.setLocked(s.isLocked());
            u.setExpired(s.isExpired());
            u.setMatchingPassword(u.getPassword());
            u.setLogin(s.getLogin());
            u.setFirstName(s.getFirstName());
            u.setLastName(s.getLastName());
        });
        return byId;

    }


    @Override
    @Transactional(readOnly = true)
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElseThrow(() -> new UserIdNotFoundException(aLong));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByRevision(Long id, int revision) {
        return AuditReaderFactory.get(entityManager).find(User.class, id, revision);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byLoginEquals = userRepository.findByLoginEquals(username);
        return byLoginEquals.map(user -> org.springframework.security.core.userdetails.User.withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .accountLocked(user.isLocked())
                .accountExpired(user.isExpired())
                .build()).orElseThrow(() -> new UsernameNotFoundException(String.format("User with name: %s not found.", username)));
    }


    @Override
    @Transactional
    public User registerNewUserAccount(User user) throws EmailExistsException {
        if (!isLoginExist(user.getLogin())) {
            user.getRoles().add(new Role(2L, "ROLE_USER"));
            return userRepository.save(user);
        } else {
            throw new EmailExistsException(user.getLogin());
        }
    }

    @Transactional
    @Override
    public VerificationToken createVerificationToken(User user, String token) {
        return verificationTokenRepository.save(new VerificationToken(token, user));
    }


    @Transactional
    @Override
    public Optional<VerificationToken> reissueVerificationToken(String token) {
        Optional<VerificationToken> byTokenEquals = verificationTokenRepository.findByTokenEquals(token);
        String newToken = UUID.randomUUID().toString();
        byTokenEquals.ifPresent(o -> o = new VerificationToken(newToken));
        return byTokenEquals;
    }

    @Override
    public <S extends VerificationToken> S save(S entity) {
        return verificationTokenRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<VerificationToken> findByTokenEquals(String token) {
        Optional<VerificationToken> byTokenEquals = verificationTokenRepository.findByTokenEquals(token);
        byTokenEquals.ifPresent(vt -> {
            User currentUser = vt.getUser();
            currentUser.setRoles(roleRepository.findAllByUsers(currentUser));
        });
        return byTokenEquals;
    }


    @Transactional
    @Override
    public boolean isLoginExist(String login) {
        return userRepository.findByLoginEquals(login).isPresent();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        long currentId = Long.parseLong(clientId);
//        User registration_exception = userRepository.findById(currentId).orElseThrow(() -> new ClientRegistrationException("Registration exception"));
//
//    }
}
