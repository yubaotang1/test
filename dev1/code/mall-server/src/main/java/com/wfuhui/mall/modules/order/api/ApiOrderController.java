package com.wfuhui.mall.modules.order.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.mall.common.annotation.Login;
import com.wfuhui.mall.common.utils.R;
import com.wfuhui.mall.modules.order.entity.OrderEntity;
import com.wfuhui.mall.modules.order.service.OrderService;

/**
 * 订单接口
 *
 */
@RestController
@RequestMapping("/api/order")
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Login
    @GetMapping("list")
    public R getOrderList(@RequestAttribute Long userId, Integer status){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("orderStatus", status);
    	params.put("memberId", userId);
    	List<OrderEntity> orderList = orderService.queryList(params);
        return R.ok().put("orderList", orderList);
    }
    
    @Login
    @PostMapping("create")
    public R createOrder(@RequestAttribute("userId") Long userId, @RequestBody OrderEntity order){
    	order.setMemberId(userId);
    	order.setCreateTime(new Date());
    	order.setOrderStatus(1);
    	orderService.createOrder(order);
    	return R.ok();
    }
    
    @Login
    @GetMapping("detail")
    public R detail(Integer id) {
    	OrderEntity order = orderService.queryObject(id);
    	return R.ok().put("order", order);
    }
    
    @Login
    @GetMapping("cancel")
    public R cancel(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(0);
    	order.setId(id);
    	orderService.update(order);
    	return R.ok();
    }
	
    @Login
	@GetMapping("pay")
    public R pay(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(2);
    	order.setId(id);
    	orderService.update(order);
    	return R.ok();
    }
    
    @Login
    @GetMapping("confirm")
    public R confirmOrder(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(4);
    	order.setId(id);
    	orderService.update(order);
    	return R.ok();
    }
    
}
