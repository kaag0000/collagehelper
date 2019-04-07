package com.kaag.collegehelper.Formatters;

import com.kaag.collegehelper.Enums.UserTypeEnum;
import com.kaag.collegehelper.Interfaces.IFormtter;

public class UserTypeEnumFormatter implements IFormtter<String> {
    @Override
    public UserTypeEnum getFormattedData(String data) {

        switch(data.trim()){
            case "UNKNOWN":
                return UserTypeEnum.UNKNOWN;
            case "STUDENT" :
                return UserTypeEnum.STUDENT;
            case "CLASS_REPRESENTATIV":
                return UserTypeEnum.CLASS_REPRESENTATIV;
            case "TEACHER":
                return UserTypeEnum.TEACHER;
            default: return UserTypeEnum.UNKNOWN;
        }
    }
}
