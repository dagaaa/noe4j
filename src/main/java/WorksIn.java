import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "WORKS_IN")
public class WorksIn {
    @StartNode
    private Worker worker;
    @EndNode
    private Company company;
    @Property
    private Date startDate;
    @Property
    private String job;
}
