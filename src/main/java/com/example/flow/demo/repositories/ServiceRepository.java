package com.example.flow.demo.repositories;

import com.example.flow.demo.entity.node.Module;
import com.example.flow.demo.entity.node.Service;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
public interface ServiceRepository extends Neo4jRepository<Service, Long> {

    @Query("Match (m:Service {name:{0}, moduleName:{1}}) return m")
    Service findByNames(String name, String module);

}


