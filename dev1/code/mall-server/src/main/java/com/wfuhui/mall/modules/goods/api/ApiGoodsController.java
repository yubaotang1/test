package com.wfuhui.mall.modules.goods.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.mall.common.utils.Query;
import com.wfuhui.mall.common.utils.R;
import com.wfuhui.mall.modules.goods.entity.GoodsEntity;
import com.wfuhui.mall.modules.goods.service.GoodsService;

@RestController
@RequestMapping("/api/goods")
public class ApiGoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("list")
    public R list(@RequestParam Map<String, Object> map){
    	List<GoodsEntity> goodsList = goodsService.queryList(map);
    	Integer total = goodsService.queryTotal(map);
        return R.ok().put("goodsList", goodsList).put("total", total);
    }
    
    @GetMapping("detail")
    public R detail(int id){
    	GoodsEntity goods = goodsService.queryObject(id);
    	return R.ok().put("goods", goods);
    }
    
}
