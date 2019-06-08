package com.kaag.collegehelper.Interfaces;

import com.kaag.collegehelper.Enums.UserTypeEnum;

public interface IFormatter<T> {

    public UserTypeEnum getFormattedData(T data);

}
