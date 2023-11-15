package com.ntlg.ordersys.service;



import com.ntlg.ordersys.pojo.WxAdsImgs;

import java.util.List;

public interface WxAdsImgsService {
    void insertImgs(WxAdsImgs imgs);
    void deleteImgs(Integer id);
    void updateImgs(WxAdsImgs imgs);

    List<WxAdsImgs> selectAllImgs();

    List<WxAdsImgs> findByTypes(Integer types);

    String findImgPath(Integer id);
}
