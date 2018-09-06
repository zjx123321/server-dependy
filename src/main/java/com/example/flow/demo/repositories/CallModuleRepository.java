package com.example.flow.demo.repositories;

import com.example.flow.demo.entity.relation.CallModule;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
public interface CallModuleRepository extends Neo4jRepository<CallModule, Long> {

    @Query("match (s:Module {name:{0}})-[r]->(e:Module {name:{1}}) return ID(r)")
    Long findIdByModules(String start, String end);

//    @Query("MATCH p=(a:Module)-[r:Call]->(b:Module) where a.name={0} and b.name={1}  RETURN p LIMIT 1")
//    @Query("match (s:Module {name:{0}})-[r:Call]->(e:Module {name:{1}}) return r")
//    Call findByModuleNames(String start, String end);


}
