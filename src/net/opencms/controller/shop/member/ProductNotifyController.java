
package net.opencms.controller.shop.member;

import javax.annotation.Resource;

import net.opencms.Message;
import net.opencms.Pageable;
import net.opencms.controller.shop.BaseController;
import net.opencms.entity.Member;
import net.opencms.entity.ProductNotify;
import net.opencms.service.MemberService;
import net.opencms.service.ProductNotifyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("shopMemberProductNotifyController")
@RequestMapping("/member/product_notify")
public class ProductNotifyController extends BaseController {

	private static final int PAGE_SIZE = 10;

	@Resource(name = "productNotifyServiceImpl")
	ProductNotifyService productNotifyService;
	@Resource(name = "memberServiceImpl")
	MemberService memberService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer pageNumber, Model model) {
		Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("page", productNotifyService.findPage(member, null, null, null, pageable));
		return "/shop/member/product_notify/list";
	}

	@RequestMapping(value = "delete")
	public @ResponseBody
	Message delete(Long id) {
		ProductNotify productNotify = productNotifyService.find(id);
		if (productNotify == null) {
			return ERROR_MESSAGE;
		}
		Member member = memberService.getCurrent();
		if (!member.getProductNotifies().contains(productNotify)) {
			return ERROR_MESSAGE;
		}
		productNotifyService.delete(productNotify);
		return SUCCESS_MESSAGE;
	}

}