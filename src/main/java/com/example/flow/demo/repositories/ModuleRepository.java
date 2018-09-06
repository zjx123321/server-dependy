package com.example.flow.demo.repositories;

import com.example.flow.demo.entity.node.Module;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
public interface ModuleRepository extends Neo4jRepository<Module, Long> {

    @Query("Match (n:Module {name:{0}}) WITH n RETURN n,[[(n)-[r_c1: Contains]->(s1:Service) | [r_c1, s1]]]")
    Module findByName(String name);



}
