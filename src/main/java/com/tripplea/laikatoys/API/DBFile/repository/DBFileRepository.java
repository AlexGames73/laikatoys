package com.tripplea.laikatoys.API.DBFile.repository;

import com.tripplea.laikatoys.API.DBFile.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
