package group2.grade15.njuse;

import group2.grade15.njuse.data.encrypt.Encrypt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/12/6.
 */
public class EncryptTest {
    Encrypt encrypt;

    @Before
    public void setUp() throws Exception {
        encrypt=new Encrypt();
    }

    @Test
    public void encryptTest(){
        assertEquals(")*+,",encrypt.encrypt("1234"));
    }

    @Test
    public void decryptTest(){
        assertEquals("1234",encrypt.decrypt(")*+,"));
    }

}