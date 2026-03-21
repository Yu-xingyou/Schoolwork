package com.xingyou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairOrder {
    private int id;
    private String studentId;
    //宿舍号
    private String dormitoryId;
    //损坏设备类型
    private String damageType;
    //损坏描述
    private String damageDesc;
    //报修状态
    private String status;
    //创建时间
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
