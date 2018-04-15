import org.neo4j.cypher.internal.frontend.v2_3.SemanticDirection;
import org.neo4j.ogm.annotation.*;

import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;


@NodeEntity(label = "Company")
public class Company {
    @Relationship(type = "WORKS_IN", direction = INCOMING)
    private Set<Worker> workers;
    @Property
    private String address;
    @GraphId
    private long id;
    @Property
    private String name;

}
