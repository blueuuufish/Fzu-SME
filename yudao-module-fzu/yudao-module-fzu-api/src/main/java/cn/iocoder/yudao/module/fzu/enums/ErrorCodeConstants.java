package cn.iocoder.yudao.module.fzu.enums;// TODO 待办：请将下面的错误码复制到 yudao-module-fzu-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

// ========== 课程 TODO 补充编号 ==========
public interface ErrorCodeConstants {
    ErrorCode COURSE_NOT_EXISTS = new ErrorCode(10086, "课程不存在");
    ErrorCode COURSE_STUDENT_NOT_EXISTS = new ErrorCode(10087, "课程学生不存在");
    ErrorCode STUDENT_NOT_EXISTS = new ErrorCode(10088, "学生不存在");
}


