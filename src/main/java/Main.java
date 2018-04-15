import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.File;


public class Main {

    public static String shortest(GraphDatabaseService gr, String name1, String type1, String name2, String type2) {
        Result res = gr.execute(
                "MATCH p=shortestPath((c:" + type1 + " {name:\"" + name1 + "\"}) -[*]- (a:" + type2 + " {name:\"" + name2 + "\"})) " +
                        "RETURN p");
        return res.resultAsString();

    }

    public static Iterable<Relationship> getRelationship(GraphDatabaseService gr, int Id) {

        Node n = gr.getNodeById(Id);
        return n.getRelationships();
    }

    public static String getRelationship1(GraphDatabaseService gr, String name, String type) {
        Result n = gr.execute(
                "MATCH (c:" + type + ") -[w]- () where c.name=\"" + name +
                        "\" RETURN w");
        return n.resultAsString();
    }

    public static void exercise1(GraphDatabaseService graphDb) {
        Node person = graphDb.createNode(Label.label("Worker"));
        person.setProperty("name", "Dagmara");
        person.setProperty("lastname", "GOj");
        person.setProperty("age", 21);
        Node dep = graphDb.createNode(Label.label("Worker"));
        dep.setProperty("name", "szefuncio");
        dep.setProperty("lastname", "GOj");
        dep.setProperty("age", 32);

        person.createRelationshipTo(dep, RelationshipType.withName("SUPERVISES"));
    }

    public  static  void exercise2(GraphDatabaseService graphDb){
        Creator creator = new Creator();
        Node a = creator.createWorker(graphDb, "Anna", "Smuda", 21);
        Node b = creator.createWorker(graphDb, "Marin", "Zebcz", 50);
        Node c = creator.createWorker(graphDb, "Jan", "Matejko", 50);
        Node d = creator.createCompany(graphDb, "rdzikoskiego", "beznadziejnafirma");

        creator.creteRelationship(graphDb, a, b, "SUPERVISES");
        creator.creteRelationship(graphDb, b, a, "REPORTS_TO");
        creator.creteRelationship(graphDb, c, d, "WORKS_IN");
    }

    public  static void exercise3(GraphDatabaseService graphDb){
        Generator generator = new Generator();
        generator.generate(graphDb);
    }
    public static void  exercise4(GraphDatabaseService graphDb){
        Iterable<Relationship> r = getRelationship(graphDb, 152);
        for (Relationship rel : r)
            System.out.println(rel);

        System.out.println(getRelationship1(graphDb, "Marysia", "Worker"));
    }
    public static void exercise5(GraphDatabaseService graphDb){
        Creator creator=new Creator();
        Node person1 = graphDb.createNode(Label.label("Worker"));
        person1.setProperty("name", "Marysia");
        person1.setProperty("lastname", "Zieba");
        person1.setProperty("age", 21);
        Node dep1 = graphDb.createNode(Label.label("Worker"));
        dep1.setProperty("name", "Andrzej");
        dep1.setProperty("lastname", "Niewiadomski");
        dep1.setProperty("age", 32);

        Node e = creator.createWorker(graphDb, "Monika", "Kostka", 45);
        Node f = creator.createWorker(graphDb, "Zbigniew", "Koscinski", 60);
        Node g = creator.createWorker(graphDb, "Julia", "Julkowska", 60);

        Node h = creator.createWorker(graphDb, "Julin", "Rybka", 57);
        Result result4 = graphDb.execute(
                "MATCH (c:Worker) -[w]- () where c.name=\"Julin\"" +
                        "RETURN c.name,c.lastname");
        System.out.println(result4.resultAsString());

        creator.creteRelationship(graphDb, e, f, "SUPERVISES");
        creator.creteRelationship(graphDb, f, g, "SUPERVISES");
        creator.creteRelationship(graphDb, g, h, "SUPERVISES");
        System.out.println(graphDb.execute("Match (p:Worker {name: \"Monika\"}) return p"));
        System.out.println(shortest(graphDb, "Monika", "Worker", "Julin", "Worker"));
    }
    public static void main(String[] args) {
        String graphDir = "C:\\Users\\dagmara\\Documents\\Neo4j\\default.graphdb";
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabaseBuilder(new File(graphDir))
                .setConfig(GraphDatabaseSettings.allow_upgrade, "true")
                .newGraphDatabase();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });


        Transaction tx = graphDb.beginTx();

        exercise1(graphDb);
        exercise2(graphDb);
        exercise3(graphDb);




        Result result = graphDb.execute(
                "MATCH (c:Worker) -[s:SUPERVISES]-> (p:Worker) " +
                        "RETURN c.name,p.name,s");
        Result result2 = graphDb.execute(
                "MATCH (c:Worker) -[r:REPORTS_TO]-> (p:Worker) " +
                        "RETURN c.name,p.name,r");
        Result result1 = graphDb.execute(
                "MATCH (c:Worker) -[r:WORKS_IN]-> (a:Company) " +
                        "RETURN c.name,a.name,r");
        System.out.println(result.resultAsString());
        System.out.println(result1.resultAsString());
        System.out.println(result2.resultAsString());

        Result result3 = graphDb.execute(
                "MATCH (c)-[r]-() where id(c)=152 " +
                        "RETURN c.name,c.lastname,r");
        System.out.println(result3.resultAsString());

        exercise5(graphDb);

        System.out.println(graphDb.execute("CALL db.schema()").resultAsString());
        System.out.println(graphDb.execute("call db.schema").resultAsString());
        System.out.println(graphDb.execute("call db.relationshipTypes").resultAsString());

        System.out.println(graphDb.execute("call db.labels").resultAsString());


        tx.success();

        tx.close();
    }


}
