package cn.iocoder.yudao.module.system.service.sme;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.sme.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sme.SMECourseScheduleDO;
import cn.iocoder.yudao.module.system.dal.mysql.sme.SMECourseScheduleMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link SMECourseScheduleServiceImpl} 的单元测试类
 *
 * @author FZU
 */
@Import(SMECourseScheduleServiceImpl.class)
public class SMECourseScheduleServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SMECourseScheduleServiceImpl sMECourseScheduleService;

    @Resource
    private SMECourseScheduleMapper sMECourseScheduleMapper;

    @Test
    public void testCreateSMECourseSchedule_success() {
        // 准备参数
        SMECourseScheduleCreateReqVO reqVO = randomPojo(SMECourseScheduleCreateReqVO.class);

        // 调用
        Long sMECourseScheduleId = sMECourseScheduleService.createSMECourseSchedule(reqVO);
        // 断言
        assertNotNull(sMECourseScheduleId);
        // 校验记录的属性是否正确
        SMECourseScheduleDO sMECourseSchedule = sMECourseScheduleMapper.selectById(sMECourseScheduleId);
        assertPojoEquals(reqVO, sMECourseSchedule);
    }

    @Test
    public void testUpdateSMECourseSchedule_success() {
        // mock 数据
        SMECourseScheduleDO dbSMECourseSchedule = randomPojo(SMECourseScheduleDO.class);
        sMECourseScheduleMapper.insert(dbSMECourseSchedule);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SMECourseScheduleUpdateReqVO reqVO = randomPojo(SMECourseScheduleUpdateReqVO.class, o -> {
            o.setId(dbSMECourseSchedule.getId()); // 设置更新的 ID
        });

        // 调用
        sMECourseScheduleService.updateSMECourseSchedule(reqVO);
        // 校验是否更新正确
        SMECourseScheduleDO sMECourseSchedule = sMECourseScheduleMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, sMECourseSchedule);
    }

    @Test
    public void testUpdateSMECourseSchedule_notExists() {
        // 准备参数
        SMECourseScheduleUpdateReqVO reqVO = randomPojo(SMECourseScheduleUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> sMECourseScheduleService.updateSMECourseSchedule(reqVO), SME_COURSE_SCHEDULE_NOT_EXISTS);
    }

    @Test
    public void testDeleteSMECourseSchedule_success() {
        // mock 数据
        SMECourseScheduleDO dbSMECourseSchedule = randomPojo(SMECourseScheduleDO.class);
        sMECourseScheduleMapper.insert(dbSMECourseSchedule);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSMECourseSchedule.getId();

        // 调用
        sMECourseScheduleService.deleteSMECourseSchedule(id);
       // 校验数据不存在了
       assertNull(sMECourseScheduleMapper.selectById(id));
    }

    @Test
    public void testDeleteSMECourseSchedule_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> sMECourseScheduleService.deleteSMECourseSchedule(id), SME_COURSE_SCHEDULE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSMECourseSchedulePage() {
       // mock 数据
       SMECourseScheduleDO dbSMECourseSchedule = randomPojo(SMECourseScheduleDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setStuId(null);
       });
       sMECourseScheduleMapper.insert(dbSMECourseSchedule);
       // 测试 courseId 不匹配
       sMECourseScheduleMapper.insert(cloneIgnoreId(dbSMECourseSchedule, o -> o.setCourseId(null)));
       // 测试 stuId 不匹配
       sMECourseScheduleMapper.insert(cloneIgnoreId(dbSMECourseSchedule, o -> o.setStuId(null)));
       // 准备参数
       SMECourseSchedulePageReqVO reqVO = new SMECourseSchedulePageReqVO();
       reqVO.setCourseId(null);
       reqVO.setStuId(null);

       // 调用
       PageResult<SMECourseScheduleDO> pageResult = sMECourseScheduleService.getSMECourseSchedulePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSMECourseSchedule, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSMECourseScheduleList() {
       // mock 数据
       SMECourseScheduleDO dbSMECourseSchedule = randomPojo(SMECourseScheduleDO.class, o -> { // 等会查询到
           o.setCourseId(null);
           o.setStuId(null);
       });
       sMECourseScheduleMapper.insert(dbSMECourseSchedule);
       // 测试 courseId 不匹配
       sMECourseScheduleMapper.insert(cloneIgnoreId(dbSMECourseSchedule, o -> o.setCourseId(null)));
       // 测试 stuId 不匹配
       sMECourseScheduleMapper.insert(cloneIgnoreId(dbSMECourseSchedule, o -> o.setStuId(null)));
       // 准备参数
       SMECourseScheduleExportReqVO reqVO = new SMECourseScheduleExportReqVO();
       reqVO.setCourseId(null);
       reqVO.setStuId(null);

       // 调用
       List<SMECourseScheduleDO> list = sMECourseScheduleService.getSMECourseScheduleList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSMECourseSchedule, list.get(0));
    }

}
