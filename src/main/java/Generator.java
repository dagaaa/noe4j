import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import java.util.LinkedList;
import java.util.List;

public class Generator {

    private List<Node> workers;
    private List<Node> companies;
    public Generator(){
        workers=new LinkedList<>();
        companies=new LinkedList<>();

    }
    public void generate(GraphDatabaseService gr){
    Creator creator=new Creator();
    for(int i=0; i<15 ;i++){
        workers.add(creator.createWorker(gr,"Krysia"+i,"Cudowna"+i ,20+(5*i)));

    }
    for (int i=0;i<5;i++)
        companies.add(creator.createCompany(gr,"Makowskiego"+i,"petmex"+i));

     for(int i=0;i<workers.size();i+=3){
         for(int j=i+1;j<i+3;j++) {
             creator.creteRelationship(gr, workers.get(i), workers.get(j), "SUPERVISES");
             creator.creteRelationship(gr, workers.get(j), workers.get(i), "REPORTS_TO");

         }
     }

    for (int i=0;i<companies.size();i++){
        for(int j=i;j<i+3;j++)
            creator.creteRelationship(gr,workers.get(j),companies.get(i),"WORKS_IN");

    }

    }
}
