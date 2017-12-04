package cn.monstar.payment.config;

import cn.monstar.payment.domain.model.enums.ExceptionEnum;
import cn.monstar.payment.web.exception.wx.WxPayException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 上午10:26
 */
@ConditionalOnClass(value = {WxConfig.class, HttpClientConfig.class})
public class WxPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(WxPayConfig.class);

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private HttpClientConfig httpClientConfig;

    public SSLContext initSSLContext() {
        if (StringUtils.isBlank(wxConfig.getMchId())) {
            throw new WxPayException(String.format(ExceptionEnum.PARAMREQUIRED.getLabel(), "mchId"));
        }

        if (StringUtils.isBlank(wxConfig.getKeyPath())) {
            throw new WxPayException(String.format(ExceptionEnum.PARAMREQUIRED.getLabel(), "keyPath"));
        }
        InputStream inputStream;
        final String prefix = "classpath:";
        String fileHasProblemMsg = "证书文件【" + wxConfig.getKeyPath() + "】有问题，请核实！";
        String fileNotFoundMsg = "证书文件【" + wxConfig.getKeyPath() + "】不存在，请核实！";
        if (wxConfig.getKeyPath().startsWith(prefix)) {
            String path = StringUtils.removeFirst(wxConfig.getKeyPath(), prefix);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            inputStream = WxPayConfig.class.getResourceAsStream(path);
            if (inputStream == null) {
                logger.error(fileNotFoundMsg);
                throw new WxPayException(fileNotFoundMsg);
            }
        } else {
            try {
                File file = new File(wxConfig.getKeyPath());
                if (!file.exists()) {
                    logger.error(fileNotFoundMsg);
                    throw new WxPayException(fileNotFoundMsg);
                }
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(fileHasProblemMsg + "{}", e);
                throw new WxPayException(fileHasProblemMsg);
            }
        }

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] partnerId2charArray = wxConfig.getMchId().toCharArray();
            keyStore.load(inputStream, partnerId2charArray);
            httpClientConfig.setSslContext(SSLContexts.custom().loadKeyMaterial(keyStore, partnerId2charArray).build());
            return httpClientConfig.getSslContext();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化 SSLContext 出现错误:{}", e.getMessage());
            throw new WxPayException("初始化 SSLContext 出现错误:" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
