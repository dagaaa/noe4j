import org.neo4j.graphdb.*;

public class Creator {
    public  Node createWorker(GraphDatabaseService graphDb, String name, String lastname, int age){
        try (Transaction transaction = graphDb.beginTx()) {
            Node person = graphDb.createNode(Label.label("Worker"));
            person.setProperty("name", name);
            person.setProperty("lastname", lastname);
            person.setProperty("age", age);
            transaction.success();
            transaction.close();
            return person;
        }
    }
    public  Node createCompany(GraphDatabaseService graphDb,String address,String name){
        try (Transaction transaction = graphDb.beginTx()) {
            Node company = graphDb.createNode(Label.label("Company"));
            company.setProperty("name", name);
            company.setProperty("address", address);
            transaction.success();
            transaction.close();
            return company;
        }
    }
    public  Relationship creteRelationship(GraphDatabaseService graphDb, Node first, Node second, String relationName){
        try (Transaction transaction = graphDb.beginTx()) {
            Relationship rel= first.createRelationshipTo(second, RelationshipType.withName(relationName));
            transaction.success();
            transaction.close();
            return rel;}
    }
}
