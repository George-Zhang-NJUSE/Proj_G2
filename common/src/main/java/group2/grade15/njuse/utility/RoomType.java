package group2.grade15.njuse.utility;

public enum RoomType {
    bigSingleBed,
    stadardDoubleBed,
    suiteRoom,
    all;
    public String toString(){
        switch (this){
            case bigSingleBed:
                return "大床房";
            case stadardDoubleBed:
                return "双人间";
            case suiteRoom:
                return "套房";
        }
        return "不限";
    }
}
