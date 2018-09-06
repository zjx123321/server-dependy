package com.example.flow.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response extends BaseDto{

    @JsonSetter("timed_out")
    private Boolean timedOut;

    private _shards _shards;

    private Hits hits;

    public Boolean getTimedOut() {
        return timedOut;
    }

    public void setTimedOut(Boolean timedOut) {
        this.timedOut = timedOut;
    }

    public Response._shards get_shards() {
        return _shards;
    }

    public void set_shards(Response._shards _shards) {
        this._shards = _shards;
    }

    public Hits getHits() {
        return hits;
    }

    public void setHits(Hits hits) {
        this.hits = hits;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Hits
    {
        private Integer total;

        private Integer max_score;

        private List<KibanaLogDto> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getMax_score() {
            return max_score;
        }

        public void setMax_score(Integer max_score) {
            this.max_score = max_score;
        }

        public List<KibanaLogDto> getHits() {
            return hits;
        }

        public void setHits(List<KibanaLogDto> hits) {
            this.hits = hits;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class _shards
    {
        private Integer total;

        private Integer successful;

        private Integer skipped;

        private Integer failed;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getSuccessful() {
            return successful;
        }

        public void setSuccessful(Integer successful) {
            this.successful = successful;
        }

        public Integer getSkipped() {
            return skipped;
        }

        public void setSkipped(Integer skipped) {
            this.skipped = skipped;
        }

        public Integer getFailed() {
            return failed;
        }

        public void setFailed(Integer failed) {
            this.failed = failed;
        }
    }

}
