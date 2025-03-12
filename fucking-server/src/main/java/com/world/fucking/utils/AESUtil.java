package com.world.fucking.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * AES加密工具类
 *
 * @author ACGkaka
 */
@SuppressWarnings("all")
public class AESUtil {
    /**
     * 日志相关
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    /**
     * 算法定义
     */
    private static final String AES_ALGORITHM = "AES";
    /**
     * 指定填充方式
     */
    private static final String CIPHER_PADDING = "AES/ECB/PKCS5Padding";
    private static final String CIPHER_CBC_PADDING = "AES/CBC/PKCS5Padding";
    /**
     * 偏移量(CBC中使用，增强加密算法强度)
     */
    private static final String IV_SEED = "1234567812345678";

    /**
     * AES加密
     * @param content 待加密内容
     * @param aesKey  密码
     * @return {@link String}
     */
    public static String encrypt(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            LOGGER.info("AES encrypt: the content is null!");
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey)){
            try {
                //对密码进行编码
//                byte[] bytes = aesKey.getBytes(ENCODING);
                byte[] bytes = Base64.getDecoder().decode(aesKey);
                //设置加密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
                //选择加密
                IvParameterSpec ivParameterSpec = null;
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
                //根据待加密内容生成字节数组
                byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
                //返回base64字符串
                return Base64Utils.encodeToString(encrypted);
            } catch (Exception e) {
                LOGGER.error("AES encrypt exception:", e);
            }

        }else {
            LOGGER.info("AES encrypt: the aesKey is null or error!");
            return null;
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param aesKey  密码
     * @return {@link String}
     */
    public static String decrypt(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            LOGGER.info("AES decrypt: the content is null!");
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) ){
            try {
                //对密码进行编码
//                byte[] bytes = aesKey.getBytes("ISO-8859-1");

                byte[] bytes = Base64.getDecoder().decode(aesKey);
                //设置解密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);

                IvParameterSpec ivParameterSpec = null;
                //选择解密
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);

                //先进行Base64解码
                byte[] decodeBase64 = Base64.getDecoder().decode(content);

                //根据待解密内容进行解密
                byte[] decrypted = cipher.doFinal(decodeBase64);
                //将字节数组转成字符串
                return new String(decrypted, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

        }else {
            LOGGER.info("AES decrypt: the aesKey is null or error!");
            return null;
        }
    }

    /**
     * AES_CBC加密
     *
     * @param content 待加密内容
     * @param aesKey  密码
     * @return {@link String}
     */
    public static String encryptCBC(String content, String aesKey) {
        if(StringUtils.isBlank(content)){
            LOGGER.info("AES_CBC encrypt: the content is null!");
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(StandardCharsets.UTF_8);
                //设置加密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
                //偏移
                IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(StandardCharsets.UTF_8));
                //选择加密
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
                //根据待加密内容生成字节数组
                byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
                //返回base64字符串
                return Base64Utils.encodeToString(encrypted);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }else {
            LOGGER.info("AES_CBC encrypt: the aesKey is null or error!");
            return null;
        }
    }

    /**
     * AES_CBC解密
     *
     * @param content 待解密内容
     * @param aesKey  密码
     * @return {@link String}
     */
    public static String decryptCBC(String content, String aesKey) {
        if(StringUtils.isBlank(content)){
            LOGGER.info("AES_CBC decrypt: the content is null!");
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(StandardCharsets.UTF_8);
                //设置解密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                //偏移
                IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(StandardCharsets.UTF_8));
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
                //选择解密
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

                //先进行Base64解码
                byte[] decodeBase64 = Base64Utils.decodeFromString(content);

                //根据待解密内容进行解密
                byte[] decrypted = cipher.doFinal(decodeBase64);
                //将字节数组转成字符串
                return new String(decrypted, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }else {
            LOGGER.info("AES_CBC decrypt: the aesKey is null or error!");
            return null;
        }
    }

    public static void main(String[] args) {
        // AES支持三种长度的密钥：128位、192位、256位。
        // 代码中这种就是128位的加密密钥，16字节 * 8位/字节 = 128位。


        String random = RandomStringUtils.random(16, "abcdefghijklmnopqrstuvwxyz1234567890");
        LOGGER.info("random: {}", random);
        random  = "sQPoC/1do9BZMkg8I5c09A==";
        String aesResult = "iTgNXRqU80YZ52+iV7Ew2w==";
        LOGGER.info("aesResult: {}", aesResult);

        LOGGER.info("随机key:{}", random);
        LOGGER.info("length:{}",Base64.getDecoder().decode(random).length);
        byte[] a1 = "abc".getBytes(StandardCharsets.UTF_8);
        LOGGER.info("abc byte: {}", a1);

        LOGGER.info("---------加密---------");
        aesResult = encrypt("Hello", random);
        LOGGER.info("aes加密结果:{}", aesResult);


        LOGGER.info("---------解密---------");
        String decrypt = decrypt(aesResult, random);
        LOGGER.info("aes解密结果:{}", decrypt);

        LOGGER.info("--------AES_CBC加密解密---------");
        aesResult = encryptCBC("Hello", random);
        LOGGER.info("aes_cbc加密结果:{}", aesResult);

        LOGGER.info("---------解密CBC---------");
        String cbcDecrypt = decryptCBC(aesResult, random);
        LOGGER.info("aes解密结果:{}", cbcDecrypt);
    }
}
