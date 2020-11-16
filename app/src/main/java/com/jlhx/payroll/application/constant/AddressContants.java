package com.jlhx.payroll.application.constant;

/**
 * 网址相关常量
 */
public final class AddressContants {


    private static String SERVER_ADDR_HOME = "";

    static {
        if (GlobalConfigContants.intEnviSwitch == 0) {
//            SERVER_ADDR_HOME = "http://huiyan-sys-test.kuaidaoapp.com";
            SERVER_ADDR_HOME = "http://huiyan-zuul-test.kuaidaoapp.com";//测试环境使用
        } else if (GlobalConfigContants.intEnviSwitch == 1) {
            SERVER_ADDR_HOME = "http://api-huiju-pre.kuaidaoapp.com";
        } else if (GlobalConfigContants.intEnviSwitch == 2) {
            SERVER_ADDR_HOME = "https://api-huiju.kuaidao.cn";
        }
    }

    //用户相关
    public static final String API_SERVER_CHECK_USER = SERVER_ADDR_HOME + "/sys/app/login";        //校验用户名密码
    public static final String API_SERVER_SMS_SEND = SERVER_ADDR_HOME + "/sys/app/sendmsgPwd";        //获取短信验证码
    public static final String API_SERVER_LOGIN = SERVER_ADDR_HOME + "/sys/app/loginAndVcode";        //登录,校验验证码和用户名密码
    public static final String API_SERVER_GET_USER = SERVER_ADDR_HOME + "/sys/app/getUser";        //查询用户状态
    public static final String API_SERVER_UPDATE_PWD = SERVER_ADDR_HOME + "/sys/app/alterPassword";        //修改密码
    public static final String API_SERVER_LOGOUT = SERVER_ADDR_HOME + "/sys/app/logout";        //退出
    public static final String API_SERVER_UPDATE_USER_DATA = SERVER_ADDR_HOME + "/sys/app/updateUser";        //修改用户信息

    //规则部分
    public static final String API_SERVER_GET_RULE_LIST = SERVER_ADDR_HOME + "/aggregation/app/queryTeleAssignRulePage";   //规则列表查询
    public static final String API_SERVER_GET_RULE_DETAIL = SERVER_ADDR_HOME + "/aggregation/app/queryTeleAssignRuleById";      //规则详情
    public static final String API_SERVER_RULE_UPDATE = SERVER_ADDR_HOME + "/aggregation/app/updateTeleAssignRule";        //电销分配规则修改
    public static final String API_SERVER_RULE_CREATE = SERVER_ADDR_HOME + "/aggregation/app/saveTeleAssignRule";      //电销分配规则新增
    public static final String API_SERVER_RULE_UPDATE_STATUS = SERVER_ADDR_HOME + "/aggregation/app/updateTeleAssignRuleStatus";    //启用规则，停用规则，规则id

    //公共资源
    public static final String API_SERVER_GET_RESOURCE_CATEGORY = SERVER_ADDR_HOME + "/sys/app/getclueCategory";    // 查询资源类别
    public static final String API_SERVER_GET_RESOURCE_TYPE = SERVER_ADDR_HOME + "/sys/app/getclueType";        // 查询资源类型
    public static final String API_SERVER_GET_PROVINCE_LIST = SERVER_ADDR_HOME + "/sys/app/getcityList";        //获取所有城市

