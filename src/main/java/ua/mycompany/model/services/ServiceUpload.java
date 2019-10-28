package ua.mycompany.model.services;

import ua.mycompany.model.dao.UploadDao;
import ua.mycompany.model.entity.UserEntity;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ServiceUpload {

    @Autowired
    private UploadDao uploadDao;

    public void addEntries(List<UserEntity> entries) {
        Executor executor = Executors.newSingleThreadExecutor();
        Scheduler scheduler = Schedulers.from(executor);
        Observable.range(0, entries.size())
                .flatMap(i -> Observable.just(entries.get(i))
                        .map(entryModel -> {
                            uploadDao.addEntry(entryModel);
                            return entryModel;
                        })
                        .subscribeOn(scheduler));
    }
}
