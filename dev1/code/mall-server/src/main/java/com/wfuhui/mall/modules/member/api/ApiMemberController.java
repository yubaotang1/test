package com.wfuhui.mall.modules.member.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.mall.common.annotation.Login;
import com.wfuhui.mall.common.utils.R;
import com.wfuhui.mall.modules.member.entity.MemberEntity;
import com.wfuhui.mall.modules.member.service.MemberService;

/**
 * 用户接口
 * @author Administrator
 */
@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Login
    @GetMapping("info")
    public R info(@RequestAttribute Long userId){
    	
    	MemberEntity member = memberService.queryObject(userId);
        return R.ok().put("member", member);
    }
	
	@Login
	@RequestMapping("/update")
	public R update(@RequestBody MemberEntity member){
		memberService.update(member);
		return R.ok();
	}
}
