package com.tiger.tigeredu.eduservice.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA
 *
 * @author TIGER
 * @Package: com.tiger.tigeredu.eduservice.model.vo
 * @ClassName: TeacherQuery
 * @date 2020/6/30 16:10
 * @Description:
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2020-01-01 16:55:00")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2020-01-01 16:55:00")
    private String end;
}
