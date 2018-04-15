import org.neo4j.cypher.internal.frontend.v2_3.SemanticDirection;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import java.util.Set;

@NodeEntity(label = "Worker")
public class Worker {
    @Relationship(type = "WORKS_IN")
    private Set<Company> companies;

    @GraphId
    private long id;

    @Relationship(type = "SUPERVISE")
    private Set<Worker> subordintes;

    @Relationship(type = "REPORTS_TO")
    private Set<Worker> peopleToReportTo;

    @Relationship(type = "SUPERVISE", direction  = INCOMING)
    private Set<Worker>  supervisors;

    @Relationship(type = "REPORTS_TO", direction  = INCOMING)
    private Set<Worker> peopleReporting;

    @Property
    private String name;

    @Property
    private String lastname;
    @Property
    private int age;


}
