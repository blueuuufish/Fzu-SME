package cn.iocoder.yudao.module.pay.api.order;

import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderCreateReqDTO;
import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderRespDTO;
import cn.iocoder.yudao.module.pay.convert.order.PayOrderConvert;
import cn.iocoder.yudao.module.pay.dal.dataobject.order.PayOrderDO;
import cn.iocoder.yudao.module.pay.service.order.PayOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 支付单 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void updatePayOrderPriceById(Long payOrderId, Integer payPrice) {
        payOrderService.updatePayOrderPriceById(payOrderId, payPrice);
    }

}
