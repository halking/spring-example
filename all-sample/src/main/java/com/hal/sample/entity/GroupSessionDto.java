package com.hal.sample.entity;

import com.google.common.base.Objects;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Steven.HUANG on 2018/12/6.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupSessionDto implements Comparable<GroupSessionDto> {

  private LocalDate date;

  private String boutiqueId;

  private String boutiqueName;

  public Boolean getFinished() {
    return date.plusDays(1).isBefore(LocalDate.now());
  }

  public GroupSessionDto(LocalDate date) {
    this.date = date;
  }

  @Override
  public int compareTo(GroupSessionDto o) {
    return this.date.compareTo(o.getDate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GroupSessionDto)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GroupSessionDto sessionDto = (GroupSessionDto) o;
    return Objects.equal(getDate(), sessionDto.getDate()) &&
        Objects.equal(getDate(), sessionDto.getDate());
  }


  @Override
  public int hashCode() {
    return Objects.hashCode(getDate());
  }
}
