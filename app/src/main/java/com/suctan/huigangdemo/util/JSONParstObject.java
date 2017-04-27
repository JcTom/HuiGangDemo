package com.suctan.huigangdemo.util;


import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderReturn;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.topic.AddCommentBean;
import com.suctan.huigangdemo.bean.topic.DellCommentBean;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicReturn;
import com.suctan.huigangdemo.bean.user.MykitchenBean;
import com.suctan.huigangdemo.bean.user.MykitchenReturn;
import com.suctan.huigangdemo.bean.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
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
                    eatFoodBean.setOrder_pic("http://119.29.137.109/tp/uploads/" + jsonObject1.getString("order_pic"));
                    eatFoodBean.setOrder_title(jsonObject1.getString("order_title"));
                    eatFoodBean.setOrder_price(Double.parseDouble(jsonObject1.getString("order_price")));
                    eatFoodBean.setFood_description(jsonObject1.getString("food_description"));
                    eatFoodBean.setNum(Integer.parseInt(jsonObject1.getString("order_num")));
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
                    topicBean.setUser_icon("http://119.29.137.109/tp/uploads/" + jsonObject1.getString("user_icon"));
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
                    topicCommentBean.setUser_icon("http://119.29.137.109/tp/uploads/" + jsonObject1.getString("user_icon"));
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


    /** 我的厨房
    * */
    public static MykitchenReturn getMykitchenBeanList(String mykitchenListString) {

        MykitchenReturn mykitchenReturn = null;
        ArrayList<MykitchenBean> mykitchenBeenList = null;
        try {
            JSONObject jsonObject = new JSONObject(mykitchenListString);
            mykitchenReturn = new MykitchenReturn();
            mykitchenBeenList = new ArrayList<>();
            mykitchenReturn.setStatus(jsonObject.getInt("status"));
            mykitchenReturn.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = null;

            jsonArray = jsonObject.getJSONArray("myMakeOrderList");

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                    MykitchenBean mykitchenBean = new MykitchenBean();

                    mykitchenBean.setOrder_pic("http://119.29.137.109/tp/uploads/"+jsonObject2.getString("order_pic"));
                    mykitchenBean.setOrder_id(Integer.parseInt(jsonObject2.getString("order_id")));
                    mykitchenBean.setOrder_title(jsonObject2.getString("order_title"));
                    mykitchenBean.setFood_description(jsonObject2.getString("food_description"));
                    mykitchenBean.setOrder_price(Double.parseDouble(jsonObject2.getString("order_price")));

                    mykitchenBeenList.add(mykitchenBean);
                }
                mykitchenReturn.setMykitchenBeanList(mykitchenBeenList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return mykitchenReturn;
    }

}



