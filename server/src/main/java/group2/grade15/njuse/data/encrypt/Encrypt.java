package group2.grade15.njuse.data.encrypt;

/**
 * Created by dell on 2016/12/6.
 */

public class Encrypt {
    private final int keyS=-8;
    private final int keyI=1313113;

    //运用凯撒加密法
    public String encrypt(String content){
        if(content==null){
            return null;
        }
        char[] split=content.toCharArray();
        for(int i=0;i<content.length();i++){
            split[i]=(char)(split[i]+keyS);
        }
        String code=new String(split);
        return code;
    }

    public String decrypt(String code){
        if(code==null){
            return null;
        }
        char[] split=code.toCharArray();
        for(int i=0;i<code.length();i++){
            split[i]=(char)(split[i]-keyS);
        }
        String content=new String(split);
        return content;
    }

    //运用异或加密
    public int encrypt(int content){
        return content^keyI;
    }

    public int decrypt(int code){
        return code^keyI;
    }
}
