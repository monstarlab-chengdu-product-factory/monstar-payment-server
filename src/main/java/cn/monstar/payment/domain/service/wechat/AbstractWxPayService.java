package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.config.*;
import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.domain.util.UrlUtil;
import cn.monstar.payment.domain.util.constant.WxConstantUtil;
import cn.monstar.payment.domain.util.encryption.WxSignUtils;
import cn.monstar.payment.domain.util.wechat.notify.WxPayNotifyRequest;
import cn.monstar.payment.domain.util.wechat.request.*;
import cn.monstar.payment.domain.util.wechat.response.*;
import cn.monstar.payment.web.error.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 下午6:19
 */
public abstract class AbstractWxPayService implements WxPayService {

    protected final String BASE_URL = "https://api.mch.weixin.qq.com";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private HttpClientConfig httpClientConfig;

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private MonstarConfig monstarConfig;

    public String getPayUrl() {
        if (this.monstarConfig.sandboxnew) {
            return BASE_URL + "/sandboxnew";
        }
        return BASE_URL;
    }

    @Override
    public WxPayUnifiedOrderResponese wxUnifiedOrder(WxPayUnifiedOrderRequest request) {
        //检查交易类型
        switch (request.getTradeType()) {
            case WxConstantUtil.TRADE_APP:
                break;
            /**
             * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
             */
            case WxConstantUtil.TRADE_JSAPI:
                if (StringUtils.isBlank(request.getOpenid())) {
                    throw new BusinessException(String.format(messageConfig.E00004, "openid"));
                }
                break;
            /**
             * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
             */
            case WxConstantUtil.TRADE_NATIVE:
                if (org.springframework.util.StringUtils.isEmpty(request.getProductId())) {
                    throw new BusinessException(String.format(messageConfig.E00004, "product_id"));
                }
                break;
            /**
             * 该字段用于上报支付的场景信息,针对H5支付有以下三种场景,请根据对应场景上报,H5支付不建议在APP端使用，针对场景1，2请接入APP支付，不然可能会出现兼容性问题
             */
            case WxConstantUtil.TRADE_H5:
                if (org.springframework.util.StringUtils.isEmpty(request.getSceneInfo())) {
                    throw new BusinessException(String.format(messageConfig.E00004, "scene_info"));
                }
                break;
            default:
                break;
        }
        request.checkedAndSign(wxConfig, messageConfig);
        String url = getPayUrl() + "/pay/unifiedorder";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayUnifiedOrderResponese result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayUnifiedOrderResponese.class);
        result.checkResult(wxConfig, request.getSignType(), true, messageConfig);
        return result;
    }

    @Override
    public WxPayNotifyRequest parseNofifyResult(String notifyString) {
        if (StringUtils.isBlank(notifyString)) {
            throw new BusinessException(messageConfig.E00006);
        }
        //执行解析
        WxPayNotifyRequest result = AbstractWxPayBaseResponse.fromXML(notifyString, WxPayNotifyRequest.class);
        //校验签名
        if (!WxSignUtils.checkSign(result, wxConfig.mchKey)) {
            throw new BusinessException(messageConfig.E00007);
        }
        return result;
    }

    @Override
    public WxPayOrderQueryResponse wxOrderQuery(String transactionId, String outTradeNo) {
        WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
        request.setOutTradeNo(outTradeNo);
        request.setTransactionId(transactionId);

        request.checkedAndSign(wxConfig, messageConfig);
        String url = getPayUrl() + "/pay/orderquery";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayOrderQueryResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayOrderQueryResponse.class);
        result.composeCoupons();
        result.checkResult(wxConfig, request.getSignType(), true, messageConfig);
        return result;
    }

    @Override
    public WxPayCloseOrderResponse wxCloseOrder(String outTradeNo) {
        WxPayCloseOrderRequest request = new WxPayCloseOrderRequest();
        request.setOutTradeNo(outTradeNo);

        request.checkedAndSign(wxConfig, messageConfig);
        String url = getPayUrl() + "/pay/closeorder";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayCloseOrderResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayCloseOrderResponse.class);
        result.checkResult(wxConfig, request.getSignType(), true, messageConfig);
        return result;
    }

    @Override
    public WxPayRefundResponse wxSendRefund(ApplyRefundResultDto applyRefundResultDto) {
        WxPayRefundRequest.Builder builder = new WxPayRefundRequest.Builder();
        builder.setOutTradeNo(applyRefundResultDto.getPaymentNo())
                .setRefundDesc(applyRefundResultDto.getRefundDescription())
                .setRefundFee(StringUtil.yuanToFee(applyRefundResultDto.getRefundMoney().toString()))
                .setTotalFee(StringUtil.yuanToFee(applyRefundResultDto.getOrderMoney().toString()));
        return this.wxSendRefund(builder.newBuiler());
    }

    @Override
    public WxPayRefundResponse wxSendRefund(WxPayRefundRequest request) {
        request.checkedAndSign(wxConfig, messageConfig);
        String url = getPayUrl() + "/secapi/pay/refund";
        String resultContent = this.post(url, request.toXML(), true);
        WxPayRefundResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayRefundResponse.class);
        result.composeCoupons();
        result.checkResult(wxConfig, request.getSignType(), true, messageConfig);
        return result;
    }

    @Override
    public WxPayRefundQueryResponse wxRefundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId) {
        WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
        request.setTransactionId(transactionId);
        request.setOutRefundNo(outRefundNo);
        request.setRefundId(refundId);
        request.setOutTradeNo(outTradeNo);

        request.checkedAndSign(wxConfig, messageConfig);

        String url = getPayUrl() + "/pay/refundquery";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayRefundQueryResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayRefundQueryResponse.class);
        result.composeRefundRecords();
        result.checkResult(wxConfig, request.getSignType(), true, messageConfig);
        return result;
    }

    @Override
    public WxPayShortUrlResponse wxLongUrlToShortUrl(String longUrl) {
        WxPayShortUrlRequst requst = new WxPayShortUrlRequst();
        requst.setLongUrl(longUrl);

        requst.checkedAndSign(wxConfig, messageConfig);
        //需要传输encode后的链接
        String longUrlencode = UrlUtil.encode(longUrl, null);
        if (StringUtils.isBlank(longUrlencode)) {
            logger.error(messageConfig.E00005);
            throw new BusinessException(messageConfig.E00005);
        }
        requst.setLongUrl(longUrlencode);

        String url = getPayUrl() + "/tools/shorturl";
        String resultContent = this.post(url, requst.toXML(), false);
        WxPayShortUrlResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayShortUrlResponse.class);
        return result;
    }

    /**
     * 发送post请求
     *
     * @param url        请求地址
     * @param requestStr 请求信息
     * @param useKey     是否使用证书
     * @return 返回请求结果字符串
     */
    protected String post(String url, String requestStr, boolean useKey) throws BusinessException {
        try {
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (useKey) {
                SSLContext sslContext = httpClientConfig.getSslContext();
                if (null == sslContext) {
                    sslContext = wxPayConfig.initSSLContext();
                }

                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                        new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
                httpClientBuilder.setSSLSocketFactory(sslsf);
            }

            HttpPost httpPost = new HttpPost(url);

            httpPost.setConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(httpClientConfig.httpConnectionTimeout)
                    .setConnectTimeout(httpClientConfig.httpConnectionTimeout)
                    .setSocketTimeout(httpClientConfig.httpTimeout)
                    .build());

            if (StringUtils.isNotBlank(httpClientConfig.httpProxyHost)
                    && httpClientConfig.httpProxyPort > 0) {
                // 使用代理服务器 需要用户认证的代理服务器
                CredentialsProvider provider = new BasicCredentialsProvider();
                provider.setCredentials(
                        new AuthScope(httpClientConfig.httpProxyHost, httpClientConfig.httpProxyPort),
                        new UsernamePasswordCredentials(httpClientConfig.httpProxyUsername, httpClientConfig.httpProxyPassword));
                httpClientBuilder.setDefaultCredentialsProvider(provider);
            }

            try (CloseableHttpClient httpclient = httpClientBuilder.build()) {
                httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
                try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                    String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    this.logger.info(messageConfig.E00001, url, requestStr, responseString);
                    return responseString;
                }
            } finally {
                httpPost.releaseConnection();
            }
        } catch (Exception e) {
            this.logger.error(messageConfig.I00001, url, requestStr, e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

}
