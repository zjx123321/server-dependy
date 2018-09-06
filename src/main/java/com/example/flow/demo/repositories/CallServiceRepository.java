package com.example.flow.demo.repositories;

import com.example.flow.demo.entity.node.Service;
import com.example.flow.demo.entity.relation.CallService;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
public interface CallServiceRepository extends Neo4jRepository<CallService, Long> {

    @Query("match (s:Service {name:{0}, moduleName:{1}})-[r]->(e:Service {name:{2}, moduleName:{3}}) return ID(r)")
    Long findIdByServices(String start, String smodule, String end, String emodule);

}
