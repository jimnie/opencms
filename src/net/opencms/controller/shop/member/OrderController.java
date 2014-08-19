
package net.opencms.controller.shop.member;

import net.opencms.Message;
import net.opencms.Pageable;
import net.opencms.Setting;
import net.opencms.controller.shop.BaseController;
import net.opencms.entity.*;
import net.opencms.entity.Order.OrderStatus;
import net.opencms.entity.Order.PaymentStatus;
import net.opencms.plugin.PaymentPlugin;
import net.opencms.service.*;
import net.opencms.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("shopMemberOrderController")
@RequestMapping("/member/order")
public class OrderController extends BaseController {

    private static final int PAGE_SIZE = 10;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    @Resource(name = "areaServiceImpl")
    private AreaService areaService;
    @Resource(name = "receiverServiceImpl")
    private ReceiverService receiverService;
    @Resource(name = "cartServiceImpl")
    private CartService cartService;
    @Resource(name = "paymentMethodServiceImpl")
    private PaymentMethodService paymentMethodService;
    @Resource(name = "shippingMethodServiceImpl")
    private ShippingMethodService shippingMethodService;
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    @Resource(name = "shippingServiceImpl")
    private ShippingService shippingService;
    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    @RequestMapping(value = "/save_receiver", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> saveReceiver(Receiver receiver, Long areaId) {
        Map<String, Object> data = new HashMap<String, Object>();
        receiver.setArea(areaService.find(areaId));
        if (!isValid(receiver)) {
            data.put("message", ERROR_MESSAGE);
            return data;
        }
        Member member = memberService.getCurrent();
        if (Receiver.MAX_RECEIVER_COUNT != null && member.getReceivers().size() >= Receiver.MAX_RECEIVER_COUNT) {
            data.put("message", Message.error("shop.order.addReceiverCountNotAllowed", Receiver.MAX_RECEIVER_COUNT));
            return data;
        }
        receiver.setMember(member);
        receiverService.save(receiver);
        data.put("message", SUCCESS_MESSAGE);
        data.put("receiver", receiver);
        return data;
    }

    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean lock(String sn) {
        Order order = orderService.findBySn(sn);
        if (order != null && memberService.getCurrent().equals(order.getMember()) && !order.isExpired() && !order.isLocked(null) && order.getPaymentMethod() != null && order.getPaymentMethod().getMethod() == PaymentMethod.Method.online && (order.getPaymentStatus() == PaymentStatus.unpaid || order.getPaymentStatus() == PaymentStatus.partialPayment)) {
            order.setLockExpire(DateUtils.addSeconds(new Date(), 20));
            order.setOperator(null);
            orderService.update(order);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/check_payment", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean checkPayment(String sn) {
        Order order = orderService.findBySn(sn);
        if (order != null && memberService.getCurrent().equals(order.getMember()) && order.getPaymentStatus() == PaymentStatus.paid) {
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(ModelMap model) {
        Cart cart = cartService.getCurrent();
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart/list.jhtml";
        }
        if (!isValid(cart)) {
            return ERROR_VIEW;
        }
        Order order = orderService.build(cart, null, null, null, false, null, false, null);
        model.addAttribute("order", order);
        model.addAttribute("cartToken", cart.getToken());
        model.addAttribute("paymentMethods", paymentMethodService.findAll());
        model.addAttribute("shippingMethods", shippingMethodService.findAll());
        return "/shop/member/order/info";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> calculate(Long paymentMethodId, Long shippingMethodId, String code, @RequestParam(defaultValue = "false") Boolean isInvoice, String invoiceTitle, @RequestParam(defaultValue = "false") Boolean useBalance, String memo) {
        Map<String, Object> data = new HashMap<String, Object>();
        Cart cart = cartService.getCurrent();
        if (cart == null || cart.isEmpty()) {
            data.put("message", Message.error("shop.order.cartNotEmpty"));
            return data;
        }
        PaymentMethod paymentMethod = paymentMethodService.find(paymentMethodId);
        ShippingMethod shippingMethod = shippingMethodService.find(shippingMethodId);
        Order order = orderService.build(cart, null, paymentMethod, shippingMethod, isInvoice, invoiceTitle, useBalance, memo);

        data.put("message", SUCCESS_MESSAGE);
        data.put("quantity", order.getQuantity());
        data.put("price", order.getPrice());
        data.put("freight", order.getFreight());
        data.put("promotionDiscount", order.getPromotionDiscount());
        data.put("couponDiscount", order.getCouponDiscount());
        data.put("tax", order.getTax());
        data.put("amountPayable", order.getAmountPayable());
        return data;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Message create(String cartToken, Long receiverId, Long paymentMethodId, Long shippingMethodId, String code, @RequestParam(defaultValue = "false") Boolean isInvoice, String invoiceTitle, @RequestParam(defaultValue = "false") Boolean useBalance, String memo) {
        Cart cart = cartService.getCurrent();
        if (cart == null || cart.isEmpty()) {
            return Message.warn("shop.order.cartNotEmpty");
        }
        if (!StringUtils.equals(cart.getToken(), cartToken)) {
            return Message.warn("shop.order.cartHasChanged");
        }
        if (cart.getIsLowStock()) {
            return Message.warn("shop.order.cartLowStock");
        }
        Receiver receiver = receiverService.find(receiverId);
        if (receiver == null) {
            return Message.error("shop.order.receiverNotExsit");
        }
        PaymentMethod paymentMethod = paymentMethodService.find(paymentMethodId);
        if (paymentMethod == null) {
            return Message.error("shop.order.paymentMethodNotExsit");
        }
        ShippingMethod shippingMethod = shippingMethodService.find(shippingMethodId);
        if (shippingMethod == null) {
            return Message.error("shop.order.shippingMethodNotExsit");
        }
        if (!paymentMethod.getShippingMethods().contains(shippingMethod)) {
            return Message.error("shop.order.deliveryUnsupported");
        }
        Order order = orderService.create(cart, receiver, paymentMethod, shippingMethod, isInvoice, invoiceTitle, useBalance, memo, null);
        return Message.success(order.getSn());
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(String sn, ModelMap model) {
        Order order = orderService.findBySn(sn);
        if (order == null || !memberService.getCurrent().equals(order.getMember()) || order.isExpired() || order.getPaymentMethod() == null) {
            return ERROR_VIEW;
        }
        if (order.getPaymentMethod().getMethod() == PaymentMethod.Method.online) {
            List<PaymentPlugin> paymentPlugins = pluginService.getPaymentPlugins(true);
            if (!paymentPlugins.isEmpty()) {
                PaymentPlugin defaultPaymentPlugin = paymentPlugins.get(0);
                if (order.getPaymentStatus() == PaymentStatus.unpaid || order.getPaymentStatus() == PaymentStatus.partialPayment) {
                    model.addAttribute("fee", defaultPaymentPlugin.calculateFee(order.getAmountPayable()));
                    model.addAttribute("amount", defaultPaymentPlugin.calculateAmount(order.getAmountPayable()));
                }
                model.addAttribute("defaultPaymentPlugin", defaultPaymentPlugin);
                model.addAttribute("paymentPlugins", paymentPlugins);
            }
        }
        model.addAttribute("order", order);
        return "/shop/member/order/payment";
    }

    @RequestMapping(value = "/calculate_amount", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> calculateAmount(String paymentPluginId, String sn) {
        Map<String, Object> data = new HashMap<String, Object>();
        Order order = orderService.findBySn(sn);
        PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin(paymentPluginId);
        if (order == null || !memberService.getCurrent().equals(order.getMember()) || order.isExpired() || order.isLocked(null) || order.getPaymentMethod() == null || order.getPaymentMethod().getMethod() == PaymentMethod.Method.offline || paymentPlugin == null || !paymentPlugin.getIsEnabled()) {
            data.put("message", ERROR_MESSAGE);
            return data;
        }
        data.put("message", SUCCESS_MESSAGE);
        data.put("fee", paymentPlugin.calculateFee(order.getAmountPayable()));
        data.put("amount", paymentPlugin.calculateAmount(order.getAmountPayable()));
        return data;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Integer pageNumber, ModelMap model) {
        Member member = memberService.getCurrent();
        Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
        model.addAttribute("page", orderService.findPage(member, pageable));
        return "shop/member/order/list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(String sn, ModelMap model) {
        Order order = orderService.findBySn(sn);
        if (order == null) {
            return ERROR_VIEW;
        }
        Member member = memberService.getCurrent();
        if (!member.getOrders().contains(order)) {
            return ERROR_VIEW;
        }
        model.addAttribute("order", order);
        return "shop/member/order/view";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public
    @ResponseBody
    Message cancel(String sn) {
        Order order = orderService.findBySn(sn);
        if (order != null && memberService.getCurrent().equals(order.getMember()) && !order.isExpired() && order.getOrderStatus() == OrderStatus.unconfirmed && order.getPaymentStatus() == PaymentStatus.unpaid) {
            if (order.isLocked(null)) {
                return Message.warn("shop.member.order.locked");
            }
            orderService.cancel(order, null);
            return SUCCESS_MESSAGE;
        }
        return ERROR_MESSAGE;
    }

    @RequestMapping(value = "/delivery_query", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> deliveryQuery(String sn) {
        Map<String, Object> data = new HashMap<String, Object>();
        Shipping shipping = shippingService.findBySn(sn);
        Setting setting = SettingUtils.get();
        if (shipping != null && shipping.getOrder() != null && memberService.getCurrent().equals(shipping.getOrder().getMember()) && StringUtils.isNotEmpty(setting.getKuaidi100Key()) && StringUtils.isNotEmpty(shipping.getDeliveryCorpCode()) && StringUtils.isNotEmpty(shipping.getTrackingNo())) {
            data = shippingService.query(shipping);
        }
        return data;
    }

}