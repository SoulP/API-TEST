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
 * date: 2017/08/03 last_date: 2017/09/07
 * 
 * @author ソウルP
 * @version 1.0 2017/08/03 API作成
 * @version 1.1 2017/08/21 Private API用のdelete操作追加
 * @version 1.2 2017/09/07 APIcoincheckの一部をAPIに移動
 */
public abstract class API {
    private final static String ERROR_NULL_API_KEY    = "apiKeyの値がありません。";
    private final static String ERROR_NULL_API_SECRET = "apiSecretの値がありません。";
    final static String         GET                   = "GET";
    final static String         POST                  = "POST";
    final static String         HEAD                  = "HEAD";
    final static String         OPTIONS               = "OPTIONS";
    final static String         PUT                   = "PUT";
    final static String         DELETE                = "DELETE";
    final static String         TRACE                 = "TRACE";
    private String              parameters            = "";
    private String              apiKey                = "";
    private String              apiSecret             = "";

    protected String getPublicAPI(String url) {
        try {
            return getPublicAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getPublicAPI(URL url) {
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);
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

    protected String getPrivateAPI(String url) {
        try {
            return getPrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getPrivateAPI(URL url) {
        clearParameters();
        HttpURLConnection connection = null;
        String result = null;
        String nonce = createNonce();
        try {
            checkAPIkeys();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);
            connection.setRequestProperty("ACCESS-KEY", apiKey);
            connection.setRequestProperty("ACCESS-NONCE", nonce);
            connection.setRequestProperty("ACCESS-SIGNATURE", createSignature(apiSecret, url.toString(), nonce));
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

    protected String postPrivateAPI(String url) {
        try {
            return postPrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String postPrivateAPI(URL url) {
        HttpURLConnection connection = null;
        String result = null;
        String nonce = createNonce();
        try {
            checkAPIkeys();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(POST);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("ACCESS-KEY", apiKey);
            connection.setRequestProperty("ACCESS-NONCE", nonce);
            connection.setRequestProperty("ACCESS-SIGNATURE", createSignature(apiSecret, url.toString(), nonce));
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            osw.write(parameters);
            osw.flush();
            osw.close();
            connection.connect();
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
            clearParameters();
        }
        return result;
    }

    protected String deletePrivateAPI(String url) {
        try {
            return deletePrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String deletePrivateAPI(URL url) {
        clearParameters();
        HttpURLConnection connection = null;
        String result = null;
        String nonce = createNonce();
        try {
            checkAPIkeys();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(DELETE);
            connection.setRequestProperty("ACCESS-KEY", apiKey);
            connection.setRequestProperty("ACCESS-NONCE", nonce);
            connection.setRequestProperty("ACCESS-SIGNATURE", createSignature(apiSecret, url.toString(), nonce));
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

    private String createSignature(String apiSecret, String url, String nonce) {
        String message = nonce + url + parameters;
        return HMAC_SHA256Encode(apiSecret, message);
    }

    private static String HMAC_SHA256Encode(String secretKey, String message) {

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

    protected void setParameters(String parameters) {
        this.parameters = parameters;
    }

    protected String getParametrs() {
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
