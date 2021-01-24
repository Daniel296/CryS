package com.wade.crys.user;

import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;
import static com.wade.crys.utils.rdf.CRYS.USER_URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDB;
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

        dataset.begin(ReadWrite.READ);
        try(QueryExecution qExec = QueryExecutionFactory.create("SELECT ?s ?p ?o WHERE { ?s ?p ?o }", dataset)) {
            ResultSet rs = qExec.execSelect() ;
            ResultSetFormatter.out(rs) ;
        }
        dataset.end();

        return Optional.ofNullable(getUserFromQueryResult(String.format(CrysOntologyEnum.GET_USER_BY_EMAIL_QRY.getCode(), email)));
    }

    @Override
    public List<User> getUsersWithEmailNotification() {

        List<User> users = new ArrayList<>();

        dataset.begin(ReadWrite.READ);

        try{
            ResultSet rs = QueryExecutionFactory.create(CrysOntologyEnum.GET_USERS_WITH_EMAIL_NOTIFICATION_QRY.getCode(), dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String uuid = qs.get("user").toString().substring(qs.get("user").toString().indexOf("-") + 1);
                String firstName = qs.get("firstName").toString();
                String lastName = qs.get("lastName").toString();
                String email = qs.get("email").toString();
                String password = qs.get("password").toString();
                String telephone = qs.get("telephone").toString();
                boolean emailNotification = qs.get("emailNotification").toString().equals("true");

                users.add(new User(uuid, firstName, lastName, email, password, telephone, emailNotification, new ArrayList<>(), new ArrayList<>()));
            }

        } finally {

            dataset.end();
        }
        
        return users;
    }

    @Override
    public void addUser(User user) {

        dataset.begin(ReadWrite.WRITE);

        try {
            Model userModel = dataset.getDefaultModel();

            Resource userResource = userModel.createResource(CRYS_URI + "user-" + user.getUuid());
            userResource.addProperty(CRYS.type, userModel.createResource(CRYS.CRYS_URI + "User"));
            userResource.addProperty(FOAF.firstName, user.getFirstName());
            userResource.addProperty(FOAF.lastName, user.getLastName());
            userResource.addProperty(FOAF.email, user.getEmail());
            userResource.addProperty(FOAF.telephone, user.getTelephone());
            userResource.addProperty(CRYS.password, user.getPassword());
            userResource.addProperty(CRYS.emailNotification, user.isEmailNotification() ? "true" : "false");

            dataset.addNamedModel(USER_URI + "user-" + user.getUuid(), userModel);

            dataset.commit();
        } catch (Exception e) {

            dataset.abort();
        }
        finally {

            dataset.end();

            TDB.sync(dataset);
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
