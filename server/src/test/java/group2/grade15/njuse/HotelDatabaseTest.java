package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hoteldata.HotelDatabaseImpl;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/12/1.
 */
public class HotelDatabaseTest {
    HotelDatabaseImpl hotelDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        hotelDatabase=new HotelDatabaseImpl(info);
    }

    @Test
    public void getHotelTest() throws RemoteException{
        assertEquals(90,hotelDatabase.getHotel(1).getScore(),0.001);
    }

    @Test
    public void modifyTest() throws RemoteException{
        HotelPO hotelPO=new HotelPO(1,null,"00001000010000100001","江苏省南京市玄武区",
                "6123456",null,null,null,5,0.00,null);
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.modify(hotelPO));
    }

    @Test
    public void modifyRoomTest() throws RemoteException{
        RoomPO roomPO=new RoomPO(RoomType.bigSingleBed,700.00,100,20);
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.modifyRoom(1,roomPO));
    }

    @Test
    public void addRoomTest() throws RemoteException{
        RoomPO roomPO=new RoomPO(RoomType.stadardDoubleBed,700.00,200,200);
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.modifyRoom(1,roomPO));
    }

    @Test
    public void deleteRoomTest() throws RemoteException{
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.deleteRoomType(1,RoomType.stadardDoubleBed));
    }

    @Test
    public void addPicTest() throws RemoteException{
        byte[] pic=null;
        try {
            File file = new File("C:/Users/dell/Pictures/scenery.jpg");
            BufferedImage temp = ImageIO.read(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(temp, "jpg", bos);
            pic = bos.toByteArray();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        byte[][] picList=new byte[1][];
        picList[0]=pic;
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.uploadPic(picList,1));
    }

    @Test
    public void deletePicTest() throws RemoteException{
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.deletePic(1,1));
    }
}