package com.jlhx.payroll.application.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/11/12 14:33
 */
public class SignBean implements Serializable {

    /**
     * personal_product_code : FUND_SAFT_SIGN_WITHHOLDING_P
     * sign_scene : INDUSTRY|SATF_ACC
     * access_params : {"channel":"ALIPAYAPP"}
     * identity_params : {"cert_no":"412727199205033517","user_name":"李卫振"}
     * product_code : FUND_SAFT_SIGN_WITHHOLDING
     * external_agreement_no : AN_2020111202021
     * third_party_type : PARTNER
     */

    private String personal_product_code;
    private String sign_scene;
    private AccessParamsBean access_params;
    private IdentityParamsBean identity_params;
    private String product_code;
    private String external_agreement_no;
    private String third_party_type;

    public String getPersonal_product_code() {
        return personal_product_code;
    }

    public void setPersonal_product_code(String personal_product_code) {
        this.personal_product_code = personal_product_code;
    }

    public String getSign_scene() {
        return sign_scene;
    }

    public void setSign_scene(String sign_scene) {
        this.sign_scene = sign_scene;
    }

    public AccessParamsBean getAccess_params() {
        return access_params;
    }

    public void setAccess_params(AccessParamsBean access_params) {
        this.access_params = access_params;
    }

    public IdentityParamsBean getIdentity_params() {
        return identity_params;
    }

    public void setIdentity_params(IdentityParamsBean identity_params) {
        this.identity_params = identity_params;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getExternal_agreement_no() {
        return external_agreement_no;
    }

    public void setExternal_agreement_no(String external_agreement_no) {
        this.external_agreement_no = external_agreement_no;
    }

    public String getThird_party_type() {
        return third_party_type;
    }

    public void setThird_party_type(String third_party_type) {
        this.third_party_type = third_party_type;
    }

    public static class AccessParamsBean {
        /**
         * channel : ALIPAYAPP
         */

        private String channel;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }
    }

    public static class IdentityParamsBean {
        /**
         * cert_no : 412727199205033517
         * user_name : 李卫振
         */

        private String cert_no;
        private String user_name;

        public String getCert_no() {
            return cert_no;
        }

        public void setCert_no(String cert_no) {
            this.cert_no = cert_no;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
