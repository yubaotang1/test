package com.wfuhui.mall.modules.advert.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.mall.common.utils.R;
import com.wfuhui.mall.modules.advert.entity.AdvertEntity;
import com.wfuhui.mall.modules.advert.service.AdvertService;


@RestController
@RequestMapping("/api/advert/")
public class ApiAdvertController {
	
	@Autowired
	private AdvertService advertService;
	
    @GetMapping("list")
    public R list(){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("enable", 1);
    	List<AdvertEntity> advertList = advertService.queryList(params);
        return R.ok().put("advertList", advertList);
    }
}
