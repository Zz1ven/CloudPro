package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.active.ActiveResponse;
import com.avatarcn.tourists.mapper.*;
import com.avatarcn.tourists.model.Active;
import com.avatarcn.tourists.model.ActiveOrder;
import com.avatarcn.tourists.model.CouponsUsers;
import com.avatarcn.tourists.model.OrderStatus;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.utils.MakeOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/19 16:26
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class ActiveOrderService {

    @Autowired
    private ActiveOrderMapper activeOrderMapper;

    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private CouponsUsersMapper couponsUsersMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private CouponsUsersService couponsUsersService;

    public ActiveOrder addActiveOrder(Integer userId, Integer activeId, Integer amount, Integer orderStatusId, String tourist_name, String tourist_phone, Integer coupons_user_id) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        Active active = activeMapper.selectById(activeId);
        if (active == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
        }
        OrderStatus orderStatus = orderStatusMapper.selectById(orderStatusId);
        if (orderStatus == null) {
            throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
        }
        ActiveOrder activeOrder = new ActiveOrder();
        activeOrder.setFk_tb_user_id(account.getId());
        activeOrder.setFk_tb_active_id(activeId);
        activeOrder.setFk_tb_order_status_id(orderStatusId);
        activeOrder.setNumber(MakeOrderUtil.makeOrderNum("active"));
        activeOrder.setAmount(amount);
        activeOrder.setTotal_money(active.getPrice() * amount);
        activeOrder.setReal_money(active.getPrice() * amount);
        activeOrder.setTourist_name(tourist_name);
        activeOrder.setTourist_phone(tourist_phone);
        activeOrder.setTime(new Date());
        activeOrderMapper.insert(activeOrder);
        //使用优惠券
        if (coupons_user_id != null) {
            float realMoney = couponsUsersService.activeUseCoupons(coupons_user_id, account.getId(), activeOrder.getId());
            activeOrder.setReal_money(realMoney);
        }
        ActiveResponse activeResponse = new ActiveResponse();
        activeResponse.setId(active.getId());
        activeResponse.setName(active.getName());
        activeResponse.setIcon(active.getIcon());
        activeResponse.setDescription(active.getDescription());
        activeResponse.setTime(active.getTime());
        activeResponse.setActiveRemarkList(active.getActiveRemarkList());
        activeOrder.setActiveResponse(activeResponse);
        activeOrder.setOrderStatus(orderStatus);
        return activeOrder;
    }

    public int deleteActiveOrder(Integer id) throws ErrorCodeException {
        //删除该订单使用的优惠券
        couponsUsersMapper.deleteByActiveOrderId(id);
        if (activeOrderMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public ActiveOrder getActiveOrder(Integer id) throws ErrorCodeException {
        ActiveOrder activeOrder = activeOrderMapper.selectCascadeById(id);
        if (activeOrder == null) {
            throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
        }
        Active active = activeMapper.selectById(activeOrder.getFk_tb_active_id());
        if (active == null) {
            throw new ErrorCodeException(TouristsErrorCode.STATUS_NULL);
        }
        ActiveResponse activeResponse = new ActiveResponse();
        activeResponse.setId(active.getId());
        activeResponse.setIcon(active.getIcon());
        activeResponse.setName(active.getName());
        activeResponse.setDescription(active.getDescription());
        activeResponse.setTime(active.getTime());
        activeResponse.setActiveRemarkList(active.getActiveRemarkList());
        activeOrder.setActiveResponse(activeResponse);
        return activeOrder;
    }

    public PageResponse<ActiveOrder> getPageActiveOrder(Integer userId, Integer offset, Integer pageSize) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        List<ActiveOrder> activeOrderList = activeOrderMapper.selectPageByUserId(account.getId(), offset, pageSize);
        for (ActiveOrder activeOrder : activeOrderList) {
            Active active = activeMapper.selectById(activeOrder.getFk_tb_active_id());
            if (active == null) {
                throw new ErrorCodeException(TouristsErrorCode.ACTIVE_NULL);
            }
            ActiveResponse activeResponse = new ActiveResponse();
            activeResponse.setId(active.getId());
            activeResponse.setIcon(active.getIcon());
            activeResponse.setName(active.getName());
            activeResponse.setDescription(active.getDescription());
            activeResponse.setTime(active.getTime());
            activeOrder.setActiveResponse(activeResponse);
        }
        PageResponse<ActiveOrder> activeOrderPageResponse = new PageResponse<>();
        activeOrderPageResponse.setItem(activeOrderList);
        activeOrderPageResponse.setOffset(offset);
        activeOrderPageResponse.setPageSize(pageSize);
        activeOrderPageResponse.setTotal(activeOrderMapper.countByUserId(account.getId()));
        return activeOrderPageResponse;
    }

    public ActiveOrder updateActiveOrder(Integer id, Integer statusId, float realMoney) throws ErrorCodeException {
        ActiveOrder activeOrder = activeOrderMapper.selectById(id);
        if (activeOrder == null) {
            throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
        }
        OrderStatus orderStatus = orderStatusMapper.selectById(statusId);
        if (orderStatus == null) {
            throw new ErrorCodeException(TouristsErrorCode.STATUS_NULL);
        }
        activeOrder.setFk_tb_order_status_id(statusId);
        activeOrder.setReal_money(realMoney);
        activeOrderMapper.update(activeOrder);

        Active active = activeMapper.selectById(activeOrder.getFk_tb_active_id());
        activeOrder.setOrderStatus(orderStatus);
        ActiveResponse activeResponse = new ActiveResponse();
        activeResponse.setId(active.getId());
        activeResponse.setName(active.getName());
        activeResponse.setIcon(active.getIcon());
        activeResponse.setDescription(active.getDescription());
        activeResponse.setTime(active.getTime());
        activeResponse.setActiveRemarkList(active.getActiveRemarkList());
        activeOrder.setActiveResponse(activeResponse);
        return activeOrder;
    }

    /**
     * 支付完成后的活动订单回调
     * @param id
     * @return
     */
    public ActiveOrder payActiveOrder(Integer id) throws ErrorCodeException {
        ActiveOrder activeOrder = activeOrderMapper.selectById(id);
        if (activeOrder == null) {
            throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
        }
        List<CouponsUsers> couponsUsersList = couponsUsersMapper.selectByActiveOrderId(id);
        for (CouponsUsers couponsUsers : couponsUsersList) {
            couponsUsers.setFk_tb_coupons_status_id(Constant.COUPON_USED);
            couponsUsersMapper.update(couponsUsers);
        }
        activeOrder.setFk_tb_order_status_id(Constant.ORDER_PAID);
        activeOrderMapper.update(activeOrder);
        return activeOrder;
    }

}
