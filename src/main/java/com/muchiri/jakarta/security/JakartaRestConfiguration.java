package com.muchiri.jakarta.security;

import com.muchiri.jakarta.security.model.Group;
import com.muchiri.jakarta.security.model.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import static java.math.BigInteger.ONE;
import java.math.BigInteger;
import java.util.Map;

/**
 * Configures Jakarta RESTful Web Services for the application.
 *
 * @author muchiri
 */
@BasicAuthenticationMechanismDefinition(realmName = "basicAuth")
@DatabaseIdentityStoreDefinition(
        callerQuery = "select password from basic_auth_user where username = ?",
        groupsQuery = "select name from basic_auth_group where username = ?",
        dataSourceLookup = "jdbc/kennedy_resource",
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3072",
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
            "Pbkdf2PasswordHash.SaltSizeBytes=64"
        }
)
@DeclareRoles({"user", "caller"})
@ApplicationScoped
@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {

    private static final BigInteger USER_ID = ONE;
    private static final BigInteger GROUP_ID = ONE;

    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Transactional
    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object applicationContext) {
        passwordHash.initialize(Map.of(
                "Pbkdf2PasswordHash.Iterations", "3072",
                "Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512",
                "Pbkdf2PasswordHash.SaltSizeBytes", "64"
        ));

        if (entityManager.find(User.class, USER_ID) == null) {
            var user = new User();
            user.id = USER_ID;
            user.username = "john";
            user.password = passwordHash.generate("secret1".toCharArray());
            entityManager.persist(user);
        }

        if (entityManager.find(Group.class, GROUP_ID) == null) {
            var group = new Group();
            group.id = GROUP_ID;
            group.name = "user";
            group.username = "john";
            entityManager.persist(group);
        }

    }
}
