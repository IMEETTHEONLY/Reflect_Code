import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;

public class RSA {

    private String MODULUS_BASE64;
    private String EXPONENT_BASE64;

    public RSA(String M,String E){
        this.EXPONENT_BASE64 = E;
        this.MODULUS_BASE64 = M;
    }

    public String encrypt(String PWD) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        byte[] modulusBytes = Base64.getDecoder().decode(MODULUS_BASE64);
        byte[] exponentBytes = Base64.getDecoder().decode(EXPONENT_BASE64);
        // 将字节数组转换为BigInteger
        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger exponent = new BigInteger(1, exponentBytes);

        // 创建RSAPublicKeySpec对象
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);

        // 使用KeyFactory生成PublicKey对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // 使用公钥加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(PWD.getBytes("UTF-8"));

        // 将加密后的字节转换为Base64字符串
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }
}