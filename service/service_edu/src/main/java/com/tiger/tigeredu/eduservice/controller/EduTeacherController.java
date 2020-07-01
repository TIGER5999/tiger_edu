package com.tiger.tigeredu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiger.tigeredu.commonutils.RespBean;
import com.tiger.tigeredu.eduservice.model.EduTeacher;
import com.tiger.tigeredu.eduservice.model.vo.TeacherQuery;
import com.tiger.tigeredu.eduservice.service.EduTeacherService;
import com.tiger.tigeredu.servicebase.exception.TigerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author TIGER
 * @since 2020-06-29
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 1 查询所有讲师数据
     *
     * @return
     */
    @ApiOperation(value = "查询所有讲师数据")
    @GetMapping("/findAll")
    public RespBean findAll() {
        return RespBean.ok().data("list", eduTeacherService.list(null));
    }

    /**
     * 2 逻辑删除讲师
     *
     * @param id 讲师ID
     * @return
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public RespBean removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 3 分页查询讲师数据
     *
     * @param current 当前页
     * @param limit   每页记录数
     * @return
     */
    @ApiOperation(value = "分页查询讲师数据")
    @GetMapping("/findByPage/{current}/{limit}")
    public RespBean findByPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit) {

        // 创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        // 调用方法实现分页
        // 底层自动封装，把分页所有数据封装到teacherPage对象里面
        eduTeacherService.page(teacherPage, null);
        // 总记录数
        long total = teacherPage.getTotal();
        // 分页数据
        List<EduTeacher> records = teacherPage.getRecords();
        return RespBean.ok().data("total", total).data("list", records);
    }

    /**
     * 4 多条件组合 带分页 查询讲师数据
     *
     * @param current 当前页
     * @param limit   每页记录数
     * @return
     */
    @ApiOperation(value = "多条件组合 带分页 查询讲师数据")
    @PostMapping("/findByPageAndConditions/{current}/{limit}")
    public RespBean findByPageAndConditions(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable
                    Integer current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable
                    Integer limit,
            @ApiParam(name = "teacherQuery", value = "查询条件对象", required = true)
            @RequestBody(required = false)
                    TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQuery)) {
            if (!StringUtils.isEmpty(teacherQuery.getName())) {
                // 模糊查询
                queryWrapper.like("name", teacherQuery.getName());
            }
            if (!StringUtils.isEmpty(teacherQuery.getLevel())) {
                // 等于查询
                queryWrapper.eq("level", teacherQuery.getLevel());
            }
            if (!StringUtils.isEmpty(teacherQuery.getBegin())) {
                // 大于等于查询
                queryWrapper.ge("gmtCreate", teacherQuery.getBegin());
            }
            if (!StringUtils.isEmpty(teacherQuery.getEnd())) {
                // 小于等于查询
                queryWrapper.le("gmtCreate", teacherQuery.getEnd());
            }
        }
        eduTeacherService.page(teacherPage, queryWrapper);
        // 总记录数
        long total = teacherPage.getTotal();
        // 分页数据
        List<EduTeacher> records = teacherPage.getRecords();
        return RespBean.ok().data("total", total).data("list", records);
    }

    /**
     * 5 添加讲师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("/addTeacher")
    @ApiOperation(value = "添加讲师")
    public RespBean addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 根据讲师ID查找讲师
     *
     * @param id 讲师ID
     * @return
     */
    @ApiOperation(value = "根据讲师ID查找讲师")
    @GetMapping("/findById/{id}")
    public RespBean findById(@PathVariable String id) {
        if (!StringUtils.isEmpty(id)) {
            EduTeacher teacher = eduTeacherService.getById(id);
            return RespBean.ok().data("obj", teacher);
        }
        return RespBean.error();
    }

    /**
     * 根据讲师ID修改讲师
     *
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "根据讲师ID修改讲师")
    @PostMapping("/updateById")
    public RespBean updateById(@RequestBody EduTeacher eduTeacher) {
        if (!StringUtils.isEmpty(eduTeacher)) {
            boolean flag = eduTeacherService.updateById(eduTeacher);
            if (flag) {
                return RespBean.ok();
            }
        }
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new TigerException(50000, "执行了自定义异常...");
        }
        return RespBean.error();
    }

}

