package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.config.HttpClientConfig;
import cn.monstar.payment.config.MonstarConfig;
import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.config.WxPayConfig;
import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.domain.util.UrlUtil;
import cn.monstar.payment.domain.util.encryption.WxSignUtils;
import cn.monstar.payment.domain.util.wechat.notify.WxPayNotifyRequest;
import cn.monstar.payment.domain.util.wechat.request.*;
import cn.monstar.payment.domain.util.wechat.response.*;
import cn.monstar.payment.web.exception.wx.WxPayException;
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
    private HttpClientConfig httpClientConfig;

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private MonstarConfig monstarConfig;

    public String getPayUrl() {
        if (this.monstarConfig.getSandboxnew()) {
            return BASE_URL + "/sandboxnew";
        }
        return BASE_URL;
    }

    @Override
    public WxPayUnifiedOrderResponese wxUnifiedOrder(WxPayUnifiedOrderRequest request) {
        request.checkedAndSign(wxConfig);
        String url = getPayUrl() + "/pay/unifiedorder";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayUnifiedOrderResponese result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayUnifiedOrderResponese.class);
        result.checkResult(wxConfig, request.getSignType(), true);
        return result;
    }

    @Override
    public WxPayNotifyRequest parseNofifyResult(String notifyString) {
        if (StringUtils.isBlank(notifyString)) {
            throw new WxPayException("需要解析的支付结果不能为空");
        }
        //执行解析
        WxPayNotifyRequest result = AbstractWxPayBaseResponse.fromXML(notifyString, WxPayNotifyRequest.class);
        //校验签名
        if (!WxSignUtils.checkSign(result, wxConfig.getMchKey())) {
            throw new WxPayException("签名不正确");
        }
        return result;
    }

    @Override
    public WxPayOrderQueryResponse wxOrderQuery(String transactionId, String outTradeNo) {
        WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
        request.setOutTradeNo(outTradeNo);
        request.setTransactionId(transactionId);

        request.checkedAndSign(wxConfig);
        String url = getPayUrl() + "/pay/orderquery";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayOrderQueryResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayOrderQueryResponse.class);
        result.composeCoupons();
        result.checkResult(wxConfig, request.getSignType(), true);
        return result;
    }

    @Override
    public WxPayCloseOrderResponse wxCloseOrder(String outTradeNo) {
        WxPayCloseOrderRequest request = new WxPayCloseOrderRequest();
        request.setOutTradeNo(outTradeNo);

        request.checkedAndSign(wxConfig);
        String url = getPayUrl() + "/pay/closeorder";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayCloseOrderResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayCloseOrderResponse.class);
        result.checkResult(wxConfig, request.getSignType(), true);
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
        request.checkedAndSign(wxConfig);
        String url = getPayUrl() + "/secapi/pay/refund";
        String resultContent = this.post(url, request.toXML(), true);
        WxPayRefundResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayRefundResponse.class);
        result.composeCoupons();
        result.checkResult(wxConfig, request.getSignType(), true);
        return result;
    }

    @Override
    public WxPayRefundQueryResponse wxRefundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId) {
        WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
        request.setTransactionId(transactionId);
        request.setOutRefundNo(outRefundNo);
        request.setRefundId(refundId);
        request.setOutTradeNo(outTradeNo);

        request.checkedAndSign(wxConfig);

        String url = getPayUrl() + "/pay/refundquery";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayRefundQueryResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayRefundQueryResponse.class);
        result.composeRefundRecords();
        result.checkResult(wxConfig, request.getSignType(), true);
        return result;
    }

    @Override
    public WxPayShortUrlResponse wxLongUrlToShortUrl(String longUrl) {
        WxPayShortUrlRequst requst = new WxPayShortUrlRequst();
        requst.setLongUrl(longUrl);

        requst.checkedAndSign(wxConfig);
        //需要传输encode后的链接
        String longUrlencode = UrlUtil.encode(longUrl, null);
        if (StringUtils.isBlank(longUrlencode)) {
            logger.error("长链接encode失败");
            throw new WxPayException("长链接encode失败");
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
    protected String post(String url, String requestStr, boolean useKey) throws WxPayException {
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
                    .setConnectionRequestTimeout(httpClientConfig.getHttpConnectionTimeout())
                    .setConnectTimeout(httpClientConfig.getHttpConnectionTimeout())
                    .setSocketTimeout(httpClientConfig.getHttpTimeout())
                    .build());

            if (StringUtils.isNotBlank(httpClientConfig.getHttpProxyHost())
                    && httpClientConfig.getHttpProxyPort() > 0) {
                // 使用代理服务器 需要用户认证的代理服务器
                CredentialsProvider provider = new BasicCredentialsProvider();
                provider.setCredentials(
                        new AuthScope(httpClientConfig.getHttpProxyHost(), httpClientConfig.getHttpProxyPort()),
                        new UsernamePasswordCredentials(httpClientConfig.getHttpProxyUsername(), httpClientConfig.getHttpProxyPassword()));
                httpClientBuilder.setDefaultCredentialsProvider(provider);
            }

            try (CloseableHttpClient httpclient = httpClientBuilder.build()) {
                httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
                try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                    String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    this.logger.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
                    return responseString;
                }
            } finally {
                httpPost.releaseConnection();
            }
        } catch (Exception e) {
            this.logger.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
            throw new WxPayException(e.getMessage());
        }
    }


}
