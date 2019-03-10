package com.demo.service;

import com.demo.dao.AnchorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("fanzengbaoAnchorService")
public class AnchorService {
    private AnchorDao anchorDao;

    @Autowired
    @Qualifier("fanzengbaoAnchorDao")
    public void setAnchorDao(AnchorDao anchorDao){
        this.anchorDao=anchorDao;
    }

    @Transactional
    public int getAnchorFollowedNumberByAnchorId(int Id){
        int number;
        number=anchorDao.getFollowedNumber(Id);
        return number;
    }
}