    //电销管理列表
    public static final String API_SERVER_GET_RESOURCE_LIST = SERVER_ADDR_HOME + "/aggregation/app/appClueBasic/appPendingAllocationList";        //待分发资源查询
    public static final String API_SERVER_GET_CUSTOMER_MANAGER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appCustomerManager/findcustomerPage";        //分页查询客户管理列表
    public static final String API_SERVER_GET_BUSINESS_CUSTOMER_MANAGER_LIST = SERVER_ADDR_HOME + "/aggregation/app/bus/busCustomerList";        //分页查询商务客户管理列表
    public static final String API_SERVER_GET_BUSINESS_CUSTOMER_MANAGER_JL_LIST = SERVER_ADDR_HOME + "/aggregation/app/bus/busSaleCustomerList";        //分页查询商务客户管理列表(商务经理)
    public static final String API_SERVER_GET_NOT_DISTRIBUTION_CUSTOMER_MANAGER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/pendingVisitList";        //分页查询 待分配客户管理列表
    public static final String API_SERVER_GET_NOT_DEAL_CUSTOMER_MANAGER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/appPendingDealVisitList";        //分页查询 查询待处理来访客户列表
    public static final String API_SERVER_GET_CUSTOMER_MANAGER_INFO_LIST = SERVER_ADDR_HOME + "/aggregation/app/appCustomerManager/findcustomerInfo";        //获取客户详情
//    public static final String API_SERVER_GET_CUSTOMER_STATUS_LIST = SERVER_ADDR_HOME + "/sys/app/getcustomerStatus";        //获取客户状态
    public static final String API_SERVER_GET_URNOVER_PERSON_INFO_LIST = SERVER_ADDR_HOME + "/aggregation/app/appCustomerManager/findCirculation";        //流转人员名单查询
    public static final String API_SERVER_GET_CALL_INFO_LIST = SERVER_ADDR_HOME + "/aggregation/app/bus/listTmCallReacordByParamsNoPage";        //通话信息查询
    public static final String API_SERVER_GET_TRACKING_LIST = SERVER_ADDR_HOME + "/aggregation/app/appCustomerManager/queryTrackingList";        //客户跟进记录信息
    public static final String API_SERVER_GET_CUSTOMER_INVITATION_INFO_LIST = SERVER_ADDR_HOME + "/aggregation/app/appClueAppiontment/get";        //获取邀约详情
    public static final String API_SERVER_GET_INVITATION_CUSTOMER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appClueAppiontment/list";        //邀约来访列表查询创 
    public static final String API_SERVER_GET_TELE_MARKETING_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appDXFZFindSaleGroupList";        //电销副总查询下属电销组及相应电销总监及电销顾问
    public static final String API_SERVER_GET_TELE_MARKETING_LIST2 = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appDXFZFindSaleGroupListWithoutUnuse";        //电销副总查询下属电销组及相应电销总监及相应电销顾问除禁用
    public static final String API_SERVER_GET_GROUP_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appFindSaleGroupListInDeptWithoutUnuse";        //查询同事业部下电销组及相应电销总监除禁用
    public static final String API_SERVER_GET_SWZJ_ALL_MEMBER_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appFindAllSaleGroup";        //查询所有电销组及相应创业顾问
    public static final String API_SERVER_GET_STATEMENTS_LIST_GROUP = SERVER_ADDR_HOME + "/aggregation/app/appReport/getResourceAllocationTable";        //报表 ，组（副总资源分发报表）
    public static final String API_SERVER_GET_STATEMENTS_LIST_PERSONAL = SERVER_ADDR_HOME + "/aggregation/app/appReport/getResourceAllocationPersionTable";        //报表，个人（总监、资源分发报表）
    public static final String API_SERVER_GET_ADVISER_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appDXZJFindUserList";        //获取电销顾问列表
    public static final String API_SERVER_GET_ADVISER_LIST2 = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appDXZJFindUserListWithoutUnuse";        //电销总监查询下属员工除禁用
    public static final String API_SERVER_GET_SWJL_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appSWZJFindUserListWithoutUnuse";        //商务总监查询下属商务经理除禁用
    public static final String API_SERVER_GET_ALL_SWJL_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/getAllSaleList";        //获取同事业线所有商务经理（组织名-大区名）
    public static final String API_SERVER_GET_SWJL_GROUP_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/appSWJLFindSaleGroup";        //商务经理待处理客户所属电销组查询
    public static final String API_SERVER_FIND_SWJL_LIST = SERVER_ADDR_HOME + "/sys/app/appTelemarketing/appSWZJFindUserList";        //商务总监查询下属商务经理
    public static final String API_SERVER_APPROVAL_BACK = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/rejectVisitRecord";        //驳回/通过来访单
    public static final String API_SERVER_APPROVAL_BACK_SIGN_ORDER = SERVER_ADDR_HOME + "/aggregation/app/bus/rejectSignOrder";        //驳回签约单，通过
    public static final String API_SERVER_FIND_SIGN_REPORT = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/findSignReport";        //根据线索id查询签约单列表
    public static final String API_SERVER_FIND_DEL_FLAG = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/findSaleManagerIdAndDelFalg";        //根据邀约来访id查询商务经理与删除标记
//    public static final String API_SERVER_GET_PHONE_GROUP_LIST = SERVER_ADDR_HOME + "/crmbj/management/security/organization/querytelemarketingteam/%1$s";        //获取电销组列表
    public static final String API_SERVER_GET_REPEAT_PHONE_NUMBER_LIST = SERVER_ADDR_HOME + "/aggregation/app/queryRepeatById";        //获取重复手机号接口
    public static final String API_SERVER_DISTRIBUTION_RESOURCE = SERVER_ADDR_HOME + "/aggregation/app/appClueBasic/appAllocationClue";        //分配资源接口
    public static final String API_SERVER_BUSINESS_DISTRIBUTION_RESOURCE = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/appBusAllocationClue";        //商务总监分配资源
    public static final String API_SERVER_TRANSFER_MANAGER = SERVER_ADDR_HOME + "/aggregation/app/bus/busAllocationClue";        //外调/分发
    public static final String API_SERVER_TRANSFER_RESOURCE = SERVER_ADDR_HOME + "/aggregation/app/appClueBasic/appTransferClue";        //转移资源接口
    public static final String API_SERVER_DISPENSE_RESOURCE = SERVER_ADDR_HOME + "/aggregation/app/appClueBasic/appCeoDistributionClue";        //分发资源接口
    public static final String API_SERVER_COMMIT_NOT_VISIT_REASON = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/notVisit";        //标记未到访
    public static final String API_SERVER_CHECK_RESOURCE_STATUS = SERVER_ADDR_HOME + "/aggregation/app/appClueBasic/findClueCirculationNew";        //查询资源最新分配记录

