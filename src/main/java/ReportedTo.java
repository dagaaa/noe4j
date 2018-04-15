import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "REPORTS_TO")
public class ReportedTo {
    @StartNode
    private Worker worker;
    @EndNode
    private Worker supervisor;

}