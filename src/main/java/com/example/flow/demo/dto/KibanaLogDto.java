package com.example.flow.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KibanaLogDto extends BaseDto {

    private String _index;

    private String _type;

    private String _id;

    private Integer _version;

    private Source _source;

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer get_version() {
        return _version;
    }

    public void set_version(Integer _version) {
        this._version = _version;
    }

    public Source get_source() {
        return _source;
    }

    public void set_source(Source _source) {
        this._source = _source;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source
    {
        private String ips;

        @JsonSetter("kafka.topic")
        private String kafkaTopic;

        private Items items;

        private String host;

        private String uuid;

        private String module;

        private Long timestamp;

        private String level;

        private String project;

        private String chain;

        private String location;

        public LogDto buildLogDto() {
            LogDto logDto = new LogDto();
            logDto.setModule(module);
            logDto.setService(items.filterRequestUrl);
            logDto.setTraceId(items.traceid);
            logDto.setSpanId(items.spanid);
            logDto.setpSpanId(items.pspanid);
            logDto.setCostTime(items.filterRequestCostTime);
            return logDto;
        }

        public String getIps() {
            return ips;
        }

        public void setIps(String ips) {
            this.ips = ips;
        }

        public String getKafkaTopic() {
            return kafkaTopic;
        }

        public void setKafkaTopic(String kafkaTopic) {
            this.kafkaTopic = kafkaTopic;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getChain() {
            return chain;
        }

        public void setChain(String chain) {
            this.chain = chain;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Items
    {
//        private String rediskey;

        @JsonSetter("pSpanId")
        private String pspanid;

        @JsonSetter("logType")
        private String logtype;

//        private String redisvalue;

        @JsonSetter("spanId")
        private String spanid;

        @JsonSetter("traceId")
        private String traceid;

        private String filterRequestUrl;

        private String filterRequestParam;

        private String filterRequestCostTime;

        public String getPspanid() {
            return pspanid;
        }

        public void setPspanid(String pspanid) {
            this.pspanid = pspanid;
        }

        public String getLogtype() {
            return logtype;
        }

        public void setLogtype(String logtype) {
            this.logtype = logtype;
        }

        public String getSpanid() {
            return spanid;
        }

        public void setSpanid(String spanid) {
            this.spanid = spanid;
        }

        public String getTraceid() {
            return traceid;
        }

        public void setTraceid(String traceid) {
            this.traceid = traceid;
        }

        public String getFilterRequestUrl() {
            return filterRequestUrl;
        }

        public void setFilterRequestUrl(String filterRequestUrl) {
            this.filterRequestUrl = filterRequestUrl;
        }

        public String getFilterRequestParam() {
            return filterRequestParam;
        }

        public void setFilterRequestParam(String filterRequestParam) {
            this.filterRequestParam = filterRequestParam;
        }

        public String getFilterRequestCostTime() {
            return filterRequestCostTime;
        }

        public void setFilterRequestCostTime(String filterRequestCostTime) {
            this.filterRequestCostTime = filterRequestCostTime;
        }
    }

}




