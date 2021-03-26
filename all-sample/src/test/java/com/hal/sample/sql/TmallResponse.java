package com.hal.sample.sql;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Steven HUANG
 * @Date: 2020/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TmallResponse {

  @JsonProperty("opencrm_member_hismemberdata_get_response")
  private InnerData data;

  public static class InnerData {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("result_dto")
    private ResultData resultData;

    public String getRequestId() {
      return requestId;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }

    public ResultData getResultData() {
      return resultData;
    }

    public void setResultData(ResultData resultData) {
      this.resultData = resultData;
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ResultData {

    @JsonProperty("total")
    private String total;

    @JsonProperty("code")
    private String code;

    @JsonProperty("hsmemberinfo_list")
    private ExtData extData;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExtData {

    @JsonProperty("hs_member_info_dto")
    private List<InfoData> infoData;
  }


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class InfoData {

    @JsonProperty("gmt_modified")
    private String gmtModified;

    @JsonProperty("buyer_nick")
    private String buyerNick;

    @JsonProperty("grade")
    private Integer grade;

    @JsonProperty("snapshot_info")
    private String snapshotInfo;

    @JsonProperty("grade_name")
    private String gradeName;

    @JsonProperty("points")
    private String points;
  }
}
