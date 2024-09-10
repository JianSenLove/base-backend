package com.jason.moneybag.classes.entity.enums;

public enum CourseTypeEnums {
    ONE_TO_ONE("一对一"),
    PIANO_LESSON("钢琴课"),
    ORFF_LESSON("奥尔夫启蒙"),
    ONE_TO_MANY("一对多");

    private final String description;

    // 构造方法
    CourseTypeEnums(String description) {
        this.description = description;
    }

    // 获取课程类型的描述
    public String getDescription() {
        return description;
    }

    // 通过描述获取对应的枚举类型
    public static CourseTypeEnums fromDescription(String description) {
        for (CourseTypeEnums type : CourseTypeEnums.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("没有找到对应的课程类型: " + description);
    }
}

