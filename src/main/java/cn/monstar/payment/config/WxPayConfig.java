package cn.monstar.payment.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 上午10:26
 */
@Component
@ConditionalOnClass(value = {WxConfig.class, HttpClientConfig.class})
public class WxPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(WxPayConfig.class);

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private HttpClientConfig httpClientConfig;

    @Autowired
    private MessageConfig messageConfig;

    /**
     * 初始化SSLContext
     *
     * @return
     */
    public SSLContext initSSLContext() {
        if (StringUtils.isBlank(wxConfig.mchId)) {
            throw new RuntimeException(String.format(messageConfig.E00004, "mchId"));
        }

        if (StringUtils.isBlank(wxConfig.keyPath)) {
            throw new RuntimeException(String.format(messageConfig.E00004, "keyPath"));
        }
        InputStream inputStream;
        final String prefix = "classpath:";
        String fileHasProblemMsg = String.format(messageConfig.E00011, wxConfig.keyPath);
        String fileNotFoundMsg = String.format(messageConfig.E00012, wxConfig.keyPath);
        if (wxConfig.keyPath.startsWith(prefix)) {
            String path = StringUtils.removeFirst(wxConfig.keyPath, prefix);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            inputStream = WxPayConfig.class.getResourceAsStream(path);
            if (inputStream == null) {
                logger.error(fileNotFoundMsg);
                throw new RuntimeException(fileNotFoundMsg);
            }
        } else {
            try {
                File file = new File(wxConfig.keyPath);
                if (!file.exists()) {
                    logger.error(fileNotFoundMsg);
                    throw new RuntimeException(fileNotFoundMsg);
                }
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(fileHasProblemMsg + "{}", e);
                throw new RuntimeException(fileHasProblemMsg);
            }
        }

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] partnerId2charArray = wxConfig.mchId.toCharArray();
            keyStore.load(inputStream, partnerId2charArray);
            httpClientConfig.setSslContext(SSLContexts.custom().loadKeyMaterial(keyStore, partnerId2charArray).build());
            return httpClientConfig.getSslContext();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化 SSLContext 出现错误:{}", e.getMessage());
            throw new RuntimeException(String.format(messageConfig.E00013, e.getMessage()));
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
