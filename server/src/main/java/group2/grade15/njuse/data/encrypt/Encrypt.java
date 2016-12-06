package group2.grade15.njuse.data.encrypt;

/**
 * Created by dell on 2016/12/6.
 */

//运用凯撒加密法
public class Encrypt {
    private final int key=-8;

    public String encryt(String content){
        char[] split=content.toCharArray();
        for(int i=0;i<content.length();i++){
            split[i]=(char)(split[i]+key);
        }
        String code=new String(split);
        return code;
    }

    public String decrypt(String code){
        char[] split=code.toCharArray();
        for(int i=0;i<code.length();i++){
            split[i]=(char)(split[i]-key);
        }
        String content=new String(split);
        return content;
    }
}
