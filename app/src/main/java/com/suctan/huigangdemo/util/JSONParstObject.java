package com.suctan.huigangdemo.util;


import com.suctan.huigangdemo.bean.address.AddAdressReturn;
import com.suctan.huigangdemo.bean.address.AddressBean;
import com.suctan.huigangdemo.bean.cart.CartBean;
import com.suctan.huigangdemo.bean.cart.CartReturn;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderReturn;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendReturn;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicReturn;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtderReturn;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherComment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Create by LZH  on 16/11/16
 */
public class JSONParstObject {

    /**
     * 获取用户的信息
     */
    public static Users GetUserJSonObject(String userString) {
        Users currentUser = null;
        try {
            JSONObject jsonObject = new JSONObject(userString);
            currentUser = new Users();
            if (jsonObject.getString("user_phone") != null) {
                currentUser.setUser_phone(jsonObject.getString("user_phone"));
            }
            if (jsonObject.getString("user_name") != null) {
                currentUser.setUser_name(jsonObject.getString("user_name"));
            }
            if (jsonObject.getString("user_icon") != null) {
                currentUser.setUser_icon(jsonObject.getString("user_icon"));
            }
            if (jsonObject.getString("user_alias") != null) {
                currentUser.setUser_alias(jsonObject.getString("user_alias"));
            }
            currentUser.setUser_sex(jsonObject.getInt("user_sex"));
            if (jsonObject.getString("user_age") != null) {
                currentUser.setUser_age(jsonObject.getString("user_age"));
            }
            if (jsonObject.getString("user_education") != null) {
                currentUser.setUser_education(jsonObject.getString("user_education"));
            }
            if (jsonObject.getString("user_skill") != null) {
                currentUser.setUser_skill(jsonObject.getString("user_skill"));
            }
            if (jsonObject.getString("user_hobby") != null) {
                currentUser.setUser_hobby(jsonObject.getString("user_hobby"));
            }
            if (jsonObject.getString("user_address") != null) {
                currentUser.setUser_address(jsonObject.getString("user_address"));
            }
            currentUser.setUser_money(jsonObject.getDouble("user_money"));

            if (jsonObject.getString("user_card") != null) {
                currentUser.setUser_card(jsonObject.getString("user_card"));
            }
            if (jsonObject.getString("user_coupon") != null) {
                currentUser.setUser_coupon(jsonObject.getString("user_coupon"));
            }

            currentUser.setIs_cooking(jsonObject.getInt("is_cooking"));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return currentUser;
    }


    /**
     * 获取轮播图列表
     *
     * @param eatFoodListString
     * @param requestKey        0为获取轮播图 1为获取首页列表的菜
     * @return
     */
    public static EatFoodReturn getRollViewDataList(String eatFoodListString, int requestKey) {

        EatFoodReturn eatFoodReturn = null;
        ArrayList<EatFoodBean> eatFoodList = null;
        try {
            JSONObject jsonObject = new JSONObject(eatFoodListString);
            eatFoodReturn = new EatFoodReturn();
            eatFoodList = new ArrayList<>();
            eatFoodReturn.setStatus(jsonObject.getInt("status"));
            eatFoodReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;

            jsonArray = jsonObject.getJSONArray("makeOrderList");

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    EatFoodBean eatFoodBean = new EatFoodBean();
                    eatFoodBean.setUser_name(jsonObject1.getString("user_name"));
                    eatFoodBean.setOrder_id(Integer.parseInt(jsonObject1.getString("order_id")));
                    eatFoodBean.setOrder_pic("http://10.5.12.125/tp/uploads/" + jsonObject1.getString("order_pic"));
                    eatFoodBean.setOrder_title(jsonObject1.getString("order_title"));
                    String orderPrice = jsonObject1.getString("order_price");
                    if (orderPrice != null) {
                        eatFoodBean.setOrder_price(Double.parseDouble(orderPrice));
                    }
                    eatFoodBean.setFood_description(jsonObject1.getString("food_description"));
                    String remainNum = jsonObject1.getString("order_num");
                    if (remainNum != null) {
                        eatFoodBean.setNum(Integer.parseInt(remainNum));
                    }
                    String order_type = jsonObject1.getString("order_type");
                    if (order_type != null) {
                        eatFoodBean.setOrder_type(Integer.parseInt(order_type));
                    }
                    eatFoodList.add(eatFoodBean);
                }
                eatFoodReturn.setEatFoodBeanList(eatFoodList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return eatFoodReturn;
    }

    /**
     * 获取话题列表
     *
     * @param topString
     * @return
     */
    public static TopicReturn getTopicStringObject(String topString) {
        TopicReturn topicReturn = null;
        ArrayList<TopicBean> topicBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(topString);
            topicReturn = new TopicReturn();
            topicBeenList = new ArrayList<>();
            topicReturn.setStatus(jsonObject.getInt("status"));
            topicReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            jsonArray = jsonObject.getJSONArray("topics");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    TopicBean topicBean = new TopicBean();
                    String topicID = jsonObject1.getString("topic_id");
                    if (topicID != null) {
                        topicBean.setTopic_id(Integer.parseInt(topicID));
                    }
                    topicBean.setUser_icon("http://10.5.12.125/tp/uploads/" + jsonObject1.getString("user_icon"));
                    topicBean.setUser_name(jsonObject1.getString("user_name"));

                    topicBean.setTopic_title(jsonObject1.getString("topic_title"));
                    topicBean.setTopic_content(jsonObject1.getString("topic_content"));
                    topicBean.setTopic_picture("http://119.29.137.109/tp/uploads/" + jsonObject1.getString("topic_picture"));
                    String commentNum = jsonObject1.getString("comment_num");
                    if (commentNum != null) {
                        topicBean.setComment_num(Integer.parseInt(commentNum));
                    }
                    topicBean.setPub_topic_time(jsonObject1.getString("pub_topic_time"));
                    topicBeenList.add(topicBean);
                }
                topicReturn.setTipBeanList(topicBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return topicReturn;
    }


    /**
     * 获取某话题评论列表
     *
     * @param topcommentString
     * @return
     */
    public static TopicCommentReturn getTopicCommentObject(String topcommentString) {
        TopicCommentReturn topicCommentReturn = null;
        ArrayList<TopicCommentBean> topicCommentList = null;
        try {
            JSONObject jsonObject = new JSONObject(topcommentString);
            topicCommentReturn = new TopicCommentReturn();
            topicCommentList = new ArrayList<>();
            topicCommentReturn.setStatus(jsonObject.getInt("status"));
            topicCommentReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            jsonArray = jsonObject.getJSONArray("topic_commentS");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    TopicCommentBean topicCommentBean = new TopicCommentBean();
                    topicCommentBean.setUser_name(jsonObject1.getString("user_name"));
                    topicCommentBean.setUser_icon("http://10.5.12.125/tp/uploads/" + jsonObject1.getString("user_icon"));
                    topicCommentBean.setContent(jsonObject1.getString("content"));
                    topicCommentBean.setComment_time(jsonObject1.getString("comment_time"));
                    String commendId = jsonObject1.getString("comment_id");
                    if (commendId != null) {
                        topicCommentBean.setComment_id(Integer.parseInt(commendId));
                    }

                    topicCommentList.add(topicCommentBean);
                }
                topicCommentReturn.setTopicCommentList(topicCommentList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return topicCommentReturn;
    }


    /**
     * 获取我要做的订单列表
     *
     * @param doorderString
     * @return
     */
    public static DoWantOrderReturn getDoWantListObject(String doorderString) {
        DoWantOrderReturn doWantOrderReturn = null;
        ArrayList<DoWantOrderBean> doWantOrderBeanList = null;
        try {
            JSONObject jsonObject = new JSONObject(doorderString);
            doWantOrderReturn = new DoWantOrderReturn();
            doWantOrderBeanList = new ArrayList<>();
            doWantOrderReturn.setStatus(jsonObject.getInt("status"));
            doWantOrderReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            jsonArray = jsonObject.getJSONArray("eat_orderS");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    DoWantOrderBean mdowantOrderBean = new DoWantOrderBean();
                    mdowantOrderBean.setUser_name(jsonObject1.getString("user_name"));
                    mdowantOrderBean.setUser_phone(jsonObject1.getString("user_phone"));
                    mdowantOrderBean.setUser_address(jsonObject1.getString("user_address"));
                    String orderId = jsonObject1.getString("order_id");
                    if (orderId != null) {
                        mdowantOrderBean.setOrder_id(Integer.parseInt(orderId));
                    }
                    mdowantOrderBean.setOrder_title(jsonObject1.getString("order_title"));
                    String orderPrice = jsonObject1.getString("order_price");
                    if (orderPrice != null) {
                        mdowantOrderBean.setOrder_price(Double.parseDouble(orderPrice));
                    }
                    String order_type = jsonObject1.getString("order_type");
                    if (order_type != null) {
                        mdowantOrderBean.setOrder_type(Integer.parseInt(order_type));
                    }
                    mdowantOrderBean.setOrder_note(jsonObject1.getString("order_note"));
                    mdowantOrderBean.setEatstrarr(jsonObject1.getString("eatstrarr"));
                    mdowantOrderBean.setOrder_res_time(jsonObject1.getString("order_res_time"));
                    mdowantOrderBean.setOrder_expect_time(jsonObject1.getString("order_expect_time"));
                    doWantOrderBeanList.add(mdowantOrderBean);
                }
                doWantOrderReturn.setDoWantOrderBeenList(doWantOrderBeanList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return doWantOrderReturn;
    }


    /**
     * 获取推荐其它的订单和评价列表
     *
     * @param wantOtherEatString
     * @return
     */
    public static WantEatOtderReturn getOtherWantEatListObject(String wantOtherEatString) {
        WantEatOtderReturn wantEatOrderReturn = null;
        ArrayList<EatFoodBean> eatFoodList = null;
        ArrayList<WantEatOtherComment> wantEatOtherCommentList = null;
        try {
            JSONObject jsonObject = new JSONObject(wantOtherEatString);
            wantEatOrderReturn = new WantEatOtderReturn();
            eatFoodList = new ArrayList<>();
            wantEatOtherCommentList = new ArrayList<>();
            wantEatOrderReturn.setStatus(jsonObject.getInt("status"));
            wantEatOrderReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArrayOther = null;
            JSONArray jsonArrayComment = null;
            jsonArrayOther = jsonObject.getJSONArray("makeOrderList");
            jsonArrayComment = jsonObject.getJSONArray("commentList");
            if (jsonArrayOther != null) {
                for (int i = 0; i < jsonArrayOther.length(); i++) {
                    JSONObject jsonObjectOther = (JSONObject) jsonArrayOther.get(i);
                    EatFoodBean eatFoodBean = new EatFoodBean();
                    String remainNum = jsonObjectOther.getString("order_num");
                    if (remainNum != null) {
                        eatFoodBean.setNum(Integer.parseInt(remainNum));
                    }
                    eatFoodBean.setUser_name(jsonObjectOther.getString("user_name"));
                    eatFoodBean.setOrder_id(Integer.parseInt(jsonObjectOther.getString("order_id")));
                    eatFoodBean.setOrder_pic("http://10.5.12.125/tp/uploads/" + jsonObjectOther.getString("order_pic"));
                    eatFoodBean.setOrder_title(jsonObjectOther.getString("order_title"));
                    String orderPrice = jsonObjectOther.getString("order_price");
                    if (orderPrice != null) {
                        eatFoodBean.setOrder_price(Double.parseDouble(orderPrice));
                    }
                    eatFoodBean.setFood_description(jsonObjectOther.getString("food_description"));
                    String order_type = jsonObjectOther.getString("order_type");
                    if (order_type != null) {
                        eatFoodBean.setOrder_type(Integer.parseInt(order_type));
                    }
                    eatFoodList.add(eatFoodBean);
                }
                wantEatOrderReturn.setEatFoodBeenList(eatFoodList);
            }
            if (jsonArrayComment != null) {
                for (int j = 0; j < jsonArrayComment.length(); j++) {
                    WantEatOtherComment wantEatOtherComment = new WantEatOtherComment();
                    JSONObject jsonComment = (JSONObject) jsonArrayComment.get(j);
                    wantEatOtherComment.setUser_name(jsonComment.getString("user_name"));
                    wantEatOtherComment.setUser_icon("http://10.5.12.125/tp/uploads/" + jsonComment.getString("user_icon"));
                    wantEatOtherComment.setComment_content(jsonComment.getString("comment_content"));
                    String average = jsonComment.getString("average");
                    if (average != null) {
                        wantEatOtherComment.setAverage(Integer.parseInt(average));
                    }
                    wantEatOtherComment.setComment_content(jsonComment.getString("comment_time"));
                    wantEatOtherCommentList.add(wantEatOtherComment);
                }
                wantEatOrderReturn.setWantEatOrderCommentList(wantEatOtherCommentList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return wantEatOrderReturn;
    }


    /**
     * 获取购物车列表
     *
     * @param cartList
     * @return
     */
    public static CartReturn getCartListObject(String cartList) {
        CartReturn cartReturn = null;
        ArrayList<CartBean> cartBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(cartList);
            cartReturn = new CartReturn();
            cartBeenList = new ArrayList<>();
            cartReturn.setStatus(jsonObject.getInt("status"));
            cartReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            jsonArray = jsonObject.getJSONArray("scList");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    CartBean cartBean = new CartBean();
                    String sc_id = jsonObject1.getString("sc_id");
                    if (sc_id != null) {
                        cartBean.setSc_id(Integer.parseInt(sc_id));
                    }
                    String order_id = jsonObject1.getString("order_id");
                    if (order_id != null) {
                        cartBean.setOrder_id(Integer.parseInt(order_id));
                    }
                    cartBean.setOrder_title(jsonObject1.getString("order_title"));
                    cartBean.setOrder_pic(jsonObject1.getString("order_pic"));
                    String order_price = jsonObject1.getString("order_price");
                    if (order_price != null) {
                        cartBean.setOrder_price(Double.parseDouble(order_price));
                    }
                    String order_allprice = jsonObject1.getString("order_allprice");
                    if (order_allprice != null) {
                        cartBean.setOrder_allprice(Integer.parseInt(order_allprice));
                    }
                    String order_type = jsonObject1.getString("order_type");
                    if (order_type != null) {
                        cartBean.setOrder_type(Integer.parseInt(order_type));
                    }
                    String order_num = jsonObject1.getString("order_num");
                    if (order_num != null) {
                        cartBean.setOrder_num(Integer.parseInt(order_num));
                    }
                    String max_num = jsonObject1.getString("max_num");
                    if (max_num != null) {
                        cartBean.setMax_num(Integer.parseInt(max_num));
                    }

                    cartBeenList.add(cartBean);
                }
                cartReturn.setCartBeenList(cartBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cartReturn;
    }


    /**
     * 获取用户地址列表
     *
     * @param addressList
     * @return
     */
    public static AddAdressReturn getAddressListObject(String addressList) {
        AddAdressReturn addAdressReturn = null;
        ArrayList<AddressBean> addressBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(addressList);
            addAdressReturn = new AddAdressReturn();
            addressBeenList = new ArrayList<>();
            addAdressReturn.setStatus(jsonObject.getInt("status"));
            addAdressReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            jsonArray = jsonObject.getJSONArray("addressList");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    AddressBean addressBean = new AddressBean();
                    addressBean.setName(jsonObject1.getString("name"));
                    addressBean.setPhone(jsonObject1.getString("phone"));
                    addressBean.setArea(jsonObject1.getString("area"));
                    addressBean.setCommunity(jsonObject1.getString("community"));
                    addressBean.setAddress(jsonObject1.getString("address"));
                    String inorge = jsonObject1.getString("status");
                    String id = jsonObject1.getString("id");
                    if (id != null) {
                        addressBean.setId(Integer.parseInt(id));
                    }
                    if (inorge != null) {
                        addressBean.setStatus(Integer.parseInt(inorge));
                    }
                    addressBeenList.add(addressBean);
                }
                addAdressReturn.setAddressList(addressBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return addAdressReturn;
    }


    /**
     * 获取发布我要吃的订单列表
     *
     * @param addressList
     * @return
     */
    public static BuyRecommendReturn getBuyRecommendList(String addressList, int status) {
        BuyRecommendReturn buyRecommendReturn = null;
        ArrayList<BuyRecommendBean> buyRecommendBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(addressList);
            buyRecommendReturn = new BuyRecommendReturn();
            buyRecommendBeenList = new ArrayList<>();
            buyRecommendReturn.setStatus(jsonObject.getInt("status"));
            buyRecommendReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            switch (status) {
                case 0:
                    jsonArray = jsonObject.getJSONArray("cusAllEatList");
                    break;
                case 1:
                    jsonArray = jsonObject.getJSONArray("cusWaitEatList");
                    break;
                case 2:
                    jsonArray = jsonObject.getJSONArray("cusDeliveryEatList");
                    break;
                case 3:
                    jsonArray = jsonObject.getJSONArray("cusFinishEatList");
                    break;
            }
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    BuyRecommendBean buyRecommendBean = new BuyRecommendBean();
                    String order_status = jsonObject1.getString("order_status");
                    if (order_status != null) {
                        buyRecommendBean.setOrder_status(Integer.parseInt(order_status));
                    }
                    String order_id = jsonObject1.getString("order_id");
                    if (order_id != null) {
                        buyRecommendBean.setOrder_id(Integer.parseInt(order_id));
                    }

                    String order_price = jsonObject1.getString("order_price");
                    if (order_price != null) {
                        buyRecommendBean.setOrder_price(Double.parseDouble(order_price));
                    }

                    String order_type = jsonObject1.getString("order_type");
                    if (order_type != null) {
                        buyRecommendBean.setOrder_type(Integer.parseInt(order_type));
                    }

                    buyRecommendBean.setOrder_title(jsonObject1.getString("order_title"));
                    buyRecommendBean.setEatstrarr(jsonObject1.getString("eatstrarr"));
                    buyRecommendBean.setOrder_note(jsonObject1.getString("order_note"));
                    buyRecommendBean.setOrder_res_time(jsonObject1.getString("order_res_time"));
                    buyRecommendBean.setOrder_expect_time(jsonObject1.getString("order_pub_time"));
                    buyRecommendBean.setUser_name(jsonObject1.getString("user_name"));
                    buyRecommendBean.setUser_phone(jsonObject1.getString("user_phone"));
                    buyRecommendBean.setUer_address(jsonObject1.getString("user_address"));

                    buyRecommendBeenList.add(buyRecommendBean);
                }
                buyRecommendReturn.setRecommendBeenList(buyRecommendBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return buyRecommendReturn;
    }


    /**
     * 获取定做我要吃的订单列表
     *
     * @param addressList
     * @return
     */
    public static BuyRecommendReturn getMakeOrderList(String addressList, int status) {
        BuyRecommendReturn buyRecommendReturn = null;
        ArrayList<BuyRecommendBean> buyRecommendBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(addressList);
            buyRecommendReturn = new BuyRecommendReturn();
            buyRecommendBeenList = new ArrayList<>();
            buyRecommendReturn.setStatus(jsonObject.getInt("status"));
            buyRecommendReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;
            switch (status) {
                case 0:
                    jsonArray = jsonObject.getJSONArray("cusAllMakeList");
                    break;
                case 1:
                    jsonArray = jsonObject.getJSONArray("cusEatWaitList");
                    break;
                case 2:
                    jsonArray = jsonObject.getJSONArray("deliveryOrderList");
                    break;
                case 3:
                    jsonArray = jsonObject.getJSONArray("finishOrderList");
                    break;
            }
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    BuyRecommendBean buyRecommendBean = new BuyRecommendBean();
                    String order_status = jsonObject1.getString("order_status");
                    if (order_status != null) {
                        buyRecommendBean.setOrder_status(Integer.parseInt(order_status));
                    }
                    String order_id = jsonObject1.getString("order_id");
                    if (order_id != null) {
                        buyRecommendBean.setOrder_id(Integer.parseInt(order_id));
                    }

                    String order_price = jsonObject1.getString("order_price");
                    if (order_price != null) {
                        buyRecommendBean.setOrder_price(Double.parseDouble(order_price));
                    }

                    String order_type = jsonObject1.getString("order_type");
                    if (order_type != null) {
                        buyRecommendBean.setOrder_type(Integer.parseInt(order_type));
                    }

                    buyRecommendBean.setPic(jsonObject1.getString("pic"));
                    buyRecommendBean.setOrder_title(jsonObject1.getString("order_title"));
                    buyRecommendBean.setEatstrarr(jsonObject1.getString("eatstrarr"));
                    buyRecommendBean.setOrder_note(jsonObject1.getString("order_note"));
                    buyRecommendBean.setOrder_res_time(jsonObject1.getString("order_res_time"));
                    buyRecommendBean.setOrder_expect_time(jsonObject1.getString("order_pub_time"));
                    buyRecommendBean.setUser_name(jsonObject1.getString("user_name"));
                    buyRecommendBean.setUser_phone(jsonObject1.getString("user_phone"));
                    buyRecommendBean.setUer_address(jsonObject1.getString("user_address"));

                    buyRecommendBeenList.add(buyRecommendBean);
                }
                buyRecommendReturn.setRecommendBeenList(buyRecommendBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return buyRecommendReturn;
    }


}



