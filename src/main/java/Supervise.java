import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Date;

@RelationshipEntity(type = "SUPERVISE")
public class Supervise {
    @StartNode
    private Worker supervisor;
    @EndNode
    private Worker worker;

}
