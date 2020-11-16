package com.jlhx.payroll.application.constant;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public class GlobalKeyContans {

    public static final int PAGE_SIZE = 20;         //网络请求单页数据大小
    public static final int SHORT_MESSAGE_TIME_INTERVAL = 60;//设置前后两次获取验证码的时间间隔

    public static final String MESSAGE_RECEIVED_ACTION = "com.crm.kd.kdapplication.MESSAGE_RECEIVED_ACTION";//推送
    public static final String MESSAGE_FINISH_ACTION = "com.crm.kd.kdapplication.MESSAGE_FINISH_ACTION";//关闭
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    //首页底部tab的index
    public static final int MAIN_HOMEPAGE_INDEX = 0;
    public static final int MAIN_SINGLE_INDEX = 1;
    public static final int MAIN_ORDER_INDEX = 2;
    public static final int MAIN_MY_INDEX = 3;
    @IntDef({MAIN_HOMEPAGE_INDEX,MAIN_SINGLE_INDEX,MAIN_ORDER_INDEX,MAIN_MY_INDEX})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndex{ }

    //用户信息修改类型
    public static final String TYPE_PERSONAL_DATA_PHONE = "phone";
    public static final String TYPE_PERSONAL_DATA_MOBILEPHONE = "mobilePhone";
    public static final String TYPE_PERSONAL_DATA_EMAIL = "email";
    public static final String TYPE_PERSONAL_DATA_QQ = "qq";
    public static final String TYPE_PERSONAL_DATA_WECHAT = "wechat";

    @StringDef({TYPE_PERSONAL_DATA_PHONE, TYPE_PERSONAL_DATA_MOBILEPHONE, TYPE_PERSONAL_DATA_EMAIL, TYPE_PERSONAL_DATA_QQ, TYPE_PERSONAL_DATA_WECHAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PersonalDataType {
    }

//    DXZJL("电销总经理"),


//    DXCYGW("电销创业顾问"),
//    SWZC("商务总裁"),
//    SWDQZJ("商务大区总监"),
//    SWZJ("商务总监"),
//    SWJL("商务经理"),
//    SWZJL("商务总经理")
    public static final String ROLE_DXFZ = "DXFZ";  //    DXFZ("电销副总"),
    public static final String ROLE_DXZJ = "DXZJ";  //    DXZJ("电销总监"),
    public static final String ROLE_SWZJ = "SWZJ";  //    DXZJ("商务总监"),
    public static final String ROLE_SWJL = "SWJL";  //    DXZJ("商务经理"),
    @StringDef({ROLE_DXFZ, ROLE_DXZJ, ROLE_SWZJ, ROLE_SWJL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Role {

    }

    public static final int CODE_SUCCESS = 0;      //接口请求成功"),
    public static final int CODE_FAIL = 1;      //接口请求失败"),
    public static final int CODE_FAIL_UNKNOWN = -1;      //未知错误"),
    public static final int CODE_TOKEN_EMPTY = 21801;        //", "Token为空!"),
    public static final int CODE_TOKEN_ERROR = 21802 ;       //Token已过期!"),
    public static final int CODE_ACCOUNT_ERROR = 21804;      //您的账户已被锁定，您被迫下线！"),
    public static final int CODE_ACCOUNT_ABNORMAL = 21805 ;  //", "您的账户在另一台设备登录，您被迫下线!")
    public static final int CODE_ACCOUNT_DISABLE = 21806;        //", "账户已禁用，您被迫下线！"),
    public static final int CODE_PASSWORD_EXPIRATION = 21807 ;       //账户密码已过期，请登录pc端忘记密码方式找回!"),
    @IntDef({CODE_SUCCESS,CODE_FAIL,
            CODE_ACCOUNT_ABNORMAL,CODE_ACCOUNT_ERROR,
            CODE_TOKEN_EMPTY,CODE_TOKEN_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CallBackCode{

    }


    //工作台各个功能入口索引
    public static final int WORK_TYPE_NO_ALLOCATED_RESOURCE = 0;//待分配资源/待处理客户
    public static final int WORK_TYPE_CUSTOMER_MANAGER = 1;   //客户管理/我的客户
    public static final int WORK_TYPE_INVITATION_CUSTOMER = 2; //邀约客户
    public static final int WORK_TYPE_VISITING_RECORDS = 3; //到访记录
    public static final int WORK_TYPE_UNVISITED_RECORD = 31; //未到访记录
    public static final int WORK_TYPE_VISITING_APPROVAL = 4; //到访审批
    public static final int WORK_TYPE_UNVISITED_APPROVAL = 41; //未到访审批
    public static final int WORK_TYPE_SIGNING = 5; //签约单
    public static final int WORK_TYPE_SIGNING_APPROVAL = 6; //签约审批
    public static final int WORK_TYPE_DISPATCHING_CAR = 7; //派车单
    public static final int WORK_TYPE_STATEMENTS = 8; //报表

    @IntDef({WORK_TYPE_NO_ALLOCATED_RESOURCE,
                WORK_TYPE_CUSTOMER_MANAGER,
            WORK_TYPE_INVITATION_CUSTOMER,
            WORK_TYPE_VISITING_RECORDS,
            WORK_TYPE_UNVISITED_RECORD,
            WORK_TYPE_VISITING_APPROVAL,
            WORK_TYPE_UNVISITED_APPROVAL,
            WORK_TYPE_SIGNING,
            WORK_TYPE_SIGNING_APPROVAL,
            WORK_TYPE_DISPATCHING_CAR,
            WORK_TYPE_STATEMENTS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WorkType{ }
}
