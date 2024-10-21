package sap.ass01.layered.business;

import sap.ass01.layered.persistence.Repository;

public interface Business<T, K> {
    void injectRepository(Repository<T, K> repository);
    void save();
    void update();
    String getId();
}
