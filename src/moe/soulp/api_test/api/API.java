package moe.soulp.api_test.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * <b>汎用API接続</b><br>
 * date: 2017/08/03 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/08/03 API作成
 * @version 1.1 2017/08/21 Private API用のdelete操作追加
 * @version 1.2 2017/09/07 APIcoincheckの一部をAPIに移動
 * @version 1.3 2017/09/08 coincheckとbitFlyer両方対応の為、通信仕組みを大幅に変更
 */
public abstract class API {
    private final static String ERROR_NULL_API_KEY    = "apiKeyの値がありません。";
    private final static String ERROR_NULL_API_SECRET = "apiSecretの値がありません。";
    private String              parameters;
    private String              apiKey;
    private String              apiSecret;
    private String              apiKeyProperty;
    private String              apiNonceProperty;
    private String              apiSignProperty;

    public API() {
        parameters = "";
        apiKey = "";
        apiSecret = "";
        apiKeyProperty = "";
        apiNonceProperty = "";
        apiSignProperty = "";
    }

    protected String publicAPI(String url, HttpMethod method) {
        try {
            return publicAPI(new URL(url), method);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String publicAPI(URL url, HttpMethod method) {
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            if (HttpMethod.GET != method) {
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
                osw.write(parameters);
                osw.flush();
                osw.close();
            }
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder temp = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    temp.append(line);
                }
                result = temp.toString();
            } else {
                throw new IOException("HTTP CODE: " + code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return result;
    }

    protected String privateAPI(String url, HttpMethod method) {
        try {
            return privateAPI(new URL(url), method);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String privateAPI(URL url, HttpMethod method) {
        HttpURLConnection connection = null;
        String result = null;
        String nonce = createNonce();
        try {
            checkAPIkeys();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            connection.setRequestProperty(apiKeyProperty, apiKey);
            connection.setRequestProperty(apiNonceProperty, nonce);
            connection.setRequestProperty(apiSignProperty, createSignature(apiSecret, url.toString(), nonce, method));
            if (HttpMethod.GET != method) {
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
                osw.write(parameters);
                osw.flush();
                osw.close();
            }
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder temp = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    temp.append(line);
                }
                result = temp.toString();
            } else {
                throw new IOException("HTTP CODE: " + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return result;
    }

    private String createNonce() {
        long currentUnixTime = System.currentTimeMillis() / 1000l;
        String nonce = String.valueOf(currentUnixTime);
        return nonce;
    }

    protected abstract String createSignature(String apiSecret, String url, String nonce, HttpMethod method);

    protected static String HMAC_SHA256Encode(String secretKey, String message) {

        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "hmacSHA256");

        Mac mac = null;
        try {
            mac = Mac.getInstance("hmacSHA256");
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] rawHmac = mac.doFinal(message.getBytes());
        return DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
    }

    /**
     * <b>APIキー 設定</b>
     * 
     * @param apiKey
     *            APIキー
     */
    public void setAPIkey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * <b>APIシークレット 設定</b>
     * 
     * @param apiSecret
     *            APIシークレット
     */
    public void setAPIsecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    /**
     * <b>API認証情報 全消去</b>
     */
    public void clearAuthInfo() {
        apiKey = "";
        apiSecret = "";
    }

    /**
     * <b>APIキーの有無</b>
     * 
     * @return <b>true</b> ない<br>
     *         <b>false</b> ある
     */
    public boolean apiKeyIsEmpty() {
        if (apiKey == null) return true;
        if (apiKey.equals("")) return true;
        return false;
    }

    /**
     * <b>APIシークレットの有無</b>
     * 
     * @return <b>true</b> ない<br>
     *         <b>false</b> ある
     */
    public boolean apiSecretIsEmpty() {
        if (apiSecret == null) return true;
        if (apiSecret.equals("")) return true;
        return false;
    }

    protected void setAPIkeyProperty(String apiKeyProperty) {
        this.apiKeyProperty = apiKeyProperty;
    }

    protected void setAPInonceProperty(String apiNonceProperty) {
        this.apiNonceProperty = apiNonceProperty;
    }

    protected void setAPIsignProperty(String apiSignProperty) {
        this.apiSignProperty = apiSignProperty;
    }

    protected void setParameters(String parameters) {
        this.parameters = parameters;
    }

    protected String getParameters() {
        return parameters;
    }

    protected void addParameter(String key, String value) {
        if (parameters == null) parameters = "";
        if (parameters.equals("")) parameters = key + "=" + value;
        else parameters += "&" + key + "=" + value;
    }

    protected void clearParameters() {
        parameters = "";
    }

    protected void checkAPIkeys() throws Exception {
        if (apiKeyIsEmpty()) throw new Exception(ERROR_NULL_API_KEY);
        if (apiSecretIsEmpty()) throw new Exception(ERROR_NULL_API_SECRET);
    }
}
