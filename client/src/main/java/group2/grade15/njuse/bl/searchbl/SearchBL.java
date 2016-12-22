package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.vo.*;

public interface SearchBL {
    public ProvinceListVO getProvince();

    public CityListVO getCity(String provinceNum);

    public DistrictListVO getDistrict(String cityNum);

    public CbdListVO getCbd(String districtNum);
}
