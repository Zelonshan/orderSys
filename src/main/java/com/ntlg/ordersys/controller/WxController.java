package com.ntlg.ordersys.controller;




import com.ntlg.ordersys.pojo.*;
import com.ntlg.ordersys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;



/**
 * 微信小程序数据传送接口
 */
@RestController
@RequestMapping("/api")
public class WxController {

    //小程序背景图
    @Autowired
    private WxAdsImgsService wxAdsImgsService;

    //菜品分类
    @Autowired
    private TypesService typesService;

    //食品分类
    @Autowired
    private FoodsService foodsService;

    //获取用户openid
    @Autowired
    private WeChatService weChatService;

    //微信用户信息
    @Autowired
    private UserService userService;

    //产生订单
    @Autowired
    private FoodordersService foodordersService;

    //订单列表
    @Autowired
    private OrderlistService orderlistService;


    //查询轮播图
    @RequestMapping("/food/index1")
    public List<WxAdsImgs> toIndex(){
        List<WxAdsImgs> list = wxAdsImgsService.findByTypes(0);
        return list;
    }
    //查询添加图
    @RequestMapping("/food/index2")
    public List<WxAdsImgs> toIndex2(){
        List<WxAdsImgs> list = wxAdsImgsService.findByTypes(1);
        return list;
    }

    //查询底部图
    @RequestMapping("/food/index3")
    public List<WxAdsImgs> toIndex3(){
        List<WxAdsImgs> list = wxAdsImgsService.findByTypes(2);
        return list;
    }
    @RequestMapping("/foodTypes/foodTypes")
    public List<Types> getAllFoodList(){
        List<Types> list = typesService.selectAllTypes();
        return list;
    }

    @RequestMapping("/foodCooks/foodCooks")
    public List<Foods> getAllFoodLists(){
        List<Foods> list = foodsService.selectAllFoods();
        return list;
    }

    @RequestMapping("/decryptCode")
    public String getCode(@RequestParam(value = "code")String  code){
        System.out.println(code);
        return weChatService.codeToOpenId(code);
    }

    //消费记录
    @RequestMapping("/recored")
    public List<Foodorders> getRecored(@RequestParam(value = "userId")String userId){

        return foodordersService.findByUseridAndTaken(userId,2);
    }

    //获取商品订单列表
    @RequestMapping("/orderList")
    public List<Foodorders> getOrderList(@RequestParam(value = "userId")String userId){
        return foodordersService.findByUserid(userId);
    }

    //修改taken信息
    @RequestMapping("/updateTaken")
    public void updateTaken(@RequestBody Foodss foodss){
        foodordersService.updateTaken(foodss.getOpenid(),foodss.getOrderinfo(),1);
        getMakeFoods();
    }

    @RequestMapping("/updateTakens")
    public  Integer updateTakens(@RequestBody Foodss foodss){
        foodordersService.updateTaken(foodss.getOpenid(),foodss.getOrderinfo(),2);
        return 1;
    }
    //获取订单
    @RequestMapping("/foodorder")
    @ResponseBody
    public String makeOrder(@RequestBody FoodOrder foodOrder){
        Foodorders foodorders = new Foodorders();
        //获取总金额
        Double totalPrice = foodOrder.getTotalPrice();
        foodorders.setTotalprice(totalPrice);

        //获取总共数量
        Integer totalNum = foodOrder.getTotalNum();
        foodorders.setTotalnum(totalNum);
        //获取订单列表
        List<Orderss> list = foodOrder.getCartList();

        //获取订单信息
        String inf ="YXE"+getOrderNo();
        foodorders.setOrderinfo(inf);

        //获取桌号
        foodorders.setMeunnumber(foodOrder.getName());

        //获取订单号
        foodorders.setOrdernum(inf);

        //获取openid
        foodorders.setUserid(foodOrder.getOpenid());

        foodordersService.insertFoodorders(foodorders);
        for(int i =0;i<list.size();i++){
            String name = list.get(i).getName();
            Integer quantity = list.get(i).getQuantity();
            Double discount = list.get(i).getDiscount();
            Orderlist orderlist = new Orderlist();
            orderlist.setName(name);
            orderlist.setDiscount(discount);
            orderlist.setOrderinfo(inf);
            orderlist.setQuantity(quantity);
            orderlistService.insertOrderlist(orderlist);
        }
        System.out.println(inf);
        return inf;
    }

    //获取列表信息
    @RequestMapping("/orderlists")
    @ResponseBody
    public Foodorders getOrderLists(@RequestBody Foodss foodss ){
        System.out.println(foodss.getOpenid());
        System.out.println(foodss.getOrderinfo());
        Foodorders foodorders = foodordersService.findByOpenidAndOrderinfo(foodss.getOpenid(),foodss.getOrderinfo());
        System.out.println(foodorders);
        return foodorders;
    }

    //获取订单商品列表信息
    @RequestMapping("/detaillist")
    public List<Orderlist> getDetailLists(String orderinfo){
        List<Orderlist> list = orderlistService.selectOrderlist(orderinfo);
        System.out.println(list);
        return list;
    }

    //获取做菜信息
    @RequestMapping("/makeFoods")
    public ModelAndView getMakeFoods(){
        List<Foodorders> list = foodordersService.findByTaken(0);
        List<MakeFoods> list2 = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            List<Orderlist> list1 = orderlistService.selectOrderlist(list.get(i).getOrderinfo());
            Foodorders foodorders = list.get(i);
            MakeFoods makeFoods = new MakeFoods(foodorders,list1);
            list2.add(makeFoods);
        }
        ModelAndView view = new ModelAndView("orderlists");
        view.addObject("orderlist_list",list2 );
        return view;
    }
    /**
     *  根据时间戳生成订单号
     * */
    public static String getOrderNo () {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime localDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return df.format(localDateTime);
    }

    //获取做菜列表
    public List<Orderlist> getOrderLists(String orderinfo){
        return orderlistService.selectOrderlist(orderinfo);
    }
}
