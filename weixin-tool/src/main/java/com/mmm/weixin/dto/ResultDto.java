package com.mmm.weixin.dto;

import java.util.List;

public class ResultDto {
    private int matchId;
    private int areaId;
    private int holeId;
    private List<MatchResultDto> Results;

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getAreaId() {
        return areaId;
    }

    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    public List<MatchResultDto> getResults() {
        return Results;
    }

    public void setResults(List<MatchResultDto> results) {
        Results = results;
    }
}