    //商务模块新加的接口
    public static final String API_SERVER_GET_PAYMENT_DETAILSBYID = SERVER_ADDR_HOME + "/aggregation/app/bus/getBusById";        //签约单详情
    public static final String API_SERVER_UPDATEBUS = SERVER_ADDR_HOME + "/aggregation/app/bus/updateBus";        //编辑签约单
    public static final String API_SERVER_INSERTBUS = SERVER_ADDR_HOME + "/aggregation/app/bus/insertBus";        //添加签约单
    public static final String API_SERVER_INSERTBUS_SHOW_INFO = SERVER_ADDR_HOME + "/aggregation/app/bus/echo";        //添加签约单时数据回显的接口
    public static final String API_SERVER_UPDATEBUS_PAYDETAIL = SERVER_ADDR_HOME + "/aggregation/app/bus/updatePayDedail";  //编辑签约单中的付款明细

    public static final String API_SERVER_GET_DICTIONARY = SERVER_ADDR_HOME + "/sys/app/getDictionary";        //字典配置接口
    public static final String API_SERVER_GET_ALL_PROJECT = SERVER_ADDR_HOME + "/aggregation/app/bus/singProject";        //查询所有项目
    public static final String API_SERVER_GET_ALL_COMPANY = SERVER_ADDR_HOME + "/aggregation/app/bus/repastCompany";        //查询所有公司

    public static final String API_SERVER_INSERT_VISITING_RECORD = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/insert";//新增到访记录
    public static final String API_SERVER_VISITING_SHOW_INFO = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/visitShowInfo";//新增到访记录时的回显信息查询接口
    public static final String API_SERVER_UPDATE_VISITING_RECORD = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/update";//更新到访记录
    public static final String API_SERVER_GET_VISITING_RECORD_INFO = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/one";        //到访记录详情查询
    public static final String API_SERVER_GET_REPEAT_RESOURCE_INFO = SERVER_ADDR_HOME + "/aggregation/app/appCustomerManager/findcustomerRepeatList";        //客户重复信息接口
    public static final String API_SERVER_GET_VISITING_RECORD_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/listVisitRecord";        //查询客户到访记录
    public static final String API_SERVER_GET_VISITING_RECORD_FOR_CUSTOMER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/queryList";        //查询某人的到访记录
    public static final String API_SERVER_GET_HOSITORY_VISITING_RECORD_FOR_CUSTOMER_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/queryHisList";        //查询客户历史到访记录
    public static final String API_SERVER_GET_NOT_VISITING_RECORD_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/listNoVisitRecord";        //查询客户未到访记录
    public static final String API_SERVER_GET_SIGN_ORDER_LIST = SERVER_ADDR_HOME + "/aggregation/app/bus/listSignRecord";        //签约记录列表
    public static final String API_SERVER_INSERT_PAYMENT_DETAIL = SERVER_ADDR_HOME + "/aggregation/app/bus/insert";        //添加付款明细

    public static final String API_SERVER_GET_DISPATCHING_CAR_LIST = SERVER_ADDR_HOME + "/aggregation/app/appBusCustomer/listTrackingOrder";        //派车单列表，，，，查询邀约来访派车单
    public static final String API_SERVER_QUERY_ORG_BY_PARAM = SERVER_ADDR_HOME + "/sys/app/bus/queryOrgByParam";        //获取区域信息

    public static final String API_SERVER_UPDATE_CUSTOMER_INFO = SERVER_ADDR_HOME + "/aggregation/app/bus/updateCustomerClue";        //编辑客户详情
    public static final String API_SERVER_WORKBENCH = SERVER_ADDR_HOME + "/aggregation/app/bus/businessJob";        //工作台
}
