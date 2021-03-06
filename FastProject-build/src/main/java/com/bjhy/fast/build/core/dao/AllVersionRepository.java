package com.bjhy.fast.build.core.dao;


import com.bjhy.fast.security.domain.AllVersion;
import org.apel.gaia.persist.dao.CommonRepository;

public interface AllVersionRepository extends CommonRepository<AllVersion, String> {


    AllVersion findByName(String name);

}
