package com.wade.crys.user;

import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;
import static com.wade.crys.utils.rdf.CRYS.USER_URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wade.crys.user.interfaces.UserRepository;
import com.wade.crys.user.model.User;
import com.wade.crys.utils.rdf.CRYS;
import com.wade.crys.utils.rdf.CrysOntologyEnum;
import com.wade.crys.utils.rdf.FOAF;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static List<User> users = new ArrayList<>();

    @Autowired
    private Dataset dataset;

    static {
        users.add(new User("uuid-13ew2-21312-31sq", "Daniel", "Oana", "test", "test", "07656784385", true, new ArrayList<>(), new ArrayList<>()));
        users.add(new User("uuid-oa423-rwe3-423wr", "Rares", "Podaru", "rares.podaru@gmail.com", "password-21", "07656784385", true, new ArrayList<>(), new ArrayList<>()));
    }

    @Override
    public Optional<User> getUserById(String uuid) {

        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        User user = null;

        dataset.begin(ReadWrite.READ);

        try{
            ResultSet rs = QueryExecutionFactory.create(
                    String.format(CrysOntologyEnum.GET_USER_BY_EMAIL_AND_PASSWORD_QRY.getCode(), email), dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String uuid = qs.get("user").toString().substring(qs.get("user").toString().indexOf("-") + 1);
                String firstName = qs.get("firstName").toString();
                String lastName = qs.get("lastName").toString();
                String password = qs.get("password").toString();
                String telephone = qs.get("telephone").toString();
                boolean emailNotification = qs.get("emailNotification").toString().equals("true") ||
                        (qs.get("emailNotification").toString().equals("false") ? false : null);

                user = new User(uuid, firstName, lastName, email, password, telephone, emailNotification, null, null);
            }

        } finally {

            dataset.end();
        }

        return Optional.of(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {

        dataset.begin(ReadWrite.WRITE);

        try {
            Model userModel = dataset.getDefaultModel();

            Resource userResource = userModel.createResource(CRYS_URI + "user-" + UUID.randomUUID());
            userResource.addProperty(CRYS.type, userModel.createResource(CRYS.CRYS_URI + "User"));
            userResource.addProperty(FOAF.firstName, user.getFirstName());
            userResource.addProperty(FOAF.lastName, user.getLastName());
            userResource.addProperty(FOAF.email, user.getEmail());
            userResource.addProperty(FOAF.telephone, user.getTelephone());
            userResource.addProperty(CRYS.password, user.getPassword());
            userResource.addProperty(CRYS.emailNotification, user.isEmailNotification() ? "true" : "false");
            userResource.addProperty(CRYS.token, user.getToken());

            dataset.addNamedModel(USER_URI, userModel);

            dataset.commit();
            dataset.end();
        } catch (Exception e) {

            System.out.println(e);
        }
        finally {

            dataset.end();
        }
    }
}
