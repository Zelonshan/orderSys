package com.ntlg.ordersys.service.impl;

import com.ntlg.ordersys.dao.WxAdsImgsDao;
import com.ntlg.ordersys.pojo.Foods;
import com.ntlg.ordersys.pojo.WxAdsImgs;
import com.ntlg.ordersys.service.WxAdsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WxAdsImgsServiceImpl implements WxAdsImgsService {
    @Autowired
    private WxAdsImgsDao wxAdsImgsDao;
    @Override
    public void insertImgs(WxAdsImgs imgs) {
        wxAdsImgsDao.save(imgs);
    }

    @Override
    public void deleteImgs(Integer id) {
        wxAdsImgsDao.deleteById(id);
    }

    @Override
    public void updateImgs(WxAdsImgs imgs) {
        wxAdsImgsDao.saveAndFlush(imgs);
    }

    @Override
    public List<WxAdsImgs> selectAllImgs() {
        return wxAdsImgsDao.findAll();
    }

    @Override
    public List<WxAdsImgs> findByTypes(Integer types) {
        return wxAdsImgsDao.findAllByTypes(types);
    }

    @Override
    public String findImgPath(Integer id) {
        WxAdsImgs wxAdsImgs= wxAdsImgsDao.findById(id).get();
        return wxAdsImgs.getSrc();
    }


}
