package cn.iocoder.yudao.module.trade.service.brokerage.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 佣金 增加 Request BO
 *
 * @author owen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerageAddReqBO {

    /**
     * 业务编号
     */
    @NotBlank(message = "业务编号不能为空")
    private String bizId;
    /**
     * 佣金基数
     */
    @NotNull(message = "佣金基数不能为空")
    private Integer basePrice;
    /**
     * 一级佣金（固定）
     */
    private Integer firstFixedPrice;
    /**
     * 二级佣金（固定）
     */
    private Integer secondFixedPrice;

}
