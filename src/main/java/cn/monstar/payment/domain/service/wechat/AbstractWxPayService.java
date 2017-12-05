package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.config.HttpClientConfig;
import cn.monstar.payment.config.MonstarConfig;
import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.config.WxPayConfig;
import cn.monstar.payment.domain.util.wechat.WxPayApiData;
import cn.monstar.payment.domain.util.wechat.request.WxPayOrderQueryRequest;
import cn.monstar.payment.domain.util.wechat.request.WxPayUnifiedOrderRequest;
import cn.monstar.payment.domain.util.wechat.response.AbstractWxPayBaseResponse;
import cn.monstar.payment.domain.util.wechat.response.WxPayOrderQueryResponse;
import cn.monstar.payment.domain.util.wechat.response.WxPayUnifiedOrderResponese;
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
    protected static ThreadLocal<WxPayApiData> wxPayApiData = new ThreadLocal<>();

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
    public WxPayOrderQueryResponse wxOrderQuery(WxPayOrderQueryRequest request) {
        request.checkedAndSign(wxConfig);
        String url = getPayUrl() + "/pay/orderquery";
        String resultContent = this.post(url, request.toXML(), false);
        WxPayOrderQueryResponse result = AbstractWxPayBaseResponse.fromXML(resultContent, WxPayOrderQueryResponse.class);
        result.composeCoupons();
        result.checkResult(wxConfig, request.getSignType(), true);
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
                    wxPayApiData.set(new WxPayApiData(url, requestStr, responseString, null));
                    return responseString;
                }
            } finally {
                httpPost.releaseConnection();
            }
        } catch (Exception e) {
            this.logger.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
            wxPayApiData.set(new WxPayApiData(url, requestStr, null, e.getMessage()));
            throw new WxPayException(e.getMessage());
        }
    }


}
