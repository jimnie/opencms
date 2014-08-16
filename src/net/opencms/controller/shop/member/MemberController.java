
package net.opencms.controller.shop.member;

import javax.annotation.Resource;

import net.opencms.controller.shop.BaseController;
import net.opencms.entity.Member;
import net.opencms.service.ConsultationService;
import net.opencms.service.CouponCodeService;
import net.opencms.service.MemberService;
import net.opencms.service.MessageService;
import net.opencms.service.OrderService;
import net.opencms.service.ProductNotifyService;
import net.opencms.service.ProductService;
import net.opencms.service.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController {

	private static final int NEW_ORDER_COUNT = 6;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;
	@Resource(name = "couponCodeServiceImpl")
	private CouponCodeService couponCodeService;
	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;
	@Resource(name = "productNotifyServiceImpl")
	private ProductNotifyService productNotifyService;
	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "consultationServiceImpl")
	private ConsultationService consultationService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		model.addAttribute("waitingPaymentOrderCount", orderService.waitingPaymentCount(member));
		model.addAttribute("waitingShippingOrderCount", orderService.waitingShippingCount(member));
		model.addAttribute("messageCount", messageService.count(member, false));
		model.addAttribute("couponCodeCount", couponCodeService.count(null, member, null, false, false));
		model.addAttribute("favoriteCount", productService.count(member, null, null, null, null, null, null));
		model.addAttribute("productNotifyCount", productNotifyService.count(member, null, null, null));
		model.addAttribute("reviewCount", reviewService.count(member, null, null, null));
		model.addAttribute("consultationCount", consultationService.count(member, null, null));
		model.addAttribute("newOrders", orderService.findList(member, NEW_ORDER_COUNT, null, null));
		return "shop/member/index";
	}

}