package ua.mycompany.model.services;

import ua.mycompany.model.dao.DownloadDao;
import ua.mycompany.model.entity.UserEntity;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Service
public class ServiceDownload {

    @Autowired
    private DownloadDao downloadDao;
    public List<UserEntity> getAllEntriesWithRXJava(){
        List<UserEntity> result = new ArrayList<>();
        int count = downloadDao.getCountEntries();
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(count));
        Observable.range(0, count)
                .flatMap(integer -> Observable.just(integer)
                        .map(i -> {
                            return downloadDao.getEntryForRXJava(i);})
                        .subscribeOn(scheduler))
                .blockingForEach(result::add);
        return result;
    }
}
