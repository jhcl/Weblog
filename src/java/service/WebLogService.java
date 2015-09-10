package service;

import dao.PostingDao;
import dao.PostingDaoImp;
import java.util.List;
import model.Posting;

public class WebLogService {

    private PostingDao postingDao;

    public WebLogService() {
        postingDao = new PostingDaoImp();
    }

    public void addPosting(Posting p) {
        postingDao.create(p);
    }

    public List<Posting> getPostings() {
        return postingDao.findAll();
    }
;
}
