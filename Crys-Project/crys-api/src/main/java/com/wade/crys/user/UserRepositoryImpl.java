package com.wade.crys.user;

import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;
import static com.wade.crys.utils.rdf.CRYS.USER_URI;

import java.util.ArrayList;
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

    @Autowired
    private Dataset dataset;

    @Override
    public Optional<User> getUserById(String uuid) {

        return Optional.ofNullable(getUserFromQueryResult(String.format(CrysOntologyEnum.GET_USER_BY_ID_QRY.getCode(), uuid)));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        return Optional.ofNullable(getUserFromQueryResult(String.format(CrysOntologyEnum.GET_USER_BY_EMAIL_QRY.getCode(), email)));
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

            dataset.addNamedModel(USER_URI, userModel);

            dataset.commit();
            dataset.end();

//            dataset.begin(ReadWrite.READ);
//            try(QueryExecution qExec = QueryExecutionFactory.create(CrysOntologyEnum.GET_ALL_USERS_QRY.getCode(), dataset)) {
//                ResultSet rs = qExec.execSelect() ;
//                ResultSetFormatter.out(rs) ;
//            }
//            dataset.end();
        } catch (Exception e) {

            System.out.println(e);
        }
        finally {

            dataset.end();
        }
    }

    private User getUserFromQueryResult(String query) {

        dataset.begin(ReadWrite.READ);

        try{
            ResultSet rs = QueryExecutionFactory.create(query, dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String uuid = qs.get("user").toString().substring(qs.get("user").toString().indexOf("-") + 1);
                String firstName = qs.get("firstName").toString();
                String lastName = qs.get("lastName").toString();
                String email = qs.get("email").toString();
                String password = qs.get("password").toString();
                String telephone = qs.get("telephone").toString();
                boolean emailNotification = qs.get("emailNotification").toString().equals("true");

                return new User(uuid, firstName, lastName, email, password, telephone, emailNotification, new ArrayList<>(), new ArrayList<>());
            }

        } finally {

            dataset.end();
        }

        return null;
    }
}
