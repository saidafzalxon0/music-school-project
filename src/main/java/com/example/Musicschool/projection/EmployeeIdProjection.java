package com.example.Musicschool.projection;

import java.util.List;

public interface EmployeeIdProjection
{ Long getId();
    String getFullName();
    String getAbout();
    String getEditor();

    Long getPosition_id();
    String getPosition_name();
    Long getDirection_id();
    String getDirection_name();
    Long getDepartment_id();
    String getDepartment_name();
    Integer getType();
    String getPhotoLink();
    List<String> getListFile();
}
