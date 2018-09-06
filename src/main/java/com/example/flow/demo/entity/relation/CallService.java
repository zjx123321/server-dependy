package com.example.flow.demo.entity.relation;

import com.example.flow.demo.entity.node.Service;
import org.neo4j.ogm.annotation.*;

/**
 * Created by zhengjiexiang on 2018/9/4
 */
@RelationshipEntity(type = "Call")
public class CallService {

    @Id
    @GeneratedValue
    private Long id;

    public CallService() {
    }

    @StartNode
    private Service start;

    @EndNode
    private Service end;

    private String time;

    private String relation;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getStart() {
        return start;
    }

    public void setStart(Service start) {
        this.start = start;
    }

    public Service getEnd() {
        return end;
    }

    public void setEnd(Service end) {
        this.end = end;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
