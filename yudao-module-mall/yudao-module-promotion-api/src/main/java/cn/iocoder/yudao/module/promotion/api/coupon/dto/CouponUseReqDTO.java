package cn.iocoder.yudao.module.promotion.api.coupon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 优惠劵使用 Request DTO
 *
 * @author 芋道源码
 */
@Data
public class CouponUseReqDTO {

    /**
     * 优惠劵编号
     */
    @NotNull(message = "优惠劵编号不能为空")
    private Long id;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /**
     * 订单编号
     */
    @NotNull(message = "订单编号不能为空")
    private Long orderId;

}
