package com.example.flow.demo.entity.relation;

import com.example.flow.demo.entity.node.Module;
import org.neo4j.ogm.annotation.*;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
@RelationshipEntity(type = "Call")
public class CallModule {

    @Id
    @GeneratedValue
    private Long id;

    public CallModule() {
    }

    @StartNode
    private Module start;

    @EndNode
    private Module end;

    private String relation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Module getStart() {
        return start;
    }

    public void setStart(Module start) {
        this.start = start;
    }

    public Module getEnd() {
        return end;
    }

    public void setEnd(Module end) {
        this.end = end;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
